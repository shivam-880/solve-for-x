package com.codingkapoor

import org.scalatest.{ FlatSpec, Matchers }

class P02Spec extends FlatSpec with Matchers {
  "bomber" should "return string dropped of all consecutive similar letters" in {
    P02.bomber("") should equal("")
    P02.bomber("abbabba") should equal("")
    P02.bomber("aabcccdee") should equal("bd")
    P02.bomber("abcdeedcbfgf") should equal("afgf")
  }
}