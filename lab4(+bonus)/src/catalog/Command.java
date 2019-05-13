package catalog;

import java.io.IOException;
import java.io.Serializable;
import catalog.Catalog;

public abstract class Command implements Serializable{
    Catalog catalog;

    Command(Catalog catalog){
        this.catalog = catalog;
    }

    public abstract void run(String[] args) throws IllegalArgumentException, IOException;
}

