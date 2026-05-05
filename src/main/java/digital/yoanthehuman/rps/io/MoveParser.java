package digital.yoanthehuman.rps.io;

import digital.yoanthehuman.rps.core.Move;

public class MoveParser {

    public Move parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        String normalizedInput = input.trim().toUpperCase();

        return switch (normalizedInput) {
            case "ROCK", "R", "1" -> Move.ROCK;
            case "PAPER", "P", "2" -> Move.PAPER;
            case "SCISSORS", "S", "3" -> Move.SCISSORS;
            default -> throw new IllegalArgumentException(
                    "Invalid move. Please enter Rock (R/1), Paper (P/2), or Scissors (S/3).");
        };
    }
}