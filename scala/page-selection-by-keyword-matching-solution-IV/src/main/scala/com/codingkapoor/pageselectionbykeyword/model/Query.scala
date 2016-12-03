package com.codingkapoor.pageselectionbykeyword.model

case class Query(id: String, keywords: List[(String, Int)])

object Query {
  val identifier = "Q"
}
