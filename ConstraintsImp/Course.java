import java.util.ArrayList;

public class Course {

   public ArrayList<Integer> student_ids;
   public String professor;
   public String course_id;
   public Room assigned_room;
   public Integer assigned_time;
   public ArrayList<String> validRooms;

   public boolean section;
   public Integer interested_students;
   public Room room2;
   public Integer time2;
   public ArrayList<Integer> student2;

   public Course() {
      validRooms = new ArrayList<>();
      student_ids = new ArrayList<>();
      interested_students = 0;
      section = false;
      assigned_room = null;
      assigned_time = 0;
   }

   public void makeSection() {
      room2 = null;
      time2 = 0;
      student2 = new ArrayList<>();
   }

   public boolean isValidRoom(String room) {
      if (validRooms.contains(room)) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      String ret = course_id + "\t" + assigned_room.name + "\t" + professor + "\t" + assigned_time + "\t";
      for (int i = 0; i < student_ids.size(); i++) {
         ret = ret + student_ids.get(i) + " ";
      }
      if (section) {
         String ret2 = "\n" + course_id + "\t" + room2.name + "\t" + professor + "\t" + time2 + "\t";
         for (int i = 0; i < student2.size(); i++) {
            ret2 = ret2 + student2.get(i) + " ";
         }
         return ret + ret2;
      } else
         return ret;
   }

}
