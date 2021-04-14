package app.state.games;

import app.StateController;
import app.networking.CommandFailedException;
import app.networking.Processor;
import app.networking.ServerNotRespondingException;
import app.view.gameobjects.Board;
import app.state.MainMenuState;
import app.state.State;
import app.users.*;
import app.users.UserPlayer;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.util.Arrays;

/**
 * The GameState class is a state in which the current game is being displayed
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public abstract class GameState extends State {
    private Board board;
    private Processor processor;

    public GameState(Processor processor, boolean online, Board board){
        this.board = board;
        this.processor = processor;

        if(online){
            launchOnline();
        }else{
            launchLocal();
        }
    }

    public void launchLocal(){
        // TO DO: fix, thread?
        board.setP1turn(true);
//        while(!isWon(getBoard())) {
            if(board.isP1turn()) {
                if (!board.getP1().isHuman()){
                    int[] move = doMoveLocal(board.getP1());
                    if (board.isValidMove(move[0], move[1])) {
                        board.drawMove(board.getP1().getUsername(), move[0], move[1]);
                        board.setTilesForMove();
                        System.out.println("p1 did turn " + move[0] + " " + move[1]);
                    }
                }
            }else{
                if (!board.getP2().isHuman()) {
                    int[] move = doMoveLocal(board.getP2());
                    if (board.isValidMove(move[0], move[1])) {
                        board.drawMove(board.getP2().getUsername(), move[0], move[1]);
                        board.setTilesForMove();
                        System.out.println("p2 did turn " + move[0] + " " + move[1]);
                    }
                }
            }
//        }
    }

    public int[] doMoveLocal(Player player){
        int[] move;

        if(board.isP1turn()){
            move = board.getP1().getMove(board);
        }else{
            move = board.getP2().getMove(board);
        }

        if(board.isValidMove(move[0], move[1])) {
            board.drawMove(player.getUsername(), move[0], move[1]);
        }

        return move;
    }

    public boolean isWon(Board board) {
        // games makes its own implementation of this method
        return false;
    }

    public void launchOnline(){
        // TO DO: implement?
        // I guess everything happens in/through notifier?
    }

    public void doMoveOnline() throws ServerNotRespondingException, CommandFailedException {
        int[] move;
        int intMove;

        if(board.isP1turn()){
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
