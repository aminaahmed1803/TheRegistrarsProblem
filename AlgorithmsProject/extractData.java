import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class extractData {

   Student[] studentPref;
   String[] timeSlots;
   String[] rooms;
   String[] professors;
   String contraints;
   String prefrence;
   BufferedReader br;

   public extractData(String prefs, String conts) {

      contraints = conts;
      prefrence = prefs;

      try {
         br = new BufferedReader(new FileReader(prefrence));
      } catch (IOException e) {
         System.out.println(e);
      }

      try {
         br = new BufferedReader(new FileReader(contraints));
      } catch (IOException e) {
         System.out.println(e);
      }

   }

   public void storePref() {

      int l = 1;
      while (true) {
         try {
            String line = br.readLine();
            if (l == 1) {
               l = Integer.parseInt(line);
               studentPref = new Student[l];
            } else {
               String[] frag = line.split(" ");
            }
         } catch (IOException ioe) {
            return;
         }
      }

   }

   public void storeContraints() {

   }

}
