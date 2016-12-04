package com.codingkapoor.pageselectionbykeyword.model

case class Page(id: String, keywords: List[(String, Int)], subPages: List[Page])

object Page {
  val identifier = "P"
}
