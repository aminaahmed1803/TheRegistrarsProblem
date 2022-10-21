import java.util.ArrayList;

public class Student {

    private ArrayList<String> preferences;
    //private String[] preferences;
    private int id;

    public Student(String id, String pref) {
        // System.out.println(id + " " + pref);
        this.id = Integer.parseInt(id);
        String[] data = pref.split(" ");
        this.preferences = new ArrayList<String>(data.length);
        //this.preferences = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            preferences.add(data[i]); //preferences[i] = data[i]
        }
    }

    public ArrayList<String> getPreferences() {
        return this.preferences;
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