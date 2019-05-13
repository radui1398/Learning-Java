package catalog;

import items.Edge;
import items.Node;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Command that saves in a serialized way the catalog to file.
 */
public class SvgCommand extends Command{

    @Override
    public void run(String[] args) throws IllegalArgumentException, IOException {
        if (args.length < 2){
            throw new IllegalArgumentException("At least 2 arguments are required.");
        }
        if (!args[0].equals("svg")){
            throw new IllegalArgumentException("Invalid command name");
        }
        Path path = Paths.get(args[1]);
        File file = new File(String.valueOf(path));

        if (file.isDirectory()){
            throw new IllegalArgumentException("Path is directory.");
        }
        if (!file.getParentFile().exists()){
            throw new IllegalArgumentException("Invalid path provided.");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<Node> nodes=new ArrayList<Node>();
        List<Edge> edges=new ArrayList<Edge>();

        String st = br.readLine();
        if (st.equals("# Nodes"))
            st = br.readLine();
        while (st!= null && !st.contains("# Edges")) {
            String pieces[] = st.split(" ");
            nodes.add(new Node(Integer.parseInt(pieces[0])));
            st = br.readLine();
        }

        st = br.readLine();
        while (st != null) {
            String pieces[] = st.split(" ");
            edges.add(new Edge(Integer.parseInt(pieces[0]),Integer.parseInt(pieces[1])));
            st = br.readLine();
        }


        GraphView view = new GraphView(nodes,edges);
        try {
            view.make();
        }
        catch (ParserConfigurationException ex) {
            throw new IOException(ex.toString());
        }
    }


    private static SvgCommand instance;
    public static Command getInstance() {
        if (instance == null){
            instance = new SvgCommand(Catalog.getInstance());
        }
        return instance;
    }
    private SvgCommand(Catalog catalog) {
        super(catalog);
    }
}


