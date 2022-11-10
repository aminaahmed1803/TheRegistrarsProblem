
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.*;

public class timeSlots {

   public Student[] availableStudents;

   public ArrayList<String> profs_teaching;

   public Integer id;

   public timeSlots() {
      profs_teaching = new ArrayList<>();
   }

   public void fillstudents(Student[] temp) {
      availableStudents = new Student[temp.length];
      for (int i = 0; i < temp.length; i++) {
         availableStudents[i] = new Student(temp[i].id, temp[i].preferences);
      }

   }

   public boolean isTeaching(String prof_id) {
      if (profs_teaching == null)
         return false;

      for (int i = 0; i < this.profs_teaching.size(); i++) {
         //System.out.println("Professor: " + profs_teaching.get(i) + " = " + prof_id + ", " + prof_id.equals(this.profs_teaching.get(i)));

         if (prof_id.equals(this.profs_teaching.get(i))) {
            //System.out.println("Professor " + profs_teaching.get(i) + " is already teaching at time " + this.id);
            return true;
         }
      }
      return false;
   }

   public void addProf(String prof_id) {
      profs_teaching.add(prof_id);
   }

   public String mostFameClass() {
      HashMap<String, Integer> course_count = new HashMap<String, Integer>();
      for (int i = 0; i < availableStudents.length; i++) {
         ArrayList<String> thisprefs = availableStudents[i].preferences;
         for (int j = 0; j < thisprefs.size(); j++) {
            String course_id = thisprefs.get(j);
            course_count.put(course_id,
                  ((course_count.get(course_id) == null) ? 0 : (course_count.get(course_id) + 1)));
         }
      }
      // Now we hava a hashmap that has courses and the number of students who want to
      // take that course
      String maxCourse = "";
      Integer max = 0;

      for (Map.Entry<String, Integer> entry : course_count.entrySet()) {
         String key = entry.getKey();
         Integer value = entry.getValue();
         // System.out.println("Course" + key + " has " + value + " students who want to
         // take it");
         if (value > max) {
            max = value;
            maxCourse = key;
         }
      }

      this.remClass(maxCourse);
      return maxCourse;
   }

   public void remClass(String class_id) {
      for (int i = 0; i < availableStudents.length; i++) {
         ArrayList<String> thisprefs = availableStudents[i].preferences;
         for (int j = 0; j < thisprefs.size(); j++) {
            if (thisprefs.get(j).equals(class_id)) {
               // remove the classs from pref list
               availableStudents[i].preferences.remove(class_id);
            }
         }
      }
   }

}
