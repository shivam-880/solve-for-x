# Page Selection By Keyword Matching

*Solution II-X is [Domain Driven Design](https://martinfowler.com/tags/domain%20driven%20design.html) oriented*

This problem statement is in continuation to the [Part I](https://github.com/codingkapoor/solve-for-x/blob/master/scala/page-selection-by-keyword-matching-I-Y/README.md) of the problem.

It’s high time we enhance our search solution to be able to handle nested pages. A Page can have nested pages e.g. P1 can have nested pages P1.1, P1.2, P1.3 etc.

When a Query matches a given nested page, it’s score is calculated as per the algorithm in [Part I](https://github.com/codingkapoor/solve-for-x/blob/master/scala/page-selection-by-keyword-matching-I-Y/README.md). However, since this nested page belongs to a parent page, we should add 10% of the nested page’s score to the parent page’s score.

For example, assume the following web pages and keyword lists:

```
Page 1: Smalltalk, programming, computers
Page 1.1: Smalltalk, computers
Page 2: COBOL, programming
Page 2.1: Smalltalk
Page 3: programming, Smalltalk
```

For *N* equal 8, a query with keywords Smalltalk and programming in that order yields the following strength ratings:

```
Page 1.1: (8x8) = 64
Page 1: (8x8 + 7x7 + 64x0.1) = 119.4
Page 2.1: (8x8) = 64
Page 2: (7x7 + 64x0.1) = 55.4
Page 3: (7x8) = 56
```

Display only the parent pages in the search results e.g. if *P1* does not match but *P1.1* matches a query, show only *P1* and not *P1.1* in the results output.

## Input
Code letters *P* denote a parent page and *PP* denote a child page under the previous parent. All *PP*s denote children/nested pages under the most recent *P*. A new *P* denotes a new parent page.

## Sample Input

```
P Smalltalk programming
PP computers programming
PP computers Ford
P FORTRAN programming
PP programming
Q Smalltalk
Q programming
Q computers
E
```

## Sample Output

Query Pages
```
Q1: P1
Q2: P2 P1
Q3: P1
```

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
