# Build package
```$ mvn package```

# Run (with default input)
```$ java -cp target/kaeru-jump-1.0-SNAPSHOT.jar com.codingkapoor.KaeruJumpAlgo```

***Output:***
*(1,3) -> (2,3) -> (2,2) -> (1,2) -> (1,1) -> (4,1) -> (4,3) -> (3,3) -> (3,0) -> x*

# Run (with custom input)
```$ java -cp target/kaeru-jump-1.0-SNAPSHOT.jar com.codingkapoor.KaeruJumpAlgo ".\input1.txt"```

***Output:***
*(1,1) -> (3,1) -> (3,2) -> (3,3) -> (3,4) -> (4,4) -> (5,4) -> (5,3) -> (2,3) -> (2,2) -> (4,2) -> (4,1) -> (4,0) -> x*
