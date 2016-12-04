package com.codingkapoor.pageselectionbykeyword.util

import com.codingkapoor.pageselectionbykeyword.model.Page
import com.codingkapoor.pageselectionbykeyword.model.Query

trait UserInputFileReader {
  
  final val totalKeywordsAllowed = 8
  final val defaultUserInputFile = "/input.txt"
  
  def readUserInputFile(fileName: Option[String]): (List[Query], List[Page])
}
