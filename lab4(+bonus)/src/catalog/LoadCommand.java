package catalog;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command that loads a previously serialized file containing a catalog.
 */
public class LoadCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException, IOException {
        if (args.length < 2){
            throw new IllegalArgumentException("At least 2 arguments are required.");
        }
        if (!args[0].equals("load")){
            throw new IllegalArgumentException("Invalid command name");
        }
        Path path = Paths.get(args[1]);
        File file = new File(String.valueOf(path));

        // if path is directory it cannot be created
        if (file.isDirectory()){
            throw new IllegalArgumentException("Path is directory.");
        }
        // test if parent directory exists
        if (!file.exists()){
            // file does not exist
            throw new IllegalArgumentException("Invalid path provided.");
        }
        Catalog newCatalog = deserialize(path);

        if (newCatalog != null) {
            catalog.set(newCatalog);
        }
    }

    private Catalog deserialize(Path path) throws IOException {
        Catalog catalog = null;
        try (
                FileInputStream fileIn = new FileInputStream(String.valueOf(path));
                ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            catalog = (Catalog) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IOException("Could not load catalog.");
        }
        return catalog;
    }

    private static LoadCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new LoadCommand(Catalog.getInstance());
        }
        return instance;
    }
    private LoadCommand(Catalog catalog) {
        super(catalog);
    }
}


