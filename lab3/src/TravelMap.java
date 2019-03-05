import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TravelMap {
    List<Node> nodes=new ArrayList<Node>();
    List<Edge> edges=new ArrayList<Edge>();

    public List<Node> getNodes() {
        Collections.sort(nodes);
        return nodes;
    }

    public void addNode(Node ... args) {
        for(Node v:args)
            nodes.add(v);
    }

    public void addEdge(Node v1, Node v2, int cost){
        Edge e=new Edge(v1,v2,cost);
        edges.add(e);
    }

    public void addEdge(Node v1, Node v2,int cost, boolean type) {
        Edge e = new Edge(v1, v2, cost, type);
        edges.add(e);
    }



    @Override
    public String toString() {
        return "TravelMap{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
