package algorithm;

import java.util.HashMap;

public class ReversieAlgorithm {

    private int[] board;

    public ReversieAlgorithm (int[] board) {
        this.board = board;
    }

    public HashMap<Integer, int[]> availableMoves() {
        HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
        // index 0 = hoeveel steentjes er worden omgedraaid
        // index 1 = score van de zet. 0 score voor een rij naast de rand
        // 1 score voor een normale ze, 2 score voor rand, 3 score voor hoekje.
        int[] scores = new int[]{};

        for (int i = 0; i < 64; i++) {
            if (i >= 1 && i <= 6) {

            } else if (i % 8 == 0 && i != 0 && i != 56) {

            } else if (i % 8 == 7 && i != 7 && i != 63) {

            } else if (i >= 57 && i <= 62) {

            } else if (i == 0 || i == 7 || i == 56 || i == 63) {

            } else {
                
            }
        }

        return map;
    }
}
