package com.lab5.ui;

import com.lab5.ui.Catalog;
import com.lab5.ui.Graph;
import com.lab5.ui.command.Command;

import java.io.*;
import java.nio.file.Path;

/**
 * Commands that outputs a html report for the catalog.
 */
public class Generate {

    /**
     * Creates a html report for the current catalog"
     */

    private Catalog catalog;
    private String path;

    public Generate(Catalog catalog, String path){
        this.path = path;
        this.catalog = catalog;
    }

    public void reportHTML()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>");
            writer.write("Report HTML");
            writer.write("</title>");
            writer.write("</head>");
            writer.write("<body>");

            for (Graph item : catalog.getGraphs())
            {
                writer.write("<p> " + item.toString() + " </p>");
                writer.write("<br>");
            }
            writer.write("</body>");

            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("There was a problem opening the image.");
            e.printStackTrace();
        }
    }

}