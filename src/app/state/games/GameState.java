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
            board.setPlayer2(p2);
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
            board.setPlayer1(p1);
            board.setPlayer2(p2);
            launchLocal();
        }
    }

    public void launchLocal(){
        // TO DO: fix
        board.setP1turn(true);
//        while(!isWon(getBoard())) {
            if(board.isP1turn()) {
                int[] move = doMoveLocal(getP1());
                if(board.isValidMove(move[0], move[1])) {
                    board.drawMove(getP1().getUsername(), move[0], move[1]);
                    board.setTilesForMove();
                    System.out.println("p1 did turn "+ move[0] + " " + move[1]);
                }
            }else{
                int[] move = doMoveLocal(getP2());
                if(board.isValidMove(move[0], move[1])) {
                    board.drawMove(p2.getUsername(), move[0], move[1]);
                    board.setTilesForMove();
                    System.out.println("p2 did turn "+ move[0] + " " + move[1]);
                }
            }
//        }
    }

    public int[] doMoveLocal(Player player){
        int[] move;

        if(board.isP1turn()){
            move = p1.getMove(board);
        }else{
            move = p2.getMove(board);
        }

        if(board.isValidMove(move[0], move[1])) {
            board.drawMove(player.getUsername(), move[0], move[1]);
        }

        return move;
    }

    public void launchOnline(){
        // TO DO: implement?
        // I guess everything happens in/through notifier?
    }

    public void doMoveOnline() throws ServerNotRespondingException, CommandFailedException {
        int[] move;
        int intMove;

        if(board.isP1turn()){
            move = p1.getMove(board);
        }else{
            move = p2.getMove(board);
        }

        intMove = board.convertMove(move, board.getBoardSize());
        if(board.isValidMove(move[0], move[1])){
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
    }

    /**
     * @return A child of Board
     */
    public Board getBoard(){
        return board;
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
