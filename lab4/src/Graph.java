import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Clasa in care salvam continutul elementelor din catalog.
 */
public class Graph implements java.io.Serializable{
    private String name;
    private String tgf;
    private String img;

    /**
     * Constructor
     *
     * @param name - Nume graf
     * @param tgf - Path catre .tgf
     * @param img - Path catre .png
     */
    public Graph(String name, String tgf, String img){
        this.name = name;
        this.tgf = tgf;
        this.img = img;
    }

    /**
     * Getters
     */
    public String getName() { return name; }

    public String getTgf() { return tgf; }

    public String getImg() { return img; }

    /**
     * Suprascriere pentru afisare
     *
     * @return - String
     */
    @Override
    public String toString() {
        return "Graph{" +
                "name='" + name + '\'' +
                ", tgf='" + tgf + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
