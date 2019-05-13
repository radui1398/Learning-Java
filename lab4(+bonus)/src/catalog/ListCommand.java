package catalog;

import items.Graph;

/**
 * Command that temporarily lists all entries in the catalog.
 */
public class ListCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException {
        if (args.length < 1){
            throw new IllegalArgumentException("At least 1 argument is required.");
        }
        if (!args[0].equals("list")){
            throw new IllegalArgumentException("Invalid command name");
        }

        for (Graph graph : catalog.graphs){
            System.out.println(graph);
        }
    }

    private static ListCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new ListCommand(Catalog.getInstance());
        }
        return instance;
    }
    private ListCommand(Catalog catalog) {
        super(catalog);
    }
}

