package app.networking;

public class serverNotRespondingException extends Exception{

    public serverNotRespondingException(String message){
        super(message);
    }
}
