package app.games.gameobjects;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Tile class holds creates the border for every Tile
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public abstract class Tile extends StackPane {
    Rectangle border;
    StackPane borderPane;
    boolean valid;

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
     * @return
     */
    public abstract boolean getValid();

    /**
     *
     */
    public abstract void setValid();

}
