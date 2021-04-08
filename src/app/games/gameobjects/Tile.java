package app.games.gameobjects;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The Tile class holds creates the border for every Tile
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public abstract class Tile extends StackPane {
    Rectangle border;
    StackPane borderPane;
    boolean valid;
    char move;

    public Tile(){
        borderPane = new StackPane();
        border = new Rectangle(80, 80);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        valid = true;

        borderPane.getChildren().add(border);
        getChildren().add(borderPane);
    }

    /**
     * Set the tile for player one
     */
    public abstract void setTileP1();

    /**
     * Set the tile for player two
     */
    public abstract void setTileP2();

    /**
     *
     * @return if a set is possible
     */
    public abstract boolean getValid();

    /**
     * Turns a tile to a false move
     */
    public abstract void setValid();

    /**
     *
     * @return value of a tile
     */
    public abstract char getMove();

    /**
     * sets a value of a tile
     */
    public abstract void setMove(char c);

    /**
     *
     * @return the coordinates of a tile
     */
    public abstract ArrayList<Integer> getCoordinates();

    /**
     * sets the coordinates of a tile
     */
    public abstract void setCoordinates(int c, int r);

}
