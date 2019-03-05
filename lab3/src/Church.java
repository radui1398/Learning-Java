import java.time.LocalTime;

public class Church extends Node implements Visitable{
    private LocalTime opening;
    private LocalTime ending;

    public Church(String name) {
        super(name);
    }

    @Override
    public void setOpeningHours(LocalTime open){
        this.opening = open;
    }

    @Override
    public void setEndingHours(LocalTime ending) {
        this.ending = ending;
    }

    @Override
    public LocalTime getOpeningHours() {
        return this.opening;
    }

    @Override
    public LocalTime getEndingHours() {
        return this.ending;
    }

    @Override
    public void getType() {
        System.out.println("This is a Church.");
    }
}
