import Util.DateValidator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class ServiceMain {
    public static Scanner scanner = new Scanner(System.in);
    public static int tempAge = 0;
    public static String tempCin;
    public static String tempNom;
    public static String defaultEntre;
    public static void displayMenuUser(){
        System.out.print( "\n|===========================================|" +
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
    public static  boolean addNewUser(HashMap<String,User> users){
        System.out.print("Give me your Cin : ");
        tempCin=scanner.nextLine();
        if(users.containsKey(tempCin)) {
            System.out.println("User with CIN " + tempCin + " already exists.");
            return false;
        }else {
            System.out.print("Give me your name : ");
            tempNom=scanner.nextLine();
            do {
                System.out.print("Give me your Age: ");
                String input = scanner.nextLine();
                try {
                    tempAge = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Age, please enter a valid integer.");
                    tempAge = 0;
                }
            } while (tempAge == 0);
            User User = new User(tempCin,tempNom,tempAge);
            users.put(User.getCin(),User);
            System.out.println("User Added with successfully :)");
            return true;
        }

    }
    public static  String displayUserListForSelect(HashMap<String,User> users){
        System.out.println("\n|===============================================|");
        System.out.println("| User CIN List :                               |");
        System.out.println("|-----------------------------------------------|");
        users.forEach((Cin, User)->{
            System.out.println("| User CIN : "+Cin);
            System.out.println("| User Name : "+User.getNom());
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
    public static boolean addNewConsumptionUser(HashMap<String,User> users){
        tempCin=displayUserListForSelect(users);
        if(tempCin.equals("null")) return false;
        boolean isExist = false;
        String tempDateS;
        String tempDateE;
        do{
            System.out.print("Give me start date : ");
            tempDateS = scanner.nextLine();
            System.out.print("Give me end date : ");
            tempDateE = scanner.nextLine();
            List<LocalDate> dateListRange = new ArrayList<>();
            dateListRange=dateListRange(users.get(tempCin).getConsomationsList());
            isExist = DateValidator.isThisDateValid(dateListRange,LocalDate.parse(tempDateS),LocalDate.parse(tempDateE));
            if(isExist)  System.out.println("This Date Already Exist Try Another Date !");
        }while (isExist);
        System.out.print("Give me Carbon : ");
        float tempCar = scanner.nextFloat();
        users.get(tempCin).addConsumption(tempDateS,tempDateE,tempCar);
        return true;
    }
    public  static void updateUser(String cin,HashMap<String,User> users){
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
    public static  void displayRapport(String cin,HashMap<String,User> users){

        System.out.println("\n===================== User Information : \n");
        System.out.println(users.get(cin).toString());
        System.out.println("\n===================== Carbon Consumption Information : \n");
        System.out.println(users.get(cin).displayConsumption());
        System.out.print("\n===================== Carbon Consumption For Days : \n");
        for(Consomation consomation : users.get(cin).getConsomationsList()){
            System.out.println("From Date : "
                    +consomation.getStartDate()+" To Date : "
                    +consomation.getEndDate()+" Carbon Consumption  : "
                    +users.get(cin).calculateDaysConsumption(consomation)+"\n");
        }

        System.out.print("\n===================== Carbon Consumption For Weeks : \n");
        for(Consomation consomation : users.get(cin).getConsomationsList()){
            System.out.println("From Date : "
                    +consomation.getStartDate()+" To Date : "
                    +consomation.getEndDate()+" Carbon Consumption  : "
                    +users.get(cin).calculateWeeklyConsumption(consomation)+"\n");
        }

        System.out.print("\n===================== Carbon Consumption For Months : \n");
        for(Consomation consomation : users.get(cin).getConsomationsList()){
            System.out.println("From Date : "
                    +consomation.getStartDate()+" To Date : "
                    +consomation.getEndDate()+" Carbon Consumption  : "
                    +users.get(cin).calculateMonthlyConsumption(consomation)+"\n");
        }
        System.out.println("\n===============================================\n\n");
    }
    public static List<LocalDate> dateListRange(List<Consomation> listDate){
        List<LocalDate> dateListRange = new ArrayList<>();
        for(Consomation consumption : listDate){
            for(LocalDate dateTest = consumption.getStartDate(); !dateTest.isAfter(consumption.getEndDate()); dateTest=dateTest.plusDays(1)){
                dateListRange.add(dateTest);
            }
        }
        return dateListRange;
    }
}


