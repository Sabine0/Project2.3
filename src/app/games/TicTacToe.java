package app.games;

import app.games.gameobjects.TicTacToeBoard;
import app.games.gameobjects.TicTacToeTile;
import app.model.Bot;
import app.model.Player;
import app.model.UserPlayer;
import app.state.GameState;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;

public class TicTacToe extends GameState {
    // game logic
    boolean online;
    TicTacToeBoard tttBoard;
    boolean p1turn;
    Player p1;
    Player p2;

    public TicTacToe(boolean online, boolean playerOneHuman, boolean playerTwoHuman) { // TO DO: missing username param
        super(online, playerOneHuman, playerTwoHuman);
        tttBoard = new TicTacToeBoard();
        this.online = online;

        p1turn = true;

        if(playerOneHuman){
            p1 = new UserPlayer(""); // TO DO: username from constructor param
        }else{
            p1 = new Bot("Victor");
        }

        if (playerTwoHuman){
            p2 = new UserPlayer(""); // TO DO: username from constructor param
        }else {
            p2 = new Bot("Victor");
        }

        if(online){
            launchOnline();
        }else{
            launchLocal();
        }
    }

    public void launchOnline(){
        getView();

        // TO DO: implement online match
    }

    @Override
    public void launchLocal() {
        getView();

        // TO DO: EVERYTHING BELOW DOESNT WORK YET FOR SOME REASON

        //If player is human
        if (p1.isHuman() || p2.isHuman()) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int c = col;
                    int r = row;
                    tttBoard.getTile(c, r).setOnMouseClicked(event -> {
                        if (p1turn) {
                            tttBoard.doMoveX(c, r);
                            p1turn = false;
                        } else if (!p1turn) {
                            tttBoard.doMoveO(c, r);
                            p1turn = true;
                        } else {
                            // Do not respond to clicks
                        }
                    });
                }
            }
        }

        //If player is bot
        if (!p1.isHuman() || !p2.isHuman()) {
            // Get move from AI: row and column
            // Code here

            if (p1turn) {
                // Do move on coordinates given by AI
//                tttBoard.doMoveX(r, c);
                p1turn = false;
            } else {
                // Do move on coordinates given by AI
//                tttBoard.doMoveO(r, c);
                p1turn = true;
            }
        }
    }

    @Override
    public Parent getView() {
        return tttBoard.boardView();
    }
}
