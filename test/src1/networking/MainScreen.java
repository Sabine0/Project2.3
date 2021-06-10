import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen extends Application {

    Stage window;
    Scene homeScene, gameScene, loginScene;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(10));

        Text welcomeText = new Text("Welcome to Hanze games");
        welcomeText.setFont(Font.font("", FontWeight.BLACK, 30));
//        grid.add(welcomeText, 0, 0);

        Button chooseGame = new Button("Choose a game");
//        grid.add(enterGame, 0, 1);
        chooseGame.setOnAction(e ->{
            window.setScene(gameScene);
        });

        Button exit = new Button("Exit menu");
//        grid.add(exit, 0, 2);
        exit.setOnAction(e ->{
            window.close();
        });

//        Layout 1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcomeText, chooseGame, exit);
        homeScene = new Scene(layout1, 800, 800);

        Text gameText = new Text("Choose a game");
        gameText.setFont(Font.font("", FontWeight.BLACK, 30));
//        grid.add(gameText, 0, 0);

        Button enterReversi = new Button("Reversi");
//        grid.add(enterReversi, 0, 1);
        enterReversi.setOnAction(e ->{
            System.out.println("test");
        });

        Button back = new Button("Go back");
//        grid.add(back, 0, 2);
        back.setOnAction(e ->{
            window.setScene(homeScene);
        });

        //        Layout 2
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(gameText, enterReversi, back);
        gameScene = new Scene(layout2, 800, 800);

        window.setScene(homeScene);
        window.setTitle("Welcome");
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
