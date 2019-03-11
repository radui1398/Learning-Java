import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("/home/radu/graphs");
        catalog.add (new Graph("K4", "complete/k4.tgf", "complete/view/k4.png"));
        catalog.add (new Graph("K5", "complete/k5.tgf", "complete/view/k5.png"));

        catalog.save("catalog.dat");

        catalog.open("K5");
        catalog.list();

    }
}