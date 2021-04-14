package app.view.gameobjects;

import app.StateController;
import app.state.MainMenuState;
import app.users.OnlineOpponent;
import app.users.OthelloAI;
import app.users.Player;
import app.users.UserPlayer;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

/**
 * The Board class creates the grid and tiles for the playboard
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public abstract class Board {
    private Pane boardPane;
    private Tile[][] grid;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private boolean p1turn;
    private String winner;

    public Board(int boardSize, Tile[][]tempGrid, String game, Player player1, Player player2){
        p1 = player1;
        p2 = player2;

        boardPane = new Pane();
        boardPane.setPrefSize(800, 800);
        currentPlayer = p1;

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

                int finalJ = j;
                int finalI = i;
                tile.setOnMouseClicked(event -> {
                    if(isValidMove(finalJ, finalI)) {
                        System.out.println("Correct tile clicked");
                        drawMove(currentPlayer.getUsername(), finalJ, finalI);
                        if(game.equalsIgnoreCase("Reversi")){
                            setTilesForMove(); // doesnt work?
                        }
                    }
                });

                tile.setTranslateX(j*80);
                tile.setTranslateY(i*80);

                boardPane.getChildren().add(tile);

                tempGrid[j][i] = tile;
            }
        }
        grid = tempGrid;
        p1turn = true;
    }

    /**
     * Will draw the move at the coordinates col, row depending on whose move it is
     * @param playerName The current players name as a String
     * @param col The column on the board
     * @param row The row on the board
     */
    public void drawMove(String playerName, int col, int row){
        if(playerName.equals(p1.getUsername()) && p1turn){
            getTile(col, row).setTileP1();
            p1turn = false;
            currentPlayer = p2;
        }else if(playerName.equals(p2.getUsername()) && !p1turn){
            getTile(col, row).setTileP2();
            p1turn = true;
            currentPlayer = p1;
        }
    }

    /**
     * @param array An array of 2 coordinates, containing column and row on the board
     * @param gridSize The size of the board (8x8 = 8, we assume all board are squares)
     * @return int coordinate on the board
     */
    public int convertMove(int[] array, int gridSize){
        // You will receive an array with 2 ints, return 1 int
        // TO DO: implement
        return 0;
    }

    /**
     * @param integer The int to be converted to an int[]
     * @param gridSize The size of the board (8x8 = 8), we assume all board are squares
     * @return An array of 2 coordinates containing column and row on the board
     */
    public int[] convertMove(int integer, int gridSize){
        // You will receive an int, return an array with 2 ints
        // TO DO: implement
        int[] array = new int[2]; // temporary
        return array;
    }

    /**
     * Alerts the player that the match has been won by winner
     */
    public void showWinAlert(int scorep1, int scorep2){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The game has ended");
        alert.setHeaderText("The winner is: " + winner);
        alert.setContentText("Click OK to return to the main menu");
        alert.setOnCloseRequest(returnEvent ->{
            StateController.setState(new MainMenuState());
        });
        alert.show();
    }

    public void showDrawAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The game has ended");
        alert.setHeaderText("The game has ended in a draw!");
        alert.setContentText("Click OK to return to the main menu");
        alert.setOnCloseRequest(returnEvent ->{
            StateController.setState(new MainMenuState());
        });
        alert.show();
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

    public boolean isP1turn(){
        return p1turn;
    }

    public void setP1turn(boolean bool){
        p1turn = bool;
    }

    public void setWinner(String winnerName){
        winner = winnerName;
    }

    public abstract int getBoardSize();

    public abstract <T extends Tile> T getTile(int col, int row);

    public abstract boolean isValidMove(int col, int row);

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public abstract void setTilesForMove();

    /**
     * @return The view of a Board object as a Parent
     */
    public abstract Parent boardView();
}
