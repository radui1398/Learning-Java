package exceptions;

public class CatalogOpenException extends Exception{
    public CatalogOpenException(){
        super("Could not open file.");
    }
    public CatalogOpenException(String s){
        super(s);
    }
}
