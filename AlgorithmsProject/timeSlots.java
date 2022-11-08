
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
      for (int i = 0; i < temp.length; i++) {
         availableStudents.add(new Student(temp[i].id, temp[i].preferences));
         //availableStudents.set(i, new Student(temp[i].id, temp[i].preferences));
      }

   }

   public boolean isTeaching(String prof_id) {
      if (profs_teaching == null) return false;

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
      //System.out.println("Added professor " + prof_id + " to time slot " + id);
      //System.out.println("Number of profs teaching in time slot " + id + ": " + profs_teaching.size());
      for(int i = 0; i < profs_teaching.size(); i++) {
         //System.out.print("Profs teaching: " + profs_teaching.get(i) + ", ");
      }
      //System.out.println();
   }

   public String mostFameClass() {
      HashMap<String, Integer> course_count = new HashMap<String, Integer>();
      for (int i = 0; i < availableStudents.size(); i++) {
         ArrayList<String> thisprefs = availableStudents.get(i).preferences;
         for (int j = 0; j < thisprefs.size(); j++) {
            String course_id = thisprefs.get(j);
            course_count.put(course_id, ((course_count.get(course_id) == null) ? 0 : (course_count.get(course_id) + 1)));
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
      //System.out.println("Counts: "+ course_count.get(maxCourse));
      return maxCourse;
   }

   //Remove scheduled class from student preference list
   //Affects enroll?
   public void remClass(String class_id) {

      //System.out.println("Size before removal: " + availableStudents.size());

      for (int i = 0; i < availableStudents.size(); i++) {
         ArrayList<String> thisprefs = availableStudents.get(i).preferences;
         //System.out.print("Course: " + class_id + ", Student: " + availableStudents.get(i).id + ",");
         for (int j = 0; j < thisprefs.size(); j++) {
            //System.out.print(" " + thisprefs.get(j));
            if (thisprefs.get(j).equals(class_id)) {
               // remove the classs from pref list
               //System.out.println("Removing course " + class_id + " from student " + availableStudents.get(i).id + " preferences");
               availableStudents.get(i).preferences.remove(class_id);
            }
         }
         //System.out.println();
      }

      //System.out.println("Size after removal: " + availableStudents.size());
      
   }

   //Remove student from available students at this time
   public void remStud(String class_id){
      ArrayList<Student> toRemove = new ArrayList<Student>();
      //System.out.println("Size before removal: " + availableStudents.size());
      for(Student student : availableStudents){
         ArrayList<String>thisprefs = student.preferences;
         for(int j = 0; j < thisprefs.size(); j++){
            if (thisprefs.get(j).equals(class_id)) {
               //remove student from list of available students
               toRemove.add(student);
            }
         }
      }

      for(Student student : toRemove){
        //System.out.println("Removing student " + student.id + " availability at " + id);
         availableStudents.remove(student);
      }
      //System.out.println("Size after removal: " + availableStudents.size());

   }


}
