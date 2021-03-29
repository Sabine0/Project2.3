package client;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Needs to be refactored to MVC

public class TempClass extends Application {
    private GameMenu gameMenu;

    private Parent CreateContent() throws Exception {
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(600,500);

        InputStream is = Files.newInputStream(Paths.get("res/images/background.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(600);
        imageView.setFitHeight(500);

        gameMenu = new GameMenu();


        root.getChildren().addAll(imageView, gameMenu);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static class MenuButton extends StackPane{
        private Text text;

        public MenuButton(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            // Fancy mouse hover
            setOnMouseEntered(event ->{
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event ->{
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
        }
    }

    private class GameMenu extends Parent{
        public GameMenu(){
            VBox menu0 = new VBox(10);
            VBox playMenu = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);

            playMenu.setTranslateX(100);
            playMenu.setTranslateY(200);

            final int offset = 400;
            playMenu.setTranslateX(offset);

            MenuButton btnPlay = new MenuButton("PLAY");
            btnPlay.setOnMouseClicked(event ->{
                getChildren().add(playMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), playMenu);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(menu0);
                });

            });

            MenuButton btnTicTacToe = new MenuButton("TICTACTOE");
            btnTicTacToe.setOnMouseClicked(event ->{
                // Start Tic Tac Toe match
                // Maybe add menu: Co-Op/AI
//                TicTacToe.launch();
            });

            MenuButton btnOthello = new MenuButton("OTHELLO");
            btnOthello.setOnMouseClicked(event ->{
                // Start Othello match
                // Maybe add menu: Co-Op/AI
            });

            MenuButton btnCredits = new MenuButton("CREDITS");
            btnCredits.setOnMouseClicked(event ->{
                // Displays credits
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event ->{
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), playMenu);
                tt.setToX(playMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(playMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(playMenu);
                });
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event ->{
                System.exit(0);
            });

            menu0.getChildren().addAll(btnPlay, btnCredits, btnExit);
            playMenu.getChildren().addAll(btnTicTacToe, btnOthello, btnBack);


            Rectangle bg = new Rectangle(800,600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}