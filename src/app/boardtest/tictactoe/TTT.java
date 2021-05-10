package app.boardtest.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class TTT extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600,600);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;i++){
                Tile tile = new Tile();
                tile.setTranslateX(j*200);
                tile.setTranslateY(j*200);

                root.getChildren().add(tile);
            }
        }

        return root;
    }

    private class Tile extends StackPane{
        public Tile(){
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}