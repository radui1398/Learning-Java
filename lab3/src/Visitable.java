import java.time.*;
import java.time.temporal.Temporal;

public interface Visitable{
    default void specialMethod(){
        this.setOpeningHours(LocalTime.of(9,30,0));
        this.setEndingHours(LocalTime.of(20,30,0));
    }
    static Duration getVisitingDuration(LocalTime opening, LocalTime ending){
        LocalDateTime t1=LocalDateTime.of(2019, Month.MARCH,1,opening.getHour(),opening.getMinute(),opening.getSecond());
        LocalDateTime t2=LocalDateTime.of(2019, Month.MARCH,1,ending.getHour(),ending.getMinute(),ending.getSecond());
        Duration d=Duration.between(t1,t2);
        return d;
    }
    void setOpeningHours(LocalTime opening);
    LocalTime getOpeningHours();
    default LocalTime getOpening(){
        return getOpeningHours();
    }
    void setEndingHours(LocalTime ending);
    LocalTime getEndingHours();
    void getType();
    default int compareToOpening(Visitable o) {
        LocalTime secondPartThis = this.getOpeningHours();
        LocalTime secondPartObject = o.getOpeningHours();
        return secondPartThis.compareTo(secondPartObject);
    }
}
