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

    private void fillCounts() {

        // classes in classCounts
        // prof of those classes in classCounts

    }

    public Schedule() {

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

    public void enroll() {

    }
}
