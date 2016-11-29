package com.codingkapoor.pageselectionbykeyword

import com.codingkapoor.pageselectionbykeyword.selector._
import com.codingkapoor.pageselectionbykeyword.util.SimpleUserInputFileReader
import com.codingkapoor.pageselectionbykeyword.util.SimpleResultBuilder

class PageSelectorApplication(fileName: Option[String]) extends SimplePageSelector with SimpleUserInputFileReader
    with SimpleResultBuilder {
  buildResult(pageSelectionByKeyword(fileName))
}

object PageSelectorApplication {
  def apply(fileName: Option[String]): PageSelectorApplication = new PageSelectorApplication(fileName)

  def main(args: Array[String]): Unit = {
    val result =
      if (args.length > 0) PageSelectorApplication(Some(args(0)))
      else PageSelectorApplication(None)
  }
}
