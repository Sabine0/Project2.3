/**
 * This class is meant for processing the communication between the server and client
 * it is the intention for this class to be the interface of the network and hide the underlying the parts of the
 * network package for teh rest of the application
 * @author Danial.B
 * @version 1.6
 */
package app.networking;

public class Processor {

    Thread tNotifier;
    Connection connection;
    // tweede argumet is UI
    public Processor(Connection connection){
          this.connection = connection;
          tNotifier = new Thread(new Notifier(connection));
    }

    public void start(){
        // thread maken  voor readArray en die naar de ui toe sturen
    }

    /**
     * @return the available games in an array
     * @throws ServerNotRespondingException if disconnected or no response from the server
     */
    public String[] getGamelsit() throws ServerNotRespondingException, CommandFailedException {
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
        connection.write("get playerlist");

        return toStringArray(connection.readDubbleLine());
    }

    /**
     *  logins you into the server so other players can challenge you
     * @param name name of the player
     */
    public void login(String name) throws ServerNotRespondingException, CommandFailedException {

        connection.write("login "+ name);
        if (connection.readSingleLine().equals("OK")){

        }else{

        }

    }

    public void leaveTheMatch(){
        connection.write("forfeit");
    }

    public void move(int location) throws ServerNotRespondingException, CommandFailedException {
          connection.write("move " + location);
          connection.readSingleLine();
    }

    public void setChallengeAccept(int challengeNumber) throws ServerNotRespondingException, CommandFailedException {
        connection.write("challenge accept " + challengeNumber);
        connection.readSingleLine();
    }

    public void subscribe(String game) throws ServerNotRespondingException, CommandFailedException {
        connection.write("subscribe " + game);
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