import java.util.ArrayList;

public class Schedule {

    // a list of scheduled courses
    public Course[] classCounts;

    // list of students with there prefrences
    private Student[] student_prefs;

    // contains rooms ordered by capacity
    private Room[] rooms;

    // A list of timeslots
    private timeSlots[] times;

    private int total_classes;

    private int total_profs;

    public long runTime;

    private void fillStruc(String prefs, String conts) {

        // student pref 
        extractData e = new extractData(prefs, conts);
        student_prefs = e.storePref();
        //FINE

        // intialize time
        ArrayList<Integer> time_data = e.storeTime();
        times = new timeSlots[time_data.size()];
        //System.out.println("Size of time_data: " + time_data.size());
        for (int i = 0; i < time_data.size(); i++) {
            times[i] = new timeSlots();
            times[i].id = time_data.get(i);
            times[i].fillstudents(student_prefs);
            //System.out.println("Created time slot: " + times[i].id);
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
                //System.out.println("Scheduling in room: " + rooms[i] + ", time: " + times[j].id + ", " + j);

                //ArrayList<String> profsTeaching = times[j].profs_teaching;
                // System.out.println("PROFESSORS TEACHING in " + times[j].id);
                // for(String s : times[j].profs_teaching){
                //     System.out.println(s);
                // }
                // System.out.println("thats all!");

                if (times[j].availableStudents.size() == 0) continue;
                String class_id;
                // System.out.println("Class ID" + class_id);
                
                String prof_id;// = findCourseById(class_id).professor; // class using class_id and find the prof for
                // System.out.println(class_id + " " + i + " " + j + " " + prof_id); // the
                // class
                while (true) {
                    class_id = times[j].mostFameClass();
                    //System.out.println("Most famous class for timeslot: " + times[j].id + " is " + class_id);
                    if (class_id.equals("")) {
                        //System.out.println("skip");
                        prof_id = null;
                        break;
                    }
                    prof_id = findCourseById(class_id).professor;
                    
                    //System.out.println("Trying to schedule course " + class_id + " at time " + times[j].id + " room " + rooms[i]);
                    if (times[j].isTeaching(prof_id)){
                        //Remove class from ALL TIMEslots, condition never met
                        //Retrieve next most famous class
                        //System.out.println("Removing course " + class_id + " from student prefs at time slot " + times[j].id);
                        times[j].remClass(class_id);
                    } else {
                        break;
                    }
                    
                
                    //Remove class from students prefrence list in availableStudents
                }
                times[j].addProf(prof_id);
                int c = getCourseIndex(class_id);
                //System.out.println("Scheduling course " + classCounts[c].course_id + " at time " + times[j].id + " room " + rooms[i]);

                // if(classCounts[Integer.parseInt(class_id)].assigned_room != null) {
                //     classCounts[Integer.parseInt(class_id)].assigned_room = rooms[i];
                //     classCounts[Integer.parseInt(class_id)].assigned_time = times[j].id;
                // }
                
                //NEED TO GET INDEX IN CLASSCOUNTS FROM CLASS_ID
                

                classCounts[c].assigned_time = times[j].id; // how will courses be stored in class count so we have o(1) look up
                classCounts[c].assigned_room = rooms[i];

                // remove students from availableStudents, so that the next famous class is not the same in the next time slot
                // should also remove course from all student preference lists
                k++;
                if (k >= total_classes) return;


                times[j].remStud(class_id);
                for(int x = 0; x < times.length; x++) {
                    times[x].remClass(class_id);
                }
                

                //System.out.println();
            }
        }
    }

    public void enroll() { // check for student time conflict and room capacity, enroll students
        for (int i = 0; i < student_prefs.length; i++) {
            for (int j = 0; j < student_prefs[i].preferences.size(); j++) {

                int idx = getCourseIndex(student_prefs[i].preferences.get(j));

                if(idx == -1){
                    continue;
                }
                if (classCounts[idx].assigned_room.getCapacity() > classCounts[idx].student_ids.size()) { // is there
                                                                                                          // enough room
                    // if there is available room space in the course
                    if (!studentHasConflict(student_prefs[i], classCounts[idx])) {

                        // if the student is not enrolled in another course at the same time
                        // coursePreference.students.add(student_prefs[i]); // CHANGE TO ARRAYLIST
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
                    if (classCounts[i].assigned_time == course.assigned_time) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
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
        for (int i = 0; i < s.classCounts.length; i++) {
           System.out.println(s.classCounts[i].toString());
        }
        long stopTime = System.nanoTime();
        s.runTime = startTime - stopTime;
    }
}
