package app.games.gameobjects;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * The OthelloBoard class defines the Othello board
 * and handles changes to the board
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class OthelloBoard extends Board {
    private OthelloTile[][] grid;
    private Pane board;

    /**
     * Create the Othello board with a two dimensional grid
     */
    public OthelloBoard() {
        grid = new OthelloTile[8][8];
        board = new Pane();
        board.setPrefSize(800, 800);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloTile tile = new OthelloTile();
                tile.setTranslateX(j*80);
                tile.setTranslateY(i*80);

                board.getChildren().add(tile);

                grid[j][i] = tile;
            }
        }
    }

    /**
     * @return The boardview as a Pane
     */
    @Override
    public Parent boardView() {
        return board;
    }

    /**
     * @param col Column in the board
     * @param row Row in the board
     * @return The OthelloTile on position: column, row
     */
    public OthelloTile getTile(int col, int row){
        return grid[col][row];
    }

    /**
     * Set a black circle on position: column, row
     * @param col
     * @param row
     */
    public void drawMoveBlack(int col, int row){
        grid[col][row].setTileP1();
    }

    /**
     * Set a white circle on position: column, row
     * @param col
     * @param row
     */
    public void drawMoveWhite(int col, int row){
        grid[col][row].setTileP2();
    }

    /**
     * Displays winner
     */
    public void playWinAnimation(){
        // later
    }
}
