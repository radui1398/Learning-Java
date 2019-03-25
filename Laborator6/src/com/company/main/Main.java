package com.company.main;


import com.company.app.DrawingFrame;

public class Main {

    public static void main(String[] args) {
        DrawingFrame drawingFrame = DrawingFrame.getInstance();
        drawingFrame.setVisible(true);
    }
}
