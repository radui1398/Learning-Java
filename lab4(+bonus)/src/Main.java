import java.text.ParseException;

import catalog.Catalog;
import catalog.Shell;
import items.Graph;

public class Main {
    public static void main(String arg[]) throws ParseException {
        String path = "D:\\GitHub\\IdeaProjects\\graph";
    try {
        Catalog catalog = Catalog.getInstance();
        Graph petersenGraph  = new Graph("Peterson", path + "\\peterson.tgf", path + "\\peterson.png","neorientat",5,5);
        Graph k4Graph = new Graph("K4", path + "\\k4.tgf", path + "\\k4.png","complet",6,6);
        catalog.add (petersenGraph);
        catalog.add (k4Graph);
        //catalog.open(petersenGraph);

        catalog.save(path + "\\catalog.dat");
        catalog.load(path + "\\catalog.dat");
        catalog.list();
     }
    catch(Exception e){
        e.printStackTrace();
        System.err.println(e.toString());
    }
        Shell shell = Shell.getInstance();
        shell.run();
    }


}
