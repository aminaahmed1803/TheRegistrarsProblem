public class Course {
    private Student[] students;
    private Professor professor;
    private int courseNum;

    public Course(int courseNum) {
        this.courseNum = courseNum;
        students = null;
        professor = null;
    }
}
