package com.codingkapoor.pageselectionbykeyword

import java.io.File
import java.io.FileWriter

import org.scalatest.Matchers
import org.scalatest.Outcome
import org.scalatest.fixture

import com.codingkapoor.pageselectionbykeyword.selector.SimplePageSelector

class PageSelectorSpec extends SimplePageSelector with fixture.FlatSpecLike with Matchers  {

  case class FixtureParam(file: File, writer: FileWriter)

  def withFixture(test: OneArgTest): Outcome = {
    val file = File.createTempFile("test", "txt")
    val writer = new FileWriter(file)

    val theFixture = FixtureParam(file, writer)

    try {
      withFixture(test.toNoArgTest(theFixture))
    } finally {
      writer.close()
      file.delete()
    }
  }

  "PageSelector" should "return expected result when the user input is ill formatted" in { f =>
    f.writer.write("P Ford Car Review                                 \n")
    f.writer.write("P                    Review Car\n")
    f.writer.write("Q Ford Review\n")
    f.writer.write("Q Ford Car\n")
    f.writer.write("Q cooking French\n")
    f.writer.write("P")

    f.writer.flush()

    val result = pageSelectionByKeyword(Some(f.file.getAbsolutePath))
    result should equal(List("Q1: P1 P2", "Q2: P1 P2", "Q3:"))
  }

  "PageSelector" should "return expected result irrespective of cases of keywords" in { f =>
    f.writer.write("P FORD car REvIew\n")
    f.writer.write("P RevieW CaR\n")
    f.writer.write("Q Ford ReVIEW\n")
    f.writer.write("Q Ford Car\n")
    f.writer.write("Q cookING French\n")
    f.writer.write("P")

    f.writer.flush()

    val result = pageSelectionByKeyword(Some(f.file.getAbsolutePath))
    result should equal(List("Q1: P1 P2", "Q2: P1 P2", "Q3:"))
  }

  "PageSelector" should "return expected result irrespective of order of listing of pages and queries in the user input" in { f =>
    f.writer.write("Q Ford Review\n")
    f.writer.write("P Ford Car Review\n")
    f.writer.write("Q Ford Car\n")
    f.writer.write("P Review Car\n")
    f.writer.write("Q cooking French\n")
    f.writer.write("P")

    f.writer.flush()

    val result = pageSelectionByKeyword(Some(f.file.getAbsolutePath))
    result should equal(List("Q1: P1 P2", "Q2: P1 P2", "Q3:"))
  }

  "PageSelector" should "return expected result for a regular input" in { f =>
    f.writer.write("P Ford Car Review\n")
    f.writer.write("P Review Car\n")
    f.writer.write("Q Ford Review\n")
    f.writer.write("Q Ford Car\n")
    f.writer.write("Q cooking French\n")
    f.writer.write("P")

    f.writer.flush()

    val result = pageSelectionByKeyword(Some(f.file.getAbsolutePath))
    result should equal(List("Q1: P1 P2", "Q2: P1 P2", "Q3:"))
  }

}
