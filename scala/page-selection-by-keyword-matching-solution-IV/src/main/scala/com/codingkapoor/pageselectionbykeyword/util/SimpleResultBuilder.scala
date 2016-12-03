package com.codingkapoor.pageselectionbykeyword.util

import com.codingkapoor.pageselectionbykeyword.model._

trait SimpleResultBuilder extends ResultBuilder {

  override def buildResult(i: List[(String, List[String])]) = {
    val result = for { r <- i } yield {
      Query.identifier + r._1 + ":" + (r._2 foldLeft ("")) { (acc, x) => acc + " " + Page.identifier + x.split("\\.").last }
    }
    
    result foreach { println(_) }
  }
}
