package com.lab5.ui.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private JPanel mainPanel;
    private JTextField graphName;
    private JButton defPath;
    private JButton imgPath;
    private JTextField nrNodes;
    private JTextField nrVertices;
    private JButton sendInfo;
    private JButton listGraphs;
    private JRadioButton simple;
    private JRadioButton directed;
    private JList listOfGraphs;
    private JButton openDef;
    private JButton openImg;
    private JButton saveCatalog;
    private JButton loadCatalog;
    private JTable graphTable;
    private JButton doReport;
    private JPopupMenu popupMenu;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        listOfGraphs.setBackground(Color.darkGray);
        listOfGraphs.setForeground(Color.white);
        simple.setSelected(true);
    }

    public JTextField getGraphName() {
        return graphName;
    }

    public JTextField getNrNodes() {
        return nrNodes;
    }

    public JTextField getNrVertices() {
        return nrVertices;
    }

    public JButton getSendInfo() {
        return sendInfo;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getDefPath() {
        return defPath;
    }

    public JButton getListGraphs() {
        return listGraphs;
    }

    public JButton getImgPath() {
        return imgPath;
    }

    public JRadioButton getSimple() {
        return simple;
    }

    public JRadioButton getDirected() {
        return directed;
    }

    public JList getListOfGraphs() {
        return listOfGraphs;
    }

    public JButton getOpenDef() {
        return openDef;
    }

    public JButton getOpenImg() {
        return openImg;
    }

    public JButton getSaveCatalog() {
        return saveCatalog;
    }

    public JButton getLoadCatalog() {
        return loadCatalog;
    }

    public JTable getGraphTable() {
        return graphTable;
    }

    public JButton getDoReport() {
        return doReport;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
}

