package catalog;

import java.io.IOException;
import items.Graph;

/**
 * Command that opens the picture showing the graph with the given name.
 */
public class OpenCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException, IOException {
        if (args.length < 2){
            throw new IllegalArgumentException("At least 2 arguments are required.");
        }
        if (!args[0].equals("open")){
            throw new IllegalArgumentException("Invalid command name");
        }

        for (Graph graph : catalog.graphs){
            if (graph.getName().equals(args[1])) {
                graph.open();
                return;
            }
        }
    }

    private static OpenCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new OpenCommand(Catalog.getInstance());
        }
        return instance;
    }

    private OpenCommand(Catalog catalog) {
        super(catalog);
    }
}
