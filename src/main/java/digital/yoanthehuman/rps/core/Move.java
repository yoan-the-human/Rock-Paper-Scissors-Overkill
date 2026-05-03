package digital.yoanthehuman.rps.core;

public enum Move {
    ROCK, PAPER, SCISSORS;

    public boolean beats(Move opponent) {
        if (this == ROCK && opponent == SCISSORS) return true;
        if (this == PAPER && opponent == ROCK) return true;
        if (this == SCISSORS && opponent == PAPER) return true;
        
        return false;
    }
}