package com.lab5.ui.command;
import com.lab5.ui.Catalog;
import com.lab5.ui.Generate;

import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable{
    private Catalog catalog;

    public Command(Catalog catalog){
        this.catalog = catalog;
    }

    public abstract void run(String[] args) throws IllegalArgumentException, IOException;


    public Catalog getCatalog() {
        return catalog;
    }
}