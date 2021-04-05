package app.networking;

public class ServerNotRespondingException extends Exception{

    public ServerNotRespondingException(String massage){
        super(massage);
    }
}
