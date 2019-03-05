import java.time.LocalTime;

public class Restaurant extends Node implements Classifiable {
    String type;
    int rank;
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
    public void setRank(int rank) {
        this.rank=rank;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void getType() {
        System.out.println("This is a Restaurant.");
    }


}
