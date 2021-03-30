package app.games.gameobjects;

import app.games.gameobjects.Board;
import javafx.scene.Parent;

public class OthelloBoard extends Board {
    public OthelloBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public Parent boardView() {
        return null;
    }
}
