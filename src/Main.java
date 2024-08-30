import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
                    "\n| 1 : Add New User" +
                    "\n| 2 : Add New Carbon Consumption" +
                    "\n| 3 : Display User Information (By CIN)" +
                    "\n| 4 : Update User" +
                    "\n| 5 : Delete User" +
                    "\n| 6 : Close" +
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
                        System.out.println("User with CIN " + tempCin + " not exists.");
                        break;
                    }
                    //List<Consomation> tempList = users.get(tempCin).consomationsList;
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
                    if(!users.containsKey(tempCin)) {
                        System.out.println("User with CIN " + tempCin + " not exists.");
                        break;
                    }
                    System.out.println("\n===============================================");
                    System.out.println(users.get(tempCin).toString());
                    System.out.println(users.get(tempCin).displayConumation());


                } break;
                case 4 : {
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
                    if(!users.containsKey(tempCin)) {
                        System.out.println("User with CIN " + tempCin + " not exists.");
                        break;
                    }
                    User tempUser = users.get(tempCin);
                    System.out.print(
                            "=========================================" +
                            "\n| Select an option please :" +
                            "\n| 1 : Update User Information" +
                            "\n| 2 : Update Carbon Consumption" +
                            "\n| 5 : Close" +
                            "\n=========================================" +
                            "\nEntre your option : ");
                    int selectOptionUpdate = scanner.nextInt();
                    switch (selectOptionUpdate){
                        case 1 :{
                            defultEntre = scanner.nextLine();
                            System.out.print("Please give me new user name : ");
                            tempUser.setNom(scanner.nextLine());
                            System.out.print("Please give me new user Age : ");
                            tempUser.setAge(scanner.nextInt());
                            System.out.print("User updated with success :)\n");
                            break;
                        }
                        case 2 : {
                            defultEntre = scanner.nextLine();
                            System.out.print("============= Carbon Consumption List ================ ");
                            System.out.println(tempUser.displayConumation());
                            System.out.print("=============Select Carbon Consumption to update using id (1,2...) : ");
                            int tempIdCarbon = scanner.nextInt();
                            defultEntre = scanner.nextLine();
                            boolean testIfExiste = false;
                            for (Consomation consumations : tempUser.consomationsList) {
                                if (consumations.id == tempIdCarbon) {
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

                } break;
                case 5 : {
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
                    if(!users.containsKey(tempCin)) {
                        System.out.println("User with CIN " + tempCin + " not exists.");
                        break;
                    }
                    users.remove(tempCin);
                    System.out.println("User Deleted with successfully :)");
                    break;
                }
                case 6 : test=true;
            }
        }while (!test);
    }
}