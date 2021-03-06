/**
 * This class is meant for processing the communication between the server and client
 * it is the intention for this class to be the interface of the network and hide the underlying the parts of the
 * network package for teh rest of the application
 * @author Danial.B
 * @version 1.6
 */
package networking;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Processor {

    Thread tNotifier;
    Connection connection;

    private Logger ntLogger = Logger.getLogger("NetworkLogger");

    // tweede argumet is UI
    public Processor(Connection connection){
          this.connection = connection;


          tNotifier = new Thread(new Notifier(connection, this));
          tNotifier.start();

    }

    public void start(){
        // thread maken  voor readArray en die naar de ui toe sturen
    }


    /**
     * @return the available games in an array
     * @throws ServerNotRespondingException if disconnected or no response from the server
     */
    public String[] getGamelist() throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO,"getGamelist() is called"  );
        connection.write("get gamelist");
        String gameList = connection.readDubbleLine();
        System.out.println("proccesor" + gameList);
        return toStringArray(gameList);
    }

    /**
     * @return an array in the player names
     * @throws ServerNotRespondingException if disconnected or no response from the server
     */
    public String[] getPlayerList() throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - getPlayerList() is called"  );
        connection.write("get playerlist");

        return toStringArray(connection.readDubbleLine());
    }

    /**
     *  logins you into the server so other players can challenge you
     * @param name name of the player
     */
    public void login(String name) throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - login() is called. arg: " + name );
        connection.write("login "+ name);
        if (connection.readSingleLine().equals("OK")){

        }else{

        }

    }

    public void leaveTheMatch(){
        connection.write("forfeit");
    }

    public void move(int location) throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - move() is called. arg: " + location  );
          connection.write("move " + location);
          connection.readSingleLine();
    }

    /**
     * Accepts the challenge with the specified  challenge number
     * @param challengeNumber
     * @throws ServerNotRespondingException if there is no response from the server.
     * @throws CommandFailedException if server returns an error.
     */
    public void setChallengeAccept(int challengeNumber) throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - setChallengeAccept() is called. arg: " + challengeNumber  );
        connection.write("challenge accept " + challengeNumber);
        connection.readSingleLine();
    }

    /**
     *
     * @param game  to subscribe
     * @throws ServerNotRespondingException if there is no response from the server.
     * @throws CommandFailedException  if server returns an error.
     */
    public void subscribe(String game) throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - subscribe() is called. arg: " + game  );
        connection.write("subscribe " + game);
        connection.readSingleLine();
    }

    /**
     *  challenge a player in the list
     * @param playerName
     * @param gameType
     * @throws ServerNotRespondingException if there is no response from the server.
     * @throws CommandFailedException if server returns an error.
     */
    public void challegengePlayer(String playerName, String gameType) throws ServerNotRespondingException, CommandFailedException {
        ntLogger.log(Level.INFO," - challegengePlayer() is called. args: playerName: " + playerName + "gametype: " + gameType );
        connection.write("challenge "+ "\"" + playerName + "\""+ " " + "\""+ gameType+ "\"");
        connection.readSingleLine();
    }

    /**
     * This method converts a String to an array
     * @param input
     * @return array of the give string values
     */
    private String[] toStringArray(String input){
        String[] stringArray;
        input = input.substring(input.indexOf("[") + 1, input.indexOf("]"));
        input = input.replace("\"", "");
        stringArray = input.split(", ");

        return stringArray;
    }
}