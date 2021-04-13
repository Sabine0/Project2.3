/**
 * This class is meant to keep track of incoming input from de server. This class is constantly listening for unexpected input
 * if there is any it wil notify the relevant class.
 * @author Danial.B
 */
package app.networking;

import java.util.HashMap;

public class Notifier implements Runnable{

    Connection connection;
    public Notifier(Connection connection){
     this.connection = connection;
    }

    public HashMap call(String s){
        return toHashMap(s);
    }

    @Override
    public void run() {
        while (true){
            if(connection.checkForNotification()==true) {
                String notification = connection.readNotification();
                System.out.println("processing notification " + notification);

                if(notification.startsWith("SVR GAME MATCH")){
                         //een match aangeboden een methode met 3 argumenten
                    // lobbyView.startMatch(game,playerToMove, opponentUsername); will return gameState
                    // this.gameState = gameState;
                }else if(notification.startsWith(" SVR GAME YOURTURN")){
                         // beurt toegewezen krijgen methode met een argument
                    // gameState.doMoveOnline(); will call move <int>
                }else if(notification.startsWith("SVR GAME MOVE")){
                        // resultaat van een zet ontvangen methode met 3 argumenten
                    // parameter with the move you get from the server -> board.convertMove(int move) will return int[]
                    // parameter with the player name
                    // if move not illegal: board.drawMove(String player, int col = move[0], int row = move[1]);
                    // if move illegal: gameState.showWinAlert(winner)
                }else if(notification.startsWith("SVR GAME CHALLENGE")){
                        // een challenge ontvangen methode met 3 argumenten
                    // lobbyView.showChallengeAlert()
                }else if(notification.startsWith("SVR GAME CHALLENGE CANCELLED")){
                       // challenge cancelled methode met een argument
                    // lobbyView.exitWaitingForOpponent()
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
