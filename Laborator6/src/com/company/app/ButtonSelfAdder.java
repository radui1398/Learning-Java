package com.company.app;

import javax.swing.*;
import java.awt.event.ActionListener;

interface ButtonSelfAdder extends ActionListener {
    default JButton getButton(String text){

        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setActionCommand(text);

        return button;
    }
}

