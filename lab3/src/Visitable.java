import java.time.LocalTime;

public interface Visitable {
    void setOpeningHours(LocalTime opening);
    void setEndingHours(LocalTime ending);
    LocalTime getOpeningHours();
    LocalTime getEndingHours();
    void getType();
}
