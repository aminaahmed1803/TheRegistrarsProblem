#!/bin/sh

for i in {0..9}
do
	java Schedule ../testfiles_bmc/sfall200${i}.txt ../testfiles_bmc/cfall200${i}.txt >> ../testschedules_bmc/schedule_cfall200${i}.txt
done

for i in {10..14}
do
	java Schedule ../testfiles_bmc/sfall20${i}.txt ../testfiles_bmc/cfall20${i}.txt >> ../testschedules_bmc/schedule_cfall20${i}.txt
done

for i in {1..9}
do
	java Schedule ../testfiles_bmc/sspring200${i}.txt ../testfiles_bmc/cspring200${i}.txt >> ../testschedules_bmc/schedule_cspring200${i}.txt
done

for i in {10..15}
do
	java Schedule ../testfiles_bmc/sspring20${i}.txt ../testfiles_bmc/cspring20${i}.txt >> ../testschedules_bmc/schedule_cspring20${i}.txt
done

