import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Graph implements java.io.Serializable{
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

    @Override
    public String toString() {
        return "Graph{" +
                "name='" + name + '\'' +
                ", tgf='" + tgf + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
