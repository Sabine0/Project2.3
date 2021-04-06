package app.games;

import app.games.gameobjects.TicTacToeBoard;
import app.model.Bot;
import app.model.Player;
import app.model.UserPlayer;
import app.state.GameState;
import javafx.scene.Parent;
/**
 * The TicTacToe class holds the game logic for TicTacToe
 * @author Sabine Schreuder
 * @version 05-04-21
 */
// TO DO: implement game logic for TicTacToe
public class TicTacToe extends GameState {
    private boolean online;
    private TicTacToeBoard tttBoard;
    private boolean p1turn;
    private Player p1;
    private Player p2;

    /**
     * @param online Boolean indicating if the game is online or not
     * @param playerOneHuman Boolean indicating if player one is human
     * @param playerTwoHuman Boolean indicating if player two is human
     */
    public TicTacToe(boolean online, boolean playerOneHuman, boolean playerTwoHuman) { // TO DO: missing username param
        super(online, playerOneHuman, playerTwoHuman);
        tttBoard = new TicTacToeBoard();
        this.online = online;

        p1turn = true;

        if(playerOneHuman){
            p1 = new UserPlayer(""); // TO DO: username from constructor param
        }else{
            p1 = new Bot("Victor BOT");
        }

        if (playerTwoHuman){
            p2 = new UserPlayer(""); // TO DO: username from constructor param
        }else {
            p2 = new Bot("Victor BOT");
        }

        if(online){
            launchOnline();
        }else{
            launchLocal();
        }
    }

    /**
     *  Launch an online variant of TicTacToe
     */
    public void launchOnline(){
        getView();

        // TO DO: implement online match
    }

    /**
     *  Launch a local variant of TicTacToe
     */
    @Override
    public void launchLocal() {
        getView();

        //If player is human
        if (p1.isHuman() || p2.isHuman()) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    int c = col;
                    int r = row;
                    tttBoard.getTile(c, r).setOnMouseClicked(event -> {
                        if (p1turn && p1.isHuman()) {
                            tttBoard.doMoveX(c, r);
                            p1turn = false;
                        } else if (!p1turn && p2.isHuman()) {
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
            // TO DO: Get move from AI: row and column
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

    /**
     * @return the view of a tictactoe board
     */
    @Override
    public Parent getView() {
        return tttBoard.boardView();
    }
}
