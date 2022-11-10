import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class extractStudents {

    public static void main(String[] args) {
        if (args.length != 1) {
           System.out.println("Usage: <prefences>");
           return;
        }
        int students = 0;
        String prefrences = args[0];
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(prefrences));
            String line = br.readLine();
   
            String[] frag = line.split("\t");
            // System.out.println(frag[1]);
            students = Integer.parseInt(frag[1]);

         } catch (IOException e) {
            System.out.println(e);
         }

         System.out.println("Maximum value:" + (students * 4));
  
     }

    
}
