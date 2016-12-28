# Merchant's Guide to Galaxy

You decided to give up on earth after the latest financial collapse left 99.99% of the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in your account, you are able to afford to rent a spaceship, leave earth, and fly all over the galaxy to sell common metals and dirt (which apparently is worth a lot). Buying and selling over the galaxy requires you to convert numbers and units, and you decided to write a program to help you. The numbers used for intergalactic transactions follows similar convention to the roman numerals and you have painstakingly collected the appropriate translation between them. 

**Roman numerals are based on seven symbols:**

| Symbol | Value |
|:-------|:------|
| I | 1 |
| V | 5 |
| X | 10 |
| L | 50 |
| C | 100 |
| D | 500 |
| M | 1,000 |


Numbers are formed by combining symbols together and adding the values. For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value, starting with the largest values. When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total. For example MCMXLIV = 1000 + (1000 − 100) + (50 − 10) + (5 − 1) = 1944.

The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be repeated.

"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
Only one small-value symbol may be subtracted from any large-value symbol.

A number written in Arabic numerals can be broken into digits. For example, 1903 is composed of 1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should be treated separately. In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.

**Note:** Input to your program consists of lines of text detailing your notes on the conversion between intergalactic units and roman numerals. You are expected to handle invalid queries appropriately.

## Sample Input:
```
glob is I<br/>
prok is V<br/>
pish is X<br/>
tegj is L<br/>
glob glob Silver is 34 Credits<br/>
glob prok Gold is 57800 Credits<br/>
pish pish Iron is 3910 Credits<br/>
how much is pish tegj glob glob ?<br/>
how many Credits is glob prok Silver ?<br/>
how many Credits is glob prok Gold ?<br/>
how many Credits is glob prok Iron ?<br/>
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?<br/>
```
## Sample Output:
```
how much is pish tegj glob glob ? 42<br/>
how many Credits is glob prok Silver ? 68 Credits<br/>
how many Credits is glob prok Gold ? 57,800 Credits<br/>
how many Credits is glob prok Iron ? 782 Credits<br/>
how much wood could a woodchuck chuck if a woodchuck could chuck= wood ? I have no idea what you are talking about.<br/>
hey? I have no idea what you are talking about.<br/>
```
# TestCases: 
A rough description of test cases.

## Input Format I
```
pish : X
tegj : L
prok : V
glob : I
```

1. If rhs token count is 1 and rhs is not a valid roman numeral.<br/>
*Display message `invalid translation` and terminate.*

2. If lhs token count is not 1.<br/>
*Display message `invalid translation` and program terminates.*

3. If rhs or lhs is empty the input line will be ignored by message format parser.

4. If lhs has more than one tokens the input line will be ignored by message format parser.

## Input Format II
```
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
```

1. If rhs token count is not 1.<br/>
*Display message `invalid translation` and program terminates.*

2. If rhs is not a valid floating point number.<br/>
*Display message `invalid translation` and program terminates.*

3. If at lhs a token has been reached that doesn't belong to the transaltion map but still there are even more subsequent tokens i.e., there should be only one such token.<br/>
*Display message `invalid translation` and program terminates.*

4. If roman number formed out of all the token found in transaltion map is not validated.<br/>
*Display message `no idea`.*

5. Check for valid poitive and negative floating point numbers.

## Input Format III
```
how much is pish tegj glob glob ?
```
1. If expression has a token that doesn't belong to the transaltion map.<br/>
*Display message `no idea` and the program continues with the next qeustion.*

2. If the roman number formed out of all these tokens doesn't validates.<br/>
*Display warning `syntactically wrong`.*

## Input Format IV
```
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
```

1. If token from credits map is reached and yet expression has any more subsequent tokens.<br/>
*Display message `no idea`.*

2. If the roman number formed is invalidated.<br/>
*Display warning `syntactically wrong`.*

3. If a token is reached which doesn't belong to translation map.<br/>
*Display message `no idea`.*

# Build package
```
mvn package
```

# Run

## With default input
```
$ java -cp target/merchants-guide-to-galaxy-1.0-SNAPSHOT.jar com.codingkapoor.merchantsguidetogalaxy.Translator
```

## With custom input
```
$ java -cp target/merchants-guide-to-galaxy-1.0-SNAPSHOT.jar com.codingkapoor.merchantsguidetogalaxy.Translator user-input.txt
```
