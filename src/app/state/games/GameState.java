package app.state.games;

import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.view.gameobjects.Board;
import app.state.State;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.Arrays;

/**
 * The GameState class is a state in which the current game is being displayed
 * @author Sabine Schreuder
 * @version 15-04-21
 */
public abstract class GameState extends State {
    private Board board;
    private Processor processor;

    public GameState(Processor processor, Board board){
        this.board = board;
        this.processor = processor;

//        launchOnline();
    }

    /**
     * Everything to be executed upon launching the game
     */
    public void launchOnline(){
        // Everything happens in/through notifier and processor
    }

    public void doMoveOnline() throws ServerNotRespondingException, CommandFailedException {
        int[] move;
        int intMove;

        if(board.getCurrentPlayer().equals(board.getP1())){
            move = board.getP1().getMove(board);
        }else{
            move = board.getP2().getMove(board);
        }

        System.out.println("Doing move: " + Arrays.toString(move));
        intMove = board.convertMove(move, board.getBoardSize());
        if(board.isValidMove(move[0], move[1])){
            processor.move(intMove);
        }else{
            System.out.println("INVALID MOVE!!");
        }
    }

    /**
     * @return A child of Board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Classes that extend the GameState class should override this method
     * @return the view of the new objectView
     */
    public Parent getView(){
        System.out.println("there should be a game view here");
        return new Pane();
    }
}
