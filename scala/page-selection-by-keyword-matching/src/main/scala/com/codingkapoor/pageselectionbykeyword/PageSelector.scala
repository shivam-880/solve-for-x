package com.codingkapoor.pageselectionbykeyword

import com.codingkapoor.pageselectionbykeyword.model.{ Page, Query }
import com.codingkapoor.pageselectionbykeyword.util.UserInputFileReader

class PageSelector(fileName: Option[String]) {

  private val (queries, pages) = UserInputFileReader(fileName).readUserInputFile

  def pageSelectionByKeyword() = {

    def strength(y: Query, x: Page) = {
      val r = for { (i, j) <- x.keywords; (p, q) <- y.keywords } yield {
        if (i == p) j * q else 0
      }

      r.sum
    }

    val i: List[(Int, List[Int])] = for { query <- queries } yield {
      val ls = for { page <- pages } yield {
        (page.id, strength(query, page))
      }

      (query.id, ls sortWith { _._2 > _._2 } take (PageSelector.maxPageSelections) filter { _._2 != 0 } map { _._1 })
    }

    for { r <- i } yield {
      "Q" + r._1 + ":" + (r._2 foldLeft ("")) { (acc, x) => acc + " " + "P" + x }
    }
  }

  def display(output: List[String]) = output foreach { println(_) }

}

object PageSelector {

  private final val maxPageSelections = 5

  def apply(fileName: Option[String]): PageSelector = new PageSelector(fileName)

  def main(args: Array[String]): Unit = {
    val pageSelector = if (args.length > 0) PageSelector(Some(args(0))) else PageSelector(None)

    val result = pageSelector.pageSelectionByKeyword()
    pageSelector.display(result)
  }
}
