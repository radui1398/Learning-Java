package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;

/**
 * Toolbar for options in interface.
 */
class Toolbar extends JPanel implements ButtonSelfAdder, Serializable{

    private DrawingFrame parent;

    private JTextField nrShapesTextField;
    private JTextField colorTextField;


    Toolbar(DrawingFrame parent){
        this.parent = parent;
        this.setLayout(new FlowLayout());

        this.add(getButton("draw"));

        nrShapesTextField = this.getTextField("Number of nodes:", 5);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int count = 0;

        switch (actionEvent.getActionCommand()){
            case "draw":

                try {
                    count = Integer.valueOf(nrShapesTextField.getText());

                }
                catch (NumberFormatException e){
                    System.err.println("Invalid value. (count)");
                    return;
                }

                parent.draw(count);
                break;

            default:
                throw new IllegalArgumentException("Event not handled: " + actionEvent.getActionCommand());
        }
    }

    private JTextField getTextField(String text, int size){
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        JTextField textField = new JTextField(size);

        label.setLabelFor(textField);

        panel.add(label);
        panel.add(textField);

        this.add(panel);

        return textField;
    }
}
