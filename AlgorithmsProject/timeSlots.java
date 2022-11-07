
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.*;

public class timeSlots {

   public ArrayList<Student> availableStudents;

   public ArrayList<String> profs_teaching;

   public Integer id;

   public timeSlots() {
      profs_teaching = new ArrayList<>();
      
   }

   public void fillstudents(Student[] temp) {
      availableStudents = new ArrayList<Student>(temp.length);
      //availableStudents = new Student[temp.length];
      if(temp.length == 0) return;
      // System.out.println(temp.length);
      // System.out.println(temp[0].id + temp[0].preferences);
      // System.out.println(temp[1111].id + temp[1111].preferences);
      for (int i = 0; i < temp.length; i++) {
         availableStudents.add(new Student(temp[i].id, temp[i].preferences));
         //availableStudents.set(i, new Student(temp[i].id, temp[i].preferences));
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
      for (int i = 0; i < availableStudents.size(); i++) {
         ArrayList<String> thisprefs = availableStudents.get(i).preferences;
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
      return maxCourse;
   }

   public void remClass(String class_id) {
      for (int i = 0; i < availableStudents.size(); i++) {
         ArrayList<String> thisprefs = availableStudents.get(i).preferences;
         for (int j = 0; j < thisprefs.size(); j++) {
            if (thisprefs.get(j).equals(class_id)) {
               // remove the classs from pref list
               availableStudents.get(i).preferences.remove(class_id);
            }
         }
      }

      
   }

   public void remStud(String class_id){
      ArrayList<Student> toRemove = new ArrayList<Student>();
      for( Student student : availableStudents){
         ArrayList<String>thisprefs = student.preferences;
         for(int j = 0; j < thisprefs.size(); j++){
            if (thisprefs.get(j).equals(class_id)) {
               //remove student from list of available students
               toRemove.add(student);
            }
         }
      }

      for(Student student : toRemove){
         availableStudents.remove(student);
      }

   }


}
