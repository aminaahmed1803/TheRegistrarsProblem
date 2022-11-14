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
        ArrayList<Float> prefValues  = new ArrayList<Float>();
        ArrayList<Integer> maxValues = new ArrayList<Integer>();
        ArrayList<Float> runtimeValues = new ArrayList<Float>();
         BufferedReader br;
         BufferedReader brRT;
         BufferedReader brConstraints;
         BufferedReader brStudentPrefs;
         for(int i = 1; i <= 100; i++){
            ArrayList<Integer> pref = new ArrayList<Integer>();
            ArrayList<Integer> max = new ArrayList<Integer>();
            ArrayList<Integer> rt = new ArrayList<Integer>();
            ArrayList<Integer> students = new ArrayList<Integer>();
            ArrayList<Integer> rooms = new ArrayList<Integer>();
            ArrayList<Integer> ts = new ArrayList<Integer>();
            ArrayList<Integer> courses = new ArrayList<Integer>();
            ArrayList<Integer> profs = new ArrayList<Integer>();
            for(int j = 1; j <= 10; j++){
                try {

                    String constraintsFile = "../testfiles/constraints_" + Integer.toString(i) + "_" + Integer.toString(j) + ".txt";
                    String studentPrefsFile = "../testfiles/studentprefs_" + Integer.toString(i) + "_" + Integer.toString(j) + ".txt";
                    brConstraints = new BufferedReader(new FileReader(constraintsFile));
                    brStudentPrefs = new BufferedReader(new FileReader(studentPrefsFile));
                    

                    String line = brStudentPrefs.readLine();
                    String[] frag = line.split("\t");
                    Integer numStudents_this = Integer.parseInt(frag[1]);
                    students.add(numStudents_this);

                    String line2 = brConstraints.readLine();
                    String[] frag2 = line2.split("\t");
                    int numTimeSlots_this = Integer.parseInt(frag2[1]); //Store number of class times
                    ts.add(numTimeSlots_this);

                    //THIS WONT WORK ON BMC DATA
                    String line3 = brConstraints.readLine();

                    String[] frag3 = line3.split("\t");
                    int numRooms_this = Integer.parseInt(frag3[1]);
                    //System.out.println(numRooms_this);
                    rooms.add(numRooms_this);
                    int idx = 0;

                    while (idx < numRooms_this) {
                    line = brConstraints.readLine();
                    idx++;
                    }

                    String line4 = brConstraints.readLine();
                    String[] frag4 = line4.split("\t");
                    int numClasses_this = Integer.parseInt(frag4[1]);
                    courses.add(numClasses_this);

                    String line5 = brConstraints.readLine();
                    String[] frag5 = line5.split("\t");
                    int numProfs_this = Integer.parseInt(frag5[1]);
                    profs.add(numProfs_this);

                   brConstraints.close();
                   brStudentPrefs.close();

                    String rtFile = "../runtime/runtime_" + Integer.toString(i) + "_" + Integer.toString(j) + ".txt";
                    String filename = "is_valid_" + Integer.toString(i) + "_" + Integer.toString(j) + ".txt";
                    brRT = new BufferedReader(new FileReader(rtFile));

                    String time = brRT.readLine();
                    //Time take: 78
                    String timeTaken = time.substring(11);
                    Integer timeTakenF = Integer.parseInt(timeTaken);
                    rt.add(timeTakenF);
                    br = new BufferedReader(new FileReader(filename));
                    br.readLine();
                    //Want to get number from "Student preferences value: 2163"
                    String l1 = br.readLine();
                    String prefValue = l1.substring(27);
                    pref.add(Integer.parseInt(prefValue));
                    String l2 = br.readLine();
                    //Want to get number from "Maximum value: 2228"
                    String maxValue = l2.substring(15);
                    max.add(Integer.parseInt(maxValue));

                    brRT.close();
                    br.close();
                 } catch (IOException e) {
                    System.out.println(e);
                 }
            }
            //Average the 10 prefs and maxValues
            Integer s = 0;
            for(Integer num : rt){
                s+= num;
            }
            Float avgRumtime = (float) s / rt.size();
            runtimeValues.add(avgRumtime);
            Integer sum = 0;
            for(Integer p : pref)
                sum += p;
            Float average = (float) sum / pref.size();
            prefValues.add(average);
            maxValues.add(max.get(0));
            numStudents.add(students.get(0));
            numCourses.add(courses.get(0));
            numRooms.add(rooms.get(0));
            numTimeslots.add(ts.get(0));
            numProfessors.add(profs.get(0));
        }

        System.out.println("no. \t Classes \t Professors \t Students \t Times \t Rooms \t Time(ms) \t Best \t Experimental \t % Optimality");
        for(int i = 1; i <= 100; i++){
            System.out.println(i + " \t " + numCourses.get(i-1) + " \t " + numProfessors.get(i-1) + " \t " + numStudents.get(i-1) + " \t " + numTimeslots.get(i-1) + " \t " + numRooms.get(i-1) + " \t " + runtimeValues.get(i-1) + " \t " + maxValues.get(i-1) + " \t " +  prefValues.get(i-1) + " \t " + (prefValues.get(i-1)/maxValues.get(i-1)));
        }
         
    }
    
}
