import java.util.ArrayList;

public class Course {

    public ArrayList<Integer> student_ids;
    public ArrayList<String> validRooms;
    public String professor;
    public String course_id;
    public Room assigned_room;
    public Integer assigned_time;
    public boolean section;
    public Integer interested_students;

    public Course() {
        student_ids = new ArrayList<>();
        validRooms = new ArrayList<>();
        section = false;
        interested_students = 0;
        assigned_room = new Room();
        assigned_time = 0;

    }

    @Override
    public String toString() {
        if(assigned_time == 0){
            return null;
        }
        String ret = course_id + "\t" + assigned_room + "\t" + professor + "\t" + assigned_time + "\t";
        for (int i = 0; i < student_ids.size(); i++) {
            ret = ret + student_ids.get(i) + " ";
        }
        return ret;
    }

    public boolean isValidRoom(String room) {
        // System.out.println("valid room size" + validRooms.size() + "");

        if (validRooms.contains(room) || validRooms.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
