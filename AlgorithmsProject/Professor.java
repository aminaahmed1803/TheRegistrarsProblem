public class Professor {

    private String courses;
    private int id;

    public Professor(String id, String crs) {
        this.courses = crs;
        System.out.println(id);
        this.id = Integer.parseInt(id);
    }
}
