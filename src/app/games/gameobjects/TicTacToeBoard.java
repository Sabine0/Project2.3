package app.games.gameobjects;

import app.TempFolder.TicTacToe;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class TicTacToeBoard extends Board {
    private TicTacToeTile[][] grid;
    private Pane board;
    public int[] corX;
    public int[] corY;

    /**
     * Create the TicTacToe board with a two dimensional grid
     */
    public TicTacToeBoard() {
        grid = new TicTacToeTile[3][3];
        board = new Pane();
        board.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeTile tile = new TicTacToeTile();
                tile.setTranslateX(j * 80);
                tile.setTranslateY(i * 80);

                board.getChildren().add(tile);

                grid[j][i] = tile;
            }
        }
    }

    /**
     * Create the TicTacToe board as a two dimensional array and return it
     *
     * @return The board
     */
    @Override
    public Parent boardView() {
        return board;
    }

    /**
     * @param col Column in the board
     * @param row Row in the board
     * @return The TicTacToeTile on position: column, row
     */
    public TicTacToeTile getTile(int col, int row) { return grid[col][row]; }

    /**
     * Set X on location col, row
     *
     * @param col Column in the board
     * @param row Row in the board
     */
    public void drawMoveX(int col, int row) {
        grid[col][row].setTileP1();
        grid[col][row].setCoordinates(col, row);
    }

    /**
     * Set O on location col, row
     *
     * @param col Column in the board
     * @param row Row in the board
     */
    public void drawMoveO(int col, int row) {
        grid[col][row].setTileP2();
        grid[col][row].setCoordinates(col, row);
    }

    /**
     * Displays winner
     */
    public void playWinAnimation() {
//        hardcode
        System.out.println("win");
    }




// hardcode test
    public boolean testWin(){


//        eventueel nog beter uitwerken
//        for(int y=0; y<3; y++){
//            if(grid[0][y].getMove() == grid[1][y].getMove() && grid[0][y].getMove() == grid[2][y].getMove()){
//                return true;
//            }
//        }
//
//        for(int x=0; x<3; x++){
//            if(grid[x][0].getMove() == grid[x][1].getMove() && grid[x][0].getMove() == grid[x][2].getMove()){
//                return true;
//            }
//        }
//
//        if(grid[0][0].getMove() == grid[1][1].getMove() && grid[0][0].getMove() == grid[2][2].getMove()){
//            return true;
//        }
//        else if(grid[2][0].getMove() == grid[1][1].getMove() && grid[2][0].getMove() == grid[0][2].getMove()){
//            return true;
//        }



        for(int y=0; y<3; y++){
            if(grid[0][y].getMove() == 'X' && grid[1][y].getMove() == 'X' && grid[2][y].getMove() == 'X'){
                return true;
            }
            else if(grid[0][y].getMove() == 'O' && grid[1][y].getMove() == 'O' && grid[2][y].getMove() == 'O'){
                return true;
            }
        }

        for(int x=0; x<3; x++){
            if(grid[x][0].getMove() == 'X' && grid[x][1].getMove() == 'X' && grid[x][2].getMove() == 'X'){
                return true;
            }
            else if(grid[x][0].getMove() == 'O' && grid[x][1].getMove() == 'O' && grid[x][2].getMove() == 'O'){
                return true;
            }
        }

        if(grid[0][0].getMove() == 'X' && grid[1][1].getMove() == 'X' && grid[2][2].getMove() == 'X'){
            return true;
        }
        else if(grid[0][0].getMove() == 'O' && grid[1][1].getMove() == 'O' && grid[2][2].getMove() == 'O'){
            return true;
        }
        else if(grid[2][0].getMove() == 'X' && grid[1][1].getMove() == 'X' && grid[0][2].getMove() == 'X'){
            return true;
        }
        else if(grid[2][0].getMove() == 'O' && grid[1][1].getMove() == 'O' && grid[0][2].getMove() == 'O'){
            return true;
        }

        return false;
    }
}
