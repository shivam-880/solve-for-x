package com.codingkapoor.pageselectionbykeyword.selector

trait PageSelector {
  
  final val maxPageSelections = 5

  def pageSelectionByKeyword(fileName: Option[String]): List[String]
  def display(result: List[String]) = result foreach { println(_) }
}
