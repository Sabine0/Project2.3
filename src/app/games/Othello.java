package app.games;

import java.net.NoRouteToHostException;

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
    private int[] listOfCoordinates;

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

    // validmove check, char playing and char tegenstander moeten nog worden omgezet naar tiles.
    public boolean validMove(int c, int r, char playing, char tegenStander) {
        OthelloBoard othelloBoard = New OthelloBoard();
        boolean valid = false;
        boolean p = true;
        while(p == true){
            if (othelloBoard.getTile(c-1, r-1) == tegenStander && checkUL(c-1, r-1, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c-1, r) == tegenStander && checkUM(c-1, r, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c-1, r+1) == tegenStander && checkUR(c-1, r+1, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c, r-1) == tegenStander && checkML(c, r-1, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c, r+1) == tegenStander && checkMR(c, r+1, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c+1, r-1) == tegenStander && checkDL(c+1, r-1, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c+1, r) == tegenStander && checkDM(c+1, r, playing)) {
                valid = true;
            } else if (othelloBoard.getTile(c+1, r+1) == tegenStander && checkDR(c+1, r+1, playing)) {
                valid = true;
            } else {
                p =false;
            }
        }
        return valid;
    }
    public boolean checkUL(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c > 0 && r > 0) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c--;
            r--;
            if(othelloBoard.getTile(c, r) == playing) {
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
        
    }

    public boolean checkUM(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c>0) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c--;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkUR(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c>0 && r<7) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c--;
            r++;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkML(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(r>0) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            r--;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkMR(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(r<7) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            r++;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkDL(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c<7 && r>0) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c++;
            r--;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkDM(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c<7) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c++;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public boolean checkDR(int c, int r, char playing) {
        int[] tempListOfCoordinates;
        tempListOfCoordinates[0] = c+1;
        tempListOfCoordinates[1] = r+1;
        boolean valid = false;
        int t = 2;
        while(c<7 && r<7) {
            tempListOfCoordinates[t] = c;
            t++;
            tempListOfCoordinates[t] = r;
            t++;
            c++;
            r++;
            if(othelloBoard.getTile(c, r) == playing){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    public void setArrayOfCoordinates(int[] tempListOfCoordinates) {
        int k = listOfCoordinates.length - 1;
        int tot = k + tempListOfCoordinates.length - 1;
        int t = 0;
        for (int i = k; i < tot; i++) {
            listOfCoordinates[i] = tempListOfCoordinates[t];
            t++;
        }
    }
}
