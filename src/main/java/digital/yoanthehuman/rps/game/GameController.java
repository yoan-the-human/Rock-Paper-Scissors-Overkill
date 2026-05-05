package digital.yoanthehuman.rps.game;

import digital.yoanthehuman.rps.core.BotMoveGenerator;
import digital.yoanthehuman.rps.core.Move;
import digital.yoanthehuman.rps.core.RoundEvaluator;
import digital.yoanthehuman.rps.core.RoundResult;
import digital.yoanthehuman.rps.core.ScoreBoard;
import digital.yoanthehuman.rps.io.InputReader;
import digital.yoanthehuman.rps.io.MoveParser;
import digital.yoanthehuman.rps.io.OutputWriter;

public class GameController {

    // Console Colors for UI Styling
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String BOLD = "\u001B[1m";

    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final MoveParser moveParser;
    private final BotMoveGenerator botMoveGenerator;
    private final RoundEvaluator roundEvaluator;
    private final ScoreBoard scoreBoard;

    public GameController(InputReader inputReader, OutputWriter outputWriter, MoveParser moveParser,
            BotMoveGenerator botMoveGenerator, RoundEvaluator roundEvaluator, ScoreBoard scoreBoard) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.moveParser = moveParser;
        this.botMoveGenerator = botMoveGenerator;
        this.roundEvaluator = roundEvaluator;
        this.scoreBoard = scoreBoard;
    }

    public void start() {
        outputWriter.displayMessage("\n" + BOLD + CYAN + "======================================" + RESET);
        outputWriter.displayMessage(BOLD + CYAN + "  🎮 WELCOME TO ROCK, PAPER, SCISSORS!" + RESET);
        outputWriter.displayMessage(BOLD + CYAN + "======================================\n" + RESET);
        boolean isPlaying = true;

        while (isPlaying) {
            playSingleRound();
            isPlaying = askToContinue();
        }

        outputWriter.displayMessage("\n" + BOLD + YELLOW + "👋 Thanks for playing! See you next time." + RESET + "\n");
    }

    private void playSingleRound() {
        Move playerMove = requestValidPlayerMove();
        outputWriter
                .displayMessage(BLUE + "👤 You chose: " + BOLD + playerMove + " " + getMoveEmoji(playerMove) + RESET);
        Move botMove = generateBotMove();

        evaluateAndDisplayResult(playerMove, botMove);
        displayScoreBoard();
    }

    private Move requestValidPlayerMove() {
        while (true) {
            outputWriter.displayPrompt(
                    BOLD + "👉 Enter your move " + BLUE + "[R/1=Rock, P/2=Paper, S/3=Scissors]: " + RESET);
            String rawInput = inputReader.readRawInput();
            try {
                return moveParser.parse(rawInput);
            } catch (IllegalArgumentException exception) {
                outputWriter.displayMessage(RED + exception.getMessage() + RESET + "\n");
            }
        }
    }

    private Move generateBotMove() {
        Move botMove = botMoveGenerator.generate();
        outputWriter.displayMessage(CYAN + "🤖 Bot chose: " + BOLD + botMove + " " + getMoveEmoji(botMove) + RESET);
        return botMove;
    }

    private void evaluateAndDisplayResult(Move playerMove, Move botMove) {
        RoundResult result = roundEvaluator.evaluate(playerMove, botMove);
        updateScoreBoard(result);
        announceWinner(result);
    }

    private void updateScoreBoard(RoundResult result) {
        if (result == RoundResult.PLAYER_WINS) {
            scoreBoard.recordPlayerWin();
        } else if (result == RoundResult.BOT_WINS) {
            scoreBoard.recordBotWin();
        }
    }

    private void announceWinner(RoundResult result) {
        switch (result) {
            case PLAYER_WINS -> outputWriter.displayMessage(GREEN + BOLD + "🎉 Result: You win this round!" + RESET);
            case BOT_WINS -> outputWriter.displayMessage(RED + BOLD + "💀 Result: Bot wins this round!" + RESET);
            case TIE -> outputWriter.displayMessage(YELLOW + BOLD + "🤝 Result: It's a tie!" + RESET);
        }
    }

    private void displayScoreBoard() {
        String scoreMessage = String.format(
                BOLD + "📊 Scoreboard - " + GREEN + "You: %d" + RESET + BOLD + " | " + RED + "Bot: %d" + RESET,
                scoreBoard.getPlayerScore(), scoreBoard.getBotScore());

        outputWriter.displayMessage(
                "\n" + scoreMessage + "\n" + CYAN + "--------------------------------------" + RESET + "\n");
    }

    private boolean askToContinue() {
        outputWriter.displayPrompt(BOLD + "🔁 Do you want to play again? (y/n): " + RESET);
        String answer = inputReader.readRawInput().trim().toLowerCase();
        return answer.equals("yes") || answer.equals("y");
    }

    private String getMoveEmoji(Move move) {
        return switch (move) {
            case ROCK -> "🪨";
            case PAPER -> "📄";
            case SCISSORS -> "✂️";
        };
    }
}