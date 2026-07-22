import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PastProfileListViewer {
    HashMap<Integer,String> profileMap = new HashMap<>();

     void pastProfileList(){

         File folder = new File("./data/UserProfile/");
         File[] files = folder.listFiles();
         if (files != null) {
             int serialNumber = 0;
             System.out.printf("%-5s %-50s %-15s %s%n", "SNo.","Name", "Date", "Time");
             for (File file : files) {
                 if (file.isFile() && file.getName().endsWith(".txt")) {
                     serialNumber++;
                      /*
                         Parse the filename and print the profile's serial number, name, date, and time.
                      */
                     System.out.printf("%-5s %-50s %-15s %s%n",
                             serialNumber,
                             file.getName().substring(file.getName().lastIndexOf('_') + 1, file.getName().indexOf('.')),
                             file.getName().substring(0, file.getName().indexOf('_')),
                             file.getName().substring(file.getName().indexOf('_') + 1, file.getName().lastIndexOf('-')) + ":" + file.getName().substring(file.getName().lastIndexOf('-') + 1, file.getName().lastIndexOf('_')));
                     profileMap.put(serialNumber, file.getName());
                 }
             }
             selectSerialNumberOfProfile();
         }
    }

    void selectSerialNumberOfProfile(){
         int choice = inputProfileSerialNumber();
         if(profileMap.containsKey(choice)){
             try{
                 File file = new File("./data/UserProfile/"+profileMap.get(choice));
                 Scanner scanner = new Scanner(file);
                 while(scanner.hasNextLine()){
                     System.out.println(scanner.nextLine());
                 }
             }
             catch(Exception e){
                 System.out.println("[PastProfileListViewer] error: "+ Arrays.toString(e.getStackTrace()));
             }
         }
         else{
             System.out.println("Did not enter valid serial number");
         }
    }

    int inputProfileSerialNumber(){
         Scanner scanner = new Scanner(System.in);
        System.out.println("Enter serial number of profile you want to view: ");
        return  scanner.nextInt();
    }
}