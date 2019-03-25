package com.company.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Class that describes a node
 */
public class NodeShape extends Ellipse2D.Double {
    /**
     * Constructor for node shape.
     * @param x0 - the position where the node is to be drawn
     * @param y0 - the position where the node is to be drawn
     * @param radius - the radius of the node
     */
    public NodeShape(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }

    private Color color;
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){this.color=color;}
}