package com.codingkapoor.pageselectionbykeyword.selector

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

import com.codingkapoor.pageselectionbykeyword.util.SimpleUserInputFileReader
import com.codingkapoor.pageselectionbykeyword.util.UserInputFileReader
import com.codingkapoor.pageselectionbykeyword.util.ResultBuilder

trait SimplePageSelector extends PageSelector {

  this: UserInputFileReader =>

  def strength(y: Query, x: Page) = {

    def strength(w: Page) = {
      val f = for { (i, j) <- w.keywords; (p, q) <- y.keywords } yield {
        if (i == p) j * q else 0
      }

      f.sum
    }

    def cumulativeStrength(w: Page): Double = w match {
      case page @ Page(_, _, Nil) =>
        strength(page) * subPageContributionPercentage

      case page @ Page(_, _, x :: Nil) =>
        (strength(page) + cumulativeStrength(x))

      case page @ Page(_, _, x :: xs) =>
        strength(page) + cumulativeStrength(x) + cumulativeStrength(xs.head)
    }

    cumulativeStrength(x)

  }

  override def pageSelectionAlgo(fileName: Option[String]) = {

    val (queries, pages) = readUserInputFile(fileName)

    val x: List[(String, List[String])] = for { query <- queries } yield {
      val ls = for { page <- pages } yield {
        (page.id, strength(query, page))
      }

      (query.id, ls sortWith { _._2 > _._2 } take (maxPageSelectionsAllowed) filter { _._2 != 0 } map { _._1 })
    }

    x
  }

}
