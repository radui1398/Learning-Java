import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravelMap {
    private List<Node> nodes=new ArrayList<Node>();
    private List<Edge> edges=new ArrayList<Edge>();

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

    public int getCost(Node v1,Node v2){
        for(Edge edge:edges){
            if(edge.getNode(1)==v1 && edge.getNode(2)==v2)
                return edge.getCost();
        }
        return 0;
    }

    public int getNrOfNodes(){
        return this.nodes.size();
    }

    public int[][] toGraph(){
        int nrOfNodes = getNrOfNodes();
        int graph[][] = new int[nrOfNodes+1][nrOfNodes+1];
        int node1Index,node2Index;
        for(Node node1:nodes){
            node1Index=nodes.indexOf(node1);
            for(Node node2:nodes){
                node2Index = nodes.indexOf(node2);
                if(node1!=node2){
                    graph[node1Index][node2Index]=getCost(node1,node2);
                }
                else{
                    graph[node1Index][node1Index]=0;
                }
            }
        }

        return graph;
    }

    @Override
    public String toString() {
        return "TravelMap{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
