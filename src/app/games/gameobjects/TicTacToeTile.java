package app.games.gameobjects;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TicTacToeTile extends Tile {
    private Text text;

    public TicTacToeTile(){
        super();
        text = new Text();
        text.setFont(Font.font(72));

        getChildren().add(text);
    }

    @Override
    public void setTileP1(){
        text.setText("X");
    }

    @Override
    public void setTileP2(){
        text.setText("O");
    }

    public Text getText(){
        return text;
    }
}
