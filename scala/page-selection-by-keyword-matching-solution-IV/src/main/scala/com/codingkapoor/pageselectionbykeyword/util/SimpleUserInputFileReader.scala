package com.codingkapoor.pageselectionbykeyword.util

import java.io.FileInputStream
import java.io.InputStream

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

trait SimpleUserInputFileReader extends UserInputFileReader {

  // Validates:
  // 1. If a line starts with "P" or "Q"  
  // 2. If a line representing page or query has at least one keyword
  override def validateInput(lines: List[String]): List[String] = {
    lines.map { _.trim().replaceAll("\\s+", " ") }.
      filter { i => i.startsWith(Page.identifier) || i.startsWith(Query.identifier) }.
      filter { _.length() != 1 }
  }

  // Takes a list of lines as parameter List("P Ford Car", "PP Review Car", "PP Review Ford", "P Toyota Car", "PP Car") and
  // builds a tree of form List((P Ford Car,List((PP Review Car,List()), (PP Review Ford,List()))), (P Toyota Car,List((PP Car,List()))))
  def buildPageTree(ls: List[String]): List[(String, List[_])] = {
    def splitBy(ls: List[String], s: String): List[(String, List[_])] = {

      ls.splitAt(ls.lastIndexWhere(_.startsWith(s + " "))) match {
        case (_, Nil)       => Nil
        case (Nil, y :: ys) => List((y, splitBy(ys, Page.identifier * (y.split(" ").head.length() + 1))))
        case (x, y :: ys)   => splitBy(x, s) ::: List((y, splitBy(ys, Page.identifier * (y.split(" ").head.length() + 1))))
      }
    }
    splitBy(ls, Page.identifier)
  }

  // Takes a line, "PP Review Ford", as a string and builds a list of keywords with their weight List((review,8), (ford,7))
  def keywords(v: String) = v.split(" ").toList.tail map (_.toLowerCase()) zip (totalKeywordsAllowed to 1 by -1)

  // 1. Ignores keywords that are beyond 8 in number
  // 2. Makes all the keywords as lower case 
  override def buildPageList(ls: List[String]): List[Page] = {
    
    val tree = buildPageTree(ls)
    convertPageTreeToPageList(tree)
  }

  // 1. Ignores keywords that are beyond 8 in number
  // 2. Makes all the keywords as lower case 
  // 3. Prepares a list of queries: (1, [(Ford,8),(Car,7),(Review,6)])  
  override def prepareQueryList(ls: List[String]): List[Query] = {

    val u = (ls map { v => v.split(" ").toList.tail map (_.toLowerCase()) zip (totalKeywordsAllowed to 1 by -1) })

    (u zip { (1 to u.length).toList map { _.toString() } } foldLeft (Nil: List[Query])) {
      case (acc, (i, j)) => acc ::: List(Query(j, i))
    }
  }

  override def readUserInputFile(fileName: Option[String]): (List[Query], List[Page]) = {

    val stream: InputStream = fileName match {
      case None    => getClass().getResourceAsStream(defaultUserInputFile)
      case Some(f) => new FileInputStream(f)
    }

    val lines = scala.io.Source.fromInputStream(stream).getLines

    // Partitions list of lines into a tuple of list of lines representing pages and queries, respectively
    val (pages, queries) = validateInput(lines.toList).partition { _.startsWith(Page.identifier) }

    (prepareQueryList(queries), buildPageList(pages))
  }

}
