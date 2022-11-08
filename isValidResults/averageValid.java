import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class averageValid {

    public static void main(String[] args) {
        ArrayList<Float> prefValues  = new ArrayList<Float>();
        ArrayList<Integer> maxValues = new ArrayList<Integer>();
         BufferedReader br;
         for(int i = 1; i <= 100; i++){
            ArrayList<Integer> pref = new ArrayList<Integer>();
            ArrayList<Integer> max = new ArrayList<Integer>();
            for(int j = 1; j <= 10; j++){
                try {
                    String filename = "is_valid_" + Integer.toString(i) + "_" + Integer.toString(j) + ".txt";
                    br = new BufferedReader(new FileReader(filename));
                    br.readLine();
                    //Want to get number from "Student preferences value: 2163"
                    String line1 = br.readLine();
                    String prefValue = line1.substring(27);
                    pref.add(Integer.parseInt(prefValue));
                    String line2 = br.readLine();
                    //Want to get number from "Maximum value: 2228"
                    String maxValue = line2.substring(15);
                    max.add(Integer.parseInt(maxValue));
                 } catch (IOException e) {
                    System.out.println(e);
                 }
            }
            //Average the 10 prefs and maxValues
            Integer sum = 0;
            for(Integer p : pref)
                sum += p;
            Float average = (float) sum / pref.size();
            prefValues.add(average);
            maxValues.add(max.get(0));
        }

        for(int i = 1; i <= 100; i++){
            System.out.println(i + ") Pref value: " + prefValues.get(i-1) + " Max value: " + maxValues.get(i-1));
        }
         
    }
    
}
