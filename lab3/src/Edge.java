public class Edge {
    private Node v1,v2;
    private int cost;
    private boolean twoWayStreet=false;

    public Edge(Node v1, Node v2, int cost) {
        this.cost = cost;
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(Node v1, Node v2,int cost, boolean type) {
        this.cost = cost;
        this.v1 = v1;
        this.v2 = v2;
        this.twoWayStreet=type;
    }

    public Node getNode(int nr){
        if(nr==1)
            return this.v1;
        else
            return this.v2;
    }

    public int getCost(){
        return this.cost;
    }
}
