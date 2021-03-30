package algorithm;

import java.util.HashMap;

public class ReversieAlgorithm {

    private int[] board;

    public ReversieAlgorithm (int[] board) {
        this.board = board;
    }

    // intereger = index 
    public HashMap<Integer, Integer> availableMoves(int tegenstander, int speler) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // index 0 = hoeveel steentjes er worden omgedraaid
        // index 1 = score van de zet. 0 score voor een rij naast de rand
        // 1 score voor een normale ze, 2 score voor rand, 3 score voor hoekje.

        for (int i = 0; i < 64; i++) {
            if (i >= 1 && i <= 6) {

            } else if (i % 8 == 0 && i != 0 && i != 56) {

            } else if (i % 8 == 7 && i != 7 && i != 63) {

            } else if (i >= 57 && i <= 62) {

            } else if (i == 0 || i == 7 || i == 56 || i == 63) {

            } else {
                if (board[i - 1] == 2) {
                    if (checkL(board, i) > -1) {
                        map.put(i, 1);
                    }
                } else if (board[i + 1] == 2) {
                    if (checkR(board, i)> -1) {
                        map.put(i, 1);
                    }
                } else if (board[i - 8] == 2) {
                    
                }
            }
        }

        return map;
    }

    public int checkL (int[] board, int i) {
        for (int k = i-1; k > (i - (i % 8)); k--) {
            if (board[k] == 1) {
                return i;  
            }
        }
        return -1;
    }
    public int checkR (int[] board, int i) {
        for (int k = i+1; k < (i + (8 - (i % 8))); k++) {
            if (board[k] == 1) {
                return i;
            }
        }
        return -1;
    }
}
