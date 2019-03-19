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
        Catalog catalog = new Catalog("D:\\GitHub\\Graph");
        catalog.load("catalog.dat");
        try {
            catalog.open("K4");
        }catch(ExtensionException ex1){
        }
        catalog.list();


        Shell shell=new Shell(catalog);
        shell.run();
    }
}