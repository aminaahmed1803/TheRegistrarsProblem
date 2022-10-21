public class Course {

    public Student[] students;
    public int professor;
    public int courseNum;
    public String assignmed_room;
    public String assigned_time;

    public Course(int courseNum, int professor) {
        this.courseNum = courseNum;
        this.professor = professor;
    }
}
