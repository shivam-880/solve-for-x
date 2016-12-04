package com.codingkapoor.pageselectionbykeyword.util

import java.io.FileInputStream
import java.io.InputStream

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

trait SimpleUserInputFileReader extends UserInputFileReader {

  def prepareInput(lines: List[String]): List[String] = {
    lines.map { _.trim().replaceAll("\\s+", " ") }
  }

  // Gets rid of all the dangling references
  // Input: List("P Ford Car", "PP Review Car", "PPPPP Review Ford", "PPPPPP Review Ford", "PPPPP Review Ford", "PPPP Review Ford", "PPPPP Review Ford", "PP Review Ford")
  // Output: List("P Ford Car", "PP Review Car", "PP Review Ford")
  def ridDanglingPages(lines: List[String]): List[String] = {
    (lines.tail foldLeft List(lines.head)) {
      case (acc, x) if (x.split(" ").head.size > acc.head.split(" ").head.size + 1) => acc
      case (acc, x) => acc :+ x
    }
  }

  // Validates:
  // 1. If a line starts with "P" or "Q"  
  // 2. If a line representing page or query has at least one keyword
  // 3. If there are no dangling references
  def validateInput(lines: List[String]): List[String] = {
    val res = lines.
      filter { i => i.startsWith(Page.identifier) || i.startsWith(Query.identifier) }.
      filter { _.length() != 1 }

    ridDanglingPages(res)
  }

  // Takes a list of lines as parameter List("P Ford Car", "PP Review Car", "PP Review Ford", "P Toyota Car", "PP Car") and
  // builds a tree, List((List(Ford, Car),List((List(Review, Car),List()), (List(Review, Ford),List()))), (List(Toyota, Car),List((List(Car),List()))))
  // which is List((List of keywords of a page, List of subpages)).
  def buildPageTree(ls: List[String]): List[(List[String], List[_])] = {

    def keywords(y: String) = y.split(" ").tail.toList

    def splitBy(ls: List[String], s: String): List[(List[String], List[_])] = {

      ls.splitAt(ls.lastIndexWhere(_.startsWith(s + " "))) match {
        case (_, Nil)       => Nil
        case (Nil, y :: ys) => List((keywords(y), splitBy(ys, Page.identifier * (y.split(" ").head.length() + 1))))
        case (x, y :: ys)   => splitBy(x, s) ::: List((keywords(y), splitBy(ys, Page.identifier * (y.split(" ").head.length() + 1))))
      }
    }

    splitBy(ls, Page.identifier)
  }

  def preparePageList(ls: List[String]): List[Page] = {
    val tree = buildPageTree(ls)

    (tree foldLeft (0, Nil: List[Page])) {
      case ((count, acc), (page, subpages)) =>
        (count + 1, acc ::: List(Page(Page.indexOfRootOfAllPages + "." + (count + 1), page, subpages)))
    }._2
  }

  // Takes: List("Q Ford Car", "Q Review Car", "Q Review") and prepares 
  // List(Query(1,List((ford,8), (car,7))), Query(2,List((review,8), (car,7))), Query(3,List((review,8))))  
  def prepareQueryList(ls: List[String]): List[Query] = {

    val u = ls map { _.split(" ").tail.toList }

    (u foldLeft ((0, Nil: List[Query]))) {
      case ((count, acc), x) => (count + 1, acc ::: List(Query((count + 1).toString(), x)))
    }._2

  }

  override def readUserInputFile(fileName: Option[String]): (List[Query], List[Page]) = {

    val stream: InputStream = fileName match {
      case None    => getClass().getResourceAsStream(defaultUserInputFile)
      case Some(f) => new FileInputStream(f)
    }

    val lines = scala.io.Source.fromInputStream(stream).getLines

    // Partitions list of lines into a tuple of list of lines representing pages and queries, respectively
    val (pages, queries) = validateInput(prepareInput(lines.toList)).partition { _.startsWith(Page.identifier) }

    (prepareQueryList(queries), preparePageList(pages))
  }

}
