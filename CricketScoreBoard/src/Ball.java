public class Ball{
    private final int runs, extras;
    private final BallType ballType;
    private final Player batsman, bowler;
    private final String input;

    public Ball(int runs, int extras, BallType ballType, Player batsman, Player bowler, String input) {
        this.runs = runs;
        this.extras = extras;
        this.ballType = ballType;
        this.batsman = batsman;
        this.bowler = bowler;
        this.input = input;
    }

    public Ball(int runs, BallType ballType, Player batsman, Player bowler, String input) {
        this.runs = runs;
        this.ballType = ballType;
        this.batsman = batsman;
        this.bowler = bowler;
        this.input = input;
        this.extras = 0;
    }

    @Override
    public String toString() {
        return "runs=" + runs +
                ", ballType=" + ballType +
                ", batsman= " + batsman +
                ", bowler= " + bowler +
                ", extras= "+ extras;
    }

    public BallType getBallType() {
        return this.ballType;
    }

    public int getRuns(){
        return this.runs;
    }

    public int getExtras(){
        return this.extras;
    }

    public int getTotalRuns(){
        return runs+extras;
    }

    public String getInput() {
        return input;
    }
}
