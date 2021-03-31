package algorithm;

import java.util.Random;

public class TicTacToeAlgorithm {

    private int[] board;
    Random rand = new Random();
    static final int[] corners = {0, 2, 6, 8};

    public void setBoard (int[] board) {
        this.board = board;
    }

    //in deze methode wordt het spel gespeeld
    // geef mee wie er aan de beurt is
    public void game(boolean beurt, int[] board){
        //met deze ints wordt bepaald op welke plaats de speler een set doet
        int setSelf;
        int setOpp;

        //initialize het board
        setBoard(board);

        //als jij de eerste stap mag maken, kies dan een random hoek uit
        if(beurt){
            setSelf = getCorner(corners);
            board[setSelf] = 1;
            beurt = false;
        }
        else{
            switch(getPosition()){
                case 0, 2, 6, 8:
                    //
                    break;
                case 4:
                    //
                    break;
                default:
                    //
                    break;
            }
        }

    }

    //met deze methode wordt een hoek uitgekozen waar een set op gedaan kan worden
    private int getCorner(int[] array){
        int corner = rand.nextInt(array.length);
        return array[corner];
    }

    private void getPossibility(){
        final int self = 1;
        final int opp = 2;
    }

    private int getPosition(){
        int pos;

        return pos;
    }

}
