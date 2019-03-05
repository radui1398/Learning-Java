import java.time.LocalTime;

public class Main {
    public static void main(String[] args){
        TravelMap map = new TravelMap();
        Hotel v1=new Hotel("California");
        Museum v2=new Museum("The Metropolitan Museum of Art");
        Church v3=new Church("St. Paul");
        Restaurant v4=new Restaurant("Ristretto");
        Restaurant v5=new Restaurant("Zeus Central Museum");

        map.addNode(v5);
        map.addNode(v1);
        map.addNode(v2);
        map.addNode(v3);
        map.addNode(v4);

        LocalTime opening=LocalTime.of(13, 30, 0);
        v4.setOpeningHours(opening);

        map.addEdge(v1, v2, 15); //two way street by default
        map.addEdge(v3, v2, 1, false); //one-way street

        System.out.println("The map is: \n" + map.getNodes());

    }
}
