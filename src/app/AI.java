package app;

import java.util.ArrayList;

public class AI {

    int[][] board = new int[8][8];
    int us;
    int them;
    private ArrayList<Integer> tilesToBeFlipped;

    public AI(int us, int them) {
        this.us = us;
        this.them = them;
        board = new int[][]{{0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,2,1,0,0,0},
                            {0,0,0,1,2,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0}};
    }

    public int getTile(int col, int row){
        return board[row][col];
    }

    public void setMove(int col, int row, int turn) {
        if(isValidMove(col, row, turn) && turn == them){
            for(int i = 0; i < tilesToBeFlipped.size(); i+= 2){
                board[tilesToBeFlipped.get(i)][tilesToBeFlipped.get(i+1)] = them;
            }
        } 
        if(isValidMove(col, row, turn) && turn == us){
            for(int i = 0; i < tilesToBeFlipped.size(); i+= 2){
                board[tilesToBeFlipped.get(i)][tilesToBeFlipped.get(i+1)] = us;
            }
        }
    }

    public int[] getMove(){
        ArrayList<Integer> listOfPossible = new ArrayList<>();
        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {
                if(isValidMove(col, row, us)) {
                    //setListOfCoordinatesEmpty();
                    listOfPossible.add(col);
                    listOfPossible.add(row);
                    if((col == 0 && row == 0) || (col == 0 && row == 7) ||(col == 7 && row == 0) ||(col == 7 && row == 7)) {
                        listOfPossible.add(5);
                    } else if((col == 0 && row == 1) || (col == 1 && row == 0) || (col == 1 && row == 1) || (col == 0 && row == 6)
                            || (col == 1 && row == 6) || (col == 1 && row == 7) || (col == 6 && row == 0) || (col == 6 && row == 1)
                            || (col == 7 && row == 1) || (col == 6 && row == 7) || (col == 6 && row == 6) || (col == 7 && row == 6)) {
                        listOfPossible.add(1);
                    } else if (col == 0 || col == 7 || row == 0 || row == 7) {
                        listOfPossible.add(4);
                    } else if (col == 1 || col == 6 || row == 1 || row == 6) {
                        listOfPossible.add(2);
                    } else {
                        listOfPossible.add(3);
                    }
                }
            }
        }

        int highestScore = 0;
        int indexOfHighestScore = 0;
        for (int i = 2; i < listOfPossible.size(); i += 3) {
            if(listOfPossible.get(i) > highestScore) {
                highestScore = listOfPossible.get(i);
                indexOfHighestScore = i;
            }
        }
        setMove(listOfPossible.get(indexOfHighestScore - 2), listOfPossible.get(indexOfHighestScore - 1), us);
        return new int[]{listOfPossible.get(indexOfHighestScore - 2), listOfPossible.get(indexOfHighestScore - 1)};
    }

    /**
     * @param col The column on the board
     * @param row The row on the board
     * @return boolean if the move is valid
     */
    public boolean isValidMove(int col, int row, int turn){

        tilesToBeFlipped.clear();
        boolean valid = false;
        int playingColour;
        int notPlayingColour;

        if(turn == them) {
            playingColour = them;
            notPlayingColour = us;
        } else {
            playingColour = us;
            notPlayingColour = them;
        }

        if(col - 1 > -1 && row - 1 > -1) {
            if (getTile(col-1, row-1) == notPlayingColour && checkUL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1) {
            if (getTile(col-1, row-1) == notPlayingColour && checkUM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col - 1 > -1 && row + 1 < 8) {
            if (getTile(col-1, row-1) == notPlayingColour && checkUR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row - 1 > -1) {
            if (getTile(col-1, row-1) == notPlayingColour && checkML(col, row, playingColour)) {
                valid = true;
            }
        }
        if(row + 1 < 8) {
            if (getTile(col-1, row-1) == notPlayingColour && checkMR(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row - 1 > -1) {
            if (getTile(col-1, row-1) == notPlayingColour && checkDL(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8) {
            if (getTile(col-1, row-1) == notPlayingColour && checkDM(col, row, playingColour)) {
                valid = true;
            }
        }
        if(col + 1 < 8 && row + 1 < 8) {
            if (getTile(col-1, row-1) == notPlayingColour && checkDR(col, row, playingColour)) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUL(int c, int r, int playingColour) {
        ArrayList<Integer> tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c > 0 && r > 0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r--;
            if(getTile(c, r) == playingColour) {
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUM(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c column in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkUR(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c>0 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c--;
            r++;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkML(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r--;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkMR(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            r++;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDL(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r>0) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r--;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDM(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * @param c collom in board
     * @param r row in board
     * @param playingColour colour of whose turn it is
     * @return true when is valid row.
     */
    public boolean checkDR(int c, int r, int playingColour) {
        ArrayList<Integer>tempListOfCoordinates = new ArrayList<>();
        tempListOfCoordinates.add(c);
        tempListOfCoordinates.add(r);
        boolean valid = false;
        while(c<7 && r<7) {
            tempListOfCoordinates.add(c);
            tempListOfCoordinates.add(r);
            c++;
            r++;
            if(getTile(c, r) == playingColour){
                valid = true;
                setArrayOfCoordinates(tempListOfCoordinates);
                break;
            }
        }
        return valid;
    }

    /**
     * Tracks the coordinates of the tiles that need to be flipped when the move is executed
     * @param tempListOfCoordinates The coordinates to be added to the tilesToBeFlipped array
     */
    public void setArrayOfCoordinates(ArrayList<Integer> tempListOfCoordinates) {
        this.tilesToBeFlipped.addAll(tempListOfCoordinates);
    }
}
