import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.*;

public class timeSlots {

   public ArrayList<Student> availableStudents;

   public ArrayList<String> profs_teaching;

   public Integer id;

   public timeSlots(int id) {
      this.id = id;
      profs_teaching = new ArrayList<>();
      this.availableStudents = new ArrayList<>();
   }

   public void fillstudents(Student[] temp) {
      for (int i = 0; i < temp.length; i++) {
         Student s = temp[i];
         availableStudents.add(s);
      }
   }

   public String mostFamousClass() {
      return null;
   }

   public boolean isTeaching(String prof) {
      return profs_teaching.contains(prof);
   }

   public void remClass(String class_id) {
   }

   public void remStuds(String class_id) {
   }

   public void addProf(String professor) {
      profs_teaching.add(professor);
   }
}
