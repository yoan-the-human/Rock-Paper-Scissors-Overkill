package digital.yoanthehuman.rps.core;

import java.util.Random;

public class BotMoveGenerator {
    private final Random randomGenerator;

    public BotMoveGenerator() {
        this.randomGenerator = new Random();
    }

    public Move generate() {
        Move[] allMoves = Move.values();
        int randomIndex = randomGenerator.nextInt(allMoves.length);
        return allMoves[randomIndex];
    }
}