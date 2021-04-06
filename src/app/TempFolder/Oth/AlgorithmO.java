//package app.TempFolder.Oth;
//
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//
//public class AlgorithmO {
//
//    private Othello.Tile[][] board;
//
//    public AlgorithmO() {
//
//    }
//
//    public void setBoard(Othello.Tile[][] board) {
//        this.board = board;
//    }
//
//    public boolean validMove(int c, int r, char playing, char tegenStander) {
//        boolean valid = false;
//        boolean p = true;
//        while (p == true) {
//            if (board[c - 1][r - 1] == tegenStander && checkUL(c - 1, r - 1, playing)) {
//                valid = true;
//            } else if (board[c - 1][r] == tegenStander && checkUM(c - 1, r, playing)) {
//                valid = true;
//            } else if (board[c - 1][r + 1] == tegenStander && checkUR(c - 1, r + 1, playing)) {
//                valid = true;
//            } else if (board[c][r - 1] == tegenStander && checkML(c, r - 1, playing)) {
//                valid = true;
//            } else if (board[c][r + 1] == tegenStander && checkMR(c, r + 1, playing)) {
//                valid = true;
//            } else if (board[c + 1][r - 1] == tegenStander && checkDL(c + 1, r - 1, playing)) {
//                valid = true;
//            } else if (board[c + 1][r] == tegenStander && checkDM(c + 1, r, playing)) {
//                valid = true;
//            } else if (board[c + 1][r + 1] == tegenStander && checkDR(c + 1, r + 1, playing)) {
//                valid = true;
//            } else {
//                p = false;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkUL(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c + 1][r + 1] = playing;
//        boolean valid = false;
//        while (c > 0 && r > 0) {
//            tempBoard[c][r] = playing;
//            c--;
//            r--;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//
//    }
//
//    public boolean checkUM(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c + 1][r] = playing;
//        boolean valid = false;
//        while (c > 0) {
//            tempBoard[c][r] = playing;
//            c--;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkUR(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c + 1][r - 1] = playing;
//        boolean valid = false;
//        while (c > 0 && r < 7) {
//            tempBoard[c][r] = playing;
//            c--;
//            r++;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkML(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c][r + 1] = playing;
//        boolean valid = false;
//        while (r > 0) {
//            tempBoard[c][r] = playing;
//            r--;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkMR(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c][r - 1] = playing;
//        boolean valid = false;
//        while (r < 7) {
//            tempBoard[c][r] = playing;
//            r++;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkDL(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c - 1][r + 1] = playing;
//        boolean valid = false;
//        while (c < 7 && r > 0) {
//            tempBoard[c][r] = playing;
//            c++;
//            r--;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkDM(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c - 1][r] = playing;
//        boolean valid = false;
//        while (c < 7) {
//            tempBoard[c][r] = playing;
//            c++;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public boolean checkDR(int c, int r, char playing) {
//        char[][] tempBoard = new char[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int k = 0; k < 10; k++) {
//                tempBoard[i][k] = board[i][k];
//            }
//        }
//        tempBoard[c - 1][r - 1] = playing;
//        boolean valid = false;
//        while (c < 7 && r < 7) {
//            tempBoard[c][r] = playing;
//            c++;
//            r++;
//            if (board[c][r] == playing) {
//                valid = true;
//                board = tempBoard;
//                break;
//            }
//        }
//        return valid;
//    }
//
//    public void printBoard() {
//        for (char[] cRow : board) {
//            System.out.print("\n");
//            for (char t : cRow) {
//                System.out.print(t + " ");
//            }
//        }
//    }
//
//
//    //    testen met board
//    public class Tile extends StackPane {
//        private Text text = new Text();
//
//        public Tile() {
//            Rectangle border = new Rectangle(100, 100);
//            border.setFill(null);
//            border.setStroke(Color.BLACK);
//
//            text.setFont(Font.font(72));
//
//            setAlignment(Pos.CENTER);
//            getChildren().addAll(border, text);
//        }
//    }
//}
