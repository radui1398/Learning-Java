import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable{
    protected Catalog catalog;
    Command(Catalog catalog){
        this.catalog = catalog;
    }
    public abstract void run(String[] args) throws Exception;
}

