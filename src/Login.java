
import java.util.Scanner;

public class Login {
    static Scanner inputString = new Scanner(System.in);
    public static void logmasuk() {
        String username, password;
        while (true) {
            System.out.print("Input your username : ");
            username =  inputString.nextLine();
            System.out.print("Input your password : ");
            password = inputString.nextLine();

            if (username.equals("admin")&& password.equals("admin")) {
                System.out.println("You have logged in.");
                break;
            } else {
                System.out.println("Incorrect username or password");
                continue;
            }
        }
        System.out.println("------------------------------");

    }
}
