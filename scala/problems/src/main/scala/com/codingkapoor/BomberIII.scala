package com.codingkapoor

/**
 * 
 * The Bomber Algorithm
 * The algorithm doesn't like consecutive characters in a given string to be same. 
 * For example, if the given string is "abcccbdd", the bomber algorithm first bombs "ccc" & "dd" and then "bb". 
 * So the final output string will become "ad". It destroys the characters only if there are more than 1 continuous occurrence of same characters in the string.
 *
 * [Don't use RegEx]
 *
 * ##Example##
 * Input: aabcccdee
 * Output: bd
 * 	 Step1: bd
 *
 * Input: abcdeedcbfgf
 * Output: afgf
 *	Step1: abcddcbfgf
 *	Step2: abccbfgf
 *	Step3: abbfgf
 *	Step4: afgf
 *
 * Input: abbabba
 * Output: Empty String
 *	Step1: aaa
 *	Step2: 
 * 
 */
object BomberIII {

  def iteration(ls: List[Char]): List[List[Char]] = ls match {
    case Nil => Nil
    case xs  => xs.takeWhile { _ == ls.head } :: iteration(xs.dropWhile { _ == ls.head })
  }

  def bomber(str: String) = {

    def bomberR(ls: List[Char]): String = {
      val ir = iteration(ls)

      if (ir.filter(_.length > 1).size == 0) ir.flatten mkString
      else bomberR(ir.filter(_.length == 1).flatten)
    }

    bomberR(str.toList)
  }

}
