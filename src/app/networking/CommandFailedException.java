package app.networking;

public class CommandFailedException extends Exception{

    public CommandFailedException(String massage){
        super(massage);
    }
}
