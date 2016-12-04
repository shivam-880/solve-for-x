package com.codingkapoor.pageselectionbykeyword

import com.codingkapoor.pageselectionbykeyword.util.KeywordsBuilder

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

object Test extends App with KeywordsBuilder {

  final val indexOfRootOfAllPages = "0"
  final val indexOfNewSubPage = "1"

  // Takes a list of lines as parameter List("P Ford Car", "PP Review Car", "PP Review Ford", "P Toyota Car", "PP Car") and
  // builds a tree of form List((P Ford Car,List((PP Review Car,List()), (PP Review Ford,List()))), (P Toyota Car,List((PP Car,List()))))  
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

  println(buildPageTree(List("P Ford Car", "PP Review Car", "PP Review Ford", "P Toyota Car", "PP Car")) + " :sk")

  def buildPageList(ls: List[String]): List[Page] = {
    val tree = buildPageTree(ls)

    (tree foldLeft (0, Nil: List[Page])) {
      case ((count, acc), (page, subpages)) => (count + 1, acc ::: List(Page(indexOfRootOfAllPages + "." + (count + 1), page, subpages)))
    }._2
  }

  println(buildPageList(List("P Ford Car", "PP Review Car", "PP Review Ford", "P Toyota Car", "PP Car")))

  // Takes a tree of form: (P Ford Car,List((PP Review Car,List()), (PP Review Ford,List()))) and
  // prepares a list of pages: Page(0.1,List((ford,8), (car,7)),List(Page(0.1.1,List((review,8), (car,7)),List()), Page(0.1.2,List((review,8), (ford,7)),List()))) where "0" is the index of root of all pages

  // Takes a tree of form: List((PP Review Car,List()), (PP Review Ford,List())) and
  // prepares a list of pages: List(Page(0.1.1,List((review,8), (car,7)),List()), Page(0.1.2,List((review,8), (ford,7)),List())) where "0" is the index of root of all pages

  // Takes a tree of form: List((List(Review, Car),List()), (List(Review, Ford),List())) where empty list, List() represents empty nodes and
  // prepares a list of pages: List(Page(0.1.1,List((review,8), (car,7)),List()), Page(0.1.2,List((review,8), (ford,7)),List())) where "0" is the index of root of all pages
  def convertPageTreeToPageList(subpagesAsTree: Any): List[Page] = {

    def convert(index: String, tree: Any): List[Page] = tree match {
      case Nil => Nil

      case (x, Nil) :: Nil =>
        Page(index, x.asInstanceOf[List[String]], Nil) :: convert(index, Nil)

      case (x, y) :: Nil =>
        Page(index, x.asInstanceOf[List[String]], convert(index + "." + indexOfNewSubPage, y)) :: convert(index, Nil)

      case (x, y) :: xs =>
        Page(index, x.asInstanceOf[List[String]], convert(index + "." + indexOfNewSubPage, y)) :: convert(index.splitAt(index.lastIndexOf("."))._1 + "." + (index.split("\\.").last.toInt + 1).toString(), xs)
    }

    convert("0.1.1", subpagesAsTree)
  }

  val l = convertPageTreeToPageList(List((List("Review", "Car"), List()), (List("Review", "Ford"), List())))
  println(l)

}
