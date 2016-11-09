package com.codingkapoor

import org.scalatest.{ FlatSpec, Matchers }

class P01Spec extends FlatSpec with Matchers {

  "bomber" should "return string dropped of all consecutive similar letters iteratively in the order" in {
    P01.bomber("") should equal("")
    P01.bomber("abbabba") should equal("a")
    P01.bomber("aabcccdee") should equal("bd")
    P01.bomber("abcdeedcbfgf") should equal("afgf")
  }
}