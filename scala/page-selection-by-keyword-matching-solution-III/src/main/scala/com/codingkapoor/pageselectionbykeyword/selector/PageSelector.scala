package com.codingkapoor.pageselectionbykeyword.selector

trait PageSelector {
  final val maxPageSelections = 5
  
  def pageSelectionByKeyword(fileName: Option[String]):List[(Int, List[Int])]
}
