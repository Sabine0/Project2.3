/**
 * This class is meant for processing the communication between the server and client
 * it is the intention for this class to be the interface of the network and hide the underlying the parts of the
 * network package for teh rest of the application
 * @author Danial.B
 * @version 1.6
 */
package app.networking;

public class Processor {
     Connection connection;
    // tweede argumet is UI
    public Processor(Connection connection){
          this.connection = connection;
    }

    public void start(){
        // thread maken  voor readArray en die naar de ui toe sturen
    }

    /**
     * @return the available games in an array
     * @throws serverNotRespondingException if disconnected or no response from the server
     */
    public String[] getGamelsit() throws serverNotRespondingException{
        connection.write("get gamelist");
        String gameList = connection.read();
        System.out.println("proccesor" + gameList);
        return toStringArray(gameList);
    }

    /**
     * @return an array in the player names
     * @throws serverNotRespondingException if disconnected or no response from the server
     */
    public String[] getPlayerList() throws serverNotRespondingException{
        connection.write("get playerlist");

        return toStringArray(connection.read());
    }

    /**
     *  logins you into the server so other players can challenge you
     * @param name name of the player
     */
    public void login(String name){
        connection.write("login "+ name);
    }

    public void leaveTheMatch(){
        connection.write("forfeit");
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
