import java.time.LocalDate;
import java.time.Month;


/**
 * Clasa principala Main
 */
public class Main {
    /**
     * Inima aplicatiei :)
     */
    public static void main(String[] args) {
        Catalog catalog = new Catalog("/home/radu/graphs");
        catalog.add (new Graph("K4", "complete/k4.tgf", "complete/view/k4.png"));
        catalog.add (new Graph("K5", "complete/k5.tgf", "complete/view/k5.png"));

        catalog.save("catalog.dat");
        try {
            catalog.open("K4");
        }catch(ExtensionException ex1){
        }
        catalog.list();

    }
}