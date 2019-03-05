import java.time.LocalTime;

public class Restaurant extends Node implements Visitable {
    String type;
    LocalTime openinghours;
    public Restaurant(String name) {
        super(name);
    }

    public String getTypeOfRestaurant() {
        return type;
    }

    public void setTypeOfRestaurant(String type) {
        this.type = type;
    }

    @Override
    public void setOpeningHours(LocalTime opening) {
        this.openinghours=opening;
    }

    @Override
    public LocalTime getOpeningHours() {
        return openinghours;
    }

    @Override
    public void getType() {
        System.out.println("This is a Restaurant.");
    }
}
