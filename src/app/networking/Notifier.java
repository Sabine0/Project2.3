/**
 * This class is meant to keep track of incoming input from de server. This class is constantly listening for unexpected input
 * if there is any it wil notify the relevant class.
 * @author Danial.B
 */
package app.networking;
import javafx.application.Platform;
import app.state.games.GameState;
import app.view.LobbyView;
import app.view.gameobjects.Board;
import org.w3c.dom.ls.LSOutput;


import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Notifier implements Runnable{

    private Connection connection;
    LobbyView lobbyView;
    GameState gameState;
    Board board;

    private Logger ntLogger = Logger.getLogger("NetworkLogger");
    public Notifier(Connection connection, LobbyView lobbyView){
     this.connection = connection;
     this.lobbyView = lobbyView;
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
                System.out.println("processing notification " + notification);

                if(notification.startsWith("SVR GAME MATCH")){
                         //een match aangeboden een methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // In the methode run you can change the javaFx threads. Use this lambda anytime you want to
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME MATCH. calling lobbyView ");
                    // make change to javafx thread.
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            gameState = lobbyView.startMatch(hashMap.get("GAMETYPE"), hashMap.get("PLAYERTOMOVE"), hashMap.get("OPPONENT")); // will return gameState
                            board = gameState.getBoard();
                        }
                    });
                    System.out.println("SERVER IS STARTING GUI FOR GAME");
                }else if(notification.startsWith("SVR GAME YOURTURN")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME YOURTURN. calling gameState.doMoveOnline() ");
                    System.out.println("test your turn statement");
                         // beurt toegewezen krijgen methode met een argument
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // make change to javafx thread.
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            try {
                                gameState.doMoveOnline(); // will call move <int>
                            } catch (ServerNotRespondingException e) {
                                e.printStackTrace();
                            } catch (CommandFailedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else if(notification.startsWith("SVR GAME MOVE")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME YOURTURN. calling gameState.doMoveOnline() ");
                    System.out.println("test game move statement");
                        // resultaat van een zet ontvangen methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);
                    // parameter with the move you get from the server -> board.convertMove(int move) will return int[]
                    // parameter with the player name
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            int[] move = board.convertMove(Integer.parseInt(hashMap.get("MOVE")), board.getBoardSize());
                            // if move not illegal: board.drawMove(String player, int col = move[0], int row = move[1]);
                            if (!hashMap.get("DETAILS").equals("Illegal move")){
                                board.drawMove(hashMap.get("PLAYER"), move[0], move[1]);
                            }
                        }
                    });

                }else if(notification.startsWith("SVR GAME CHALLENGE")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME CHALLENGE. calling gameState.doMoveOnline(lobbyView.showChallengeAlert ");
                        // een challenge ontvangen methode met 3 argumenten
                    HashMap<String, String> hashMap = toHashMap(notification);

                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            lobbyView.showChallengeAlert(hashMap.get("CHALLENGER"), Integer.parseInt(hashMap.get("CHALLENGENUMBER")));
                        }
                    });

                }else if(notification.startsWith("SVR GAME CHALLENGE CANCELLED")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME CHALLENGE CANCELLED. calling lobbyView.exitWaitingForOpponent();");
                       // challenge cancelled methode met een argument
                    HashMap<String, String> hashMap = toHashMap(notification);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            lobbyView.exitWaitingForOpponent();
                            lobbyView.showChallengeDeclinedAlert(Integer.parseInt(hashMap.get("CHALLENGENUMBER")));
                        }
                    });

                }else if(notification.startsWith("SVR GAME WIN")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME WIN. calling  board.showWinAlert(Integer.parseInt");
                    HashMap<String, String> hashMap = toHashMap(notification);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            board.showWinAlert(Integer.parseInt(hashMap.get("PLAYERONESCORE")), Integer.parseInt(hashMap.get("PLAYERONESCORE")));
                        }
                    });

                }else if(notification.startsWith("SVR GAME LOSE")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME LOSE. calling  board.showWinAlert(Integer.parseInt");
                    HashMap<String, String> hashMap = toHashMap(notification);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            board.showWinAlert(Integer.parseInt(hashMap.get("PLAYERONESCORE")), Integer.parseInt(hashMap.get("PLAYERONESCORE")));
                        }
                    });

                }else if(notification.startsWith("SVR GAME DRAW")){
                    ntLogger.log(Level.INFO,  " - ntf matched with: SVR GAME DRAW. calling  board.showDrawAlert()");
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            board.showDrawAlert();
                        }
                    });

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
