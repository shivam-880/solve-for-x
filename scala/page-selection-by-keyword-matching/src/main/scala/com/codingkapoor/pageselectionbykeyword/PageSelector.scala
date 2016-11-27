package com.codingkapoor.pageselectionbykeyword

import com.codingkapoor.pageselectionbykeyword.model.{ Page, Query }
import com.codingkapoor.pageselectionbykeyword.util.UserInputFileReader

object PageSelector {
  
  private val maxPageSelections = 5

  def pageSelectionByKeyword(queries: List[Query], pages: List[Page]) = {

    def strength(y: Query, x: Page) = {
      val r = for { (i, j) <- x.keywords; (p, q) <- y.keywords } yield {
        if (i == p) j * q else 0
      }

      r.sum
    }

    for { query <- queries } yield {
      val ls = for { page <- pages } yield {
        (page.id, strength(query, page))
      }

      (query.id, ls sortWith { _._2 > _._2 } take (maxPageSelections) filter { _._2 != 0 } map { _._1 })
    }
  }

  def display(result: List[(Int, List[Int])]) = result foreach { r =>
    println("Q" + r._1 + ":" + (r._2 foldLeft ("")) { (acc, x) => acc + " " + "P" + x })
  }

  def main(args: Array[String]): Unit = {
    val (queries, pages) =
      if ((args.length > 0))
        UserInputFileReader(Some(args(0))).readUserInputFile
      else
        UserInputFileReader(None).readUserInputFile

    val result = pageSelectionByKeyword(queries, pages)
    display(result)
  }

}
