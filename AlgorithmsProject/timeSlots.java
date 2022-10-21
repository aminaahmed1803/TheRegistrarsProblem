import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class timeSlots {

   public Student[] availableStudents;

   public ArrayList<String> profs_teaching;

   public String name;

   public timeSlots() {
      profs_teaching = new ArrayList<>();
   }

   public void fillstudents(Student[] temp) {
      availableStudents = new Student[temp.length];
      for (int i = 0; i < temp.length; i++) {
         availableStudents[i] = temp[i];
      }

   }

   public boolean isTeaching(String prof_id) {
      if (profs_teaching == null)
         return false;

      for (int i = 0; i < this.profs_teaching.size(); i++) {
         if (prof_id.equals(this.profs_teaching.get(i))) {
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
         ArrayList<String> thisprefs = availableStudents[i].getPreferences();
         for (int j = 0; j < thisprefs.size(); j++) {
            String course_id = thisprefs.get(j);
            course_count.put(course_id,
                  ((course_count.get(course_id) == null) ? 0 : (course_count.get(course_id) + 1)));
         }
      }
      // Now we hava a hashmap that has courses and the number of students who want to
      // take that course
      // Source:https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
      String maxCourse = course_count.entrySet().stream()
            .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

      this.remClass(maxCourse);
      return maxCourse;
   }

   public void remClass(String class_id) {
      for (int i = 0; i < availableStudents.length; i++) {
         ArrayList<String> thisprefs = availableStudents[i].getPreferences();
         for (int j = 0; j < thisprefs.size(); j++) {
            if (thisprefs.get(j).equals(class_id)) {
               // remove the classs from pref list
               thisprefs.remove(class_id);
            }
         }
      }
   }

   // public void remStud(String class_id, int class_capacity, int c_max) { //c_max
   // is the number of students taking c
   // // removes students taking that class
   // //need to remove min(capacity[i], #of students to take c) students from
   // availablestudents[j]
   // int toRemove = Math.min(class_capacity,c_max);
   // }

}
