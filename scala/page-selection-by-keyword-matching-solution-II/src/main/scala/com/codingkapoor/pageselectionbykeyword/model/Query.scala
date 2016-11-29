package com.codingkapoor.pageselectionbykeyword.model

case class Query(id: Int, keywords: List[(String, Int)])

object Query {
  val identifier = "Q"
}
