//--module-path /Users/yannickhuisman/Downloads/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Board extends Application {
    int serverX;
    int serverY;
    int boardSize;
    private Pane boardPane;

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(800, 800);

        serverX = 3;
        serverY = 2;

        for(int i =0; i < 8; i++){
            for(int j =0; j < 8; j++){
                Tile tile = new Tile();
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);

                if(j == serverX && i == serverY){
                    tile.draw1();
                }

                root.getChildren().add(tile);
            }
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

//        drawMove(3,2);
    }

    private class Tile extends StackPane {
        private Text move = new Text();

        public Tile(){
            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, move);

            setOnMouseClicked(event -> {
                if(event.getButton() ==  MouseButton.PRIMARY){
                    draw1();
                }
                else if(event.getButton() == MouseButton.SECONDARY){
                    draw2();
                }
            });
        }

        private void draw1(){
            move.setText("1");
        }

        private void draw2(){
            move.setText("2");
        }
    }

//    private void drawMove(int setX, int setY){
//        boardSize = 8;
//
//        for (int i = 0; i < boardSize; i++) {
//            for (int j = 0; j < boardSize; j++) {
//                Tile tile = new Tile();
//
//                if(j == serverX && i == serverY){
//                    tile.draw1();
//                    System.out.println("gevonden");
//                }
//            }
//        }
//    }

    public static void main(String[] args){
        launch(args);
    }
}