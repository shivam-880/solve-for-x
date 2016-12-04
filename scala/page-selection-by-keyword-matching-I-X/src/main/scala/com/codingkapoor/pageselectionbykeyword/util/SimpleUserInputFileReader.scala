package com.codingkapoor.pageselectionbykeyword.util

import java.io.FileInputStream
import java.io.InputStream

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

trait SimpleUserInputFileReader extends UserInputFileReader {

  // Validates:
  // 1. If a line starts with "P" or "Q"  
  // 2. If a line representing page or query has at least one keyword
  private def validateInput(lines: List[String]) = {
    lines.map { _.trim().replaceAll("\\s+", " ") }.
      filter { i => i.startsWith(Page.identifier) || i.startsWith(Query.identifier) }.
      filter { _.length() != 1 }
  }

  // Ignores keywords that are beyond 8 in number
  // Makes all the keywords as lower case 
  // Prepares an intermediate list of pages/queries in a particular format: (1, [(Ford,8),(Car,7),(Review,6)]) 
  private def processInput(ls: List[String]) = {
    val u = (ls map { v => v.split(" ").toList.tail map (_.toLowerCase()) zip (totalKeywordsAllowed to 1 by -1) })
    u zip (1 to u.length)
  }

  private def preparePageList(ls: List[(List[(String, Int)], Int)]) = {
    (ls foldLeft (Nil: List[Page])) {
      case (acc, (i, j)) => acc ::: List(Page(j, i))
    }
  }

  private def prepareQueryList(ls: List[(List[(String, Int)], Int)]) = {
    (ls foldLeft (Nil: List[Query])) {
      case (acc, (i, j)) => acc ::: List(Query(j, i))
    }
  }

  override def readUserInputFile(fileName: Option[String]): (List[Query], List[Page]) = {
    
    val stream: InputStream = fileName match {
      case None    => getClass().getResourceAsStream(defaultUserInputFile)
      case Some(f) => new FileInputStream(f)
    }
    
    val lines = scala.io.Source.fromInputStream(stream).getLines

    // Partitions list of lines into a tuple of list of lines representing pages and queries, respectively
    val (pages, queries) = validateInput(lines.toList).partition { _.startsWith(Page.identifier) }

    (prepareQueryList(processInput(queries)), preparePageList(processInput(pages)))
  }

}
