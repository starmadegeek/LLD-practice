public class BowlerScoreCard {
    private final Player bowler;
    private int ballsBowled;
    private int runsConceded;
    private int foursConceded, sixesConceded;
    private int wicketsTaken, dotBallsBowled, maidens;
    private int wides, nobes, legs;

    public BowlerScoreCard(Player bowler) {
        this.bowler = bowler;
        ballsBowled = runsConceded = foursConceded = sixesConceded = 0;
        wicketsTaken = dotBallsBowled = wides = nobes = legs = maidens = 0;
    }

    public void playBall(Ball ball) {
        if (ball.getBallType() == BallType.FOUR) foursConceded++;
        else if (ball.getBallType() == BallType.SIX) sixesConceded++;
        else if (ball.getBallType() == BallType.DOT) dotBallsBowled++;
        else if (ball.getBallType() == BallType.NOBALL) {
            ballsBowled--;
            nobes++;
        } else if (ball.getBallType() == BallType.WIDE) {
            ballsBowled--;
            wides++;
        } else if (ball.getBallType() == BallType.WICKET) wicketsTaken++;
        else if (ball.getBallType() == BallType.LEGBYES) legs++;

        runsConceded += ball.getTotalRuns();
        ballsBowled++;
    }

    public double getEconomy() {
        return runsConceded * 6.0 / ballsBowled;
    }

    public Player getBowler() {
        return bowler;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public int getFoursConceded() {
        return foursConceded;
    }

    public int getSixesConceded() {
        return sixesConceded;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public String getOversBowled(){
        return (ballsBowled/6 + "." + ballsBowled%6);
    }
    public int getDotBallsBowled() {
        return dotBallsBowled;
    }

    public int getExtras() {
        return wides + nobes + legs;
    }

    public int getWides() {
        return wides;
    }

    public int getNobes() {
        return nobes;
    }

    public int getLegs() {
        return legs;
    }

    public void addMaiden() {
        maidens++;
    }

    public int getMaidens() {
        return maidens;
    }
}
