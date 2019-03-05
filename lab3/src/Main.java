import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.stream.*;

import static java.time.temporal.ChronoUnit.*;

public class Main {
    public static void main(String[] args) {
        TravelMap map = new TravelMap();
        Hotel v1 = new Hotel("California");
        Museum v2 = new Museum("The Metropolitan Museum of Art");
        Church v3 = new Church("St. Paul");
        Restaurant v4 = new Restaurant("Ristretto");
        Restaurant v5 = new Restaurant("Zeus Central Museum");
        Church v6 = new Church("St. Andrew");
        Church v7 = new Church("St. Maria");
        Museum v8 = new Museum("The Metropolitan Museum of Art");

        map.addNode(v8);
        map.addNode(v7);
        map.addNode(v5);
        map.addNode(v1);
        map.addNode(v2);
        map.addNode(v3);
        map.addNode(v4);
        map.addNode(v6);

        LocalTime opening = LocalTime.of(13, 30, 0);
        v3.setOpeningHours(opening);


        LocalTime opening1 = LocalTime.of(9, 30, 0);
        v6.setOpeningHours(opening1);

        LocalTime opening2 = LocalTime.of(15, 30, 0);
        v7.setOpeningHours(opening2);
        LocalTime ending2 = LocalTime.of(20, 30, 0);
        v7.setEndingHours(ending2);

        map.addEdge(v1, v2, 15); //two way street by default
        map.addEdge(v3, v2, 1, false); //one-way street

        System.out.println("The map is: \n" + map.getNodes());

        List<Node> result = map.getNodes().stream()
                .filter(line -> line instanceof Visitable && !(line instanceof Payable))
                .sorted((o1, o2) -> ((Visitable) o1).compareToOpening((Visitable) o2))
                .collect(Collectors.toList());
        System.out.println(result);


        v2.setEntryFee(30.0);
        v8.setEntryFee(25.0);
        double result1 = map.getNodes().stream()
                .filter(line -> line instanceof Payable)
                .collect(Collectors.averagingDouble(p -> ((Payable) p).getEntryFee()));
        System.out.println("The average price ticket is : " + result1);

        Duration d = Visitable.getVisitingDuration(v7.getOpeningHours(), v7.getEndingHours());
        System.out.println("The location is opened : " + d.getSeconds() + " seconds");

        /**
         *  Implement Dijkstra algorithm for finding shortest path
         */
    }
}
