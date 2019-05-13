package exceptions;

public class InvalidArgumentsException extends Exception{

    public InvalidArgumentsException(){
        super();
    }

    public InvalidArgumentsException(String s){
        super(s);
    }
}