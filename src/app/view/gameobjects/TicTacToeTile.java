package app.view.gameobjects;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The class TicTacToeTile defines the contents of a tile in a TicTacToe game
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class TicTacToeTile extends Tile{
    private Text text;

    public TicTacToeTile(){
        super();

        text = new Text();
        text.setFont(Font.font(72));

        getChildren().add(text);
    }

    /**
     * Set the tile for player one to X
     */
    @Override
    public void setTileP1(){
        text.setText("X");
    }

    /**
     * Set the tile for player two to O
     */
    @Override
    public void setTileP2(){
        text.setText("O");
    }

    public String getContent(){
        return text.getText();
    }

}
