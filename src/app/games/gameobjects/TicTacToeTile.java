package app.games.gameobjects;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class TicTacToeTile defines the contents of a tile in a TicTacToe game
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class TicTacToeTile extends Tile {
    private Text text;
    boolean valid;
    char move;
    private ArrayList<Integer> coordinates = new ArrayList<Integer>();

    public TicTacToeTile(){
        super();
        valid= true;
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
        setMove('X');
    }

    /**
     * Set the tile for player two to O
     */
    @Override
    public void setTileP2(){
        text.setText("O");
        setMove('O');
    }

    /**
     *
     * @return
     */
    public boolean getValid() { return valid; }

    /**
     *
     */
    public void setValid() { valid = false; }

    public char getMove() { return move; }

    public void setMove(char c) {
        move = c;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int c, int r) {
        coordinates.add(r);
        coordinates.add(c);
    }

    /**
     * @return The text that belongs to the tile
     */
    public Text getText(){
        return text;
    }



}
