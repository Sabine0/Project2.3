package app.view.gameobjects;

import app.StateController;
import app.state.MainMenuState;
import app.users.Player;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

/**
 * The Board class creates the grid and tiles for the play board
 * @author Sabine Schreuder
 * @version 14-04-21
 */
public abstract class Board {
    private Pane boardPane;
    private Tile[][] grid;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private boolean online;
    private int serverX;
    private int serverY;

    public Board(int boardSize, Tile[][]tempGrid, String game, Player player1, Player player2, boolean online){
        p1 = player1;
        p2 = player2;

        this.online = online;


        boardPane = new Pane();
        boardPane.setPrefSize(800, 800);

        // player 1 starts
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

                if(online){
//                    System.out.println("online");

//                    hier nog wat mee
                    tile.setOnMouseClicked(event -> {
                        drawMove(currentPlayer.getUsername(), serverX, serverY);
                    });
                }
                else{
//                    System.out.println("offline");
                    tile.setOnMouseClicked(event -> {
                        if ((p1.isHuman() && currentPlayer.equals(p1)) || (p2.isHuman() && currentPlayer.equals(p2))){
                            if (isValidMove(finalJ, finalI)) {
                                System.out.println("Correct tile clicked");
                                drawMove(currentPlayer.getUsername(), finalJ, finalI);
                            }
                        }
                    });
                }

                tile.setTranslateX(j*80);
                tile.setTranslateY(i*80);

                boardPane.getChildren().add(tile);

                tempGrid[j][i] = tile;
            }
        }
        grid = tempGrid;
    }

//      DEZE METHODE IS BELANGRIJK OM DE JUISTE COORDINATEN VANUIT DE SERVER TE ZETTEN
    public void setServerCoordinates(int x, int y){
        this.serverX=x;
        this.serverY=y;
    }

    /**
     * Will draw the move at the coordinates col, row depending on whose move it is
     * @param playerName The current players name as a String
     * @param col The column on the board
     * @param row The row on the board
     */
    public void drawMove(String playerName, int col, int row){
        if(playerName.equals(p1.getUsername())){
            getTile(col, row).setTileP1();
            currentPlayer = p2; // might need to move this for double moves in othello
        }else if(playerName.equals(p2.getUsername())){
            getTile(col, row).setTileP2();
            currentPlayer = p1; // might need to move this for double moves in othello
        }
    }

    /**
     * @param array An array of 2 coordinates, containing column and row on the board
     * @param gridSize The size of the board (8x8 = 8, we assume all board are squares)
     * @return int coordinate on the board
     */
    public int convertMove(int[] array, int gridSize){
        int row = array[0];
        int col = array[1];

        return (row*gridSize) + col;
    }

    /**
     * @param integer The int to be converted to an int[]
     * @param gridSize The size of the board (8x8 = 8), we assume all board are squares
     * @return An array of 2 coordinates containing column and row on the board
     */
    public int[] convertMove(int integer, int gridSize){
        int r = integer/gridSize;
        int c = integer % gridSize;

        return new int[]{r, c};
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Alerts the player that the match has been won by winner
     */
    public void showWinAlert(int scorep1, int scorep2){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The game has ended");
        if(scorep1 > scorep2) {
            alert.setHeaderText("The winner is " + p1.getUsername());
        }else{
            alert.setHeaderText("The winner is " + p2.getUsername());
        }
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
     * @return boolean if the game has been won
     */
    public abstract boolean isWon();

    /**
     * @param col Column on the play board
     * @param row Row on the play board
     * @return boolean if the move (col, row) can be done
     */
    public abstract boolean isValidMove(int col, int row);

    /**
     * @return grid The grid for the board
     */
    public <T extends Tile> Tile[][] getGrid(){
        return grid;
    }

    public abstract int getBoardSize();

    public abstract <T extends Tile> T getTile(int col, int row);

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    /**
     * @return The pane containing the Tiles
     */
    public Pane getBoardPane() {
        return boardPane;
    }

    /**
     * @return The view of a Board object as a Parent
     */
    public abstract Parent boardView();
}
