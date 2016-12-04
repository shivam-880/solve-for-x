package com.codingkapoor.pageselectionbykeyword.selector

trait PageSelector {
  
  final val maxPageSelectionsAllowed = 5
  final val subPageContributionPercentage = 0.1
  
  def pageSelectionAlgo(fileName: Option[String]): List[(String, List[String])]
}
