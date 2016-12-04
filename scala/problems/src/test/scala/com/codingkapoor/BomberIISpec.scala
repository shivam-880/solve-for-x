package com.codingkapoor

import org.scalatest.{ FlatSpec, Matchers }

class BomberIISpec extends FlatSpec with Matchers {

  "bomber" should "return string dropped of all more than twice occuring consecutive similar letters iteratively in the order" in {    
    BomberII.bomber("adbcccbbd") should equal("add")
  }
}
