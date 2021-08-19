package exceptions;

public class FibonacciException extends RuntimeException{

    public FibonacciException(String error){
        super(error);
    }
}
