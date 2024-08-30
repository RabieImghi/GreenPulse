import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private String cin;
    private String nom;
    private  int age;
    private List<Consomation> consomationsList;
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

    public List<Consomation> getConsomationsList() {
        return consomationsList;
    }

    public void setConsomationsList(List<Consomation> consomationsList) {
        this.consomationsList = consomationsList;
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
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setCin(String cin){
        this.cin = cin;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String toString(){
        return "\n===============================\n" +
                "Cin : "+this.cin+"\nNom : "+this.nom+
                "\nAge : "+this.age+
                "\nCarbon Consumption :";
    }

    public  String displayConumation(){
        StringBuilder tempChain = new StringBuilder();
        final float[] totalConsumation = {0};
        this.consomationsList.forEach((consumations) -> {
            tempChain.append(String.format("\n%d) \t- Start Date Consumption: %s", consumations.getId(), consumations.getStartDate()));
            tempChain.append(String.format("\n\t- End Date Consumption: %s", consumations.getEndDate()));
            tempChain.append(String.format("\n\t- Carbon Consumption: %.2f", consumations.getCarbon()));
            tempChain.append("\n---------------------------");
            totalConsumation[0] += consumations.getCarbon();
        });
        tempChain.append(String.format("\nTotal Consumption: %.2f", totalConsumation[0]));
        return tempChain.toString();
    }
    public float calculateDaysConsumation(){
        float totalDays = 0;
        float totalCarbon = 0;
        for (Consomation consomation : this.consomationsList) {
            totalDays += ChronoUnit.DAYS.between(consomation.getStartDate(), consomation.getEndDate());
            totalCarbon += consomation.getCarbon();
        }
        return totalCarbon/totalDays;
    }
    public float calculateWeeklyConsumation(){
        float totalWeeks = 0;
        float totalCarbon = 0;
        for (Consomation consomation : this.consomationsList) {
            totalWeeks += ChronoUnit.WEEKS.between(consomation.getStartDate(), consomation.getEndDate());
            totalCarbon += consomation.getCarbon();
        }
        return totalCarbon/totalWeeks;
    }
    public float calculateMonthlyConsumation(){
        float totalMonths = 0;
        float totalCarbon = 0;
        for (Consomation consomation : this.consomationsList) {
            totalMonths += ChronoUnit.MONTHS.between(consomation.getStartDate(), consomation.getEndDate());
            totalCarbon += consomation.getCarbon();
        }
        return totalCarbon/totalMonths;
    }



}
