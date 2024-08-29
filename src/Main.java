import java.util.ArrayList;
import  java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String defultEntre;
        String tempCin;
        boolean test =false;
        do{
            System.out.print("chois oprion\n1 : add person \n2 : add consumation\n3 : Afiche detaile\n" +
                    "4 : Qut\n-------------------------------------------------" +
                    " \nEntre your chois : ");
            int chois = scanner.nextInt();
            switch (chois){
                case 1 : {
                    defultEntre = scanner.nextLine();
                    System.out.print("Give me your Cin : ");
                    tempCin=scanner.nextLine();
                    System.out.print("Give me your name : ");
                    String tempNom=scanner.nextLine();
                    System.out.print("Give me your Age : ");
                    int tempAge=scanner.nextInt();
                    User User = new User(tempCin,tempNom,tempAge);
                    users.put(User.getCin(),User);
                    System.out.println("user added succefully ");
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