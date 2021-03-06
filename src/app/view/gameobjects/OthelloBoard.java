package app.view.gameobjects;

import app.users.Player;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

/**
 * The OthelloBoard class defines the Othello board
 * and handles changes to the board
 * @author Sabine Schreuder
 * @author Luc Willemse
 * @version 14-04-21
 */
public class OthelloBoard extends Board{
    private ArrayList<Integer> tilesToBeFlipped;

    public OthelloBoard(Player player1, Player player2){
        super(8, new OthelloTile[8][8], "OTHELLO", player1, player2);
        getTile(3,3).setTileP2();
        getTile(3,4).setTileP1();
        getTile(4,4).setTileP2();
        getTile(4,3).setTileP1();
        tilesToBeFlipped = new ArrayList<>();
    }

    @Override
    public int getBoardSize() {
        return 8;
    }

    /**
     * @param col The column on the board
     * @param row The row on the board
     * @return boolean if the move is valid
     */
    @Override
    public boolean isValidMove(int col, int row){
//        if(getTile(col, row).getContent() == Color.WHITE || getTile(col, row).getContent() == Color.BLACK ){
//            return false;
//        } // maybe new method
        tilesToBeFlipped.clear();

        Paint playingColour;
        Paint notPlayingColour;

        if(getCurrentPlayer().equals(getP1())){
            playingColour = Color.BLACK;
            notPlayingColour = Color.WHITE;
        }else{
            playingColour = Color.WHITE;
            notPlayingColour = Color.BLACK;
        }
        boolean valid = false;
        if(col - 1 > -1 && row - 1 > -1) {
            if (getTile(col-1, row-1).getContent()== notPlayingColour && checkUL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1) {
            if (getTile(col-1, row).getContent() == notPlayingColour && checkUM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1 && row + 1 < 8) {
            if (getTile(col-1, row+1).getContent() == notPlayingColour && checkUR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row - 1 > -1) {
            if (getTile(col, row-1).getContent() == notPlayingColour && checkML(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row + 1 < 8) {
            if (getTile(col, row+1).getContent() == notPlayingColour && checkMR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row - 1 > -1) {
            if (getTile(col+1, row-1).getContent()== notPlayingColour && checkDL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8) {
            if (getTile(col+1, row).getContent() == notPlayingColour && checkDM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row + 1 < 8) {
            if (getTile(col+1, row+1).getContent() == notPlayingColour && checkDR(col, row, playingColour)) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUL(int c, int r, Paint playingColour) {
        ArrayList<Integer> tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c > 0 && r > 0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r--;
            if(getTile(c, r).getContent() == playingColour) {
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUM(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            if(getTile(c, r).getContent() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUR(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r++;
            if(getTile(c, r).getContent() == playingColour){
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
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkML(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r--;
            if(getTile(c, r).getContent() == playingColour){
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
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkMR(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r++;
            if(getTile(c, r).getContent() == playingColour){
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
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDL(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r--;
            if(getTile(c, r).getContent() == playingColour){
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
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDM(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            if(getTile(c, r).getContent() == playingColour){
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
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDR(int c, int r, Paint playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r++;
            if(getTile(c, r).getContent() == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * Tracks the coordinates of the tiles that need to be flipped when the move is executed
     * @param tempListOfCoordinates The coordinates to be added to the tilesToBeFlipped array
     */
    public void setArrayOfCoordinates(ArrayList<Integer> tempListOfCoordinates) {
        this.tilesToBeFlipped.addAll(tempListOfCoordinates);
    }

    /**
     * Flip all the tiles that need to be flipped when doing a move
     */
    public void setTilesForMove(){
        for (int tile:tilesToBeFlipped) {
            System.out.println(tile);
        }

        for (int i = 0; i< tilesToBeFlipped.size(); i+=2) {
            if (getCurrentPlayer().equals(getP1())) {
                int[] array = {tilesToBeFlipped.get(i), tilesToBeFlipped.get(i + 1)};
                drawMove(getP1().getUsername(), array[0], array[1]);
            }else{
                int[] array = {tilesToBeFlipped.get(i), tilesToBeFlipped.get(i + 1)};
                drawMove(getP2().getUsername(), array[0], array[1]);
            }
        }
        tilesToBeFlipped.clear();
    }

    @Override
    public boolean isWon(){
        // TO DO: implement!
        return false;
    }

    /**
     * @param col column on the play board
     * @param row row on the play board
     * @return the Tile in position col, row
     */
    @Override
    public OthelloTile getTile(int col, int row) {
        return (OthelloTile) getGrid()[col][row];
    }

    /**
     * @return The view of a Board object as a Parent
     */
    @Override
    public Parent boardView() {
        return getBoardPane();
    }
}
