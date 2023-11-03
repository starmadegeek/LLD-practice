public class PlayerScoreCard {
    final Player player;
    int score;
    int fours, sixes;
    int ballFaced;

    public PlayerScoreCard(Player player) {
        this.player = player;
        this.score = 0;
        this.fours = 0;
        this.sixes = 0;
        this.ballFaced = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getBallFaced() {
        return ballFaced;
    }

    public void setBallFaced(int ballFaced) {
        this.ballFaced = ballFaced;
    }
}
