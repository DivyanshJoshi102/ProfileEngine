import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PastProfileListViewer {

     void pastProfileList(){

         HashMap<Integer,String> profileMap = new HashMap<>();

         File folder = new File("./data/UserProfile/");
         File[] files = folder.listFiles();
         if (files != null) {
             int serialNumber = 0;
             System.out.printf("%-5s %-50s %-15s %s%n", "SNo.","Name", "Date", "Time");
             for (File file : files) {
                 if (file.isFile() && file.getName().endsWith(".txt")) {
                     serialNumber++;
                     System.out.printf("%-5s %-50s %-15s %s%n",
                             serialNumber,
                             file.getName().substring(file.getName().lastIndexOf('_') + 1, file.getName().indexOf('.')),
                             file.getName().substring(0, file.getName().indexOf('_')),
                             file.getName().substring(file.getName().indexOf('_') + 1, file.getName().lastIndexOf('-')) + ":" + file.getName().substring(file.getName().lastIndexOf('-') + 1, file.getName().lastIndexOf('_')));
                     profileMap.put(serialNumber, file.getName());
                 }
             }
             int profileSNo = inputProfileSerialNumber();
             selectSerialNumberOfProfile(profileMap,profileSNo);
         }
    }

    void selectSerialNumberOfProfile(HashMap<Integer,String> profileMap , int choice){
         if(profileMap.containsKey(choice)){
             try{
                 File file = new File("./data/UserProfile/"+profileMap.get(choice));
                 Scanner scanner = new Scanner(file);
                 while(scanner.hasNextLine()){
                     System.out.println(scanner.nextLine());
                 }
             }
             catch(Exception e){
                 System.out.println("[PastProfileListViewer] error");
                 System.out.println(Arrays.toString(e.getStackTrace()));
             }
         }
         else{
             System.out.println("enter valid serial number");
         }
    }

    int inputProfileSerialNumber(){
         Scanner scanner = new Scanner(System.in);
        System.out.println("enter serial number of profile you want to view: ");
        int profileSerialNumber = scanner.nextInt();
        return profileSerialNumber;
    }
}