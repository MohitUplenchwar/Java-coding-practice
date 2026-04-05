/*
 * Create a Custom Exception in Java.
 */

package corejava.exceptionHandling;

public class NoElementPresentException extends RuntimeException{

    public NoElementPresentException(String message){
        super(message);
    }
}
