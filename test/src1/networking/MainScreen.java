import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen extends Application {

    Stage window;
    Scene homeScene, gameScene, gameOptionScene, loginScene;

//    method for universal font text
    private void setTextFont(Text thisText){
        thisText.setFont(Font.font("", FontWeight.BLACK, 30));
    }

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

//        creating grid
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));




//        setting up scene 1
        Text welcomeText = new Text("Welcome to Hanze games");
        setTextFont(welcomeText);

        Button chooseGame = new Button("Choose a game");
        chooseGame.setOnAction(e ->{
            window.setScene(gameScene);
        });

        Button exit = new Button("Exit menu");
        exit.setOnAction(e ->{
            window.close();
        });

//        layout scene 1
//        VBox layout1 = new VBox(20);
//        layout1.getChildren().addAll(welcomeText, chooseGame, exit);

        GridPane layout1 = layout;

        layout1.add(welcomeText,0, 0);
        layout1.add(chooseGame,0, 1);
        layout1.add(exit,0, 2);

        homeScene = new Scene(layout1, 800, 800);



//        setting up scene 2
        Text gameText = new Text("Choose a game");
        setTextFont(gameText);

        Button enterReversi = new Button("Reversi");
        enterReversi.setOnAction(e ->{
            window.setScene(gameOptionScene);
        });

        Button back = new Button("Go back");
        back.setOnAction(e ->{
            window.setScene(homeScene);
        });

//        layout scene 2
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(gameText, enterReversi, back);
        gameScene = new Scene(layout2, 800, 800);



//        setting up scene 3
        Text optionText = new Text("Do you want to play local or online");
        setTextFont(optionText);

        Button enterLocal = new Button("Local");
        enterLocal.setOnAction(e ->{
            System.out.println("test");
        });

        Button enterOnline = new Button("Online");
        enterOnline.setOnAction(e ->{
            window.setScene(loginScene);
        });

        Button backGame = new Button("Back");
        backGame.setOnAction(e ->{
            window.setScene(gameScene);
        });

//        layout scene 3
        VBox layout3 = new VBox(20);
        layout3.getChildren().addAll(optionText, enterLocal, enterOnline, backGame);
        gameOptionScene = new Scene(layout3, 800, 800);



//        setting up scene 4
        Text loginText = new Text("Login for " + "...");
        setTextFont(loginText);

        Label lUser = new Label("Enter username");

        TextField tfUser = new TextField();
        tfUser.setPromptText("username");

        Label lIp = new Label("Enter IP");

        TextField tfIp = new TextField();
        tfIp.setPromptText("IP");

        Label lPort = new Label("Enter port");

        TextField tfPort = new TextField();
        tfPort.setPromptText("port");

        Button enterLobby = new Button("Enter online lobby");;
        enterLobby.setOnAction(e ->{
            System.out.println("test");
        });

        //        layout scene 4
        VBox layout4 = new VBox(20);
        layout4.getChildren().addAll(loginText, lUser, tfUser, lIp, tfIp, lPort, tfPort, enterLobby, backGame);
        loginScene = new Scene(layout4, 800, 800);



//        showing a scene
        window.setScene(homeScene);
        window.setTitle("Welcome");
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
