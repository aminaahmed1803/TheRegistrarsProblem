import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.*;

public class extractData {

   String contraints;
   String prefrence;
   BufferedReader br;
   int classes;
   int profs;
   private String roomsTemp;

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
   public ArrayList<Integer> storeTime() {

      ArrayList<Integer> timeSlots;

      try {
         br = new BufferedReader(new FileReader(this.contraints));
      } catch (IOException e) {
         System.out.println(e);
      }

      try {
         String line = br.readLine();
         // System.out.println(line);
         String[] frag = line.split("\t");
         int l = Integer.parseInt(frag[1]); // Store number of class times
         timeSlots = new ArrayList<Integer>(l);
         int idx = 0;

         line = br.readLine();
         if (line.contains("Rooms")) {
            frag = line.split("\t");
            this.roomsTemp = frag[1];
            for (int i = 1; i < l + 1; i++) {
               timeSlots.add(i);
            }
         } else {
            System.out.println(line);
            frag = line.split("\t");
            timeSlots.set(idx, Integer.parseInt(frag[0]));
            idx++;
            while (idx < l) {
               line = br.readLine();
               frag = line.split("\t");
               timeSlots.set(idx, Integer.parseInt(frag[0]));
               System.out.println(timeSlots.get(idx));
               idx++;
            }
         }
         // while (idx < l) {
         // line = br.readLine();
         // if(line.contains("Rooms")){
         // for(int i = 1; i < l+1; i++){
         // timeSlots.add(i);
         // }
         // break;
         // }
         // frag = line.split("\t");
         // // System.out.println(frag[1]);
         // timeSlots.set(idx, Integer.parseInt(frag[0]));
         // idx++;
         // }

      } catch (IOException ioe) {
         return null;
      }

      // System.out.println("Done time slots");
      return timeSlots;
   }

   // second
   public Room[] storeRoom() {

      Room[] rooms;

      try {
         String line;
         int l;
         if (this.roomsTemp != null) {
            l = Integer.parseInt(this.roomsTemp);
         } else {
            line = br.readLine();
            String[] frag = line.split("\t");
            l = Integer.parseInt(frag[1]);
         }
         rooms = new Room[l];
         int idx = 0;

         while (idx < l) {
            line = br.readLine();
            String[] frag = line.split("\t");
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
      // System.out.println("Started");
      try {
         String line = br.readLine();
         String[] frag = line.split("\t");
         int l = Integer.parseInt(frag[1]);
         classes = Integer.parseInt(frag[1]);
         professors = new String[l];

         line = br.readLine();
         frag = line.split("\t");
         l = Integer.parseInt(frag[1]);
         profs = l;
         int idx = 0;

         while (idx < classes) {
            line = br.readLine();
            // frag = line.split("\t");
            // System.out.println(frag[0] + " " + frag[1] + "len: " + frag.length);
            professors[idx] = line;// frag[0] + " " + frag[1]; // 0 = courses, 1 = prof id
            idx++;
         }

      } catch (IOException ioe) {
         return null;
      }

      // System.out.println("done prof");
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
