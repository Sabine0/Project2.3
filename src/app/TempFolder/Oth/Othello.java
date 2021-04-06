//package app.TempFolder.Oth;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Othello extends Application {
//
//    private boolean playable = true;
//    private boolean turnX = true;
//    private Othello.Tile[][] board = new Othello.Tile[8][8];
//    private List<Othello.Combo> combos = new ArrayList<>();
//    Pane root = new Pane();
//
//    private Parent createContent() {
//
//        root.setPrefSize(800, 800);
//
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                Othello.Tile tile = new Othello.Tile();
//                tile.setTranslateX(j * 100);
//                tile.setTranslateY(i * 100);
//
//                root.getChildren().add(tile);
//
//                board[j][i] = tile;
//            }
//        }
//        return root;
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new Scene(createContent()));
//        board[3][3].drawO();
//        board[4][3].drawX();
//        board[3][4].drawX();
//        board[4][4].drawO();
//        primaryStage.show();
//        AlgorithmO algorithm = new AlgorithmO();
//        algorithm.setBoard(board);
//    }
//
//    private void checkState() {
////        hardcode, moet beter
//        int done = 0;
//
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                if(!board[j][i].getValue().isEmpty()){ done++; }
//            }
//        }
//
//        if(done == 64){
//            playable = false;
//            whoWon();
//        }
////        ------------------------------------------------------------
//
//        for(Othello.Combo combo : combos){
//            if(combo.isComplete()){
//                flipDiscs(combo);
//                break;
//            }
//        }
//    }
//
//    private class Combo {
//        private Othello.Tile[] tiles;
//
//        public Combo(Othello.Tile... tiles) {
//            this.tiles = tiles;
//        }
//
//        public boolean isComplete() {
//            if (tiles[0].getValue().isEmpty()) {
//                return false;
//            }
//
//            return true;
//        }
//    }
//
//    private void flipDiscs(Combo combo){
//
//    }
//
//    public class Tile extends StackPane {
//        private Text text = new Text();
//
//        public Tile(){
//            Rectangle border = new Rectangle(100,100);
//            border.setFill(null);
//            border.setStroke(Color.BLACK);
//
//            text.setFont(Font.font(72));
//
//            setAlignment(Pos.CENTER);
//            getChildren().addAll(border, text);
//
//            setOnMouseClicked(event ->{
//                if(!playable){
//                    return;
//                }
//                if(event.getButton() == MouseButton.PRIMARY) {
//                    if (!turnX) return;
//                    if(empty()){
//                        drawX();
//                        turnX = false;
//                        checkState();
//                    }
//
//                }else if(event.getButton() == MouseButton.SECONDARY){
//                    if(turnX) return;
//                    if(empty()) {
//                        drawO();
//                        turnX = true;
//                        checkState();
//                    }
//                }
//            });
//        }
//
//        public String getValue(){ return text.getText(); }
//
//        private void drawX(){ text.setText("\u25CF"); }
//
//        private void drawO(){ text.setText("\u25CB"); }
//
//        public boolean empty(){ return getValue().isEmpty(); }
//    }
//
//    public void whoWon(){
//        int X = 0;
//        int O = 0;
//
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                if(board[j][i].getValue() == "\u25CF"){ X++; }
//                else{ O++; }
//            }
//        }
//        if(X > O){ System.out.println("Player X won!"); }
//        else if(O > X){ System.out.println("Player O won!"); }
//        else{ System.out.println("Tie!"); }
//    }
//
//    public Othello.Tile[][] getBoard(){ return board; }
//}
