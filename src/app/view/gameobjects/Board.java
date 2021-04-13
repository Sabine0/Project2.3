package app.view.gameobjects;

import app.users.Player;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * The Board class creates the grid and tiles for the play board
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public abstract class Board {
    private Pane boardPane;
    private Tile[][] grid;
    private Player p1;
    private Player p2;

    public Board(int boardSize, Tile[][]tempGrid, String game){
        boardPane = new Pane();
        boardPane.setPrefSize(800, 800);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Tile tile;
                if (game.equals("TIC-TAC-TOE")) {
                   tile = new TicTacToeTile();
                } else if(game.equals("OTHELLO")){
                    tile = new OthelloTile();
                }else{
                    tile = new TicTacToeTile();
                    System.out.println("uh-oh!");
                }

                tile.setTranslateX(j*80);
                tile.setTranslateY(i*80);

                boardPane.getChildren().add(tile);

                tempGrid[j][i] = tile;
            }
        }
        grid = tempGrid;
    }

    public void drawMove(String playerName, int col, int row){
        if(playerName.equals(p1.getUsername())){
            getTile(col, row).setTileP1();
        }else{
            getTile(col, row).setTileP2();
        }
    }

    // You will receive an array with 2 ints, return 1 int
    /**
     * @param array An array of 2 coordinates, containing column and row on the board
     * @param gridSize The size of the board (8x8 = 8, we assume all board are squares)
     * @return int coordinate on the board
     */
    public int convertMove(int[] array, int gridSize){
        // TO DO: implement
        return 0;
    }

    // You will receive an int, return an array with 2 ints
    /**
     * @param integer The int to be converted to an int[]
     * @param gridSize The size of the board (8x8 = 8), we assume all board are squares
     * @return An array of 2 coordinates containing column and row on the board
     */
    public int[] convertMove(int integer, int gridSize){
        // TO DO: implement
        int[] array = new int[2]; // temporary
        return array;
    }

    /**
     * @return the grid
     */
    public <T extends Tile> Tile[][] getGrid(){
        return grid;
    }

    /**
     * @return The board pane
     */
    public Pane getBoardPane() {
        return boardPane;
    }

    public void setPlayer1(Player player){
        p1 = player;
    }

    public void setPlayer2(Player player){
        p2 = player;
    }

    public abstract int getBoardSize();

    public abstract <T extends Tile> T getTile(int col, int row);

    public abstract boolean isValidMove(int col, int row, boolean p1turn);

    /**
     * @return The view of a Board object as a Parent
     */
    public abstract Parent boardView();
}
