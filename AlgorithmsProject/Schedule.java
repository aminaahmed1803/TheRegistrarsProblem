import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {

    // a list of scheduled courses
    public ArrayList<Course> classCounts;

    // list of students with there prefrences
    private Student[] student_prefs;

    // contains rooms ordered by capacity
    private Room[] rooms;

    // A list of timeslots
    private timeSlots[] times;

    private int total_classes;

    private int total_profs;

    private int size;

    //Map contains overlapping timeslots
    private HashMap<Integer, Integer> overlappingTimes;

    //Maps assignment of rooms with timeslots
    private HashMap<String, ArrayList<Integer>> room_timeslots;

    private void fillStruc(String prefs, String conts) {

        // student pref
        extractData e = new extractData(prefs, conts);
        student_prefs = e.storePref();

        // intialize time
        ArrayList<Integer> time_data = e.storeTime();

        // intialize rooms
        Room[] temp = e.storeRoom(); // sort this
        rooms = new Room[temp.length];
        for (int i = 0; i < rooms.length; i++) {
            int big = 0;
            int idx = 0;
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].maxCapacity > big) {
                    big = temp[j].maxCapacity;
                    idx = j;

                }
            }
            temp[idx].maxCapacity = 0;
            rooms[i] = new Room(big, temp[idx].name);
            // System.out.println(rooms[i]);
        }

        // prof, names and rooms of those classes in classCounts
        String[] prof_data = e.storeProf();
        classCounts = new ArrayList<Course>();
        for (int i = 0; i < e.classes; i++) {
            String[] frag = prof_data[i].split("\t");
            Course t = new Course();
            t.course_id = frag[0];
            t.professor = frag[1];
            for (int j = 2; j < frag.length; j++) {
                if (!t.validRooms.contains(frag[j])) {
                    t.validRooms.add(frag[j]);
                }
            }
            classCounts.add(t);
        }
        this.total_classes = e.classes;
        this.total_profs = e.profs;

        //preprocessPref();

        // System.out.println("made sections");

        times = new timeSlots[time_data.size()];
        for (int i = 0; i < time_data.size(); i++) {
            times[i] = new timeSlots();
            times[i].id = time_data.get(i);
            times[i].fillstudents(student_prefs);
            // System.out.println(times[i].name);
        }

        //n is the number of overlapping timeslots we want (always between 0 and times_slots/3)
        int n = (int) (Math.random() * (int)(time_data.size()/3)) + 0;
        overlappingTimes = makeOverlapping(n, time_data.size());
        room_timeslots = new HashMap<String, ArrayList<Integer>>();
      for (Room room : rooms) {
         ArrayList<Integer> timeslots = new ArrayList<Integer>();
         room_timeslots.put(room.name, timeslots);
      }
    }

    /*
    * Function that makes overlapping timeSlots
    * n is the number of overlapping timeslots you want to produce
    * size is the number of total timeslots
    * Returns a mapping of overlapping timeslots
    */
   public HashMap<Integer, Integer> makeOverlapping(int n, int size) {
        HashMap<Integer, Integer> overlappingTimeSlots = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int rand1 = (int) (Math.random() * size) + 1;
            int rand2 = (int) (Math.random() * size) + 1;
            overlappingTimeSlots.put(rand1, rand2);
            overlappingTimeSlots.put(rand2, rand1);
        }
        return overlappingTimeSlots;
    }

/*
 * Determine if a timeslot is being used by a given room
 * Input: room and timeslot 
 * Return true if that room has a class in this timeslot
 * Returns false if that room doesnt have a class in this timeslot
 */
 public boolean isTimeslotUsed(String room, Integer timeslot) {
    ArrayList<Integer> timeslots = room_timeslots.get(room);
    for (int i = 0; i < timeslots.size(); i++) {
       if (timeslots.get(i) == timeslot)
          return true;
    }
    return false;
 }

    private void preprocessPref() {

        int total = 0;
        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].preferences.size(); j++) {
                int idx = getCourseIndex(student_prefs[i].preferences.get(j));
                if (idx == -1) {
                    System.out.println("Input Error at Process Pref");
                    System.exit(-1);
                }
                classCounts.get(idx).interested_students++;
                total++;
            }
        }
        int average = total/this.total_classes;
        this.size = 6*average;
        this.size /= 5;

        //System.out.println("aVERAGE" + average +"\nSIZEE: "+this.size);

        for (int i = 0; i < classCounts.size(); i++) {
            if (classCounts.get(i).interested_students > this.size) {
                 //System.out.println("Section Made" + classCounts.get(i).course_id);
                classCounts.get(i).section = true;
                int students = 0;
                for (int j = 0; j < student_prefs.length; j++) {
                    for (int k = 0; k < student_prefs[j].preferences.size(); k++) {
                        if (student_prefs[j].preferences.get(k).equals(classCounts.get(i).course_id)) {
                            if (students >= classCounts.get(i).interested_students / 2) {
                                String newid = student_prefs[j].preferences.get(k) + "b";
                                student_prefs[j].preferences.set(k, newid);
                            } else {
                                String newid = student_prefs[j].preferences.get(k) + "a";
                                student_prefs[j].preferences.set(k, newid);
                            }
                            students++;
                        }
                    }
                }
            }

        }

        for (int i = 0; i < classCounts.size(); i++) {
            boolean made = classCounts.get(i).course_id.contains("a") || classCounts.get(i).course_id.contains("b");
            if (classCounts.get(i).section && !made) {

                ArrayList<String> validRoom = classCounts.get(i).validRooms;
                String professor = classCounts.get(i).professor;
                String course_id = classCounts.get(i).course_id;
                boolean section = classCounts.get(i).section;

                Course s1 = new Course();
                Course s2 = new Course();

                s1.validRooms = validRoom;
                s1.professor = professor;
                s1.course_id = course_id + "a";
                s1.section = section;

                s2.validRooms = validRoom;
                s2.professor = professor;
                s2.course_id = course_id + "b";
                s2.section = section;

                classCounts.remove(i);
                classCounts.add(s1);
                classCounts.add(s2);

            }

        }

    }

    public Schedule(String prefs, String conts) {
        fillStruc(prefs, conts);
        int total_pref = 0;
        for (Student s : student_prefs) {
            total_pref += s.preferences.size();
        }
        //System.out.println(total_pref);
    }


    /**
    * Returns index in times array that matches with the id needed
    */
   public int findIndex(Integer overlapping_timeslot_id) {
    if (overlapping_timeslot_id == 0) {
       //System.out.println("No overlapping timeslots!");
       return -1;
    }
    for (int i = 0; i < times.length; i++) {
       if (times[i].id == overlapping_timeslot_id) {
          return i;
       }
    }
    return -2; // ERROR
    }

    public void makeSchedule() {

        int k = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < times.length; j++) {
                Integer timeslot_id = times[j].id;
                Integer overlapping_timeslot_id = (overlappingTimes.get(timeslot_id) != null)
                  ? overlappingTimes.get(timeslot_id)
                  : 0;
                //if(overlapping_timeslot_id != 0) System.out.println("Overlapping timeslot for " + timeslot_id + " is" + overlapping_timeslot_id);
                boolean overlappingScheduledInRoom = isTimeslotUsed(rooms[i].name, overlapping_timeslot_id);
                if (overlappingScheduledInRoom) {
                    //System.out.println("Overlapping timeslot is already scheduled in room " + rooms[i].name);
                   continue;
                }
                String class_id = times[j].mostFameClass();
                // System.out.println("Class ID" + class_id);
                if (class_id.equals("")) {
                    // System.out.println("skip");
                    continue;
                }
                // System.out.println(class_id);

                Course t = findCourseById(class_id);
                //System.out.println(class_id);
                String prof_id = t.professor; // class using class_id and find the prof for
                //System.out.println(prof_id);
                // System.out.println(class_id + " " + i + " " + j + " " + prof_id); // the class
                
                int index = findIndex(overlapping_timeslot_id); //find index in times of the overlappint timeslot
                boolean isTeachingOverlapping = false;
                if (index == -1){
                    isTeachingOverlapping = false; //NO OVERLAPPING TIMESLOT
                } else if (index == -2) {
                    System.exit(0); //Something went wrong!
                } else {
                    isTeachingOverlapping = times[index].isTeaching(prof_id); //is this professor teaching in overlapping timeslot
                }
                while (times[j].isTeaching(prof_id) || isTeachingOverlapping || !t.isValidRoom(rooms[i].name)) {
                    class_id = times[j].mostFameClass();
                    t = findCourseById(class_id);
                    prof_id = t.professor;

                    // remove class from students prefrence list in availableStudents
                }
                times[j].addProf(prof_id);

                ArrayList<Integer> ts = room_timeslots.get(rooms[i].name);
                ts.add(times[j].id);
                room_timeslots.replace(rooms[i].name, ts);

                classCounts.get(k).assigned_time = times[j].id;
                classCounts.get(k).assigned_room = rooms[i];
                k++;
                if (k >= total_classes)
                    return;
            }
        }
    }

    public void enroll() { // check for student time conflict and room capacity, enroll students
        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].preferences.size(); j++) {

                // System.out.println(student_prefs[i].preferences.get(j));
                int idx = getCourseIndex(student_prefs[i].preferences.get(j));
                if(idx == -1){
                    continue;
                }

                if (classCounts.get(idx).assigned_room.getCapacity() > classCounts.get(idx).student_ids.size()) { // is
                                                                                                                  // there
                    // enough room

                    // if there is available room space in the course
                    if (!studentHasConflict(student_prefs[i], classCounts.get(idx))) {

                        // if the student is not enrolled in another course at the same time
                        // coursePreference.students.add(student_prefs[i]); // CHANGE TO ARRAYLIST
                        classCounts.get(idx).student_ids.add(student_prefs[i].id);
                    }
                }
            }
        }

    }

    public Course findCourseById(String class_id) {
        for (int i = 0; i < classCounts.size(); i++) {
            //System.out.println(classCounts.get(i));
            if (class_id.equals(classCounts.get(i).course_id)) {
                return classCounts.get(i);
            }
        }
        return null;
    }

    public int getCourseIndex(String class_id) {
        for (int i = 0; i < classCounts.size(); i++) {
            // System.out.println(classCounts.get(i));
            if (class_id.equals(classCounts.get(i).course_id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean studentHasConflict(Student student, Course course) {
        for (int i = 0; i < classCounts.size(); i++) {
            for (int j = 0; j < classCounts.get(i).student_ids.size(); j++) {

                if (classCounts.get(i).student_ids.get(j).equals(student.id)) {
                    if (classCounts.get(i).assigned_time == course.assigned_time) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: <prefences> <constraints>");
            return;
        }
        String prefrences = args[0];
        String constraints = args[1];
        Schedule s = new Schedule(prefrences, constraints);
        s.makeSchedule();
        s.enroll();
        System.out.println("Course\tRoom\tTeacher\tTime\tStudents");
        for (int i = 0; i < s.classCounts.size(); i++) {
            if(s.classCounts.get(i).toString() == null) continue;
            System.out.println(s.classCounts.get(i).toString());
        }
    }
}
