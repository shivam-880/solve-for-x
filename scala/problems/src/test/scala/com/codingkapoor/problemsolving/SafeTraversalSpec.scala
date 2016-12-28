package com.codingkapoor.problemsolving

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class SafeTraversalSpec extends FlatSpec with Matchers {

  val safeTraversal = new SafeTraversal()

  "isTraversalSafe" should "find no safe traversals for input string 'SUUUUUSUUS'" in {
    safeTraversal.isTraversalSafe("SUUUUUSUUS") should equal((false, 0))
  }

  "isTraversalSafe" should "find 2 safe traversals for input string 'SSUSSUSUUS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSUUS") should equal((true, 2))
  }

  "isTraversalSafe" should "find 1 safe traversal for input string 'SSUSUUSUUS'" in {
    safeTraversal.isTraversalSafe("SSUSUUSUUS") should equal((true, 1))
  }

  "isTraversalSafe" should "find no safe traversals for input string 'SSUSSUSUUSUUUUSUUUS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSUUSUUUUSUUUS") should equal((false, 0))
  }

  "isTraversalSafe" should "find 2 safe traversals for input string 'SSUSSUSUUSUUUSUUUUS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSUUSUUUSUUUUS") should equal((true, 2))
  }

  "isTraversalSafe" should "find no safe traversals for input string 'QWERTY'" in {
    safeTraversal.isTraversalSafe("QWERTY") should equal((false, 0))
  }

  "isTraversalSafe" should "find no safe traversals for input string 'SSUSSUSUUUSS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSUUUSS") should equal((false, 0))
  }

  "isTraversalSafe" should "find 1 safe traversal for input string 'SSUSSUSUUUS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSUUUS") should equal((true, 1))
  }

  "isTraversalSafe" should "find 1 safe traversal for input string 'SSUSSUSS'" in {
    safeTraversal.isTraversalSafe("SSUSSUSS") should equal((true, 1))
  }

  "isTraversalSafe" should "find 1 safe traversal for input string 'SSUSS'" in {
    safeTraversal.isTraversalSafe("SSUSS") should equal((true, 1))
  }

  "isTraversalSafe" should "find 5 safe traversals for input string 'SSSSS'" in {
    safeTraversal.isTraversalSafe("SSSSS") should equal((true, 5))
  }

  "isTraversalSafe" should "find 1 safe traversal for input string 'S'" in {
    safeTraversal.isTraversalSafe("S") should equal((true, 1))
  }

  "isTraversalSafe" should "find no safe traversals for input string 'U'" in {
    safeTraversal.isTraversalSafe("U") should equal((false, 0))
  }
}
