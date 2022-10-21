import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class extractData {

   String contraints;
   String prefrence;
   BufferedReader br;
   int classes;

   public extractData(String prefs, String conts) {

      contraints = conts;
      prefrence = prefs;

   }

   public Student[] storePref() {

      Student[] studentPref;
      try {
         br = new BufferedReader(new FileReader(this.prefrence));
      } catch (IOException e) {
         System.out.println(e);
      }

      try {
         String line = br.readLine();

         String[] frag = line.split("\t");
         // System.out.println(frag[1]);
         int l = Integer.parseInt(frag[1]);
         studentPref = new Student[l];
         int idx = 0;

         while (idx < l) {
            line = br.readLine();
            frag = line.split("\t");
            // System.out.println(frag.length);
            studentPref[idx] = new Student(frag[0], frag[1]);
            idx++;
         }

      } catch (IOException ioe) {
         return null;
      }
      return studentPref;
   }

   // first
   public String[] storeTime() {

      String[] timeSlots;

      try {
         br = new BufferedReader(new FileReader(this.contraints));
      } catch (IOException e) {
         System.out.println(e);
      }

      try {
         String line = br.readLine();
         System.out.println(line);
         String[] frag = line.split("\t");
         int l = Integer.parseInt(frag[1]);
         // System.out.println(l);
         timeSlots = new String[l];
         int idx = 0;

         while (idx < l) {
            line = br.readLine();
            frag = line.split("\t");
            // System.out.println(frag[1]);
            timeSlots[idx] = frag[1];
            idx++;
         }

      } catch (IOException ioe) {
         return null;
      }

      return timeSlots;
   }

   // second
   public Room[] storeRoom() {

      Room[] rooms;
      try {
         String line = br.readLine();

         String[] frag = line.split("\t");
         int l = Integer.parseInt(frag[1]);
         // System.out.println(l);
         rooms = new Room[l];
         int idx = 0;

         while (idx < l) {
            line = br.readLine();
            frag = line.split("\t");
            // System.out.println(frag[0] + " " + frag[1]);
            rooms[idx] = new Room(Integer.parseInt(frag[1]), frag[0]);
            idx++;
         }

      } catch (IOException ioe) {
         return null;
      }
      return rooms;
   }

   // third
   public String[] storeProf() {

      String[] professors;

      try {
         String line = br.readLine();
         String[] frag = line.split("\t");
         int l = Integer.parseInt(frag[1]);
         classes = Integer.parseInt(frag[1]);

         line = br.readLine();
         frag = line.split("\t");
         l = Integer.parseInt(frag[1]);
         // System.out.println(l);
         professors = new String[l];
         int idx = 0;

         while (idx < l) {
            line = br.readLine();
            frag = line.split("\t");
            // System.out.println(frag[0] + " " + frag[1] + "len: " + frag.length);
            professors[idx] = frag[0] + " " + frag[1]; // 0 = courses, 1 = prof id
            idx++;
         }

      } catch (IOException ioe) {
         return null;
      }
      return professors;

   }

   public static void main(String[] args) {
      if (args.length != 2) {
         System.out.println("Usage: <prefences> <constraints>");
         return;
      }
      String prefrences = args[0];
      String constrains = args[1];
      extractData e = new extractData(prefrences, constrains);
      e.storePref();

   }

}
