package exceptions;

public class VelocityException extends Exception {
    public VelocityException(){
        super("File does not exist.");
    }

    public VelocityException(String s) {
        super(s);
    }
}
