public class Schedule {

    // a list of scheduled courses
    public Course[] classCounts;

    // list of students with there prefrences
    private Student[] student_prefs;

    // contains rooms ordered by capacity
    private Room[] rooms;

    // a list of timeSlots
    private timeSlots[] times;

    private int total_classes;

    private int total_profs;

    // map of professors to courseID
    // HashMap<string,string> prof_courses;

    private void fillStruc(String prefs, String conts) {

        // student pref
        extractData e = new extractData(prefs, conts);
        student_prefs = e.storePref();
        // System.out.println(student_prefs[0]);

        // intialize time
        String[] time_data = e.storeTime();
        times = new timeSlots[time_data.length];
        for (int i = 0; i < time_data.length; i++) {
            times[i] = new timeSlots();
            times[i].name = time_data[i];
            times[i].fillstudents(student_prefs);
            // System.out.println(times[i].name);
        }

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

        // prof of those classes in classCounts
        String[] prof_data = e.storeProf();
        classCounts = new Course[e.classes];
        for (int i = 0; i < classCounts.length; i++) {
            String[] frag = prof_data[i].split(" ");
            classCounts[i] = new Course();
            classCounts[i].course_id = frag[0];
            classCounts[i].professor = frag[1];
        }
        this.total_classes = e.classes;
        this.total_profs = e.profs;
    }

    public Schedule(String prefs, String conts) {
        fillStruc(prefs, conts);
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
                String prof_id = findCourseById(class_id).professor; // get class using class_id and find the prof for
                // System.out.println(class_id + " " + i + " " + j + " " + prof_id); // the
                // class
                while (times[j].isTeaching(prof_id)) {
                    class_id = times[j].mostFameClass();
                    prof_id = findCourseById(class_id).professor;
                    // remove class from students prefrence list in availableStudents
                }
                times[j].addProf(prof_id);
                classCounts[k].assigned_time = times[j].name; // how will courses be stored in class count so we have
                                                              // o(1) look up
                classCounts[k].assigned_room = rooms[i];
                // remove students from availableStudents
                k++;
                if (k >= total_classes)
                    return;
            }
        }
    }

    public void enroll() { // check for student time conflict and room capacity, enroll students
        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].preferences.size(); j++) {
                int idx = getCourseIndex(student_prefs[i].preferences.get(j));
                Course coursePreference = classCounts[idx];
                if (coursePreference.assigned_room.getCapacity() > coursePreference.student_ids.size()) {

                    // if there is available room space in the course
                    if (studentHasConflict(student_prefs[i], coursePreference) == false) {

                        // if the student is not enrolled in another course at the same time
                        classCounts[idx].student_ids.add(student_prefs[i].id);
                    }
                }
            }
        }
    }

    public Course findCourseById(String class_id) {
        for (int i = 0; i < classCounts.length; i++) {
            if (class_id.equals(classCounts[i].course_id)) {
                return classCounts[i];
            }
        }
        return null;
    }

    public int getCourseIndex(String class_id) {
        for (int i = 0; i < classCounts.length; i++) {
            if (class_id.equals(classCounts[i].course_id)) {
                return i;
            }
        }
        return -1;
    }

    public boolean studentHasConflict(Student student, Course course) {
        for (int i = 0; i < classCounts.length; i++) {
            for (int j = 0; j < classCounts[i].student_ids.size(); j++) {
                if (classCounts[i].student_ids.get(j).equals(student.id)) {
                    if (classCounts[i].assigned_time != course.assigned_time) {
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
        String constrains = args[1];
        Schedule s = new Schedule(prefrences, constrains);
        s.makeSchedule();
        s.enroll();
        System.out.println("Course          Room            Teacher         Time            Students");
        for (int i = 0; i < s.classCounts.length; i++) {
            System.out.println(s.classCounts[i].toString());
        }
    }
}
