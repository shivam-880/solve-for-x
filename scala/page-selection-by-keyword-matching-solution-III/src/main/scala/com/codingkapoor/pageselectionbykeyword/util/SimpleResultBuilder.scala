package com.codingkapoor.pageselectionbykeyword.util

import com.codingkapoor.pageselectionbykeyword.model._

trait SimpleResultBuilder extends ResultBuilder {

  def buildResult(i: List[(Int, List[Int])]) = {
    val result = for { r <- i } yield {
      Query.identifier + r._1 + ":" + (r._2 foldLeft ("")) { (acc, x) => acc + " " + Page.identifier + x }
    }
    
    result foreach { println(_) }
  }
}
