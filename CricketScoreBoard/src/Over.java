public class Over {
    Ball[] balls = new Ball[6];
    int currentBall;

    public Over() {
        currentBall = 0;
    }

    public Ball[] getBalls() {
        return balls;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public void setCurrentBall(Ball ball) {
        if(currentBall>5) throw new IllegalStateException("Current ball is invalid");
        balls[currentBall++] = ball;
    }

}
