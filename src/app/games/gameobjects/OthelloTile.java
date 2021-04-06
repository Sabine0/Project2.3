package app.games.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The class OthelloTile defines the contents of a tile in an Othello game
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class OthelloTile extends Tile {
    Circle circle;

    // TO DO: fix position of circles
    public OthelloTile(){
        super();
        circle = new Circle(20);
        circle.setLayoutX(200);
        circle.setLayoutY(200);
    }

    /**
     * Set the tile for player one to black
     */
    @Override
    public void setTileP1(){
        getChildren().add(circle);
        circle.setFill(Color.BLACK);
    }

    /**
     * Set the tile for player two to white
     */
    @Override
    public void setTileP2(){
        getChildren().add(circle);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
    }

    /**
     * @return The circle that belongs to the tile object
     */
    public Circle getContent(){
        return circle;
    }
}
