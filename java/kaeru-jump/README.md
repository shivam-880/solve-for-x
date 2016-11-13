# Problem Statement
The puzzle (designed by [GameDesign](http://www.gamedesign.jp/flash/kaeru/kaeru.html)) is to find the way out while hopping through all the tiles.

> *[Backtracking](https://en.wikipedia.org/wiki/Backtracking) algorithm is used to solve this problem in Java.*

![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-1.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-2.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-3.JPG)
![Kaeru Jump](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/images/kaeru-jump-4.JPG)

# Documents
- [Puzzle](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/puzzle.xls): 
The puzzle is available as an excel document.
- [Analysis](https://github.com/codingkapoor/solve-for-x/blob/master/java/kaeru-jump/doc/analysis.xls): 
I have worked out this problem in sheets in an excel document. It comprises pseudo codes, flowcharts, etc.

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
