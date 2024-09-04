
import  java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static HashMap<String, User> users = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String tempCin;
    public static boolean functionReturn = false;

    public static void main(String[] args) {

        boolean test = false;
        do {
            ServiceMain.displayMenuUser();
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    functionReturn = ServiceMain.addNewUser(users);
                    if (!functionReturn) break;

                }
                break;
                case "2": {
                    functionReturn = ServiceMain.addNewConsumptionUser(users);
                    if (!functionReturn) break;
                }
                break;
                case "3": {
                    tempCin = ServiceMain.displayUserListForSelect(users);
                    if (tempCin.equals("null")) break;
                    else {
                        System.out.println("\n===============================================");
                        System.out.println(users.get(tempCin).toString());
                        System.out.println(users.get(tempCin).displayConsumption());
                    }
                }
                break;
                case "4": {
                    tempCin = ServiceMain.displayUserListForSelect(users);
                    if (tempCin.equals("null")) break;
                    ServiceMain.updateUser(tempCin,users);
                }
                break;
                case "5": {
                    tempCin = ServiceMain.displayUserListForSelect(users);
                    if (tempCin.equals("null")) break;
                    users.remove(tempCin);
                    System.out.println("User Deleted with successfully :)");
                    break;
                }
                case "6": {
                    tempCin = ServiceMain.displayUserListForSelect(users);
                    if (tempCin.equals("null")) break;
                    ServiceMain.displayRapport(tempCin,users);
                    break;
                }
                case "7":
                    test = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (!test);
    }
}
