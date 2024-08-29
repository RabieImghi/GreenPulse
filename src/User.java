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
    public String toString(){
        return "Cin : "+this.cin+"\nNom : "+this.nom+
                "\nAge : "+this.age;
    }

}
