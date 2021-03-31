package app.algorithm;

import java.util.HashMap;

// @author Luc Willemse
// @version 1.0
public class FindAvailableMoves {
    private int[][] board;

    public FindAvailableMoves (int[][] board) {
        this.board = board;
    }

    // hier wordt een hashmap aangemaakt. de index van de hashmap is int array, met daarin de coordinaten van het board.
    // de value van de hashmap is ook een int array, de eerste int is hoeveel steentjes ermee worden omgedraaid, andere is
    // 0 wanneer de zet niet aan een rand is, 
    public HashMap<int[], int[]> MapOfMoves() {
        HashMap<int[], int[]> hashmap = new HashMap<int[], int[]>();
        return hashmap;
    }
}
