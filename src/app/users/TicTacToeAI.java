package app.users;

import app.users.Bot;
import app.view.gameobjects.Board;

import java.util.ArrayList;
import java.util.Random;

/**
 * The TicTacToeAI class holds the attributes of the tictactoe algorithm
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public class TicTacToeAI extends Bot {
    public TicTacToeAI(String username) {
        super(username);
    }

    boolean first = true;

    public int[] getMove(Board board) {

        int[] corners = {0, 2};
        Random rand = new Random();
        int X;
        int Y;
        int[] coordinates = new int[]{};

        if (first) {
            X = rand.nextInt(corners.length);
            Y = rand.nextInt(corners.length);
            first = false;

        } else {
            X = rand.nextInt(3);
            Y = rand.nextInt(3);
        }

        if(board.isValidMove(X,Y)){
            coordinates = new int[]{corners[X], corners[Y]};
        }
        else{
            getMove(board);
        }
        return coordinates;
    }
}
