import java.time.LocalDate;

public class Consomation {
    LocalDate startDate;
    LocalDate endDate;
    float carbon;
    User user;

    public  Consomation(String startDate, String endDate, float carbon,User user){
        this.startDate = LocalDate.parse(startDate) ;
        this.endDate = LocalDate.parse(endDate);
        this.carbon = carbon;
        this.user = user;
    }

    public String toString(){
        return "\nStart Date : "+startDate+
                "\nEnd Date : "+endDate+
                "\nCarbon Consume : "+carbon
                ;

    }



}
