package digital.yoanthehuman.rps.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundEvaluatorTest {

    private final RoundEvaluator evaluator = new RoundEvaluator();

    @Test
    void identicalMovesResultInTie() {
        assertEquals(RoundResult.TIE, evaluator.evaluate(Move.ROCK, Move.ROCK));
    }

    @Test
    void winningPlayerMoveResultsInPlayerWin() {
        assertEquals(RoundResult.PLAYER_WINS, evaluator.evaluate(Move.PAPER, Move.ROCK));
    }

    @Test
    void losingPlayerMoveResultsInBotWin() {
        assertEquals(RoundResult.BOT_WINS, evaluator.evaluate(Move.ROCK, Move.PAPER));
    }
}