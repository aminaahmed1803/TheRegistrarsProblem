#!/bin/sh
for i in {1..100}
do
	diff=$((100-50+1))
	num_rooms=$(($(($RANDOM%$diff))+50))
	num_rooms=${num_rooms/\.*/}
	diff2=$((100-30+1))
	num_classes=$(($(($RANDOM%$diff2))+30))
	diff3=$((60-10+1))
	num_slots=$(($(($RANDOM%$diff3))+10))
	diff4=$((1500-300+1))
	num_students=$(($(($RANDOM%$diff4))+300))
	if [[ `expr $num_classes % 2` !=  0 ]]
		 then	
			num_classes=`expr $num_classes + 1`
	fi
	for j in {1..10}
	do
		perl make_random_input.pl $num_rooms $num_classes $num_slots $num_students constraints_${i}_${j}.txt studentprefs_${i}_${j}.txt
	done
done
