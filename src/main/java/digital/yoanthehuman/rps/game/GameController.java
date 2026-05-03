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
        outputWriter.displayMessage("Welcome to Rock, Paper, Scissors!");
        boolean isPlaying = true;
        
        while (isPlaying) {
            playSingleRound();
            isPlaying = askToContinue();
        }
        
        outputWriter.displayMessage("Thanks for playing!");
    }

    private void playSingleRound() {
        Move playerMove = requestValidPlayerMove();
        Move botMove = generateBotMove();
        
        evaluateAndDisplayResult(playerMove, botMove);
        displayScoreBoard();
    }

    private Move requestValidPlayerMove() {
        while (true) {
            outputWriter.displayPrompt("Enter your move (Rock, Paper, Scissors):");
            String rawInput = inputReader.readRawInput();
            try {
                return moveParser.parse(rawInput);
            } catch (IllegalArgumentException exception) {
                outputWriter.displayMessage(exception.getMessage());
            }
        }
    }

    private Move generateBotMove() {
        Move botMove = botMoveGenerator.generate();
        outputWriter.displayMessage("Bot chose: " + botMove);
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
            case PLAYER_WINS -> outputWriter.displayMessage("Result: You win this round!");
            case BOT_WINS -> outputWriter.displayMessage("Result: Bot wins this round!");
            case TIE -> outputWriter.displayMessage("Result: It's a tie!");
        }
    }

    private void displayScoreBoard() {
        String scoreMessage = String.format("Scoreboard - You: %d | Bot: %d", 
                scoreBoard.getPlayerScore(), scoreBoard.getBotScore());
        outputWriter.displayMessage(scoreMessage);
    }

    private boolean askToContinue() {
        outputWriter.displayPrompt("Do you want to play again? (yes/no):");
        String answer = inputReader.readRawInput().trim().toLowerCase();
        return answer.equals("yes") || answer.equals("y");
    }
}