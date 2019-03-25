package catalog;

import items.Graph;

/**
 * Command that adds a new graph to the catalog
 */
public class AddCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException {
        if (args.length < 6){
            throw new IllegalArgumentException("At least 6 arguments are required.");
        }
        if (!args[0].equals("add")){
            throw new IllegalArgumentException("Invalid command name");
        }

        Graph graph = new Graph(args[2], args[3], args[4], args[1],
                Integer.parseInt(args[5]), Integer.parseInt(args[6]));
        catalog.graphs.add(graph);
    }

    private static AddCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new AddCommand(Catalog.getInstance());
        }
        return instance;
    }
    private AddCommand(Catalog catalog) {
        super(catalog);
    }

}

