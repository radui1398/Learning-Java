import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa catalog serializata.
 */
public class Catalog implements java.io.Serializable{

    private String folder;
    private List<Graph> graphs = new ArrayList<>();

    /**
     * Constructor
     *
     * @param folder - folder.
     */
    public Catalog(String folder){
        this.folder = folder;
    }

    /**
     * Constructor
     *
     * @param graph - Obiect Graph.
     */
    public void add(Graph graph){
        graphs.add(graph);
    }

    /**
     * Deschide un fisier .tgf
     *
     * @param graphName - nume Graph.
     */
    public void open(String graphName){
        System.out.println("Deschid "+graphName);
        for(Graph graph:graphs){
            if(graph.getName().equals(graphName))
            try {
                Desktop desktop = Desktop.getDesktop();
                File myFile = new File(folder + "/" + graph.getTgf());
                desktop.open(myFile);
            } catch (IOException ex) {
                System.out.println("Fisierul nu exista!");
            }
        }
    }

    /**
     * Getter
     *
     * @return folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Getter
     *
     * @return List
     */
    public List<Graph> getGraphs() {
        return graphs;
    }

    /**
     * Afiseaza o lista cu elementele din catalog.
     */
    public void list(){
        System.out.println("Catalogul contine urmatoarele grafuri:");
        for(Graph graph:graphs){
            System.out.println(graph.getName());
        }
    }

    /**
     * Salveaza continutul obiectului intr-un fisier .dat
     *
     * @param file - numele fisierului
     */
    public void save(String file){
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("Salvarea a reusit.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Incarca continutul unui obiect Catalog dintr-un fisier .dat
     *
     * @param file - numele fisierului
     */
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

    /**
     * Metoda toString pentru afisare (Nu stiu inca daca e necesara la serializare)
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Catalog{" +
                "folder='" + folder + '\'' +
                ", graphs=" + graphs +
                '}';
    }
}
