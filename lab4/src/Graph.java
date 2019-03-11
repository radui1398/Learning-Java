public class Graph {
    private String name;
    private String tgf;
    private String img;

    public Graph(String name, String tgf, String img){
        this.name = name;
        this.tgf = tgf;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getTgf() {
        return tgf;
    }

    public String getImg() {
        return img;
    }
}
