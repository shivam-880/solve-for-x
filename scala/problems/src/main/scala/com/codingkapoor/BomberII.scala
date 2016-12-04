package com.codingkapoor

/**
 *
 * The Bomber Algorithm
 * The algorithm doesn't like consecutive characters in a given string to be same.
 * For example, if the given string is "abcccbd", the bomber algorithm first bombs "ccc" and then "bb".
 * So the final output string will become "ad". It destroys the characters only if there are more than 1 continuous occurrence of same characters in the string.
 *
 * [Don't use RegEx]
 *
 * ##Example##
 * Input: aabcccdee
 * Output: bd
 * 	 Step1: bcccdee
 * 	 Step2: bdee
 * 	 Step3: bd
 *
 * Input: abcdeedcbfgf
 * Output: afgf
 * 	Step1: abcddcbfgf
 * 	Step2: abccbfgf
 * 	Step3: abbfgf
 * 	Step4: afgf
 *
 * Input: abbabba
 * Output: a
 * 	Step1: aabba
 * 	Step2: bba
 * 	Step3: a
 *
 */
object BomberII {

  def iteration(result: List[Char], ls: List[Char]): List[Char] = ls match {
    case Nil                                         => Nil
    case y :: Nil                                    => result ::: List(y)
    case x :: xs :: Nil                              => result ::: List(x) ::: List(xs)
    case x :: xs :: ys if (x == xs && xs == ys.head) => result ::: ys.dropWhile { _ == x }
    case x :: xs :: ys                               => iteration(result ::: List(x), List(xs) ::: ys)
  }

  def bomber(str: String) = {

    def bomberR(ls: List[Char]): List[Char] = {

      def check(res: List[Char], list: List[Char]): List[Char] = list match {
        case Nil                                         => Nil
        case _ :: Nil                                    => res
        case x :: xs :: Nil                              => res
        case x :: xs :: ys if (x == xs && xs == ys.head) => bomberR(res)
        case x :: xs :: ys                               => check(res, List(xs) ::: ys)
      }

      val iter = iteration(Nil, ls)
      check(iter, iter)
    }

    (bomberR(str.toList) foldLeft "") { (acc, x) => acc + x }
  }

}
