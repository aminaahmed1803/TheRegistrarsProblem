#!/bin/sh
for i in {0..9}
do
	perl is_valid.pl ../testfiles_bmc/cfall200${i}.txt ../testfiles_bmc/sfall200${i}.txt ../testschedules_bmc/schedule_cfall200${i}.txt >> is_valid_fall200${i}.txt
done

for i in {10..14}
do
	perl is_valid.pl ../testfiles_bmc/cfall20${i}.txt ../testfiles_bmc/sfall20${i}.txt ../testschedules_bmc/schedule_cfall20${i}.txt >> is_valid_fall20${i}.txt
done

for i in {1..9}
do
	perl is_valid.pl ../testfiles_bmc/cspring200${i}.txt ../testfiles_bmc/sspring200${i}.txt ../testschedules_bmc/schedule_cspring200${i}.txt >> is_valid_spring200${i}.txt
done

for i in {10..15}
do
	perl is_valid.pl ../testfiles_bmc/cspring20${i}.txt ../testfiles_bmc/sspring20${i}.txt ../testschedules_bmc/schedule_cspring20${i}.txt >> is_valid_spring20${i}.txt
done

