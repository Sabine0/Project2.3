package algorithm;

public class MakeBoard {

    private int[] board;

    public MakeBoard() {
        this.board = new int[]{};
    }

    /*
    / hier wordt het board aangemaakt en klaar gezet voor het spel
    / game bevat de naam van het spel en beginner is een boolean die aangeeft of wij mogen beginnen
    */
    public void createBoard(String game, boolean beginner) {
        if (game == "Reversie") {
            for (int i = 0; i < 64; i++) {
                board[i] = 0;
            } 
            if (beginner == true) {
                board[28] = 1;
                board[35] = 1;
                board[27] = 2;
                board[36] = 2;
            } else if (beginner == false) {
                board[27] = 1;
                board[36] = 1;
                board[28] = 2;
                board[35] = 2;
            }
        } else if (game == "Tic-tac-toe") {
            for (int i = 0; i < 9; i++) {
                board[i] = 0;
            }
        }
    }

    /*
    / int move is de zet die gedaan is of door ons of door de tegenpartij
    / boolean turn is true wanneer het ons beurt is en dan wordt er een 1 in het bord gezet
    / int game geeft aan welke game we spelen, dit kan nog veranderd worden. voor nu in reversie 1 en tic-tac-toe 2
    */
    public void modifyBoard(int move, boolean turn, String game) {
        if (checkValidMove(move, game) == true) {
            if (turn == true) {
                board[move] = 1;
            } else if (turn == false) {
                board[move] = 2;
            }
        } else {
            System.out.println("dit is geen geldige zet");
        }
    } 

    public boolean checkValidMove(int move, String game) {
        boolean valid = false;
        if (game == "Reversie") {
            // hier moet kijken welke zetten mogelijk zijn, hier wordt een andere functie voor aangeroepen.
        } else if (game == "Tic-tac-toe") {
            if (board[move] == 0) {
                valid = true;
            }
        }
        return valid;
    }

    public int[] getBoard() {
        return board;
    }
        


}
