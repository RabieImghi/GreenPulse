import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    public String cin;
    public String nom;
    public  int age;
    public List<Consomation> consomationsList;

    public User(String cin, String nom, int age){
        this.cin = cin;
        this.nom = nom;
        this.age = age;
        this.consomationsList = new ArrayList<>();
    }

    public void addConsomation(String startDate, String endDate, float carbon){
        Consomation consomation = new Consomation(startDate,endDate,carbon, this);
        this.consomationsList.add(consomation);
    }
    public  User finUser(String cin, HashMap<String,User> users){
        if(users.containsKey(cin)) return users.get(cin);
        else return null;
    }
    public String getCin(){
        return this.cin;
    }
    public String getNom(){
        return this.nom;
    }
    public int getAge(){
        return this.age;
    }
    public String toString(){
        return "\n===============================\n" +
                "Cin : "+this.cin+"\nNom : "+this.nom+
                "\nAge : "+this.age+
                "\nCarbon Consumption :";
    }

    public  String displayConumation(){
        StringBuilder tempChain= new StringBuilder();
        this.consomationsList.forEach((consumations)->{
            tempChain.append("\t- Start Date Consumption : "+consumations.startDate);
            tempChain.append("\n\t- End Date Consumption : "+consumations.endDate);
            tempChain.append("\n\t- Carbon Consumption : "+consumations.carbon);
            tempChain.append("\n---------------------------");
        });
        return tempChain.toString();
    }

}
