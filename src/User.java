import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class User {
    final private String cin;
    private String nom;
    private  int age;
    final private List<Consomation> consomationsList;
    public User(String cin, String nom, int age){
        this.cin = cin;
        this.nom = nom;
        this.age = age;
        this.consomationsList = new ArrayList<>();
    }
    public void addConsumption(String startDate, String endDate, float carbon){
        Consomation consomation = new Consomation(startDate,endDate,carbon, this);
        this.consomationsList.add(consomation);
    }


    public List<Consomation> getConsomationsList() {
        return consomationsList;
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
    public void setAge(int age){
        this.age = age;
    }

    public String toString(){
        return "\n===============================\n" +
                "Cin : "+this.cin+"\nNom : "+this.nom+
                "\nAge : "+this.age+
                "\nCarbon Consumption :";
    }

    public  String displayConsumption(){
        StringBuilder tempChain = new StringBuilder();
        final float[] totalConsumption = {0};
        this.consomationsList.forEach((consumption) -> {
            tempChain.append(String.format("\n%d) \t- Start Date Consumption: %s", consumption.getId(), consumption.getStartDate()));
            tempChain.append(String.format("\n\t- End Date Consumption: %s", consumption.getEndDate()));
            tempChain.append(String.format("\n\t- Carbon Consumption: %.2f", consumption.getCarbon()));
            tempChain.append("\n---------------------------");
            totalConsumption[0] += consumption.getCarbon();
        });
        tempChain.append(String.format("\nTotal Consumption: %.2f", totalConsumption[0]));
        return tempChain.toString();
    }
    public float calculateDaysConsumption(){
        float totalDays = 0;
        float totalCarbon = 0;
        for (Consomation consumption : this.consomationsList) {
            totalDays += ChronoUnit.DAYS.between(consumption.getStartDate(), consumption.getEndDate());
            totalCarbon += consumption.getCarbon();
        }
        return totalCarbon/totalDays;
    }
    public float calculateWeeklyConsumption(){
        float totalWeeks = 0;
        float totalCarbon = 0;
        for (Consomation consomation : this.consomationsList) {
            totalWeeks += ChronoUnit.WEEKS.between(consomation.getStartDate(), consomation.getEndDate());
            totalCarbon += consomation.getCarbon();
        }
        return totalCarbon/totalWeeks;
    }
    public float calculateMonthlyConsumption(){
        float totalMonths = 0;
        float totalCarbon = 0;
        for (Consomation consomation : this.consomationsList) {
            totalMonths += ChronoUnit.MONTHS.between(consomation.getStartDate(), consomation.getEndDate());
            totalCarbon += consomation.getCarbon();
        }
        return totalCarbon/totalMonths;
    }



}
