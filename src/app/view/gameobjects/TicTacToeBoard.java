package app.view.gameobjects;

import app.users.Player;
import javafx.scene.Parent;


/**
 * The TicTacToeBoard class defines the Tic-tac-toe board
 * and handles changes to the board
 * @author Sabine Schreuder
 * @version 10-04-21
 */
public class TicTacToeBoard extends Board {
    public TicTacToeBoard(Player player1, Player player2){
        super(3, new TicTacToeTile[3][3], "TIC-TAC-TOE", player1, player2);
    }

    @Override
    public int getBoardSize() {
        return 3;
    }

    /**
     * @param col The column on the board
     * @param row The row on the board
     * @return boolean if the move is valid
     */
    @Override
    public boolean isValidMove(int col, int row){
        if(getTile(col, row).getContent() == "X" || getTile(col, row).getContent() == "O"){
            return false;
        }
        return true;
    }

    @Override
    public TicTacToeTile getTile(int col, int row) {
        return (TicTacToeTile) getGrid()[col][row];
    }
    @Override
    public void setTilesForMove(){
        //NOTHING
    }

    @Override
    public Parent boardView() {
        return getBoardPane();
    }
}
