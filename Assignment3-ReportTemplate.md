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

<img width="814" alt="截屏2022-03-01 下午7 17 43" src="https://user-images.githubusercontent.com/74373450/156291389-34decd1b-be43-47e5-99a6-1d7718bbafb1.png">


the def-use sets per statement:
L151: def={total}
use={}
L152: def={rowCount}
use={}
L153: def={v}
p-use={v} c-use={v}
L154: def={row}
c-use={v}
L155: def={}
p-use={row, rowCount}
L156: def={n}
c-use={row,column}
L157: def={}
p-use={n}
L158: def={}
c-use={total, n}
L162: def={}
c-use={total}

list all DU-pairs per variable:
du(1,6,total)={[1,2,3,4,6]}
du(1,9,total)={[1,2,9],[1,2,3,5,9],[1,2,3,4,7,9]}
du(1,3,rowCount)={[1,2,3]}
du(2,3,v)={[2,3]}
du(2,9,v)={[2,9]}
du(2,8,v)={[2,3,4,6,8]}
du(3,4,row)={[3,4]}
du(3,5,row)={[3,5]}
du(4,6,n)={[4,6]}
du(4,7,n)={[4,7]}

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



# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

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
