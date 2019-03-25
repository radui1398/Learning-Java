package catalog;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import items.Edge;
import items.Node;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class GraphView {
    Graph<Integer, Integer> g;

    public GraphView(List<Node> nodes, List<Edge> edges) {
        g = new SparseMultigraph<Integer, Integer>();
        for(Node n:nodes)
            g.addVertex(n.getValue());
        int contor=1;
        for(Edge e:edges){
            g.addEdge(contor,e.getValue1(),e.getValue2());
            contor++;
        }

    }
    public void make() throws IOException, ParserConfigurationException {

        DocumentBuilder dbf = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        DOMImplementation domImpl = dbf.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create a Circle Layout
        Layout<Integer, String> layout = new CircleLayout(g);
        layout.setSize(new Dimension(1000, 500));
        VisualizationImageServer<Integer, String> vv = new VisualizationImageServer<>(layout, new Dimension(1400, 800));

        vv.setPreferredSize(new Dimension(1000, 500));
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        vv.paint(svgGenerator);

        Writer out = new OutputStreamWriter(new FileOutputStream("graph.svg"), "UTF-8");
        svgGenerator.stream(out, true);
        out.flush();
        out.close();
        System.out.println("File is created successfully");
    }
}