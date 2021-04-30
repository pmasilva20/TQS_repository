Task1
Has your project passed the defined quality gate? Elaborate your answer.
R:Yes it has passed all conditions, coverage is greater than 80%,
duplicate lines are less than 2%, maintability rating is equal or greater than A,reliability rating is equal or greater than A,
there are no security hotspots, and the security rating is equal or greater than A.
Issue	Description					HowTo
Bug	"Random" objects is being created when needed	Random object should be stored and reused, garanting it's randomness and efficiency

Code Smell	Refactor the code in order to not assign to this loop counter from within the loop body		Stop updating variable i inside the loop body in order to improve efficiency and make easier to undestand to code

Code Smell	Unused method parameters should be removed	Remove the unused parameter from the method signature

Task2

2a/ Take note of the technical debt found. Explain what this value means.
2h10min of technical debt, thecnical debt of a project is the sum of
technical debt of every code smell in the project, each one has a time estimative to what it would take to refactor it which is given by it's remediation function

2b/ Analyze the reported problems and be sure to correct the severe code smells reported (critical
and major).

2d/ Run the static analysis and observe/explore the coverage values on the SonarQube dashboard.
How many lines are “uncovered”? And how many conditions?
69.7%
Coverage on 136 Lines to cover
13 conditions

