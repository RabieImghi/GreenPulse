import java.util.ArrayList;
import  java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String defultEntre;
        String tempCin;
        int tempAge = 0;
        boolean test =false;
        do{
            System.out.print(
                    "=========================================" +
                    "\n| Select an option please :" +
                    "\n| 1 : Add New Person" +
                    "\n| 2 : Add New Carbon Consumption" +
                    "\n| 3 : Display User Information (By CIN)" +
                    "\n| 4 : Close" +
                    "\n=========================================" +
                    "\nEntre your option : ");
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    defultEntre = scanner.nextLine();
                    System.out.print("Give me your Cin : ");
                    tempCin=scanner.nextLine();
                    if(users.containsKey(tempCin)) {
                        System.out.println("User with CIN " + tempCin + " already exists.");
                        break;
                    }
                    System.out.print("Give me your name : ");
                    String tempNom=scanner.nextLine();
                    do{
                        System.out.print("Give me your Age : ");
                        tempAge=scanner.nextInt();
                    }while (tempAge == 0);
                    User User = new User(tempCin,tempNom,tempAge);
                    users.put(User.getCin(),User);
                    System.out.println("User Added with successfully :)");
                } break;
                case 2 : {
                    defultEntre = scanner.nextLine();
                    System.out.print("Give me CIN of User : ");
                    tempCin = scanner.nextLine();
                    System.out.print("Give me start date : ");
                    String tempDateS = scanner.nextLine();
                    System.out.print("Give me end date : ");
                    String tempDateE = scanner.nextLine();
                    System.out.print("Give me Carbon : ");
                    float tempCar = scanner.nextFloat();
                    users.get(tempCin).addConsomation(tempDateS,tempDateE,tempCar);
                } break;
                case 3 : {
                    defultEntre = scanner.nextLine();
                    System.out.println("\n===============================================");
                    System.out.print("User CIN List : \n");
                    users.forEach((Cin, User)->{
                        System.out.print("\nUser CIN : "+Cin);
                        System.out.print(" / User Name : "+User.getNom()
                                        +" / User Age : "+ User.getAge());
                    });
                    System.out.println("\n===============================================\n");

                    System.out.print("Give me CIN of User : ");
                    tempCin = scanner.nextLine();
                    System.out.println("\n===============================================");
                    System.out.println(users.get(tempCin).toString());
                    System.out.println(users.get(tempCin).consomationsList);


                } break;
                case 4 : test=true;
            }
        }while (!test);


        
    }
}