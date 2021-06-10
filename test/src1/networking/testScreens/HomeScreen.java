//--module-path /Users/yannickhuisman/Downloads/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml
package testScreens;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScreen extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        Text text = new Text("Welcome to Hanze games");
        text.setFont(Font.font("", FontWeight.BLACK, 30));
        grid.add(text, 0, 0);

        Button enterGame = new Button("Choose a game");
        grid.add(enterGame, 0, 1);
        enterGame.setOnAction(e ->{
            System.out.println("test");
        });

        Button exit = new Button("Exit menu");
        grid.add(exit, 0, 2);
        exit.setOnAction(e ->{
            window.close();
        });

        Scene scene = new Scene(grid, 800, 800);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
