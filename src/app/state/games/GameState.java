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

/**
 * The GameState class is a state in which the current game is being displayed
 * @author Sabine Schreuder
 * @version 13-04-21
 */
public abstract class GameState extends State {
    private Player p1;
    private Player p2;
    private Board board;
    private String winner;
    private boolean playing;
    private boolean p1turn;
    private int points; // unsure if we need this
    Processor processor;

    public GameState(Processor processor, boolean online, String appUserUsername, String opponentUsername,
                     boolean appUserPlayer1, boolean p1Human, boolean p2Human, Board board){
        this.board = board;
        this.processor = processor;

        if(online){
            if(appUserPlayer1){
                p1 = new UserPlayer(appUserUsername);
                p1.setIsPlayer1(true);
                p2 = new OnlineOpponent(opponentUsername);
                p2.setIsPlayer1(false);
            }else{
                p2 = new UserPlayer(appUserUsername);
                p2.setIsPlayer1(true);
                p1 = new OnlineOpponent(opponentUsername);
                p1.setIsPlayer1(false);
            }
            board.setPlayer1(p1);
            launchOnline();
        }else{
            if(p1Human && p2Human){
                p1 = new UserPlayer(appUserUsername);
                p1.setIsPlayer1(true);
                p2 = new UserPlayer(opponentUsername);
                p2.setIsPlayer1(false);
            }else if(p1Human){
                p1 = new UserPlayer(appUserUsername);
                p1.setIsPlayer1(true);
                p2 = new Bot(opponentUsername);
                p2.setIsPlayer1(false);
            } else{
                p1 = new Bot(appUserUsername);
                p1.setIsPlayer1(true);
                p2 = new UserPlayer(opponentUsername);
                p2.setIsPlayer1(false);
            }
            board.setPlayer2(p2);
            launchLocal();
        }
    }

    public void launchLocal(){
        // TO DO: implement
        p1turn = true;
        playing = true;
    }

    public int[] doMoveLocal(Player player){
        int[] move;

        // not sure if this works
        if(p1turn){
            move = p1.getMove(board);
        }else{
            move = p2.getMove(board);
        }

        if(board.isValidMove(move[0], move[1], p1turn)) {
            board.drawMove(player.getUsername(), move[0], move[1]);
        }

        return move;
    }

    public void launchOnline(){
        // TO DO: implement?
        // I guess everything happens in/through notifier?
        playing = true;
        p1turn = true;
    }

    public void doMoveOnline() throws ServerNotRespondingException, CommandFailedException {
        int[] move;
        int intMove;

        if(p1turn){
            move = p1.getMove(board);
        }else{
            move = p2.getMove(board);
        }

        intMove = board.convertMove(move, board.getBoardSize());
        if(board.isValidMove(move[0], move[1], p1turn)){
            processor.move(intMove);
        }else{
            System.out.println("INVALID MOVE!!");
        }
    }

    /**
     * Alerts the player that the match has been won by winner
     */
    public void showWinAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The game has ended");
        alert.setHeaderText("The winner is: " + winner);
        alert.setContentText("Click OK to return to the main menu");
        alert.setOnCloseRequest(returnEvent ->{
            StateController.setState(new MainMenuState());
        });
        alert.show();
        playing = false;
    }

    public void showDrawAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The game has ended");
        alert.setHeaderText("The game has ended in a draw!");
        alert.setContentText("Click OK to return to the main menu");
        alert.setOnCloseRequest(returnEvent ->{
            StateController.setState(new MainMenuState());
        });
        alert.show();
        playing = false;
    }

    /**
     * @return A child of Board
     */
    public <T extends Board> T getBoard(){
        return (T) board;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setWinner(String winnerName){
        winner = winnerName;
    }

    public void setWinnerPoints(int winnerPoints){
        points = winnerPoints;
    } // not sure if we should implement

    // only for othello
    public abstract boolean isWon(Board board);

    /**
     * Classes that extend the GameState class should override this method
     * @return the view of the new objectView
     */
    public Parent getView(){
        System.out.println("there should be a game view here");
        return new Pane();
    }
}
