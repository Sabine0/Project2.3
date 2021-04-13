package app.state.games;

import app.networking.Processor;
import app.view.gameobjects.Board;
import app.view.gameobjects.TicTacToeBoard;
import javafx.scene.Parent;

/**
 * The TicTacToe class holds the game logic for TicTacToe
 * @author Sabine Schreuder
 * @version 12-04-21
 */
public class TicTacToeState extends GameState{
    public TicTacToeState(Processor processor, boolean online, String appUserUsername, String opponentUsername,
                          boolean appUserPlayer1, boolean p1Human, boolean p2Human, TicTacToeBoard tttBoard){
        super(processor, online, appUserUsername, opponentUsername, appUserPlayer1, p1Human, p2Human, tttBoard);
    }

    /**
     * @param board The current play board
     * @return boolean if game is won
     */
    public boolean isWon(Board board){
        for(int y=0; y<3; y++){
            if(board.getGrid()[0][y].getContent() == "X"
                    && board.getGrid()[1][y].getContent() == "X"
                    && board.getGrid()[2][y].getContent() == "X"){
                setWinner(getP1().getUsername());
                return true;
            }
            else if(board.getGrid()[0][y].getContent() == "O"
                    && board.getGrid()[1][y].getContent() == "O"
                    && board.getGrid()[2][y].getContent() == "O"){
                setWinner(getP2().getUsername());
                return true;
            }
        }

        for(int x=0; x<3; x++){
            if(board.getGrid()[x][0].getContent() == "X"
                    && getBoard().getGrid()[x][1].getContent() == "X"
                    && getBoard().getGrid()[x][2].getContent() == "X"){
                setWinner(getP1().getUsername());
                return true;
            }
            else if(getBoard().getGrid()[x][0].getContent() == "O"
                    && getBoard().getGrid()[x][1].getContent() == "O"
                    && getBoard().getGrid()[x][2].getContent() == "O"){
                setWinner(getP2().getUsername());
                return true;
            }
        }

        if(getBoard().getGrid()[0][0].getContent() == "X"
                && getBoard().getGrid()[1][1].getContent() == "X"
                && getBoard().getGrid()[2][2].getContent() == "X"){
            setWinner(getP1().getUsername());
            return true;
        }
        else if(getBoard().getGrid()[0][0].getContent() == "O"
                && getBoard().getGrid()[1][1].getContent() == "O"
                && getBoard().getGrid()[2][2].getContent() == "O"){
            setWinner(getP2().getUsername());
            return true;
        }
        else if(getBoard().getGrid()[2][0].getContent() == "X"
                && getBoard().getGrid()[1][1].getContent() == "X"
                && getBoard().getGrid()[0][2].getContent() == "X"){
            setWinner(getP1().getUsername());
            return true;
        }
        else if(getBoard().getGrid()[2][0].getContent() == "O"
                && getBoard().getGrid()[1][1].getContent()== "O"
                && getBoard().getGrid()[0][2].getContent()== "O"){
            setWinner(getP2().getUsername());
            return true;
        }
        return false;
    }

    /**
     * @return The view of a tictactoe board
     */
    @Override
    public Parent getView(){
        return getBoard().boardView();
    }
}

