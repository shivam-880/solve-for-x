package com.codingkapoor.problemsolving

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BomberIISpec extends FlatSpec with Matchers {

  "bomber" should "return string dropped of all more than twice occuring consecutive similar letters iteratively in the order" in {    
    BomberII.bomber("adbcccbbd") should equal("add")
  }
}
