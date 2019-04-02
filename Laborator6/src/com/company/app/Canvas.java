package com.company.app;

import com.company.shapes.NodeShape;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Canvas of the app where drawns can be made.
 */
public class Canvas extends JPanel implements Serializable {

    private Graphics2D graphics;
    private ArrayList<NodeShape> nodesLocation;
    private NodeShape startNode;


    public Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void reset() {
        shapesCount = 0;
        shapesList.clear();
        lineList.clear();
        nodesLocation = new ArrayList<>();
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
        SHAPES_LIST, MULTIPLE_RANDOM_NODES, LINE;
    }

    private DrawingFrame parent;

    private DrawType drawType;
    private int shapesCount = 0;
    private Color color;

    private ArrayList<NodeShape> shapesList;
    private ArrayList<Line2D.Double> lineList;

    Canvas(DrawingFrame parent){
        this.addMouseListener(new clickOnShape());
        this.parent = parent;

        shapesList = new ArrayList<>();
        lineList = new ArrayList<>();
        shapesCount = 0;
        this.color=Color.black;
        drawType = DrawType.SHAPES_LIST;

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x1 = e.getX();
                int y1 = e.getY();
                boolean nodeAlreadyThere = false;
                for(NodeShape shape : nodesLocation)
                    if(x1<shape.getMaxX() && x1>shape.getMinX())
                        if(y1<shape.getMaxY() && y1>shape.getMinY()) {
                            nodeAlreadyThere = true;
                            break;
                        }
                if(!nodeAlreadyThere)
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

            case LINE:
                paintLine((Graphics2D) g);
                break;

            default:
                System.err.println("Invalid draw type.");
        }
    }

    private void paintLine(Graphics2D g){
        for(Line2D.Double line : lineList)
            draw2DLine(g,line);
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
            nodesLocation.add(shape);
        }
    }

    private void drawNode(Graphics2D g, NodeShape nodeShape){
        g.setPaint(nodeShape.getColor());
        g.fill(nodeShape);

        g.setPaint(this.color);

        g.draw(nodeShape);
        paintLine(g);
        nodesLocation.add(nodeShape);
    }

    private void draw2DLine(Graphics2D g, Line2D.Double line){
        g.setPaint(this.color);
        g.drawLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2());

    }

    public class clickOnShape extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            int  x1 = e.getX();
            int  y1 = e.getY();
            for(NodeShape shape : nodesLocation){
                if(x1<shape.getMaxX() && x1>shape.getMinX())
                    if(y1<shape.getMaxY() && y1>shape.getMinY()){
                        startNode = shape;
                        break;
                    }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int  x1 = e.getX();
            int  y1 = e.getY();
            for(NodeShape shape : nodesLocation){
                if(x1<shape.getMaxX() && x1>shape.getMinX())
                    if(y1<shape.getMaxY() && y1>shape.getMinY()){
                        Line2D.Double line = new Line2D.Double(
                                startNode.getCenterX(),
                                startNode.getCenterY(),
                                shape.getCenterX(),
                                shape.getCenterY()
                        );
                        lineList.add(line);
                        drawType = DrawType.LINE;

                        repaint();
                        //drawLine((int)startNode.getCenterX(),(int)startNode.getCenterY(),(int)shape.getCenterX(),(int)shape.getCenterY());
                        break;
                    }
            }
        }
    }

}


