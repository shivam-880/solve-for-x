package com.codingkapoor

import org.scalatest.{ FlatSpec, Matchers }

class BigBangBomberSpec extends FlatSpec with Matchers {
  "bomber" should "return string dropped of all consecutive similar letters" in {
    BigBangBomber.bomber("") should equal("")
    BigBangBomber.bomber("abbabba") should equal("")
    BigBangBomber.bomber("aabcccdee") should equal("bd")
    BigBangBomber.bomber("abcdeedcbfgf") should equal("afgf")
  }
}