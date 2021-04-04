package app.games.gameobjects;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import app.games.gameobjects.TicTacToeTile;

public class TicTacToeBoard extends Board {
    private TicTacToeTile[][] grid;

    public TicTacToeBoard() {

    }

    @Override
    public Parent boardView() {
        grid = new TicTacToeTile[3][3];
        Pane board = new Pane();
        board.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeTile tile = new TicTacToeTile();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                board.getChildren().add(tile);

                grid[j][i] = tile;
            }
        }
        return board;
    }

    public TicTacToeTile getTile(int c, int r){
        return grid[c][r];
    }

    public void doMoveX(int c, int r){
        grid[c][r].setTileP1();
    }

    public void doMoveO(int c, int r){
        grid[c][r].setTileP2();
    }

    public void playWinAnimation(){
        // later
    }


}