package com.company.app;

import com.company.shapes.NodeShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Canvas of the app where drawns can be made.
 */
public class Canvas extends JPanel implements Serializable {

    private Graphics2D graphics;

    public Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void reset() {
        shapesCount = 0;
        shapesList.clear();

        repaint();
    }

    void save() throws IOException {
        try(
                FileOutputStream fileOut = new FileOutputStream(DrawingFrame.savePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ){
            out.writeObject(shapesList);
        }
        catch(IOException e){
            throw new IOException("Failed to save drawing." + e);
        }
    }

    void load() throws IOException {
        ArrayList< NodeShape> newList;

        try (
                FileInputStream fileIn = new FileInputStream(DrawingFrame.savePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            newList = (ArrayList< NodeShape>) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new IOException("Could not load drawing.");
        }

        shapesList = newList;
        repaint();
    }

    public enum DrawType{
        SHAPES_LIST, MULTIPLE_RANDOM_NODES;
    }

    private DrawingFrame parent;

    private DrawType drawType;
    private int shapesCount = 0;
    private Color color;

    private ArrayList<NodeShape> shapesList;

    Canvas(DrawingFrame parent){
        this.parent = parent;

        shapesList = new ArrayList<>();
        shapesCount = 0;
        this.color=Color.black;
        drawType = DrawType.SHAPES_LIST;

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                drawShapeAt(e.getX(), e.getY());
            }
        });

        reset();
    }

    private NodeShape getRandomNodeShape(int x, int y){
        Random random = new Random();
        
        int radius=50;
        NodeShape nodeShape = new NodeShape(x,y,radius);
        nodeShape.setColor(this.color);
        return nodeShape;

    }



    private void drawShapeAt(int x, int y) {
        shapesList.add(getRandomNodeShape(x, y));
        drawType = DrawType.SHAPES_LIST;

        repaint();
    }


    public void drawRandomShapes(int count){
        drawType = DrawType.MULTIPLE_RANDOM_NODES;

        this.color=getRandomColor();
        shapesCount = count;
        shapesList.clear();

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (drawType){
            case SHAPES_LIST:
                paintShapesList((Graphics2D) g);
                break;

            case MULTIPLE_RANDOM_NODES:
                paintRandomShapes((Graphics2D) g);
                break;

            default:
                System.err.println("Invalid draw type.");
        }
    }

    private void paintShapesList(Graphics2D g) {
        resetCanvas(g);

        for (NodeShape shape : shapesList){
            drawNode(g, shape);
        }
    }

    private void resetCanvas(Graphics2D g) {
        Rectangle bounds = new Rectangle(0,0,getWidth(), getHeight() );
        g.setPaint( Color.white );
        g.fill( bounds );
    }

    private void paintRandomShapes(Graphics2D g) {
        Random random = new Random();

        resetCanvas(g);

        for (int i = 0; i < shapesCount; ++i){
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());

            NodeShape shape = getRandomNodeShape(x, y);

            drawNode(g, shape);

            shapesList.add(shape);
        }
    }

    private void drawNode(Graphics2D g, NodeShape nodeShape){
        g.setPaint(nodeShape.getColor());
        g.fill(nodeShape);

        g.setPaint(this.color);

        g.draw(nodeShape);
    }
}


