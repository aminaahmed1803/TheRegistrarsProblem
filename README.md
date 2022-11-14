# GenerateSchedule

This greedy algorithm is designed to schedule courses so that as many students can take their preferred courses as possible. 
We generate schedules for randomely generated data and Bryn Mawr College data. 

## To generate the schedule:

Navigate to AlgorithmsProject directory

```
java Schedule <preference-file.txt> <constraints-file.txt> >> <schedule-file.txt>
```

## To test for validity of the schedule:

```
perl ../isValidResults/is_valid <constraints-file.txt> <preference-file.txt> <schedule-file.txt>
```

