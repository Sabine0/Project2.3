package app.view.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * The class OthelloTile defines the contents of a tile in an Othello game
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class OthelloTile extends Tile{
    Circle circle;

    public OthelloTile(){
        super();
        circle = new Circle(20);
        circle.setFill(Color.GREEN);
        circle.setLayoutX(200);
        circle.setLayoutY(200);
    }

    /**
     * Set the tile for player one to black
     */
    @Override
    public void setTileP1(){
        if(circle.getFill() == Color.GREEN){
            getChildren().add(circle);
        }
        circle.setFill(Color.BLACK);
    }

    /**
     * Set the tile for player two to white
     */
    @Override
    public void setTileP2(){
        if(circle.getFill() == Color.GREEN){
            getChildren().add(circle);
        }
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
    }

    public Paint getContent(){
        return circle.getFill();
    }
}
