import java.time.LocalDate;
import  java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static HashMap<String, User> users = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String defaultEntre;
    public static String tempCin;
    public static int tempAge = 0;
    public static  boolean functionReturn = false;
    public static void main(String[] args) {
        boolean test =false;
        do{
            displayMenuUser();
            String option = scanner.nextLine();
            switch (option){
                case "1" : {
                    functionReturn = addNewUser();
                    if(!functionReturn) break;

                } break;
                case "2" : {
                    functionReturn = addNewConsumptionUser();
                    if(!functionReturn) break;
                } break;
                case "3" : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    else  {
                        System.out.println("\n===============================================");
                        System.out.println(users.get(tempCin).toString());
                        System.out.println(users.get(tempCin).displayConsumption());
                    }
                } break;
                case "4" : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    updateUser(tempCin);
                } break;
                case "5" : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    users.remove(tempCin);
                    System.out.println("User Deleted with successfully :)");
                    break;
                }
                case "6" : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    displayRapport(tempCin);
                    break;
                }
                case "7" : test=true; break;
                default: System.out.println("Invalid option, please try again.");
            }
        }while (!test);
    }
    public static void displayMenuUser(){
        System.out.print( "|===========================================|" +
                        "\n| Select an option please :                 |" +
                        "\n|===========================================|"+
                        "\n| 1 : Add New User                          |" +
                        "\n| 2 : Add New Carbon Consumption            |" +
                        "\n| 3 : Display User Information (By CIN)     |" +
                        "\n| 4 : Update User                           |" +
                        "\n| 5 : Delete User                           |" +
                        "\n| 6 : Consumption Analysis                  |" +
                        "\n| 7 : Close                                 |" +
                        "\n|===========================================|" +
                        "\nEntre your option : ");
    }
    public static  boolean addNewUser(){
        System.out.print("Give me your Cin : ");
        tempCin=scanner.nextLine();
        if(users.containsKey(tempCin)) {
            System.out.println("User with CIN " + tempCin + " already exists.");
            return false;
        }else {
            System.out.print("Give me your name : ");
            String tempNom=scanner.nextLine();
            do{
                System.out.print("Give me your Age : ");
                tempAge=scanner.nextInt();
            }while (tempAge == 0);
            User User = new User(tempCin,tempNom,tempAge);
            users.put(User.getCin(),User);
            System.out.println("User Added with successfully :)");
            return true;
        }

    }
    public static  String displayUserListForSelect(){
        System.out.println("\n|===============================================|");
        System.out.println("| User CIN List :                               |");
        System.out.println("|-----------------------------------------------|");
        users.forEach((Cin, User)->{
        System.out.println("| User CIN : "+Cin);
            System.out.println("| User CIN : "+Cin);
            System.out.println("| User Age : "+ User.getAge());
            System.out.println("|-----------------------------------------------|");
        });
        System.out.println("|===============================================|");
        System.out.print("Select CIN of User To Add Carbon Consumption : ");
        tempCin = scanner.nextLine();
        if(!users.containsKey(tempCin)) {

            return "null";
        }
        return tempCin;
    }
    public static boolean addNewConsumptionUser(){
        tempCin=displayUserListForSelect();
        if(tempCin.equals("null")) return false;
        System.out.print("Give me start date : ");
        String tempDateS = scanner.nextLine();
        for (Consomation consumptions : users.get(tempCin).getConsomationsList()) {
            if (consumptions.getEndDate().equals(LocalDate.parse(tempDateS)) || consumptions.getEndDate().isAfter(LocalDate.parse(tempDateS))) {
                System.out.println("You can't add this date because it's already added.");
                return false;
            }
        }
        System.out.print("Give me end date : ");
        String tempDateE = scanner.nextLine();
        if(LocalDate.parse(tempDateS).equals(LocalDate.parse(tempDateE)) || LocalDate.parse(tempDateS).isAfter(LocalDate.parse(tempDateE))){
            System.out.println("End date must be greater than start date.");
            return false;
        }
        System.out.print("Give me Carbon : ");
        float tempCar = scanner.nextFloat();
        users.get(tempCin).addConsumption(tempDateS,tempDateE,tempCar);
        return true;
    }
    public  static void updateUser(String cin){
        User tempUser = users.get(cin);
        System.out.print(" |=======================================|" +
                        "\n| Select an option please :             |" +
                        "\n|=======================================|"+
                        "\n| 1 : Update User Information           |" +
                        "\n| 2 : Update Carbon Consumption         |" +
                        "\n| 5 : Close                             |" +
                        "\n|=======================================|" +
                        "\nEntre your option : ");
        String selectOptionUpdate = scanner.nextLine();
        switch (selectOptionUpdate){
            case "1" :{
                System.out.print("Please give me new user name : ");
                tempUser.setNom(scanner.nextLine());
                System.out.print("Please give me new user Age : ");
                tempUser.setAge(scanner.nextInt());
                System.out.print("User updated with success :)\n");
                break;
            }
            case "2" : {
                System.out.print("============= Carbon Consumption List ================ ");
                System.out.println(tempUser.displayConsumption());
                System.out.print("=============Select Carbon Consumption to update using id (1,2...) : ");
                int tempIdCarbon = scanner.nextInt();
                defaultEntre = scanner.nextLine();
                boolean testExists = false;
                for (Consomation consumptions : tempUser.getConsomationsList()) {
                    if (consumptions.getId() == tempIdCarbon) {
                        System.out.print("Give me new start date : ");
                        consumptions.setStartDate(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Give me new end date : ");
                        consumptions.setEndDate(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Give me new Carbon : ");
                        consumptions.setCarbon(scanner.nextFloat());
                        System.out.print("User Carbon Consumption updated with success :)\n\n");
                        testExists = true;
                        break;
                    }
                }
                if(!testExists)
                    System.out.println("Carbon Consumption with id " +tempIdCarbon + " not exists.\n\n");
                break;
            }
            case "3" : break;
            default: System.out.println("Invalid option, please try again.");
        }
    }
    public static  void displayRapport(String cin){
        System.out.println("\n===================== User Information : \n");
        System.out.println(users.get(cin).toString());
        System.out.println("\n===================== Carbon Consumption Information : \n");
        System.out.println(users.get(cin).displayConsumption());
        System.out.print("\n===================== Carbon Consumption For Days : ");
        System.out.println(users.get(cin).calculateDaysConsumption());
        System.out.print("\n===================== Carbon Consumption For Weeks : ");
        System.out.println(users.get(cin).calculateWeeklyConsumption());
        System.out.print("\n===================== Carbon Consumption For Months : ");
        System.out.println(+users.get(cin).calculateMonthlyConsumption());
        System.out.println("\n===============================================\n\n");
    }
}