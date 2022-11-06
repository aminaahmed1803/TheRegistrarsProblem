import java.util.ArrayList;

public class Course {

   public ArrayList<Integer> student_ids = new ArrayList<>();
   public String professor;
   public String course_id;
   public Room assigned_room;
   public Integer assigned_time;

   public Course() {

   }

   @Override
   public String toString() {
      String ret = course_id + "\t" + assigned_room + "\t" + professor + "\t" + assigned_time + "\t";
      for (int i = 0; i < student_ids.size(); i++) {
         ret = ret + student_ids.get(i) + " ";
      }
      return ret;
   }

}
