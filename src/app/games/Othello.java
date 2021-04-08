package app.games;

import app.Main;
import app.games.gameobjects.OthelloBoard;
import app.model.Bot;
import app.model.Player;
import app.model.UserPlayer;
import app.state.GameState;
import app.state.MainMenuState;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;

/**
 * The Othello class holds the game logic for Othello
 * @author Sabine Schreuder
 * @author Luc Willemse
 * @version 05-04-21
 */
// TO DO: implement game logic for Othello
public class Othello extends GameState {
    private boolean online;
    private boolean p1turn;
    private OthelloBoard othelloBoard;
    private Player p1;
    private Player p2;
    private int countMoves;
    private boolean isWon;
    ArrayList<Integer> listOfCoordinates;

    /**
     * @param online Boolean indicating if the game is online or not
     * @param playerOneHuman Boolean indicating if player one is human
     * @param playerTwoHuman Boolean indicating if player two is human
     */
    public Othello(boolean online, boolean playerOneHuman, boolean playerTwoHuman,
                   boolean appUserPlayer1, String appPlayerUsername, String opponentUsername) { // TO DO: missing username param
        othelloBoard = new OthelloBoard();
        this.online = online;
        listOfCoordinates = new ArrayList<>();

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

    public Othello() {
        getView();
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
        isWon = false;
        countMoves = 0;

        //If player is human
        if (p1.isHuman() || p2.isHuman()) {
            for (int col = 0; col < 8; col++) {
                for (int row = 0; row < 8; row++) {
                    int c = col;
                    int r = row;
                    othelloBoard.getTile(c, r).setOnMouseClicked(event -> {
                        if ((p1.isHuman()  && p1turn) || (p2.isHuman() && !p1turn)) {
                            if (isValidMove(c, r)) {
                                for (int i =0; i<listOfCoordinates.size(); i+=2){
                                    drawMove(listOfCoordinates.get(i), listOfCoordinates.get(i+1));
                                }
                                countMoves++;
                                listOfCoordinates.clear();
                                System.out.println("HOPELIJK LEGE LIST: " + listOfCoordinates);

                                if(p1turn){
                                    p1turn = false;
                                }else{
                                    p1turn = true;
                                }

                                if (countMoves == 60) {
                                    ArrayList<Object> winnerList = new ArrayList<>();
                                    winnerList = calcWinner();
                                    
                                }

                                if (isWon){
                                    // TO DO: Display a button, upon clicked return to the main menu
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.show();
                                    //Main.setState(new MainMenuState());
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
//            othelloBoard.getTile(col, row).setValid();
            System.out.println(p2.getUsername()+"s turn"); // for testing only
        }else{
            othelloBoard.drawMoveWhite(col, row);
//            othelloBoard.getTile(col, row).setValid();
            System.out.println(p1.getUsername()+"s turn"); // for testing only
        }
    }

    /**
     * @param col Col in the board
     * @param row Row in the board
     * @return Boolean if move is valid
     */
    public boolean isValidMove(int col, int row){
        // fix
//        if(othelloBoard.getTile(col, row) == null){
//            return false;
//        }

        Paint playingColour;
        Paint notPlayingColour;

        if(p1turn){
            playingColour = Color.BLACK;
            notPlayingColour = Color.WHITE;
        }else{
            playingColour = Color.WHITE;
            notPlayingColour = Color.BLACK;
        }
        boolean valid = false;
        if(col - 1 > -1 && row - 1 > -1) {
            if (othelloBoard.getTile(col-1, row-1).getContent().getFill() == notPlayingColour && checkUL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1) {
            if (othelloBoard.getTile(col-1, row).getContent().getFill() == notPlayingColour && checkUM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1 && row + 1 < 8) {
            if (othelloBoard.getTile(col-1, row+1).getContent().getFill() == notPlayingColour && checkUR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row - 1 > -1) {
            if (othelloBoard.getTile(col, row-1).getContent().getFill() == notPlayingColour && checkML(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row + 1 < 8) {
            if (othelloBoard.getTile(col, row+1).getContent().getFill() == notPlayingColour && checkMR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row - 1 > -1) {
            if (othelloBoard.getTile(col+1, row-1).getContent().getFill() == notPlayingColour && checkDL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8) {
            if (othelloBoard.getTile(col+1, row).getContent().getFill() == notPlayingColour && checkDM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row + 1 < 8) {
            if (othelloBoard.getTile(col+1, row+1).getContent().getFill() == notPlayingColour && checkDR(col, row, playingColour)) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkUL(int c, int r, Paint playingColour) {
        System.out.println("UL! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c > 0 && r > 0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r--;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour) {
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkUM(int c, int r, Paint playingColour) {
        System.out.println("UM! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                System.out.println(playingColour);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkUR(int c, int r, Paint playingColour) {
        System.out.println("UR! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r++;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                System.out.println(playingColour);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkML(int c, int r, Paint playingColour) {
        System.out.println("ML! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r--;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkMR(int c, int r, Paint playingColour) {
        System.out.println("MR! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r++;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkDL(int c, int r, Paint playingColour) {
        System.out.println("DL! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r--;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkDM(int c, int r, Paint playingColour) {
        System.out.println("DM! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                System.out.println(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour color of who's turn it is
     * @return true when is valid row.
     */
    public boolean checkDR(int c, int r, Paint playingColour) {
        System.out.println("DR! " + c +" "+ r);
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r++;
            if(othelloBoard.getTile(c, r).getContent().getFill() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public void setArrayOfCoordinates(ArrayList<Integer> tempListOfCoordinates) {
        listOfCoordinates.addAll(tempListOfCoordinates);
        System.out.println("list is now: " + listOfCoordinates);
    }

    /**
     * @return Boolean if the game has been won by someone
     */
    public ArrayList<Object> calcWinner(){
        ArrayList<Object> listWinner = new ArrayList<>();
        int countBlack = 0;
        int countWhite = 0;
        String winner;
        for(int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if(othelloBoard.getTile(col, row).getContent().getFill() == Color.BLACK) {
                    countBlack++;
                } 
                if (othelloBoard.getTile(col, row).getContent().getFill() == Color.WHITE) {
                    countWhite++;
                }
            }
        } 
        if (countBlack > countWhite) {
            winner = p1.getUsername();
            listWinner.add(winner);
            listWinner.add(countBlack);
        } else if (countBlack < countWhite) {
            winner = p2.getUsername();
            listWinner.add(winner);
            listWinner.add(countWhite);
        } else {
            winner = "Gelijk spel";
            listWinner.add(winner);
        }
        isWon = true;
        return listWinner;
    }


    /**
     * @return the view of a new Othello object
     */
    @Override
    public Parent getView() {
        return othelloBoard.boardView();
    }
}
