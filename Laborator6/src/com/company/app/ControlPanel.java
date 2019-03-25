package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


class ControlPanel extends JPanel implements ActionListener, ButtonSelfAdder {

    private DrawingFrame parent;

    ControlPanel(DrawingFrame parent){
        this.parent = parent;
        this.setLayout(new FlowLayout());

        this.add(getButton("load"));
        this.add(getButton("save"));
        this.add(getButton("reset"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());

        switch (actionEvent.getActionCommand()){
            case "load":
                try {
                    parent.load();
                } catch (IOException e) {
                    System.err.println("Could not load." + e.toString());
                }
                break;

            case "save":
                try {
                    parent.save();
                }
                catch(IOException e){
                    System.err.println(e.toString());
                }
                break;

            case "reset":
                parent.reset();
                break;

            default:
                System.err.println("Invalid action.");
        }
    }
}

