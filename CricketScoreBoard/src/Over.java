import java.util.HashSet;
import java.util.Set;

public class Over {
    private final Ball[] balls;
    private int currentBall;
    private final Set<Player> bowlers;

    public Over() {
        currentBall = 0;
        balls = new Ball[6];
        bowlers = new HashSet<>();
    }

    public Ball[] getBalls() {
        return balls;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public Ball setCurrentBall(String ballString, Player batsman, Player bowler) {
        if (currentBall > 5) throw new IllegalStateException("Current ball is invalid");
        Ball ball = parseBall(ballString, batsman, bowler);
        balls[currentBall] = ball;
        if (ball.getBallType() != BallType.NOBALL && ball.getBallType() != BallType.WIDE) currentBall++;
        bowlers.add(bowler);
        return ball;
    }

    private Ball parseBall(String ball, Player batsman, Player bowler) {
        BallType ballType;
        int runs = 0;
        int parsedRuns;
        try {
            parsedRuns = Integer.parseInt(String.valueOf(ball.charAt(ball.length() - 1)));
        } catch (NumberFormatException e) {
            parsedRuns = 0;
        }

        int extras = 0;

        if (ball.startsWith("Wd")) {
            ballType = BallType.WIDE;
            extras = parsedRuns + 1;
        } else if (ball.startsWith("W")) {
            ballType = BallType.WICKET;
            extras = parsedRuns;
        } else if (ball.startsWith("N")) {
            ballType = BallType.NOBALL;
            runs = parsedRuns;
            extras = 1;
        } else if (ball.startsWith("L")) {
            ballType = BallType.LEGBYES;
            extras = parsedRuns;
        } else if (ball.equals("6")) {
            ballType = BallType.SIX;
            runs = 6;
        } else if (ball.equals("4")) {
            ballType = BallType.FOUR;
            runs = 4;
        } else if (ball.equals("0")) {
            ballType = BallType.DOT;
        } else {
            ballType = BallType.RUN;
            runs = parsedRuns;
        }
        return new Ball(runs, extras, ballType, batsman, bowler, ball);
    }

    public int getRuns(){
        int runs = 0;
        for(Ball ball: balls){
            if(ball != null) runs += ball.getTotalRuns();
        }
        return runs;
    }

    public Set<Player> getBowlers() {
        return bowlers;
    }

    public String getOverSummary(){
        int runs = 0;
        StringBuilder res = new StringBuilder();
        res.append("[   ");
        for(Ball ball: balls){
            if(ball != null) {
                res.append(ball.getInput());
                runs += ball.getTotalRuns();
            }
            else res.append("-");
            res.append("    ");
        }
        res.append("]   ").append(runs).append(" runs");
        return res.toString();
    }

    public boolean isMaiden() {
        return currentBall == 6 && bowlers.size() == 1 && getRuns() == 0;
    }
}
