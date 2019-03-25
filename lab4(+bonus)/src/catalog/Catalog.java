package catalog;

import exceptions.CatalogLoadException;
import exceptions.CatalogSaveException;
import exceptions.CatalogOpenException;
import items.Graph;

import java.io.*;
import java.util.ArrayList;

public class Catalog implements Serializable {
    ArrayList<Graph> graphs;

    private Catalog() {
        graphs = new ArrayList<>();
    }
    private static Catalog instance;

    public static Catalog getInstance() {
        if (instance == null){
            instance = new Catalog();
        }
        return instance;
    }

    /**
     * Adds graph to catalog.
     * @param graph The graph to be added.
     */
    public void add(Graph graph) {
        graphs.add(graph);
    }

    /**
     * Lists all graphs in the catalog to standard output.
     */
    public void list() {
        if (graphs.isEmpty()) {
            System.out.println("None");
        }
        graphs.forEach(System.out::println);
    }

    /**
     * Opens graph image.
     * @param graph The graph image to be opened.
     * @throws CatalogOpenException If graph image cannot be opened.
     */
    public void open(Graph graph) throws CatalogOpenException {
         graph.open();
    }

    public int getSize() {
        return graphs.size();
    }

    public Graph getGraph(int index) {
        return graphs.get(index);
    }
    /**
     * Saves entire catalog to the file specified by path (through standard Java serialization)
     * @param path The path of the target save file.
     * @throws CatalogSaveException If catalog cannot be saved (invalid path, no access etc.)
     */
    public void save(String path) throws CatalogSaveException {
        try(
                FileOutputStream fileOut = new FileOutputStream(path);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ){
            out.writeObject(this);
        }
        catch(IOException e){
            throw new CatalogSaveException(e.toString());
        }
    }

    /**
     * Loads catalog from the file specified by path
     * @param path The path of the target load file.
     * @throws CatalogLoadException If the catalog cannot be loaded (invalid path, no read access etc.)
     */
    public void load(String path) throws CatalogLoadException {
        Catalog catalog = null;
        try (
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            catalog = (Catalog) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new CatalogLoadException();
        }
        this.set(catalog);
    }

    /**
     * Clones newCatalog into this.
     * @param newCatalog The catalog to be cloned.
     */
    void set(Catalog newCatalog) {
        this.graphs = new ArrayList<>(newCatalog.graphs);
    }
}

