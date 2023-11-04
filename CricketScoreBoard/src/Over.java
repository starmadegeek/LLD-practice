public class Over {
    private final Ball[] balls = new Ball[6];
    private int currentBall;

    public Over() {
        currentBall = 0;
    }

    public Ball[] getBalls() {
        return balls;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public Ball setCurrentBall(String ballString, Player batsman, Player bowler) {
        if(currentBall>5) throw new IllegalStateException("Current ball is invalid");
        Ball ball = parseBall(ballString, batsman, bowler);
        balls[currentBall] = ball;
        if(ball.getBallType() != BallType.NOBALL && ball.getBallType() != BallType.WIDE) currentBall++;
        return ball;
    }

    private Ball parseBall(String ball, Player batsman, Player bowler){
        if(ball.equals("W"))  return new Ball(0, BallType.WICKET, batsman, bowler);
        if(ball.equals("Wd")) return new Ball(0,1, BallType.WIDE, batsman, bowler);
        if(ball.equals("N")) return new Ball(0,1, BallType.NOBALL, batsman, bowler);
        int run = 0;
        if(ball.startsWith("L")) {
            run = Integer.parseInt(ball.substring(1));
            return new Ball(0, run, BallType.LEGBYES, batsman, bowler);
        }
        run = Integer.parseInt(ball);
        if(run == 6) return new Ball(run, BallType.SIX, batsman, bowler);
        if(run == 4) return new Ball(run, BallType.FOUR, batsman, bowler);
        return new Ball(run, BallType.RUN, batsman, bowler);
    }
}
