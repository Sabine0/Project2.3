package app.games.gameobjects;

import javafx.scene.Parent;

public abstract class Board {
    private int width;
    private int height;
    public Board(int width, int height){
        this.height = height;
        this.width = width;

    }

    public abstract Parent boardView();
}
