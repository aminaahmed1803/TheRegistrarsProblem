#!/bin/sh
# <number of rooms> <number of classes> <number of class times> <number of students> <number of teachers> <number of classes per student> <contraint file> <student prefs file>\n";
#number of teachers was half of num of classes. now it can be anything up to number of classes
#number of classes per students 3, 4, or 5
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
	diff5=$((5-3+1))
	num_classes_per_student=$(($(($RANDOM%$diff5))+3))
	diff6=$(($num_classes-($num_classes/3)+1))
	num_teachers=$(($(($RANDOM%$diff6))+($num_classes/2)))
	for j in {1..10}
	do
		perl make_random_constrained.pl $num_rooms $num_classes $num_slots $num_students $num_teachers $num_classes_per_student constraints_${i}_${j}.txt studentprefs_${i}_${j}.txt
	done
done
