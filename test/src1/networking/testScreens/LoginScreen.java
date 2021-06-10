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

public class LoginScreen extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        Text text = new Text("Login for " + "...");
        text.setFont(Font.font("", FontWeight.BLACK, 30));
        grid.add(text, 0, 0);

        Label lUser = new Label("Enter username");
        grid.add(lUser, 0 ,1);

        TextField tfUser = new TextField();
        tfUser.setPromptText("username");
        grid.add(tfUser, 1, 1);

        Label lIp = new Label("Enter IP");
        grid.add(lIp, 0, 2);

        TextField tfIp = new TextField();
        tfIp.setPromptText("IP");
        grid.add(tfIp, 1, 2);

        Label lPort = new Label("Enter port");
        grid.add(lPort, 0, 3);

        TextField tfPort = new TextField();
        tfPort.setPromptText("port");
        grid.add(tfPort, 1, 3);

        Button enterLobby = new Button("Enter online lobby");
        grid.add(enterLobby, 1, 4);
        enterLobby.setOnAction(e ->{
            System.out.println("test");
        });

        Scene scene = new Scene(grid, 800, 800);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
