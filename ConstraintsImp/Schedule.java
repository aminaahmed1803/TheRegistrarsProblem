/**
 * A class that creates a schedule which accounts
 * for the following constraints
 * overlapping timeSlots
 * students having more than five prefrences
 * professors teaching more or less than two classes
 * scheduling lab_courses
 * scheduling sections
 * scheduling classes in certain buildings
 */

public class Schedule {

   Rooms; // sorted by largest to smallest
   Students;//
   Timeslots; //
   Courses; //

   /**
    * a constructor
    */
   public Schedule() {

   }

   /** */
   public void makeSchedule(){
      int [][] assignmentRT = new int[rooms][timeslots];
      for (int i = 0 ; j< rooms ; j++){
         for (int j = 0 ; j < timeSlots ; j++ ){
            If (room[i] is assigned to overlapping timeslot) continue;
            If (timeSlot[j] has no AvailableStudents or no class) continue;
            do {
               Find the next MostFamousClass
               RemClass from preference list of AvailableStudents
            } while (MostFamousClass can’t be schedule in room[i] OR Prof for MostFamousClass is unavailable) 
            AssignmentRT[i][j] = MostFamousClass 
            //labs if mostFamousClass is lab, schedule lab in next overlapping timeslot
         }
      }
      
   }

   /**
    * 
    * @return
    */
   public void makeSections() {

   }

   /*
    * A fucntion which determines if there is
    * an overlap between two classes
    */
   public void classOverlap() {

   }

   /*
    * A function which determines which timeslots
    * one timeslot has overlaps with
    */
   public boolean overlapTime() { // foqia
      // uses overlapUtil()
      return false;
   }

   /*
    * A function which determines if there is an
    * overlap between two timeslots
    */
   public boolean overlapUtil() { // foqia
      return false;
   }

   /**
    * a function which checks if the room is okay for a course
    * 
    * @return
    */
   public boolean validRoom() { // joon
      return false;
   }

}