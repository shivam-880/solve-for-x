package com.codingkapoor.pageselectionbykeyword.util

trait KeywordsBuilder {

  val totalKeywordsAllowed = 8

  // 1. Ignores keywords that are beyond 8 in number
  // 2. Makes all the keywords as lower case 
  def keywordsWithWeightage(keywords: List[String]): List[(String, Int)] =
    keywords map (_.toLowerCase()) zip (totalKeywordsAllowed to 1 by -1)

}
