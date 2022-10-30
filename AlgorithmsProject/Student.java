import java.util.ArrayList;

public class Student {

    public ArrayList<String> preferences;
    // private String[] preferences;
    public int id;

    public Student(String id, String pref) {
        this.id = Integer.parseInt(id);
        String[] data = pref.split(" ");

        this.preferences = new ArrayList<String>(data.length);
        for (int i = 0; i < data.length; i++) {
            preferences.add(data[i]); // preferences[i] = data[i]
            // System.out.print(data[i]);
        }

    }

    public Student(int id, ArrayList<String> pref) {
        this.id = id;
        this.preferences = new ArrayList<String>(pref.size());
        for (int i = 0; i < pref.size(); i++) {
            this.preferences.add(pref.get(i));
        }

    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String ret = "id: " + this.id + " preferences: ";
        for (int i = 0; i < this.preferences.size(); i++) {
            ret = ret + this.preferences.get(i) + " ";
        }
        return ret;
    }
}