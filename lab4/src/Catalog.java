import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private String folder;
    private List<Graph> graphs = new ArrayList<>();

    public Catalog(String folder){
        this.folder = folder;
    }

    public void add(Graph graph){
        graphs.add(graph);
    }

    public void open(String graphName){
        System.out.println("Deschid "+graphName);
        for(Graph graph:graphs){
            try {
                Desktop desktop = Desktop.getDesktop();
                File myFile = new File(folder + "/" + graph.getTgf());
                desktop.open(myFile);
            } catch (IOException ex) {}
        }
    }

    public void list(){
        System.out.println("Catalogul contine urmatoarele grafuri:");
        for(Graph graph:graphs){
            System.out.println(graph.getName());
        }
    }
}
