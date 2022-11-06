#!/bin/sh
for i in {1..100}
do
	for j in {1..10}
	do
		java Schedule ../testfiles/studentprefs_${i}_${j}.txt ../testfiles/constraints_${i}_${j}.txt >> ../schedules_testfiles/schedule_${i}_${j}.txt
	done
done
