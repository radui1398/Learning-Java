import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements java.io.Serializable{

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
            if(graph.getName().equals(graphName))
            try {
                Desktop desktop = Desktop.getDesktop();
                File myFile = new File(folder + "/" + graph.getTgf());
                desktop.open(myFile);
            } catch (IOException ex) {}
        }
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public List<Graph> getGraphs() {
        return graphs;
    }

    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void list(){
        System.out.println("Catalogul contine urmatoarele grafuri:");
        for(Graph graph:graphs){
            System.out.println(graph.getName());
        }
    }

    public void save(String file){
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void load(String file){
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Catalog catalog = (Catalog) in.readObject();
            this.graphs = catalog.getGraphs();
            this.folder = catalog.getFolder();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Catalog class not found");
            c.printStackTrace();
            return;
        }
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "folder='" + folder + '\'' +
                ", graphs=" + graphs +
                '}';
    }
}
