#!/bin/sh
for i in {1..100}
do
	for j in {1..10}
	do
		perl is_valid.pl ../testfiles/constraints_${i}_${j}.txt ../testfiles/studentprefs_${i}_${j}.txt ../testschedules/schedule_${i}_${j}.txt >> is_valid_${i}_${j}.txt
	done
done
