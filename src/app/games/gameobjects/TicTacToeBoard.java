package app.games.gameobjects;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;


/**
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class TicTacToeBoard extends Board {
    private TicTacToeTile[][] grid;
    private Pane board;

    /**
     * Create the TicTacToe board with a two dimensional grid
     */
    public TicTacToeBoard(){
        grid = new TicTacToeTile[3][3];
        board = new Pane();
        board.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeTile tile = new TicTacToeTile();
                tile.setTranslateX(j*80);
                tile.setTranslateY(i*80);

                board.getChildren().add(tile);

                grid[j][i] = tile;
            }
        }
    }

    /**
     * Create the TicTacToe board as a two dimensional array and return it
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
    public TicTacToeTile getTile(int col, int row){
        return grid[col][row];
    }

    /**
     * Set X on location col, row
     * @param col Column in the board
     * @param row Row in the board
     */
    public void drawMoveX(int col, int row){
        grid[col][row].setTileP1();
    }

    /**
     *  Set O on location col, row
     * @param col Column in the board
     * @param row Row in the board
     */
    public void drawMoveO(int col, int row){
        grid[col][row].setTileP2();
    }

    /**
     * Displays winner
     */
    public void playWinAnimation(){
        // later
    }


}