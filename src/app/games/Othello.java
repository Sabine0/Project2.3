package app.games;

import app.Main;
import app.games.gameobjects.OthelloBoard;
import app.model.Bot;
import app.model.Player;
import app.model.UserPlayer;
import app.state.GameState;
import app.state.MainMenuState;
import javafx.scene.Parent;

/**
 * The Othello class holds the game logic for Othello
 * @author Sabine Schreuder
 * @version 05-04-21
 */
// TO DO: implement game logic for Othello
public class Othello extends GameState {
    private boolean online;
    private boolean p1turn;
    private OthelloBoard othelloBoard;
    private Player p1;
    private Player p2;

    /**
     * @param online Boolean indicating if the game is online or not
     * @param playerOneHuman Boolean indicating if player one is human
     * @param playerTwoHuman Boolean indicating if player two is human
     */
    public Othello(boolean online, boolean playerOneHuman, boolean playerTwoHuman,
                   boolean appUserPlayer1, String appPlayerUsername, String opponentUsername) { // TO DO: missing username param
        othelloBoard = new OthelloBoard();
        this.online = online;

        if(appUserPlayer1 && playerOneHuman){
            p1 = new UserPlayer(appPlayerUsername);

            if(playerTwoHuman) {
                p2 = new UserPlayer(opponentUsername);
            }else{
                p2 = new Bot(opponentUsername);
            }
        }else if(!playerOneHuman){
            p1 = new Bot(appPlayerUsername);
        }

        if(!appUserPlayer1 && playerOneHuman){
            p2 = new UserPlayer(appPlayerUsername);

            if(playerTwoHuman) {
                p1 = new UserPlayer(opponentUsername);
            }else{
                p1 = new Bot(opponentUsername);
            }
        }else if(!playerOneHuman){
            p2 = new Bot(appPlayerUsername);
        }

        if(online){
            launchOnline();
        }else{
            launchLocal();
        }
    }
    /**
     *  Launch an online variant of Othello
     */
    public void launchOnline(){
        getView();

        // TO DO: implement online match

    }

    /**
     *  Launch a local variant of Othello
     */
    @Override
    public void launchLocal() {
        getView();
        System.out.println(p1.getUsername() + " starts the game!");

        // Player 1 always goes first in a local match
        p1turn = true;

        //If player is human
        if (p1.isHuman() || p2.isHuman()) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    int c = col;
                    int r = row;
                    othelloBoard.getTile(c, r).setOnMouseClicked(event -> {
                        if ((p1.isHuman()  && p1turn) || (p2.isHuman() && !p1turn)) {
                            if (isValidMove(c, r)) {
                                drawMove(c, r);
                                if (isWon()){
                                    // TO DO: Display a button, upon clicked return to the main menu
                                    Main.setState(new MainMenuState());
                                }
                            }
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

    /**
     * @param col Column in board
     * @param row Row in board
     */
    public void drawMove(int col, int row){
        if(p1turn){
            othelloBoard.drawMoveBlack(col, row);
            p1turn = false;
            othelloBoard.getTile(col, row).setValid();
            System.out.println(p2.getUsername()+"s turn"); // for testing only
        }else{
            othelloBoard.drawMoveWhite(col, row);
            p1turn = true;
            othelloBoard.getTile(col, row).setValid();
            System.out.println(p1.getUsername()+"s turn"); // for testing only
        }
    }

    /**
     * @param col Col in the board
     * @param row Row in the board
     * @return Boolean if move is valid
     */
    public boolean isValidMove(int col, int row){
        return othelloBoard.getTile(col, row).getValid();
    }

    /**
     * @return Boolean if the game has been won by someone
     */
    public boolean isWon(){
        return false;
    }


    /**
     * @return the view of a new Othello object
     */
    @Override
    public Parent getView() {
        return othelloBoard.boardView();
    }
}
