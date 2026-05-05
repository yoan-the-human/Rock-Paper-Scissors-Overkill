package digital.yoanthehuman.rps.io;

import digital.yoanthehuman.rps.core.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveParserTest {

    private final MoveParser parser = new MoveParser();

    @Test
    void parsesFullWords() {
        assertEquals(Move.ROCK, parser.parse("ROCK"));
        assertEquals(Move.PAPER, parser.parse("paper"));
        assertEquals(Move.SCISSORS, parser.parse("ScIsSoRs"));
    }

    @Test
    void parsesLetterShorthands() {
        assertEquals(Move.ROCK, parser.parse("r"));
        assertEquals(Move.PAPER, parser.parse("P"));
        assertEquals(Move.SCISSORS, parser.parse("s"));
    }

    @Test
    void parsesNumericShorthands() {
        assertEquals(Move.ROCK, parser.parse("1"));
        assertEquals(Move.PAPER, parser.parse("2"));
        assertEquals(Move.SCISSORS, parser.parse("3"));
    }

    @Test
    void ignoresTrailingAndLeadingWhitespace() {
        assertEquals(Move.ROCK, parser.parse("  1  "));
        assertEquals(Move.PAPER, parser.parse("\tP\n"));
    }

    @Test
    void throwsExceptionOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("Lizard"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("4"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }
}