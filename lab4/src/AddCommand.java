import java.util.Arrays;

public class AddCommand extends Command{
    public AddCommand(Catalog catalog) {
        super(catalog);
    }

    public void run(String[] args) throws Exception {
        if (args.length < 2){
            throw new IllegalArgumentException("At least 2 arguments are required.");
        }
        String format=args[1];
        System.out.println(format);
        String path='\\'+args[1];
        Graph graph=new Graph(args[1],args[1]+".tgf","\\"+args[1]+".png");
        catalog.add(graph);
    }
}

