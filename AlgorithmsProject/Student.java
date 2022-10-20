public class Student {

    private String[] preferences;
    private int id;

    public Student(String id, String pref) {
        // System.out.println(id + " " + pref);
        this.id = Integer.parseInt(id);
        String[] data = pref.split(" ");
        this.preferences = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            preferences[i] = data[i];
        }
    }

    public String[] getPreferences() {
        return this.preferences;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " preferences: " + this.preferences;

    }
}