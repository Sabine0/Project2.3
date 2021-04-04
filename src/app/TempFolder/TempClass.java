package app.TempFolder;

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
        root.setPrefSize(500,500);

        InputStream is = Files.newInputStream(Paths.get("res/images/background.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(500);
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
            VBox mainMenu = new VBox(10);
            VBox connectionModeMenu = new VBox(10);
            VBox onlinePlayModeMenu = new VBox(10);
            VBox localPlayModeMenu = new VBox(10);
            VBox onlineGamesMenu = new VBox(10);
            VBox localGamesMenu = new VBox(10);

            mainMenu.setTranslateX(100);
            mainMenu.setTranslateY(200);

            connectionModeMenu.setTranslateX(100);
            connectionModeMenu.setTranslateY(200);

            localGamesMenu.setTranslateX(100);
            localGamesMenu.setTranslateY(200);

            onlineGamesMenu.setTranslateX(100);
            onlineGamesMenu.setTranslateY(200);

            onlinePlayModeMenu.setTranslateX(100);
            onlinePlayModeMenu.setTranslateY(200);

            localPlayModeMenu.setTranslateX(100);
            localPlayModeMenu.setTranslateY(200);

            final int offset = 400;
            localGamesMenu.setTranslateX(offset);
            connectionModeMenu.setTranslateX(offset);
            onlineGamesMenu.setTranslateX(offset);

            MenuButton btnPlay = new MenuButton("PLAY");
            btnPlay.setOnMouseClicked(event ->{
                getChildren().add(connectionModeMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
                tt.setToX(mainMenu.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), connectionModeMenu);
                tt1.setToX(mainMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(mainMenu);
                });

            });

            MenuButton btnTicTacToe = new MenuButton("TICTACTOE");
            btnTicTacToe.setOnMouseClicked(event ->{
                getChildren().add(onlinePlayModeMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), onlineGamesMenu);
                tt.setToX(onlineGamesMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), onlinePlayModeMenu);
                tt1.setToX(onlineGamesMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(onlineGamesMenu);
                });


            });

            MenuButton btnOthello = new MenuButton("OTHELLO");
            btnOthello.setOnMouseClicked(event ->{
                getChildren().add(localPlayModeMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), localGamesMenu);
                tt.setToX(localGamesMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), localPlayModeMenu);
                tt1.setToX(localGamesMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(localGamesMenu);
                });
            });

            MenuButton btnCredits = new MenuButton("CREDITS");
            btnCredits.setOnMouseClicked(event ->{
                // Displays credits
            });

            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event ->{
                getChildren().add(mainMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), connectionModeMenu);
                tt.setToX(connectionModeMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
                tt1.setToX(connectionModeMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(connectionModeMenu);
                });
            });

            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event ->{
                System.exit(0);
            });

            MenuButton btnPvp = new MenuButton("PLAYER VS PLAYER");
            btnPvp.setOnMouseClicked(event->{
                // Start game player vs player match: TicTacToe/Othello
            });

            MenuButton btnPvc = new MenuButton("PLAYER VS COMPUTER");
            btnPvc.setOnMouseClicked(event ->{
                // Start game player vs computer match: TicTacToe/Othello
            });

            MenuButton btnCvc = new MenuButton("COMPUTER VS COMPUTER");
            btnCvc.setOnMouseClicked(event ->{
                // start computer vs computer game
            });

            MenuButton btnLocal = new MenuButton("LOCAL");
            btnLocal.setOnMouseClicked(event ->{
                getChildren().add(localGamesMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), connectionModeMenu);
                tt.setToX(connectionModeMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), localGamesMenu);
                tt1.setToX(connectionModeMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(connectionModeMenu);
                });
            });

            MenuButton btnOnline = new MenuButton("ONLINE");
            btnOnline.setOnMouseClicked(event ->{
                getChildren().add(onlineGamesMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), connectionModeMenu);
                tt.setToX(connectionModeMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), onlineGamesMenu);
                tt1.setToX(connectionModeMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(connectionModeMenu);
                });
            });

            mainMenu.getChildren().addAll(btnPlay, btnCredits, btnExit);
            connectionModeMenu.getChildren().addAll(btnLocal, btnOnline, btnBack);
            localGamesMenu.getChildren().addAll(btnOthello);
            localPlayModeMenu.getChildren().addAll(btnPvp, btnPvc);
            onlineGamesMenu.getChildren().addAll(btnTicTacToe);
            onlinePlayModeMenu.getChildren().addAll(btnCvc);

            Rectangle bg = new Rectangle(800,600);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, mainMenu);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}