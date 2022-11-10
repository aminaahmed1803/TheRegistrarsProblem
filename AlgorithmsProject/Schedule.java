import java.util.ArrayList;

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

        preprocessPref();

        // System.out.println("made sections");

        times = new timeSlots[time_data.size()];
        for (int i = 0; i < time_data.size(); i++) {
            times[i] = new timeSlots();
            times[i].id = time_data.get(i);
            times[i].fillstudents(student_prefs);
            // System.out.println(times[i].name);
        }

    }

    private void preprocessPref() {

        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].preferences.size(); j++) {
                int idx = getCourseIndex(student_prefs[i].preferences.get(j));
                if (idx == -1) {
                    System.out.println("Input Error at Process Pref");
                    System.exit(-1);
                }
                classCounts.get(idx).interested_students++;
            }
        }

        for (int i = 0; i < classCounts.size(); i++) {
            if (classCounts.get(i).interested_students >= 60) {
                // System.out.println("Section Made" + classCounts.get(i).course_id);
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
        System.out.println(total_pref);
    }

    public void makeSchedule() {

        int k = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < times.length; j++) {
                String class_id = times[j].mostFameClass();
                // System.out.println("Class ID" + class_id);
                if (class_id.equals("")) {
                    // System.out.println("skip");
                    continue;
                }
                // System.out.println(class_id);

                Course t = findCourseById(class_id);
                String prof_id = t.professor; // class using class_id and find the prof for
                // System.out.println(class_id + " " + i + " " + j + " " + prof_id); // the
                // class
                while (times[j].isTeaching(prof_id) || !t.isValidRoom(rooms[i].name)) {
                    class_id = times[j].mostFameClass();
                    t = findCourseById(class_id);
                    prof_id = t.professor;

                    // remove class from students prefrence list in availableStudents
                }
                times[j].addProf(prof_id);

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
            System.out.println(s.classCounts.get(i).toString());
        }
    }
}
