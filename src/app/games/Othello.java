package app.games;

import app.state.GameState;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Othello extends GameState {
    private boolean online;
    private boolean playerOneHuman;
    private boolean playerTwoHuman;

    public Othello(boolean online, boolean playerOneHuman, boolean playerTwoHuman) {
        super(online, playerOneHuman, playerTwoHuman);
        this.online = online;
        this.playerOneHuman = playerOneHuman;
        this.playerTwoHuman = playerTwoHuman;
    }

    public void launchOnline(){
        if(playerOneHuman){
            // start server connection + run online multiplayer game
        }else{
            if(playerTwoHuman){
                // start server connection + run online AI vs AI game
            }else{
                // start server connection + run online player vs AI game
            }
        }
    }

    @Override
    public void launchLocal() {
        if(playerOneHuman){
            // run local CO-OP game
        }else{
            // run player vs AI game
        }
    }

    @Override
    public Parent getView() {
        // return board
        return new Label("Othello");
    }
}
