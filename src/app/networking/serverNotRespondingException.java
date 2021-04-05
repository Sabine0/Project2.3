package app.networking;

public class serverNotRespondingException extends Exception{
    public serverNotRespondingException(String massage){
        super(massage);
    }
}
