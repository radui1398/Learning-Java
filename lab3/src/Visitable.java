import java.time.LocalTime;

public interface Visitable {
    void setOpeningHours(LocalTime opening);
    LocalTime getOpeningHours();
    void getType();
}
