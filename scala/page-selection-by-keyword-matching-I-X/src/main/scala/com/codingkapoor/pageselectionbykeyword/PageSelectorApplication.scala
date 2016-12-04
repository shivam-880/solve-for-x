package com.codingkapoor.pageselectionbykeyword

import com.codingkapoor.pageselectionbykeyword.selector._

class PageSelectorApplication(fileName: Option[String]) extends SimplePageSelector {
  val result = pageSelectionByKeyword(fileName)
  display(result)
}

object PageSelectorApplication {
  def apply(fileName: Option[String]): PageSelectorApplication = new PageSelectorApplication(fileName)

  def main(args: Array[String]): Unit = {
    val result =
      if (args.length > 0) PageSelectorApplication(Some(args(0)))
      else PageSelectorApplication(None)
  }
}
