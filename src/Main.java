import java.time.LocalDate;
import  java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static HashMap<String, User> users = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String defultEntre;
    public static String tempCin;
    public static int tempAge = 0;
    public static  boolean functionReturn = false;
    public static void main(String[] args) {
        boolean test =false;
        do{
            int option = displayMenuUser();
            defultEntre = scanner.nextLine();
            switch (option){
                case 1 : {
                    functionReturn = addNewUser();
                    if(!functionReturn) break;
                };
                case 2 : {
                    functionReturn = addNewConsumptionUser();
                    if(!functionReturn) break;
                } ;
                case 3 : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    else {
                        System.out.println("\n===============================================");
                        System.out.println(users.get(tempCin).toString());
                        System.out.println(users.get(tempCin).displayConumation());
                    }
                } break;
                case 4 : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    updateUser(tempCin);
                } break;
                case 5 : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    users.remove(tempCin);
                    System.out.println("User Deleted with successfully :)");
                    break;
                }
                case 6 : {
                    tempCin = displayUserListForSelect();
                    if(tempCin.equals("null")) break;
                    displayRapport(tempCin);
                    break;
                }
                case 7 : test=true; break;
            }
        }while (!test);
    }
    public static int displayMenuUser(){
        System.out.print( "=========================================" +
                        "\n| Select an option please :" +
                        "\n| 1 : Add New User" +
                        "\n| 2 : Add New Carbon Consumption" +
                        "\n| 3 : Display User Information (By CIN)" +
                        "\n| 4 : Update User" +
                        "\n| 5 : Delete User" +
                        "\n| 6 : Consumption Analysis" +
                        "\n| 7 : Close" +
                        "\n=========================================" +
                        "\nEntre your option : ");
        return scanner.nextInt();
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
        System.out.println("\n===============================================");
        System.out.print("User CIN List : \n");
        users.forEach((Cin, User)->{
            System.out.print("\nUser CIN : "+Cin);
            System.out.print(" / User Name : "+User.getNom()
                    +" / User Age : "+ User.getAge());
        });
        System.out.println("\n===============================================\n");
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
        for (Consomation consumations : users.get(tempCin).getConsomationsList()) {
            if (consumations.getEndDate().equals(LocalDate.parse(tempDateS)) || consumations.getEndDate().isAfter(LocalDate.parse(tempDateS))) {
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
        users.get(tempCin).addConsomation(tempDateS,tempDateE,tempCar);
        return true;
    }
    public  static void updateUser(String cin){
        User tempUser = users.get(cin);
        System.out.print(
                "=========================================" +
                        "\n| Select an option please :" +
                        "\n| 1 : Update User Information" +
                        "\n| 2 : Update Carbon Consumption" +
                        "\n| 5 : Close" +
                        "\n=========================================" +
                        "\nEntre your option : ");
        int selectOptionUpdate = scanner.nextInt();
        defultEntre = scanner.nextLine();
        switch (selectOptionUpdate){
            case 1 :{
                System.out.print("Please give me new user name : ");
                tempUser.setNom(scanner.nextLine());
                System.out.print("Please give me new user Age : ");
                tempUser.setAge(scanner.nextInt());
                System.out.print("User updated with success :)\n");
                break;
            }
            case 2 : {
                System.out.print("============= Carbon Consumption List ================ ");
                System.out.println(tempUser.displayConumation());
                System.out.print("=============Select Carbon Consumption to update using id (1,2...) : ");
                int tempIdCarbon = scanner.nextInt();
                defultEntre = scanner.nextLine();
                boolean testIfExiste = false;
                for (Consomation consumations : tempUser.getConsomationsList()) {
                    if (consumations.getId() == tempIdCarbon) {
                        System.out.print("Give me new start date : ");
                        consumations.setStartDate(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Give me new end date : ");
                        consumations.setEndDate(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Give me new Carbon : ");
                        consumations.setCarbon(scanner.nextFloat());
                        System.out.print("User Carbon Consumption updated with success :)\n\n");
                        testIfExiste = true;
                        break;
                    }
                }
                if(!testIfExiste)
                    System.out.println("Carbon Consumption with id " +tempIdCarbon + " not exists.\n\n");
                break;
            }
            case 3 : break;
        }
    }
    public static  void displayRapport(String cin){
        System.out.println("\n===================== User Information : \n");
        System.out.println(users.get(cin).toString());
        System.out.println("\n===================== Carbon Consumption Information : \n");
        System.out.println(users.get(cin).displayConumation());
        System.out.print("\n===================== Carbon Consumption For Days : ");
        System.out.println(users.get(cin).calculateDaysConsumation());
        System.out.print("\n===================== Carbon Consumption For Weeks : ");
        System.out.println(users.get(cin).calculateWeeklyConsumation());
        System.out.print("\n===================== Carbon Consumption For Months : ");
        System.out.println(+users.get(cin).calculateMonthlyConsumation());
        System.out.println("\n===============================================\n\n");
    }
}