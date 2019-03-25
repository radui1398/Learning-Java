package catalog;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command that saves in a serialized way the catalog to file.
 */
public class SaveCommand extends Command{

    @Override
    public void run(String[] args) throws  IllegalArgumentException, IOException {
        if (args.length < 2){
            throw new IllegalArgumentException("At least 2 arguments are required.");
        }
        if (!args[0].equals("save")){
            throw new IllegalArgumentException("Invalid command name");
        }
        Path path = Paths.get(args[1]);
        File file = new File(String.valueOf(path));

        // if path is directory it cannot be created
        if (file.isDirectory()){
            throw new IllegalArgumentException("Path is directory.");
        }
        // test if parent directoru exists
        if (!file.getParentFile().exists()){
            // file does not exist
            throw new IllegalArgumentException("Invalid path provided.");
        }
        serialize(catalog, path);
    }

    private void serialize(Serializable object, Path path) throws IOException {
        try(
                FileOutputStream fileOut = new FileOutputStream(String.valueOf(path));
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ){
            out.writeObject(object);
        }
        catch(IOException e){
            throw new IOException("Failed to save catalog.");
        }
    }

    private static SaveCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new SaveCommand(Catalog.getInstance());
        }
        return instance;
    }
    private SaveCommand(Catalog catalog) {
        super(catalog);
    }
}


