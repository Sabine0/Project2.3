package app.games.gameobjects;

import app.games.gameobjects.Tile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OthelloTile extends Tile {
    Circle circle;

    public OthelloTile(){
        super();
        circle = new Circle();

        getChildren().add(circle);
    }

    public Circle getContent(){
        return circle;
    }

    @Override
    public void setTileP1(){
        circle.setFill(Color.WHITE);
    }

    @Override
    public void setTileP2(){
        circle.setFill(Color.BLACK);
    }
}
