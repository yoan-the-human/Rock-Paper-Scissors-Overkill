package digital.yoanthehuman.rps.core;

public class RoundEvaluator {
    
    public RoundResult evaluate(Move playerMove, Move botMove) {
        if (playerMove == botMove) {
            return RoundResult.TIE;
        }
        
        if (playerMove.beats(botMove)) {
            return RoundResult.PLAYER_WINS;
        }
        
        return RoundResult.BOT_WINS;
    }
}