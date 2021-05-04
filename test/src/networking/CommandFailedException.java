/**
 * This exception is thrown when server returns an error. There error form the server will be given as argument to te super class.
 * @author Dantal.B
 */
package networking;

public class CommandFailedException extends Exception{

    public CommandFailedException(String massege){
        super(massege);
    }
}
