/**
 * This exception is thrown when there is no response from the server.
 */

public class ServerNotRespondingException extends Exception{

    public ServerNotRespondingException(String messege){
        super(messege);
    }
}
