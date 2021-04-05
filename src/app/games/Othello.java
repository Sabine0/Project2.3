package app.games;

import app.games.gameobjects.OthelloBoard;
import app.model.Bot;
import app.model.Player;
import app.model.UserPlayer;
import app.state.GameState;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Othello extends GameState {
    // game logic in this class (methods)
    private boolean online;
    private boolean p1turn;
    private OthelloBoard othelloBoard;
    private Player p1;
    private Player p2;

    public Othello(boolean online, boolean playerOneHuman, boolean playerTwoHuman) { // TO DO: missing username param
        super(online, playerOneHuman, playerTwoHuman);
        othelloBoard = new OthelloBoard();
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

        if (p1.isHuman() || p2.isHuman()) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    int c = col;
                    int r = row;
                    othelloBoard.getTile(c, r).setOnMouseClicked(event -> {
                        if (p1turn && p1.isHuman()) {
                            othelloBoard.doMoveBlack(c, r);
                            p1turn = false;
                        } else if (!p1turn && p2.isHuman()) {
                            othelloBoard.doMoveWhite(c, r);
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
            // TO DO: Get move from AI (row and column)
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
        // return board
        return othelloBoard.boardView();
    }
}
