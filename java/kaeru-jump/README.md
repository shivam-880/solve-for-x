# Problem Statement
The puzzle (designed by [GameDesign](http://www.gamedesign.jp/flash/kaeru/kaeru.html)) is to find the way out while hopping through all the tiles.

> *I have attempted to solve this puzzle programmatically using [Backtracking](https://en.wikipedia.org/wiki/Backtracking) algorithm in Java.*

![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-1.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-2.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-3.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-4.JPG)

# Documents
- The [puzzle](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/puzzle.xls) itself.
- My [analysis](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/analysis.xls) of this problem.

# Build package
```$ mvn package```

# Run 
## With default input

```$ java -cp target/kaeru-jump-1.0-SNAPSHOT.jar com.codingkapoor.KaeruJumpAlgo```

***Output:***
*(1,3) -> (2,3) -> (2,2) -> (1,2) -> (1,1) -> (4,1) -> (4,3) -> (3,3) -> (3,0) -> x*

## With custom input

```$ java -cp target/kaeru-jump-1.0-SNAPSHOT.jar com.codingkapoor.KaeruJumpAlgo ".\input1.txt"```

***Output:***
*(1,1) -> (3,1) -> (3,2) -> (3,3) -> (3,4) -> (4,4) -> (5,4) -> (5,3) -> (2,3) -> (2,2) -> (4,2) -> (4,1) -> (4,0) -> x*
