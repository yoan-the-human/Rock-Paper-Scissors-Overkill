package digital.yoanthehuman.rps.io;

import digital.yoanthehuman.rps.core.Move;

public class MoveParser {
    
    public Move parse(String input) {
        try {
            return Move.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid move. Please enter Rock, Paper, or Scissors.");
        }
    }
}