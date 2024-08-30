import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Consomation {
    static final AtomicInteger count = new AtomicInteger(0);
    int id;
    LocalDate startDate;
    LocalDate endDate;
    float carbon;
    User user;

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public float getCarbon() {
        return carbon;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCarbon(float carbon) {
        this.carbon = carbon;
    }

    public  Consomation(String startDate, String endDate, float carbon, User user){
        this.id= count.incrementAndGet();
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
