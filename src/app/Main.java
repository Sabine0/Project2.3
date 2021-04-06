package app;

import app.networking.serverNotRespondingException;
import app.state.MainMenuState;
import app.state.State;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class serves as a starting point for the javaFX application
 * @author Sabine Schreuder
 * @version 01-04-21
 */
public class Main extends Application {
    private static Stage stage;
    private static State currentState;

    /**
     * Starts the javaFX program by initialising its values and showing the stage.
     * If an exception occurred, don't start.
     * @param primaryStage The main stage
     * @throws Exception Any exception that is caught
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        State initialState = new MainMenuState();
        setState(initialState);

        stage.setHeight(700);
        stage.setWidth(700);

        stage.setTitle("Games");
        stage.show();
    }

    /**
     * Set the state to the given parameter.
     * If there is a state already, exit current state first.
     * @param state The state to be set to
     */
    public static void setState(State state){
        if(currentState!=null) {
            currentState.exit();
        }

        currentState = state;
        Scene scene = new Scene(state.getView());
        scene.getStylesheets().add("/res/backgrounds/background.css");
        stage.setScene(scene);

        try {
            state.enter();
        }catch(serverNotRespondingException E){
            System.out.println(E);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
