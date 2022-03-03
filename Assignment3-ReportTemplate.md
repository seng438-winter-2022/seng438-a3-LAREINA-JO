**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      |     |
| -------------- | --- |
| Student Names: |     |
|                |     |
|                |     |
|                |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

Text…to

# 2 Manual data-flow coverage calculations for X and Y methods

Method: DataUtilities.calculateColumnTotal

<img width="684" alt="截屏2022-03-02 下午9 30 09" src="https://user-images.githubusercontent.com/74373450/156496442-26015bfe-4bc4-4ea7-9581-be85d5b8b2c1.png">


the def-use sets per statement:
L125: def={total}
use={}

L126: def={rowCount}
use={}

L127: def={r}
p-use={r} c-use={r}

L128: def={n}
c-use={r,column}

L129: def={}
p-use={n}

L130: def={}
c-use={total, n}

L131: def={}
c-use={total}

list all DU-pairs per variable:
du(1,4,total)={[1,2,3,4]}
du(1,7,total)={[1,2,7]}
du(1,2,rowCount)={[1,2]}
du(2,3,r)={[2,3]}
du(2,7,r)={[2,7]}
du(2,6,r)={[2,3,4,6]}
du(3,4,n)={[3,4]}
du(3,5,n)={[3,5]}

CalculateColumnTotalTest:
testNullValue2DForcalculateColumnTotal(): didn't cover any DU-pairs

testZeroValueForcalculateColumnTotal(): cover all the DU-pairs

testPositiveValueForcalculateColumnTotal(): cover all the DU-pairs except du(3,5,n)

testNegativeValueForcalculateColumnTotal(): du(1,2,rowCount), du(2,3,r)

calculate the DU-Pair coverage:
DU-Pair Coverage=8/8 * 100% = 100%


Method: Range.constrain

<img width="712" alt="截屏2022-03-01 下午10 17 46" src="https://user-images.githubusercontent.com/74373450/156300015-389ce802-f617-4979-af3e-4ee1a0f22bdf.png">

the def-use sets per statement:
L188: def={result}
use={}
L191: def={}
c-use={result}
L194: def={}
c-use={result}
L197: def={}
c-use={result}

list all DU-pairs per variable:
du(1,3,result)={[1,2,3]}
du(1,5,result)={[1,2,4,5]}
du(1,7,result)={[1,7],[1,2,3,7],[1,2,4,5,7],[1,2,4,6,7]}

CalculateColumnTotalTest:

ContainedValue(): du(1,7,result)={[1,7]}

ValueGreaterThanUpper():du(1,3,result)

ValueLessThanLower(): didn't cover any DU-Pairs

ValueLessThanUpperAndGreaterThanLower(): du(1,5,result)

calculate the DU-Pair coverage:
DU-Pair Coverage=3/3 * 100% = 100%


# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

DataUtilities.calculateRowTotal():



# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
