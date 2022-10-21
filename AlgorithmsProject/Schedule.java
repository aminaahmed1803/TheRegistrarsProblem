public class Schedule {

    // a list of scheduled courses
    private Course[] classCounts;

    // list of students with there prefrences
    private Student[] student_prefs;

    // contains rooms ordered by capacity
    private Room[] rooms;

    // a list of timeSlots
    private timeSlots[] times;

    // map of professors to courseID
    // HashMap<string,string> prof_courses;

    private void fillStruc(String prefs, String conts) {

        // student pref
        extractData e = new extractData(prefs, conts);
        student_prefs = e.storePref();

        // intialize time
        String[] time_data = e.storeTime();
        times = new timeSlots[time_data.length];
        for (int i = 0; i < time_data.length; i++) {
            times[i].name = time_data[i];
        }

        // intialize rooms
        Room[] temp = e.storeRoom(); // sort this
        rooms = new Room[temp.length];
        for (int i = 0; i < temp.length; i++) {
            int size = 0;
            int idx = 0;
            for (int j = 0; j < temp.length; i++) {
                if (temp[j].maxCapacity > size) {
                    size = temp[j].maxCapacity;
                    idx = j;
                    temp[j].maxCapacity = -1;
                }
            }
            rooms[i].maxCapacity = size;
            rooms[i].name = temp[idx].name;
        }

        // prof of those classes in classCounts
        String[] prof_data = e.storeProf();
        for (int i = 0; i < classCounts.length; i++) {
            String[] frag = prof_data[i].split("\t");
            classCounts[i] = new Course();
            classCounts[i].courseNum = frag[0];
            classCounts[i].professor = frag[1];
        }

    }

    public Schedule(String prefs, String conts) {
        fillStruc(prefs, conts);
    }

    public void makeSchedule() {

        int k = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < times.length; j++) {
                int class_id = times[j].mostFameClass();
                int prof_id = 0; // get class using class_id and find the prof for the class
                while (times[j].isTeaching(prof_id)) {
                    class_id = times[j].mostFameClass();
                    prof_id = 0;
                    // remove class from students prefrence list in availableStudents
                }
                classCounts[k].assigned_time = times[i].name; // how will courses be stored in class count so we have
                                                              // o(1) look up
                classCounts[k].assignmed_room = rooms[j].name;
                // remove students from availableStudents
            }
        }
    }

    public void enroll() { // check for student time conflict and room capacity, enroll students
        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].getPreferences().length; j++) {
                Course coursePreference = findCourseById(student_prefs[j].getPreferences()[j]);
                if (coursePreference.assigned_room.getCapacity() < coursePreference.students.size()) { // if there is
                                                                                                       // available room
                                                                                                       // space in the
                                                                                                       // course
                    if (studentHasConflict(student_prefs[i], coursePreference) == false) { // if the student is not
                                                                                           // enrolled in another course
                                                                                           // at the same time
                        coursePreference.students.add(student_prefs[i]); // CHANGE TO ARRAYLIST
                    }
                }
            }
        }
    }

    public Course findCourseById(String class_id) {
        for (int i = 0; i < classCounts.length; i++) {
            if (class_id == classCounts[i].courseName) {
                return classCounts[i];
            }
        }
        return null;
    }

    public boolean studentHasConflict(Student student, Course course) {
        for (int i = 0; i < classCounts.length; i++) {
            for (int j = 0; j < classCounts[i].students.length; j++) {
                if (classCounts[i].students[j] == student) {
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
        Schedule e = new Schedule(prefrences, constrains);

    }
}
