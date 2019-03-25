package com.lab5.ui;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Clasa in care salvam continutul elementelor din catalog.
 */
public class Graph implements java.io.Serializable{
    private String name;
    private String tgf;
    private String img;
    private String type;
    private int nrOfNodes;
    private int nrOfVertices;

    public Graph(){
        name = "";
        tgf = "";
        img = "";
    }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTgf(String tgf) {
        this.tgf = tgf;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNrOfNodes() {
        return nrOfNodes;
    }

    public void setNrOfNodes(int nrOfNodes) {
        this.nrOfNodes = nrOfNodes;
    }

    public int getNrOfVertices() {
        return nrOfVertices;
    }

    public void setNrOfVertices(int nrOfVertices) {
        this.nrOfVertices = nrOfVertices;
    }

    /**
     * Suprascriere pentru afisare
     *
     * @return - String
     */
    @Override
    public String toString() {
        return "Name:" + name + " @ Type: " + type + " @ Nodes: " + nrOfNodes + " @ Vertices: " + nrOfVertices;
    }
}
