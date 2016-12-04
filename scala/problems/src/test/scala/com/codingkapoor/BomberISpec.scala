package com.codingkapoor

import org.scalatest.{ FlatSpec, Matchers }

class BomberISpec extends FlatSpec with Matchers {

  "bomber" should "return string dropped of all consecutive similar letters iteratively in the order" in {
    Bomber.bomber("") should equal("")
    Bomber.bomber("abbabba") should equal("a")
    Bomber.bomber("aabcccdee") should equal("bd")
    Bomber.bomber("abcdeedcbfgf") should equal("afgf")
  }
}