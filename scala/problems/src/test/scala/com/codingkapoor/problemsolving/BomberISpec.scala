package com.codingkapoor.problemsolving

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BomberISpec extends FlatSpec with Matchers {

  "bomber" should "return string dropped of all consecutive similar letters iteratively in the order" in {
    Bomber.bomber("") should equal("")
    Bomber.bomber("abbabba") should equal("a")
    Bomber.bomber("aabcccdee") should equal("bd")
    Bomber.bomber("abcdeedcbfgf") should equal("afgf")
  }
}