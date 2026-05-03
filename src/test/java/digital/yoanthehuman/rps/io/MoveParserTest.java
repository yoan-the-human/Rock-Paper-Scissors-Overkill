package digital.yoanthehuman.rps.io;

import digital.yoanthehuman.rps.core.Move;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveParserTest {

    private final MoveParser parser = new MoveParser();

    @Test
    void parsesValidUppercaseString() {
        assertEquals(Move.ROCK, parser.parse("ROCK"));
    }

    @Test
    void parsesValidLowercaseString() {
        assertEquals(Move.PAPER, parser.parse("paper"));
    }

    @Test
    void parsesValidStringWithWhitespace() {
        assertEquals(Move.SCISSORS, parser.parse("  Scissors  "));
    }

    @Test
    void throwsExceptionOnInvalidString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> parser.parse("Lizard")
        );
        
        assertEquals("Invalid move. Please enter Rock, Paper, or Scissors.", exception.getMessage());
    }
}