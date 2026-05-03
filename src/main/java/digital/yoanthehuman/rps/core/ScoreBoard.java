package digital.yoanthehuman.rps.core;

public class ScoreBoard {
    private int playerScore = 0;
    private int botScore = 0;

    public void recordPlayerWin() {
        playerScore++;
    }

    public void recordBotWin() {
        botScore++;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBotScore() {
        return botScore;
    }
}