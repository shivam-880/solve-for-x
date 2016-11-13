# Problems

## Table of Content
- [The Bomber Algorithm](#the-bomber-algorithm)
- [The Big Bang Bomber Algorithm](#the-big-bang-bomber-algorithm)

## The Bomber Algorithm
The algorithm doesn't like consecutive characters in a given string to be same. For example, if the given string is "abcccbd", the bomber algorithm first bombs "ccc" and then "bb". So the final output string will become "ad". It destroys the characters only if there are more than 1 continous occurence of same characters in the string.

*[Don't use RegEx]*

###Examples
-

**Input 1**: aabcccdee

**Output 1**: bd
```
	Step1: bcccdee
	Step2: bdee
	Step3: bd
```
<br/>
**Input 2**: abcdeedcbfgf

**Output 2**: afgf

```
	Step1: abcddcbfgf
	Step2: abccbfgf
	Step3: abbfgf
	Step4: afgf
```
<br/>
**Input 3**: abbabba

**Output 3**: a

```
	Step1: aabba
	Step2: bba
	Step3: a
```
<br/>

### [Solution](https://github.com/codingkapoor/solve-for-x/blob/master/scala/src/main/scala/com/codingkapoor/P01.scala)

<br/>
## The Big Bang Bomber Algorithm
This algorithm is a variant of the bomber algorithm. It bombs all the consecutive characters which are similar in a single iteration.

*[Don't use RegEx]*

###Examples
-

**Input 1**: aabcccdee

**Output 1**: bd

```
	Step1: bd
```
<br/>
**Input 2**: abcdeedcbfgf

**Output 2**: afgf

```
	Step1: abcddcbfgf
	Step2: abccbfgf
	Step3: abbfgf
	Step4: afgf
```
<br/>
**Input 3**: abbabba

**Output 3**: Empty String

```
	Step1: aaa
```


### [Solution](https://github.com/codingkapoor/solve-for-x/blob/master/scala/src/main/scala/com/codingkapoor/P02.scala)
