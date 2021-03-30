package app.games;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public class TicTacToe extends GameState {
    // game logic
    boolean online = false;
    boolean multiplayer;
    boolean playerOneHuman;

    public TicTacToe(boolean multiplayer, boolean online, boolean playerOneHuman) {
        super(3, 3, multiplayer, online);
        this.online = online;
        this.multiplayer = multiplayer;
        this.playerOneHuman = playerOneHuman;
    }

    public void launchOnline(boolean multiplayer){
        if(multiplayer){
            // start server connection + run online multiplayer game
        }else{
            if(playerOneHuman){
                // start server connection + run online AI vs AI game
            }else{
                // start server connection + run online player vs AI game
            }
        }
    }

    @Override
    public void launchLocal(boolean multiplayer) {
        if(multiplayer){
            // run local CO-OP game
        }else{
            // run player vs AI game
        }
    }

    @Override
    public Parent getView() {
        // return board
        return new Label("TicTacToe");
    }
}
