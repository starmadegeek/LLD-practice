public class BatsmanScoreCard {
    private final Player batsman;
    private int score;
    private int fours, sixes;
    private int ballFaced;

    public BatsmanScoreCard(Player batsman) {
        this.batsman = batsman;
        this.score = 0;
        this.fours = 0;
        this.sixes = 0;
        this.ballFaced = 0;
    }

    public void playBall(Ball ball) {
        if (ball.getBallType() == BallType.FOUR) fours++;
        else if (ball.getBallType() == BallType.SIX) sixes++;
        else if (ball.getBallType() == BallType.NOBALL || ball.getBallType() == BallType.WIDE) ballFaced--;

        score += ball.getRuns();
        ballFaced++;
    }

    public Player getBatsman() {
        return batsman;
    }

    public int getScore() {
        return score;
    }

    public double getStrikeRate() {
        return score * 100.0 / ballFaced;
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
