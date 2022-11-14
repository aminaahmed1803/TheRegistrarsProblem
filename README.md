# GenerateSchedule

This greedy algorithm is designed to schedule courses so that as many students can take their preferred courses as possible. 
We generate schedules for randomely generated data and Bryn Mawr College data. 

## To generate the schedule from random data

Navigate to AlgorithmsProject directory

```
java Schedule <preference-file.txt> <constraints-file.txt> >> <schedule-file.txt>

```

Note:

Format as follows

<preference-file.txt> needs to be formatted as "../testfiles/studentprefs_i_j.txt" where 1 <= i <= 100 and 1 <= j <= 10

<constraints-file.txt> needs to be formatted as "../testfiles/constraints_i_j.txt" where 1 <= i <= 100 and 1 <= j <= 10

This will produce a schedule in <schedule-file.txt> in AlgorithmsProject directory

## To test for validity of the schedule from random data

```
perl ../isvalidresults/is_valid.pl <constraints-file.txt> <preference-file.txt> <schedule-file.txt>
```


## To generate the schedule from bmc data

Navigate to AlgorithmsProject_bmc directory

```
java Schedule <preference-file.txt> <constraints-file.txt> >> <schedule-file.txt>

```

Note:

Format as follows

<preference-file.txt> needs to be formatted as "../testfiles_bmc/sfall2000.txt" where sfall2000.txt is the preferences file

<constraints-file.txt> needs to be formatted as "../testfiles_bmc/cfall2000.txt" where cfall2000.txt is the constraints file

## To test for validity of the schedule from bmc data

```
perl ../isvalidresults_bmc/is_valid.pl <constraints-file.txt> <preference-file.txt> <schedule-file.txt>
```

## Random Data files

AlgorithmsProject

testfiles

testschedules

runtime

isvalidresults

## BMC Data files

AlgorithmsProject_bmc

testfiles_bmc

testschedules_bmc

runtime_bmc

isvalidresults_bmc

## File explanation using random data

AlgorithmProject contains implementation of the algorithm. Schedule.java is the main driver of the algorithm.

testfiles contains 1000 pairs of randomely generated data: 100 unique combinations of constraints as well as scripts used to generate data

testschedules: schedules of the 1000 pairs of datasets

runtime: contains the runtime of each schedule generation

isvalidresults: contains the results of running is valid. Also contains averageValid.java that tabulates the results.

All files are similarly replicated for bmc data except that the data is limited to 30 pairs. 
