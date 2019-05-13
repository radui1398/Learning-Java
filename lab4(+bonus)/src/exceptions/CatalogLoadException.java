package exceptions;

public class CatalogLoadException extends Exception{
    public CatalogLoadException(){
        super("Could not load catalog.");
    }
}

