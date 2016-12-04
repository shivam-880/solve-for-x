package com.codingkapoor.pageselectionbykeyword.model

import com.codingkapoor.pageselectionbykeyword.util.KeywordsBuilder

// Keywords are accepted as arguments as List(Review, Car) and returned as List((review,8), (car,7)).
// The idea here is to take constructor arguments in different format and return the Page object with fields in different format.
// For this reason Page object can't be used for pattern matching. Also this would require overriding of toString() method.
case class Query(private val _id: String, private val _keywords: List[String]) extends KeywordsBuilder {

  val id: String = _id
  val keywords: List[(String, Int)] = keywordsWithWeightage(_keywords)

  override def toString = "Query(" + id + ", " + keywords + ")"
}

object Query extends KeywordsBuilder {
  final val identifier = "Q"
}
