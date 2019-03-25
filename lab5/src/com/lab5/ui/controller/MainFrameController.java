package com.lab5.ui.controller;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.lab5.ui.Catalog;
import com.lab5.ui.ExtensionException;
import com.lab5.ui.Generate;
import com.lab5.ui.Graph;
import com.lab5.ui.command.Command;
import com.lab5.ui.view.MainFrame;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainFrameController {

    private MainFrame mainFrame;
    private JTextField graphName;
    private JTextField nrOfNodes;
    private JTextField nrOfVertices;
    private JButton sendInfo;
    private Catalog catalog;
    private Graph graph;
    private JButton defPath;
    private JButton list;
    private JList listOfGraphs;
    private JRadioButton simple;
    private JButton imgPath;
    private DefaultListModel model;
    private JButton openDef;
    private JButton openImg;
    private JButton saveCatalog;
    private JButton loadCatalog;
    private JButton generateReport;
    private JTable graphTable;
    private JPopupMenu popup;

    public MainFrameController() {
        Catalog catalog = new Catalog("/home/radu/graphs");
        Graph graph = new Graph();
        this.graph = graph;
        this.catalog = catalog;
        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        graphName = mainFrame.getGraphName();
        nrOfNodes = mainFrame.getNrNodes();
        nrOfVertices = mainFrame.getNrVertices();
        sendInfo = mainFrame.getSendInfo();
        defPath = mainFrame.getDefPath();
        list = mainFrame.getListGraphs();
        listOfGraphs = mainFrame.getListOfGraphs();
        simple = mainFrame.getSimple();
        imgPath = mainFrame.getImgPath();
        openDef = mainFrame.getOpenDef();
        openImg = mainFrame.getOpenImg();
        saveCatalog = mainFrame.getSaveCatalog();
        loadCatalog = mainFrame.getLoadCatalog();
        graphTable = mainFrame.getGraphTable();
        generateReport = mainFrame.getDoReport();
        initPopup();

        this.model = new DefaultListModel();
        listOfGraphs.setModel(model);
        mainFrame.setSize(400, 500);
    }


    private void initPopup() {
        // build poup menu
        popup = new JPopupMenu();
        // Image Location
        JMenuItem menuItem = new JMenuItem("Image Location");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Image Location");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, graph.getImg(),catalog.getGraphs().get(listOfGraphs.getSelectedIndex()).getName(),JOptionPane.PLAIN_MESSAGE);
            }
        });
        popup.add(menuItem);
        // Def Location
        menuItem = new JMenuItem("Def Location");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Def Location");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, graph.getTgf(),catalog.getGraphs().get(listOfGraphs.getSelectedIndex()).getName(),JOptionPane.PLAIN_MESSAGE);
            }
        });
        popup.add(menuItem);
        // Search on Web
        menuItem = new JMenuItem("Search on web");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Search on web");

        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://www.google.com/search?q=" + graph.getName() + "+graph+theory");
                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    if (desktop != null)
                        desktop.browse(uri);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (URISyntaxException use) {
                    use.printStackTrace();
                }
            }
        });
        popup.add(menuItem);

        // add mouse listener
        listOfGraphs.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (!listOfGraphs.isSelectionEmpty())
                {
                    if (e.isPopupTrigger()) {
                        graph = catalog.getGraphs().get(listOfGraphs.getSelectedIndex());
                        popup.show(e.getComponent(),
                                e.getX(), e.getY());
                    }
                }
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setVisible(true);
    }

    private void initListeners() {
        clearOnClick(graphName);
        clearOnClick(nrOfNodes);
        clearOnClick(nrOfVertices);
        sendInfo.addActionListener(new SendInfoListener());
        defPath.addActionListener(new GetDefPath());
        imgPath.addActionListener(new GetImgPath());
        openDef.addActionListener(new OpenDefinition());
        openImg.addActionListener(new OpenImage());
        saveCatalog.addActionListener(new SaveCatalog());
        loadCatalog.addActionListener(new LoadCatalog());
        generateReport.addActionListener(new GenerateReport());
    }

    private void clearOnClick(JTextField element) {
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                element.setText("");
            }
        });
    }

    private class SendInfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            graph.setName(graphName.getText());
            if(simple.isSelected()){
                graph.setType("Simple");
            }
            else{
                graph.setType("Directed");
            }
            graph.setNrOfVertices(parseInt(nrOfVertices.getText()));
            graph.setNrOfNodes(parseInt(nrOfNodes.getText()));
            catalog.add(graph);
            model.addElement(graph);
            graph = new Graph();
            defPath.setEnabled(true);
            defPath.setText("Path to Def");
            imgPath.setEnabled(true);
            imgPath.setText("Path to Img");
        }
    }

    private class GetDefPath implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(catalog.getFolder());
            FileFilter filter = new FileNameExtensionFilter("Definition file", "tgf");
            chooser.setFileFilter(filter);
            int option = chooser.showOpenDialog(mainFrame); // parentComponent must a component like JFrame, JDialog...
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                graph.setTgf(path);
                defPath.setText(path);
                defPath.setEnabled(false);
            }
        }
    }

    private class GetImgPath implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(catalog.getFolder());
            FileFilter filter = new FileNameExtensionFilter("PNG file", "png");
            chooser.setFileFilter(filter);
            int option = chooser.showOpenDialog(mainFrame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                graph.setImg(path);
                imgPath.setText(path);
                imgPath.setEnabled(false);
            }
        }
    }

    private class OpenImage implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showMessageDialog(null,"Nu am implementat inca!");
        }
    }

    private class OpenDefinition implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = listOfGraphs.getSelectedIndex();
            if(index != -1){
                try {
                    catalog.open(index);
                }catch(ExtensionException ex1){
                    showMessageDialog(null, "Path-ul pentru definitie este gresit!");
                }
            }
            else
            showMessageDialog(null,"Nu ai selectat nici un graf!");
        }
    }
    private class SaveCatalog implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(catalog.getFolder());
            FileFilter filter = new FileNameExtensionFilter("CTG file", "ctg");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                File file;
                if (!fileChooser.getSelectedFile().toString().endsWith(".ctg")) {
                    file = new File(fileChooser.getSelectedFile().toString() + ".ctg");
                }
                else {
                    file = fileChooser.getSelectedFile();
                }
                try {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(catalog);
                    objectOut.close();
                    showMessageDialog(null,"Salvarea a reusit!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showMessageDialog(null,"Salvarea nu a reusit!");
                }

            }
            else
                showMessageDialog(null,"Salvarea nu a reusit!");
        }
    }

    private class LoadCatalog implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(catalog.getFolder());
            FileFilter filter = new FileNameExtensionFilter("CTG file", "ctg");
            chooser.setFileFilter(filter);
            int option = chooser.showOpenDialog(mainFrame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    FileInputStream fileIn = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    catalog = (Catalog) in.readObject();
                    model.clear();
                    model.addAll(catalog.list());
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("Catalog class not found");
                    c.printStackTrace();
                }
            }
        }
    }

    private class GenerateReport implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(catalog.getFolder());
            FileFilter filter = new FileNameExtensionFilter("HTML file", "html");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                File file;
                if (!fileChooser.getSelectedFile().toString().endsWith(".html")) {
                    file = new File(fileChooser.getSelectedFile().toString() + ".html");
                }
                else {
                    file = fileChooser.getSelectedFile();
                }
                Generate generator = new Generate(catalog,file.getPath());
                generator.reportHTML();

            }
            else
                showMessageDialog(null,"Generarea a reusit!");
        }
    }
}

