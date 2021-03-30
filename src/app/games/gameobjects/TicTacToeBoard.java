package app.games.gameobjects;

import app.games.gameobjects.Board;
import javafx.scene.Parent;

public class TicTacToeBoard extends Board {

    public TicTacToeBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public Parent boardView() {
        return null;
    }
}
