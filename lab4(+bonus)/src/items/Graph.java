package items;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Graph implements Serializable {

    private String name;
    private String tgfPath;
    private String pngPath;
    private String type;
    private int verticesCount;
    private int edgesCount;

     public Graph(String name, String tgfPath, String pngPath, String type, int noVertices, int noEdges)
     {
         this.name = name;
         this.tgfPath = tgfPath;
         this.pngPath = pngPath;
         this.type = type;
         this.verticesCount = noVertices;
         this.edgesCount = noEdges;
     }

    public void setName(String name) {
        this.name = name;
    }

    public void setPngPath(String pngPath) {
        this.pngPath = pngPath;
    }

    public void setTgfPath(String tgfPath) {
        this.tgfPath = tgfPath;
    }

    public String getName() {
        return name;
    }

    public String getPngPath() {
        return pngPath;
    }

    public String getTgfPath() {
        return tgfPath;
    }

    public String getType() {
         return type;
     }

    public int getVerticesCount() {
        return verticesCount;
    }

    public int getEdgesCount() {
         return edgesCount;
     }

    public void open(){
        try {
            Desktop desktop = Desktop.getDesktop();
            File image = new File(getPngPath());
            desktop.open(image);
        } catch (IOException e) {
            System.out.println("There was a problem opening the image.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Graful cu numele "+ getName() + " e de tipul " + getType() + " are " + getVerticesCount() + " varfuri si "+getEdgesCount()+" muchii.";
    }
}
