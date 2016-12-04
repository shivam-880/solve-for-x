package com.codingkapoor.pageselectionbykeyword.model

import com.codingkapoor.pageselectionbykeyword.util.KeywordsBuilder

// The idea here is to take constructor arguments in different format and return the Page object with fields in different format.
// For this reason Page object can't be used for pattern matching. Also this would require overriding of toString() method.
case class Page(private val _id: String, private val _keywords: List[String], private val _subpages: Any)
    extends KeywordsBuilder {

  val id: String = _id
  val keywords: List[(String, Int)] = keywordsWithWeightage(_keywords)
  val subpages: List[Page] = subpagesFromSubpagesTree(_subpages)

  // Takes a tree of form: List((List(Review, Car),List()), (List(Review, Ford),List())) where empty list, List() represents empty nodes and
  // prepares a list of pages: List(Page(0.1.1,List((review,8), (car,7)),List()), Page(0.1.2,List((review,8), (ford,7)),List())) where "0" is the index of root of all pages
  def subpagesFromSubpagesTree(subpagesAsTree: Any): List[Page] = {

    def build(index: String, tree: Any): List[Page] = tree match {
      case Nil => Nil

      case (x, Nil) :: Nil =>
        Page(index, x.asInstanceOf[List[String]], Nil) :: build(index, Nil)

      case (x, y) :: Nil =>
        Page(index, x.asInstanceOf[List[String]],
          build(index + "." + Page.indexOfNewSubPage, y)) :: build(index, Nil)

      case (x, y) :: xs =>
        Page(index, x.asInstanceOf[List[String]],
          build(index + "." + Page.indexOfNewSubPage, y)) :: build(index.splitAt(index.lastIndexOf("."))._1 + "." + (index.split("\\.").last.toInt + 1).toString(), xs)
    }

    build(id + "." + Page.indexOfNewSubPage, subpagesAsTree)
  }

  override def toString() = "Page(" + id + ", " + keywords + ", " + subpages + ")"
}

object Page {
  final val identifier = "P"

  final val indexOfRootOfAllPages = "0"
  final val indexOfNewSubPage = "1"
}
