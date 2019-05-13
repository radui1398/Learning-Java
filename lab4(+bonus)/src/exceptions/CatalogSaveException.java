package exceptions;

public class CatalogSaveException extends Exception{
    public CatalogSaveException(){
        super("Could not save catalog.");
    }
    public CatalogSaveException(String s){
        super(s);
    }
}

