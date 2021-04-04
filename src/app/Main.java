package app;

import app.state.MainMenuState;
import app.state.State;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    private static State currentState;

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

    public static void setState(State state){
        if(currentState!=null) {
            currentState.exit();
        }

        currentState = state;
        Scene scene = new Scene(state.getView());
        scene.getStylesheets().add("res/backgrounds/background.css");
        stage.setScene(scene);
        state.enter();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
