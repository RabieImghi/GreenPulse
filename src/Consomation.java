import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Consomation {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private float carbon;
    private final User user;


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
