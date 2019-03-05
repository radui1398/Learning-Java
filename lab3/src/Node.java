public abstract class Node implements Comparable<Node> {
    private String name;
    private double longitude=0, latitude=0;

    public Node(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Node o) {
        String secondPartThis = this.name;
        String secondPartObject = o.name;
        return secondPartThis.compareTo(secondPartObject);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

}
