package exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(){
        super("Invalid command.");
    }
    public InvalidCommandException(String s){
        super(s);
    }
}
