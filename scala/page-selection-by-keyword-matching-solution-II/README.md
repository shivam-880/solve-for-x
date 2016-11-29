# MavenHive Challenge

# Design Description
- Page and Query are modeled as case classes with "id" and list of "keywords" as tuple of a keyword and it's weight.
- SimpleUserInputFileReader is a trait that knows to parse input file in a particular format and return a list of Query & Page objects.  
- SimpleUserInputFileReader is designed as a trait so that PageSelector algorithm can mixin a different UserInputFileReader trait that may know to parse input file in a different format.
- SimpleUserInputFileReader processes a default input file present under resources directory if no file is supplied.
- SimplePageSelector is designed as a trait so that it can be substituted with a more sophisticated PageSelector.
- Test cases are BDD oriented and are pretty self descriptive.

# Build Package

- To create FAT JAR.
  ```
  sbt assembly
  ```

# Run

## With default input
- To run as a FAT JAR.
  ```
  $ java -cp target/scala-2.11/page-selection-by-keyword-matching-assembly-1.0.jar com.codingkapoor.pageselectionbykeyword.PageSelectorApplication
  ```

- To run as a SBT project.
  ```
  sbt run
  ```

## With custom input
- To run as a FAT JAR.
  ```
  $ java -cp target/scala-2.11/page-selection-by-keyword-matching-assembly-1.0.jar com.codingkapoor.pageselectionbykeyword.PageSelectorApplication input.txt
  ```

- To run as a SBT project.
  ```
  $ sbt "run-main com.codingkapoor.pageselectionbykeyword.PageSelectorApplication input.txt"
  ```
