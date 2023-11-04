import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Over {
    private final ArrayList<Ball> balls;
    private int currentBall;
    private final Set<Player> bowlers;

    public Over() {
        currentBall = 0;
        balls = new ArrayList<>();
        bowlers = new HashSet<>();
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public Ball setCurrentBall(String ballString, Player batsman, Player bowler) {
        if (currentBall > 5) throw new IllegalStateException("Current ball is invalid");
        Ball ball = parseBall(ballString, batsman, bowler);
        balls.add(ball);
        if (!ball.getBallType().special) currentBall++;
        bowlers.add(bowler);
        return ball;
    }

    private Ball parseBall(String ball, Player batsman, Player bowler) throws NumberFormatException {
        String[] ballList = ball.split("\\|");
        int runs = Integer.parseInt(ballList[1]), extras = Integer.parseInt(ballList[2]);
        BallType ballType = BallType.getByCode(ballList[0]);

        if(BallType.isSpecial(ballList[0])) extras++;

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
