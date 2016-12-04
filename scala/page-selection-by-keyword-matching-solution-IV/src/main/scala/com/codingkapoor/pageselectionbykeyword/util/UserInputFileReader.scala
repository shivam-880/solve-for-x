package com.codingkapoor.pageselectionbykeyword.util

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

trait UserInputFileReader {
  
  final val totalKeywordsAllowed = 8
  final val defaultUserInputFile = "/input.txt"

  final val indexOfRootOfAllPages = "0"
  final val indexOfNewSubPage = "1"

  def prepareInput(lines: List[String]): List[String]

  def ridDanglingPages(lines: List[String]): List[String]

  def validateInput(lines: List[String]): List[String]

  def buildPageList(ls: List[String]): List[Page]

  def prepareQueryList(ls: List[String]): List[Query]

  def buildPageTree(ls: List[String]): List[(String, List[_])]

  def keywords(v: String): List[(String, Int)]

  // Takes a tree of form: List((P Ford Car,List((PP Review Car,List()), (PP Review Ford,List()))), (P Toyota Car,List((PP Car,List())))) and
  // prepares a list of pages: List(Page(0.1,List((ford,8), (car,7)),List(Page(0.1.1,List((review,8), (car,7)),List()), Page(0.1.2,List((review,8), (ford,7)),List()))), Page(0.2,List((toyota,8), (car,7)),List(Page(0.2.1,List((car,8)),List())))) where "0" is the index of root of all pages
  def convertPageTreeToPageList(tree: Any): List[Page] = {

    def convert(index: String, tree: Any): List[Page] = tree match {
      case Nil => Nil

      case (x, Nil) :: Nil =>
        Page(index, keywords(x.toString()), Nil) :: convert(index, Nil)

      case (x, y) :: Nil =>
        Page(index, keywords(x.toString()), convert(index + "." + indexOfNewSubPage, y)) :: convert(index, Nil)

      case (x, y) :: xs =>
        Page(index, keywords(x.toString()), convert(index + "." + indexOfNewSubPage, y)) :: convert(index.splitAt(index.lastIndexOf("."))._1 + "." + (index.split("\\.").last.toInt + 1).toString(), xs)
    }

    val indexOfFirstPage = indexOfRootOfAllPages + "." + indexOfNewSubPage
    convert(indexOfFirstPage, tree)
  }

  def readUserInputFile(fileName: Option[String]): (List[Query], List[Page])
}
