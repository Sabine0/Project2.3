package games;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// WERKT NOG NIET

public class Othello extends Application {
    Pane root = new Pane();
    Tile[][] board = new Tile[10][10];
    private Parent createContent() {

        root.setPrefSize(1000, 1000);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }
        return root;
    }

    public void start(Stage primaryStage) throws Exception{
        Tile[][] board = new Tile[10][10];

    }

    public static void main(String[] args) {
        launch(args);
    }

     class Tile extends StackPane{
        private Text text = new Text();

        public Tile(){
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

        }
    }
}


