import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.*;

public class timeSlots {

   public Student[] availableStudents;

   public ArrayList<String> profs_teaching;

   public Integer id;

   public String time;

   public timeSlots() {
      profs_teaching = new ArrayList<>();
   }

   public void fillstudents(Student[] temp) {
      availableStudents = new Student[temp.length];
      for (int i = 0; i < temp.length; i++) {
         availableStudents[i] = new Student(temp[i].id, temp[i].preferences);
      }

   }

   /*
    * A function which determines which timeslots
    * one timeslot has overlaps with
    */
   public boolean overlapTime() { // foqia
      // uses overlapUtil()
      return false;
   }

   /*
    * A function which determines if there is an
    * overlap between two timeslots
    */
   public boolean overlapUtil() { // foqia
      return false;
   }
}
