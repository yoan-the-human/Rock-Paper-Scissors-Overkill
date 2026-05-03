package digital.yoanthehuman.rps;

import digital.yoanthehuman.rps.core.BotMoveGenerator;
import digital.yoanthehuman.rps.core.RoundEvaluator;
import digital.yoanthehuman.rps.core.ScoreBoard;
import digital.yoanthehuman.rps.game.GameController;
import digital.yoanthehuman.rps.io.InputReader;
import digital.yoanthehuman.rps.io.MoveParser;
import digital.yoanthehuman.rps.io.OutputWriter;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        OutputWriter outputWriter = new OutputWriter();
        MoveParser moveParser = new MoveParser();
        BotMoveGenerator botMoveGenerator = new BotMoveGenerator();
        RoundEvaluator roundEvaluator = new RoundEvaluator();
        ScoreBoard scoreBoard = new ScoreBoard();

        GameController gameController = new GameController(
                inputReader, outputWriter, moveParser,
                botMoveGenerator, roundEvaluator, scoreBoard
        );

        gameController.start();
    }
}