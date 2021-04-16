package app;

import app.state.State;
import javafx.scene.Scene;

/**
 * The StateController class is responsible for switching the scene of the primary stage
 * @author Sabine Schreuder
 * @version 09-04-21
 */
public class StateController {
    private static State currentState;

    /**
     * Set the current scene of the primaryStage to the state in the parameter.
     * @param state The state to be set to
     */
    public static void setState(State state) {
        if (currentState != null) {
            currentState.exit();
        }

        currentState = state;
        Scene scene = new Scene(state.getView());
        scene.getStylesheets().add("/res/background/background.css");
        Main.getStage().setScene(scene);

        state.enter();
    }
}
