package digital.yoanthehuman.rps.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MoveTest {

    @Test
    void rockBeatsScissors() {
        assertTrue(Move.ROCK.beats(Move.SCISSORS));
    }

    @Test
    void paperBeatsRock() {
        assertTrue(Move.PAPER.beats(Move.ROCK));
    }

    @Test
    void scissorsBeatsPaper() {
        assertTrue(Move.SCISSORS.beats(Move.PAPER));
    }

    @Test
    void movesDoNotBeatThemselves() {
        assertFalse(Move.ROCK.beats(Move.ROCK));
        assertFalse(Move.PAPER.beats(Move.PAPER));
        assertFalse(Move.SCISSORS.beats(Move.SCISSORS));
    }

    @Test
    void rockDoesNotBeatPaper() {
        assertFalse(Move.ROCK.beats(Move.PAPER));
    }
}