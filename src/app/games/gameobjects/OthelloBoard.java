package app.games.gameobjects;

import app.games.Othello;
import app.games.gameobjects.Board;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class OthelloBoard extends Board {
    private OthelloTile[][] grid;

    public OthelloBoard() {

    }

    // TO DO: The scaling for this board is off and needs to be rescaled
    @Override
    public Parent boardView() {
        grid = new OthelloTile[8][8];
        Pane board = new Pane();
        board.setPrefSize(600, 600);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                OthelloTile tile = new OthelloTile();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                board.getChildren().add(tile);

                grid[j][i] = tile;
            }
        }
        return board;
    }

    public OthelloTile getTile(int c, int r){
        return grid[c][r];
    }

    public void doMoveBlack(int c, int r){
        grid[c][r].setTileP1();
    }
    
    public void doMoveWhite(int c, int r){
        grid[c][r].setTileP2();
    }

    public void playWinAnimation(){
        // later
    }
}
