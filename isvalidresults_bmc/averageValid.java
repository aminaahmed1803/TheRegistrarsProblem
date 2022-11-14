import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class averageValid {

    public static void main(String[] args) {

        ArrayList<Integer> numStudents = new ArrayList<Integer>();
        ArrayList<Integer> numRooms = new ArrayList<Integer>();
        ArrayList<Integer> numTimeslots = new ArrayList<Integer>();
        ArrayList<Integer> numCourses = new ArrayList<Integer>();
        ArrayList<Integer> numProfessors = new ArrayList<Integer>();
        ArrayList<Integer> prefValues  = new ArrayList<Integer>();
        ArrayList<Integer> maxValues = new ArrayList<Integer>();
        ArrayList<Integer> runtimeValues = new ArrayList<Integer>();
        BufferedReader br;
        BufferedReader brRT;
        BufferedReader brConstraints;
        BufferedReader brStudentPrefs;
        for(int i = 0; i <= 9; i++){
            try {
                String constraintsFile = "../testfiles_bmc/cfall200" + Integer.toString(i) + ".txt";
                String studentPrefsFile = "../testfiles_bmc/sfall200" + Integer.toString(i) + ".txt";
                brConstraints = new BufferedReader(new FileReader(constraintsFile));
                brStudentPrefs = new BufferedReader(new FileReader(studentPrefsFile));
                String line = brStudentPrefs.readLine();
                String[] frag = line.split("\t");
                Integer numStudents_this = Integer.parseInt(frag[1]);
                numStudents.add(numStudents_this);
                String line2 = brConstraints.readLine();
                String[] frag2 = line2.split("\t");
                int numTimeSlots_this = Integer.parseInt(frag2[1]); //Store number of class times
                numTimeslots.add(numTimeSlots_this);

                for(int x = 0; x < numTimeSlots_this; x++){
                    brConstraints.readLine();
                }

                String line3 = brConstraints.readLine();
                String[] frag3 = line3.split("\t");
                int numRooms_this = Integer.parseInt(frag3[1]);
                numRooms.add(numRooms_this);
                int idx = 0;

                while (idx < numRooms_this) {
                    line = brConstraints.readLine();
                    idx++;
                }

                String line4 = brConstraints.readLine();
                String[] frag4 = line4.split("\t");
                int numClasses_this = Integer.parseInt(frag4[1]);
                numCourses.add(numClasses_this);

                String line5 = brConstraints.readLine();
                String[] frag5 = line5.split("\t");
                int numProfs_this = Integer.parseInt(frag5[1]);
                numProfessors.add(numProfs_this);

                brConstraints.close();
                brStudentPrefs.close();
                String rtFile = "../runtime_bmc/runtime_cfall200" + Integer.toString(i) + ".txt";
                String filename = "is_valid_fall200" + Integer.toString(i) + ".txt";
                brRT = new BufferedReader(new FileReader(rtFile));

                String time = brRT.readLine();
                //Time take: 78
                String timeTaken = time.substring(11);
                Integer timeTakenF = Integer.parseInt(timeTaken);
                runtimeValues.add(timeTakenF);

                br = new BufferedReader(new FileReader(filename));
                br.readLine();
                //Want to get number from "Student preferences value: 2163"
                String l1 = br.readLine();
                String prefValue = l1.substring(27);
                prefValues.add(Integer.parseInt(prefValue));
                String l2 = br.readLine();
                //Want to get number from "Maximum value: 2228"
                String maxValue = l2.substring(15);
                maxValues.add(Integer.parseInt(maxValue));
                brRT.close();
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        for(int i = 10; i <= 14; i++){
            try {
                String constraintsFile = "../testfiles_bmc/cfall20" + Integer.toString(i) + ".txt";
                String studentPrefsFile = "../testfiles_bmc/sfall20" + Integer.toString(i) + ".txt";
                brConstraints = new BufferedReader(new FileReader(constraintsFile));
                brStudentPrefs = new BufferedReader(new FileReader(studentPrefsFile));
                String line = brStudentPrefs.readLine();
                String[] frag = line.split("\t");
                Integer numStudents_this = Integer.parseInt(frag[1]);
                numStudents.add(numStudents_this);
                String line2 = brConstraints.readLine();
                String[] frag2 = line2.split("\t");
                int numTimeSlots_this = Integer.parseInt(frag2[1]); //Store number of class times
                numTimeslots.add(numTimeSlots_this);

                for(int x = 0; x < numTimeSlots_this; x++){
                    brConstraints.readLine();
                }

                String line3 = brConstraints.readLine();
                String[] frag3 = line3.split("\t");
                int numRooms_this = Integer.parseInt(frag3[1]);
                numRooms.add(numRooms_this);
                int idx = 0;

                while (idx < numRooms_this) {
                    line = brConstraints.readLine();
                    idx++;
                }

                String line4 = brConstraints.readLine();
                String[] frag4 = line4.split("\t");
                int numClasses_this = Integer.parseInt(frag4[1]);
                numCourses.add(numClasses_this);

                String line5 = brConstraints.readLine();
                String[] frag5 = line5.split("\t");
                int numProfs_this = Integer.parseInt(frag5[1]);
                numProfessors.add(numProfs_this);

                brConstraints.close();
                brStudentPrefs.close();
                String rtFile = "../runtime_bmc/runtime_cfall20" + Integer.toString(i) + ".txt";
                String filename = "is_valid_fall20" + Integer.toString(i) + ".txt";
                brRT = new BufferedReader(new FileReader(rtFile));

                String time = brRT.readLine();
                //Time take: 78
                String timeTaken = time.substring(11);
                Integer timeTakenF = Integer.parseInt(timeTaken);
                runtimeValues.add(timeTakenF);

                br = new BufferedReader(new FileReader(filename));
                br.readLine();
                //Want to get number from "Student preferences value: 2163"
                String l1 = br.readLine();
                String prefValue = l1.substring(27);
                prefValues.add(Integer.parseInt(prefValue));
                String l2 = br.readLine();
                //Want to get number from "Maximum value: 2228"
                String maxValue = l2.substring(15);
                maxValues.add(Integer.parseInt(maxValue));
                brRT.close();
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        for(int i = 1; i <= 9; i++){
            try {
                String constraintsFile = "../testfiles_bmc/cspring200" + Integer.toString(i) + ".txt";
                String studentPrefsFile = "../testfiles_bmc/sspring200" + Integer.toString(i) + ".txt";
                brConstraints = new BufferedReader(new FileReader(constraintsFile));
                brStudentPrefs = new BufferedReader(new FileReader(studentPrefsFile));
                String line = brStudentPrefs.readLine();
                String[] frag = line.split("\t");
                Integer numStudents_this = Integer.parseInt(frag[1]);
                numStudents.add(numStudents_this);
                String line2 = brConstraints.readLine();
                String[] frag2 = line2.split("\t");
                int numTimeSlots_this = Integer.parseInt(frag2[1]); //Store number of class times
                numTimeslots.add(numTimeSlots_this);

                for(int x = 0; x < numTimeSlots_this; x++){
                    brConstraints.readLine();
                }

                String line3 = brConstraints.readLine();
                String[] frag3 = line3.split("\t");
                int numRooms_this = Integer.parseInt(frag3[1]);
                numRooms.add(numRooms_this);
                int idx = 0;

                while (idx < numRooms_this) {
                    line = brConstraints.readLine();
                    idx++;
                }

                String line4 = brConstraints.readLine();
                String[] frag4 = line4.split("\t");
                int numClasses_this = Integer.parseInt(frag4[1]);
                numCourses.add(numClasses_this);

                String line5 = brConstraints.readLine();
                String[] frag5 = line5.split("\t");
                int numProfs_this = Integer.parseInt(frag5[1]);
                numProfessors.add(numProfs_this);

                brConstraints.close();
                brStudentPrefs.close();
                String rtFile = "../runtime_bmc/runtime_cspring200" + Integer.toString(i) + ".txt";
                String filename = "is_valid_spring200" + Integer.toString(i) + ".txt";
                brRT = new BufferedReader(new FileReader(rtFile));

                String time = brRT.readLine();
                //Time take: 78
                String timeTaken = time.substring(11);
                Integer timeTakenF = Integer.parseInt(timeTaken);
                runtimeValues.add(timeTakenF);

                br = new BufferedReader(new FileReader(filename));
                br.readLine();
                //Want to get number from "Student preferences value: 2163"
                String l1 = br.readLine();
                String prefValue = l1.substring(27);
                prefValues.add(Integer.parseInt(prefValue));
                String l2 = br.readLine();
                //Want to get number from "Maximum value: 2228"
                String maxValue = l2.substring(15);
                maxValues.add(Integer.parseInt(maxValue));
                brRT.close();
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        for(int i = 10; i <= 15; i++){
            try {
                String constraintsFile = "../testfiles_bmc/cspring20" + Integer.toString(i) + ".txt";
                String studentPrefsFile = "../testfiles_bmc/sspring20" + Integer.toString(i) + ".txt";
                brConstraints = new BufferedReader(new FileReader(constraintsFile));
                brStudentPrefs = new BufferedReader(new FileReader(studentPrefsFile));
                String line = brStudentPrefs.readLine();
                String[] frag = line.split("\t");
                Integer numStudents_this = Integer.parseInt(frag[1]);
                numStudents.add(numStudents_this);
                String line2 = brConstraints.readLine();
                String[] frag2 = line2.split("\t");
                int numTimeSlots_this = Integer.parseInt(frag2[1]); //Store number of class times
                numTimeslots.add(numTimeSlots_this);

                for(int x = 0; x < numTimeSlots_this; x++){
                    brConstraints.readLine();
                }

                String line3 = brConstraints.readLine();
                String[] frag3 = line3.split("\t");
                int numRooms_this = Integer.parseInt(frag3[1]);
                numRooms.add(numRooms_this);
                int idx = 0;

                while (idx < numRooms_this) {
                    line = brConstraints.readLine();
                    idx++;
                }

                String line4 = brConstraints.readLine();
                String[] frag4 = line4.split("\t");
                int numClasses_this = Integer.parseInt(frag4[1]);
                numCourses.add(numClasses_this);

                String line5 = brConstraints.readLine();
                String[] frag5 = line5.split("\t");
                int numProfs_this = Integer.parseInt(frag5[1]);
                numProfessors.add(numProfs_this);

                brConstraints.close();
                brStudentPrefs.close();
                String rtFile = "../runtime_bmc/runtime_cspring20" + Integer.toString(i) + ".txt";
                String filename = "is_valid_spring20" + Integer.toString(i) + ".txt";
                brRT = new BufferedReader(new FileReader(rtFile));

                String time = brRT.readLine();
                //Time take: 78
                String timeTaken = time.substring(11);
                Integer timeTakenF = Integer.parseInt(timeTaken);
                runtimeValues.add(timeTakenF);

                br = new BufferedReader(new FileReader(filename));
                br.readLine();
                //Want to get number from "Student preferences value: 2163"
                String l1 = br.readLine();
                String prefValue = l1.substring(27);
                prefValues.add(Integer.parseInt(prefValue));
                String l2 = br.readLine();
                //Want to get number from "Maximum value: 2228"
                String maxValue = l2.substring(15);
                maxValues.add(Integer.parseInt(maxValue));
                brRT.close();
                br.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        System.out.println("no. \t Classes \t Professors \t Students \t Times \t Rooms \t Time(ms) \t Best \t Experimental \t % Optimality");
        for(int i = 0; i <= 9; i++){
            System.out.println("Fall 200" + i + " \t " + numCourses.get(i) + " \t " + numProfessors.get(i) + " \t "+ numStudents.get(i) + " \t " + numTimeslots.get(i) + " \t " + numRooms.get(i) + " \t " + runtimeValues.get(i) + " \t " + maxValues.get(i) + " \t " +  prefValues.get(i) + " \t " + ((float) prefValues.get(i)/ (float) maxValues.get(i)));
        }

        for(int i = 10; i <= 14; i++){
            System.out.println("Fall 20" + i + " \t " + numCourses.get(i) + " \t " + numProfessors.get(i) + " \t "+ numStudents.get(i) + " \t " + numTimeslots.get(i) + " \t " + numRooms.get(i) + " \t " + runtimeValues.get(i) + " \t " + maxValues.get(i) + " \t " +  prefValues.get(i) + " \t " + ((float) prefValues.get(i)/ (float) maxValues.get(i)));
        }

        for(int i = 1; i <= 9; i++){
            System.out.println("Spring 200" + i + " \t " + numCourses.get(i) + " \t " + numProfessors.get(i) + " \t "+ numStudents.get(i) + " \t " + numTimeslots.get(i) + " \t " + numRooms.get(i) + " \t " + runtimeValues.get(i) + " \t " + maxValues.get(i) + " \t " +  prefValues.get(i) + " \t " + ((float) prefValues.get(i)/ (float) maxValues.get(i)));
        }

        for(int i = 10; i <= 15; i++){
            System.out.println("Spring 20" + i + " \t " + numCourses.get(i) + " \t " + numProfessors.get(i) + " \t "+ numStudents.get(i) + " \t " + numTimeslots.get(i) + " \t " + numRooms.get(i) + " \t " + runtimeValues.get(i) + " \t " + maxValues.get(i) + " \t " +  prefValues.get(i) + " \t " + ((float) prefValues.get(i)/ (float) maxValues.get(i)));
        }
         
    }
    
}
