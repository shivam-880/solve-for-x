package com.codingkapoor.pageselectionbykeyword.model

case class Page(id: Int, keywords: List[(String, Int)])

object Page {
  val identifier = "P"
}
