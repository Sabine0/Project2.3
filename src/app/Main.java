package app;

import app.state.MainMenuState;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * The Main class serves as a starting point for the javaFX application
 * A stage will be created, which will then be altered by the StateController
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;

        primaryStage.setHeight(700);
        primaryStage.setWidth(700);

        primaryStage.setTitle("Hanze games");
        StateController.setState(new MainMenuState());
        primaryStage.show();
    }

    /**
     * @return primaryStage The current primaryStage
     */
    public static Stage getStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
