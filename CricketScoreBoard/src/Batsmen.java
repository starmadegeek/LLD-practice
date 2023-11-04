public class Batsmen {
    private BatsmanScoreCard striker;
    private BatsmanScoreCard nonStriker;

    public Batsmen(BatsmanScoreCard striker, BatsmanScoreCard nonStriker) {
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    public void rotateStrike(){
        BatsmanScoreCard temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    public void setStriker(BatsmanScoreCard striker) {
        this.striker = striker;
    }

    public BatsmanScoreCard getStriker() {
        return striker;
    }

    public BatsmanScoreCard getNonStriker() {
        return nonStriker;
    }

    public boolean isBatter(BatsmanScoreCard player) {
        return player.equals(striker) || player.equals(nonStriker);
    }
}
