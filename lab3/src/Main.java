import java.time.LocalTime;

public class Main {
    public static void main(String[] args){
        //Declarari
        TravelMap map = new TravelMap();
        Hotel h1=new Hotel("Moldova");
        Museum m1=new Museum("Muzeul Renasterii");
        Church c1=new Church("Vestita Manastire Putna");
        Restaurant r1=new Restaurant("KFC");

        //Adaugarea Punctelor
        map.addNode(h1);
        map.addNode(m1);

        Hotel h2=new Hotel("Eazy");

        map.addNode(h2);
        map.addNode(c1);
        map.addNode(r1);

        LocalTime opening=LocalTime.of(12, 25, 0);
        LocalTime ending=LocalTime.of(12, 25, 0);
        c1.setOpeningHours(opening);
        c1.setEndingHours(ending);

        map.addEdge(h1, m1, 15); //two way street by default
        map.addEdge(h1, h2, 1, false); //one-way street

        System.out.println("Harta generata: \n" + map.getNodes());

    }
}
