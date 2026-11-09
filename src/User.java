import java.util.Scanner;

/** this class inputs user's name and gender
 * name of user stored in name variable
 * gender of user stored in gender variable
 */
public class User {
    String name;
    String gender;


    String inputName(){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter name: ");
        name= sc.nextLine();
        return name;
    }

    void inputGender(){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter gender: ");
        gender= sc.nextLine();
    }
}
