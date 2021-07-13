# Problems
This project aims to solve data structures and agorithmic problems in Scala.

## Table of Content
- [The Bomber Algorithm: I](https://github.com/codingkapoor/solve-for-x/tree/master/scala/problems#the-bomber-algorithm-i)
- [The Bomber Algorithm: II](https://github.com/codingkapoor/solve-for-x/tree/master/scala/problems#the-bomber-algorithm-ii)
- [The Bomber Algorithm: III](https://github.com/codingkapoor/solve-for-x/tree/master/scala/problems#the-bomber-algorithm-iii)
- [Safe Traversal](https://github.com/codingkapoor/solve-for-x/tree/master/scala/problems#safe-traversal)

## [The Bomber Algorithm: I](https://github.com/codingkapoor/solve-for-x/blob/master/scala/problems/src/main/scala/com/codingkapoor/problemsolving/BomberI.scala)
The algorithm doesn't like consecutive characters in a given string to be same. 

For example, if the given string is "abcccbd", the bomber algorithm first bombs "ccc" and then "bb". So the final output string will become "ad". It destroys the characters only if there are more than 1 continous occurence of same characters in the string.

*[Don't use RegEx]*

### Examples

**Input 1** : aabcccdee

**Output 1** : bd
```
	Step1: bcccdee
	Step2: bdee
	Step3: bd
```
<br/>

**Input 2** : abcdeedcbfgf

**Output 2** : afgf

```
	Step1: abcddcbfgf
	Step2: abccbfgf
	Step3: abbfgf
	Step4: afgf
```
<br/>

**Input 3** : abbabba

**Output 3** : a

```
	Step1: aabba
	Step2: bba
	Step3: a
```
<br/>

## [The Bomber Algorithm: II](https://github.com/codingkapoor/solve-for-x/blob/master/scala/problems/src/main/scala/com/codingkapoor/problemsolving/BomberII.scala)
The algorithm doesn't like more than two consecutive characters in a given string to be same. 

For example, if the given string is "abcccbd", the bomber algorithm first bombs "ccc" to get to "abbd".

*[Don't use RegEx]*

### Examples

**Input** : adbcccbbd

**Output** : add
```
	Step1: adbbbd
	Step2: add
```

<br/>

##  [The Bomber Algorithm: III](https://github.com/codingkapoor/solve-for-x/blob/master/scala/problems/src/main/scala/com/codingkapoor/problemsolving/BomberIII.scala)
This algorithm is a variant of the bomber algorithm. It bombs all the consecutive characters which are similar in a single iteration.

*[Don't use RegEx]*

### Examples

**Input 1** : aabcccdee

**Output 1** : bd

```
	Step1: bd
```
<br/>

**Input 2** : abcdeedcbfgf

**Output 2** : afgf

```
	Step1: abcddcbfgf
	Step2: abccbfgf
	Step3: abbfgf
	Step4: afgf
```

<br/>

**Input 3** : abbabba

**Output 3** : Empty String

```
	Step1: aaa
```

## [Safe Traversal](https://github.com/codingkapoor/solve-for-x/blob/master/scala/problems/src/main/scala/com/codingkapoor/problemsolving/SafeTraversal.scala)

The Program should take a input string of characters 'S' and 'U' where 'S' stands for Safe, 'U' stands for Unsafe. The first character is always an 'S' and that is the position from where traversal begins. 

The objective is to write a program to move from the first 'S' to the end of the array without ever stepping into a 'U' (Unsafe) block. When it finishes, the program should tell if a safe traversal from left to right is possible at all or not for the given array. To make the traversal, the program has to use a jump-size. Initial jump-size should be 1 (so that one can move only one block at a time). But the jump-size can be increased or decreased by 1. The jump-size can never go below 1 and cannot be incremented/decremented from current size by more than 1.

A rather GREEDY ALGORITHMIC approach would be as described below; which won't return correct result for variety of input strings.

For sequence, 'SSUSSUSUUS', the steps would be:

1. Program starts at the left-most end with initial position 1 of S with jump-size of 1.
2. Program finds that the next block is a S and so does not increase the jump-size and traverses to position 2.
3. Program finds that the next block is a U and so increases the jump-size to 2 and moves to position 4.
4.  Program finds that with jump-size of 2 the block in position 6 is unsafe. So it decreases the jump-size to 1 and traverses to position 5.
5. Program finds that block in position 6 is unsafe and so increases the jump-size to 2 and traverses to position 7.
6. Program finds that block in position 9 is unsafe, also finds that block in position 8 is unsafe (so decreasing jump-size will not help), so increases the jump-size to 3 and traverses to position 10. Since we have hit the end of array the program returns saying safe-traversal-possible for the given sequence.

### Solution
This problem however can be solved using DYNAMIC PROGRAMMING approach using MEMOIZATION in O(n^2) polynomial time complexity.

### Run
#### With default input

```
$ sbt run
```

#### With custom input

```
$ sbt "run-main com.codingkapoor.problemsolving.SafeTraversal SSSSS"
```
