/**
 * This class is meant to keep track of incoming input from de server. This class is constantly listening for unexpected input
 * if there is any it wil notify the relevant class.
 * @author Danial.B
 */
import com.sun.jdi.IntegerValue;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;


import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notifier implements Runnable{

    private Connection connection;
    private int turn;
    private AI ai;
    private Logger ntLogger = Logger.getLogger("NetworkLogger");
    private Processor processor;
    private Board board;
    private Stage primaryStage;

    public Notifier(Connection connection, Processor processor){//networking.AI ai
        this.processor = processor;
        this.ai = null;
        this.connection = connection;
    }

    public HashMap call(String s){
        return toHashMap(s);
    }

    @Override
    public void run() {
        while (true){
            if(connection.isNotification()) {
                String notification = connection.readNotification();
                ntLogger.log(Level.INFO,  " - processing notification " + notification);
                //System.out.println("processing notification " + notification);

                if(notification.startsWith("SVR GAME MATCH")){
                    board = new Board();
                    primaryStage = new Stage();


                    //een match aangeboden een methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // In the methode run you can change the javaFx threads. Use this lambda anytime you want to
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME MATCH. calling lobbyView ");
                    // make change to javafx thread.
                   if(hashMap.get("PLAYERTOMOVE").equals("yannick")) {
                      this.ai = new AI(1,2);
                      turn = 2;
                   }else{
                       this.ai = new AI(2,1);
                       turn = 1;
                   }
                    //System.out.println("SERVER IS STARTING GUI FOR GAME");
                }else if(notification.startsWith("SVR GAME YOURTURN")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME YOURTURN. calling gameState.doMoveOnline() ");
                    //System.out.println("test your turn statement");
                         // beurt toegewezen krijgen methode met een argument
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // make change to javafx thread.
                    if(ai != null){
                        try {
                            int move = ai.getMove();
                            if (move != -1){
                                processor.move( move);
                            }
                            ai.printBoard();
                        } catch (ServerNotRespondingException e) {
                            e.printStackTrace();
                        } catch (CommandFailedException e) {
                            e.printStackTrace();
                        }

                    }
                }else if(notification.startsWith("SVR GAME MOVE")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME YOURTURN. calling gameState.doMoveOnline() ");
                    //System.out.println("test game move statement");
                        // resultaat van een zet ontvangen methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // parameter with the move you get from the server -> board.convertMove(int move) will return int[]
                    // parameter with the player name
                 if(ai != null){
                    ai.setMove((Integer.parseInt(hashMap.get("MOVE"))),turn);
                 }

                }else if(notification.startsWith("SVR GAME CHALLENGE")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME CHALLENGE. calling gameState.doMoveOnline(lobbyView.showChallengeAlert ");
                        // een challenge ontvangen methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);


                }else if(notification.startsWith("SVR GAME CHALLENGE CANCELLED")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME CHALLENGE CANCELLED. calling lobbyView.exitWaitingForOpponent();");
                       // challenge cancelled methode met een argument
                    HashMap<String, String> hashMap = toHashMap(notification);


                }else if(notification.startsWith("SVR GAME WIN")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME WIN. calling  board.showWinAlert(Integer.parseInt");
                    HashMap<String, String> hashMap = toHashMap(notification);

                }else if(notification.startsWith("SVR GAME LOSS")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME LOSE. calling  board.showWinAlert(Integer.parseInt");
                    HashMap<String, String> hashMap = toHashMap(notification);


                }else if(notification.startsWith("SVR GAME DRAW")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME DRAW. calling  board.showDrawAlert()");

                }
            }
        }
    }

    private HashMap toHashMap(String input){
        HashMap<String, String> map = new HashMap<>();
        input = input.substring(input.indexOf("{") + 1, input.indexOf("}"));
        input = input.replace("\"", "");
        String[] keyValuePairs = input.split(",");
        for(String pair: keyValuePairs){
            String[] key = pair.split(": ");
            if (key.length < 2)
                continue;
            map.put(key[0].trim(), key[1].trim());
        }
        return map;
    }
}
