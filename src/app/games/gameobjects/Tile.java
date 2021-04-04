package app.games.gameobjects;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public abstract class Tile extends StackPane {
    public Tile(){
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().add(border);
    }

    public abstract void setTileP1();

    public abstract void setTileP2();

}
