package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Main window of the application.
 */
public class DrawingFrame extends JFrame implements Serializable {

    static final String savePath = "drawing.ser";

    private DrawingFrame(){
        initUI();
    }

    private static class SingletonHelper{
        private static final DrawingFrame instance = new DrawingFrame();
    }

    public static DrawingFrame getInstance(){
        return SingletonHelper.instance;
    }

    private Canvas canvas;
    private Toolbar toolbar;
    private ControlPanel controlPanel;

    /**
     * Initializes the content of the window.
     */
    private void initUI(){
        setLayout(new BorderLayout());

        setTitle("Draw here");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        canvas = new Canvas(this);
        toolbar = new Toolbar(this);
        controlPanel = new ControlPanel(this);

        this.add(canvas, BorderLayout.CENTER);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the number of shapes to be drawn
     * @param count - the number of shapes
     */
    void draw(Integer count) {
        canvas.drawRandomShapes(count);
    }

    /**
     * Resets the content of the canves
     */
    void reset() {
        canvas.reset();
    }

    /**
     * Saves the content of the canvs.
     * @throws IOException
     */
    void save() throws IOException {
        canvas.save();
    }

    void load() throws IOException {
        canvas.load();
    }
}

