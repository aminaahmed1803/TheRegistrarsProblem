import java.util.ArrayList;
import java.util.HashMap;

import javax.rmi.ssl.SslRMIClientSocketFactory;

/**
 * A class that creates a schedule which accounts
 * for the following constraints
 * overlapping timeSlots
 * students having more than five prefrences
 * professors teaching more or less than two classes
 * scheduling lab_courses
 * scheduling sections
 * scheduling classes in certain buildings
 */

public class Schedule {

   // a list of scheduled courses
   public Course[] classCounts;

   // list of students with there prefrences
   private Student[] student_prefs;

   // contains rooms ordered by capacity
   private Room[] rooms;

   // A list of timeslots
   private timeSlots[] times;

   // the total number of classes
   private int total_classes;

   // the total number of professors
   private int total_profs;

   // COPY START
   private HashMap<Integer, Integer> overlappingTimes;

   private HashMap<String, Integer[]> room_timeslots;
   // COPY END

   /**
    * a constructor
    */
   public Schedule(String prefs, String conts) {
      fillData(prefs, conts);

   }

   private void fillData(String prefs, String conts) {

      extractData e = new extractData(prefs, conts);
      student_prefs = e.storePref();
      ArrayList<Integer> t = e.storeTime();
      rooms = e.storeRoom();
      String[] prof = e.storeProf();
      this.total_classes = e.classes;
      this.total_profs = e.profs;

      fillCourse(prof);
      preprocessPref();

      times = new timeSlots[t.size()];
      for (int i = 0; i < t.size(); i++) {
         times[i] = new timeSlots(t.get(i));
         times[i].fillstudents(student_prefs);
      }

      // COPY START
      int n = (int) (Math.random() * 15) + 0;
      overlappingTimes = makeOverlapping(n, t.size());
      // COPY END

   }

   // COPY START

   /*
    * Function that makes overlapping timeSlots
    * n is the number of overlapping timeslots you want to produce
    * size is the number of total timeslots
    * Returns a mapping of overlapping timeslots
    */
   public HashMap<Integer, Integer> makeOverlapping(int n, int size) {
      HashMap<Integer, Integer> overlappingTimeSlots = new HashMap<Integer, Integer>();
      for (int i = 0; i < n; i++) {
         int rand1 = (int) (Math.random() * size) + 1;
         int rand2 = (int) (Math.random() * size) + 1;
         overlappingTimeSlots.put(rand1, rand2);
         overlappingTimeSlots.put(rand2, rand1);
      }
      return overlappingTimeSlots;
   }

   public boolean isRoomFree(String room, Integer timeslot) {
      Integer[] timeslots = room_timeslots.get(room);
      for (int i = 0; i < timeslots.length; i++) {
         if (timeslots[i] == timeslot)
            return true;
      }
      return false;
   }

   // COPY END

   private void preprocessPref() {

      for (int i = 0; i < student_prefs.length; i++) {
         for (int j = 0; j < student_prefs[i].preferences.size(); j++) {
            int idx = getCourseIndex(student_prefs[i].preferences.get(j));
            if (idx == -1) {
               System.out.println("Input Error at Process Pref");
               System.exit(-1);
            }
            classCounts[idx].interested_students++;
         }
      }

      for (int i = 0; i < classCounts.length; i++) {
         if (classCounts[i].interested_students >= 60) {
            System.out.println("Section Made" + classCounts[i].course_id);
            classCounts[i].section = true;
            int students = 0;
            for (int j = 0; j < student_prefs.length; j++) {
               for (int k = 0; k < student_prefs[j].preferences.size(); k++) {
                  if (student_prefs[j].preferences.get(k).equals(classCounts[i].course_id)) {
                     if (students >= classCounts[i].interested_students / 2) {
                        String newid = student_prefs[j].preferences.get(k) + "b";
                        student_prefs[j].preferences.set(k, newid);
                     } else {
                        String newid = student_prefs[j].preferences.get(k) + "a";
                        student_prefs[j].preferences.set(k, newid);
                     }
                     students++;
                  }
               }
            }
         }

      }

      for (Student s : student_prefs) {
         System.out.println(s);
      }

   }

   public int getCourseIndex(String class_id) {
      if (class_id.contains("a") || class_id.contains("b")) {
         for (int i = 0; i < classCounts.length; i++) {
            // System.out.println(classCounts[i].course_id);
            if (class_id.equals(classCounts[i].course_id.substring(0, classCounts[i].course_id.length() - 1))) {
               return i;
            }
         }

      } else {
         for (int i = 0; i < classCounts.length; i++) {
            // System.out.println(classCounts[i].course_id);
            if (class_id.equals(classCounts[i].course_id)) {
               return i;
            }
         }
      }
      return -1;
   }

   private void fillCourse(String[] prof) {

      if (prof.length != this.total_classes) {
         System.out.println("Input Error");
         System.exit(1);
      }

      classCounts = new Course[this.total_classes];
      for (int i = 0; i < classCounts.length; i++) {
         String[] frag = prof[i].split("\t", -1);
         classCounts[i] = new Course();
         classCounts[i].course_id = frag[0];
         classCounts[i].professor = frag[1];
         for (int j = 2; j < frag.length; j++) {
            if (!classCounts[i].validRooms.contains(frag[j])) {
               classCounts[i].validRooms.add(frag[j]);
            }
         }
      }
   }

   public void makeSchedule() {

      int[][] assignmentRT = new int[rooms.length][times.length];
      for (int i = 0; i < rooms.length; i++) {
         for (int j = 0; j < times.length; j++) {
            // COPY START
            // if (rooms[i] is assigned to overlapping timeslot) continue;
            Integer timeslot_id = times[j].id;
            Integer overlapping_timeslot_id = (overlappingTimes.get(timeslot_id) != null)
                  ? overlappingTimes.get(timeslot_id)
                  : 0;
            boolean roomFree = isRoomFree(rooms[j].name, overlapping_timeslot_id);
            if (!roomFree) {
               continue;
            }
            // COPY END

            if (times[j].availableStudents.isEmpty())
               continue;
            int idx = -1;
            do {
               String class_id = times[j].mostFamousClass();
               idx = getCourseIndex(class_id);
               boolean sectionOverlap = false;
               if (classCounts[idx].section) {
                  if (classCounts[idx].assigned_time != 0) {
                     sectionOverlap = timeOverlap(classCounts[idx].assigned_time, times[j].id);
                  }
               }
               if (!times[j].isTeaching(classCounts[idx].professor) || classCounts[idx].isValidRoom(rooms[i].name)
                     || !sectionOverlap) {
                  times[j].remClass(class_id);
                  class_id = times[j].mostFamousClass();
               } else {

                  times[j].remStuds(class_id);
                  class_id = times[j].mostFamousClass();
                  break;
               }
            } while (true);
            if (classCounts[idx].assigned_time != null) {
               classCounts[idx].time2 = times[j].id;
               classCounts[idx].room2 = rooms[i];
            } else {
               classCounts[idx].assigned_time = times[j].id;
               classCounts[idx].assigned_room = rooms[i];
            }
            times[j].addProf(classCounts[idx].professor);
            // MostFamousClass = this room, this timeslot
         }
      }

   }

   /*
    * A fucntion which returns true if there is
    * an overlap between two timeSlots
    */
   public boolean timeOverlap(int t1, int t2) {
      return false;

   }

   public void enroll() {

      // if a class is a section, enroll has to run twice for both sections

   }

   public static void main(String[] args) {
      if (args.length != 2) {
         System.out.println("Usage: <prefences> <constraints>");
         return;
      }
      String prefrences = args[0];
      String constraints = args[1];
      Schedule s = new Schedule(prefrences, constraints);

   }

}