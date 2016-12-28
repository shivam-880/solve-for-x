package com.codingkapoor.problemsolving

class SafeTraversal {

  def isTraversalSafe(str: String): (Boolean, Int) = {

    val cache = collection.mutable.Map[(Int, List[Char]), List[Char]]().withDefaultValue(Nil)

    def compute(jump: Int, ls: List[Char]): List[Char] = ls match {
      case Nil                                     => Nil

      case x :: Nil if (x == SafeTraversal.UNSAFE) => Nil
      case x :: Nil if (x == SafeTraversal.SAFE)   => List(x)

      case x :: xs if (x == SafeTraversal.UNSAFE)  => Nil
      case x :: xs if (x == SafeTraversal.SAFE) => {

        val left = (if (jump > 1) compute(jump - 1, (x :: xs).drop(jump - 1)) else Nil)
        val center = compute(jump, (x :: xs).drop(jump))
        val right = compute(jump + 1, (x :: xs).drop(jump + 1))

        cache.getOrElseUpdate((jump, ls), left ::: center ::: right)
      }
    }

    if (SafeTraversal.isValidInput(str)) {
      compute(1, str.toList) match {
        case Nil => (false, 0)
        case xs  => (true, xs.length)
      }
    } else (false, 0)
  }
}

object SafeTraversal {
  private final val DEFAULT = "SSUSSUSUUSUUUSUUUUS";

  private final val SAFE = 'S';
  private final val UNSAFE = 'U';

  private def isValidInput(str: String): Boolean = {
    (str filter { _ != 'S' } filter { _ != 'U' }).size == 0
  }

  def main(args: Array[String]): Unit = {
    val safeTraversal = new SafeTraversal()

    val str = if (args.length > 0) args(0) else DEFAULT
    val res = safeTraversal.isTraversalSafe(str)

    res match {
      case (true, n)  => println(s"""Safe traversal is possible for string "$str" where possible number of solutions are $n!""")
      case (false, _) => println(s"""Safe traversal is not possible for string "$str"!""")
    }

  }
}
