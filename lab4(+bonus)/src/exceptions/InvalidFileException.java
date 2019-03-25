package exceptions;

public class InvalidFileException extends Exception{
    public InvalidFileException(){
        super("File does not exist.");
    }

    public InvalidFileException(String s) {
        super(s);
    }
}
