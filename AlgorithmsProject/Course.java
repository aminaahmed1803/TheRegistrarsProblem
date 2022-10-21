import java.util.ArrayList;

public class Course {

    public ArrayList<Integer> student_ids = new ArrayList<>();
    public String professor;
    public String course_id;
    public Room assigned_room;
    public String assigned_time;

    public Course() {

    }

    @Override
    public String toString() {
        String ret = course_id + "       " + assigned_room + "      " + professor + "      " + assigned_time + "      ";
        for (int i = 0; i < student_ids.size(); i++) {
            ret = ret + " " + student_ids.get(i);
        }
        return ret;
    }
}
