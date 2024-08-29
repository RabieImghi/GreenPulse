import java.time.LocalDate;
import java.time.LocalDateTime;

public class Carbon extends Person{
    LocalDateTime startDate;
    LocalDateTime endDate;
    float carbon;

    public Carbon(String nom, String cin, int age,LocalDateTime startDate,LocalDateTime endDate){
        super.addPerson(nom,cin,age);
        this.startDate = startDate;
        this.endDate = endDate;

    }


}
