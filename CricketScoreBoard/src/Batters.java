public class Batters {
    private PlayerScoreCard striker;
    private PlayerScoreCard nonStriker;

    public Batters(PlayerScoreCard striker, PlayerScoreCard nonStriker) {
        this.striker = striker;
        this.nonStriker = nonStriker;
    }

    public void rotateStike(){
        PlayerScoreCard temp = striker;
        striker = nonStriker;
        nonStriker = temp;
    }

    public void setStriker(PlayerScoreCard striker) {
        this.striker = striker;
    }

    public PlayerScoreCard getStriker() {
        return striker;
    }

    public PlayerScoreCard getNonStriker() {
        return nonStriker;
    }

    public boolean isBatter(PlayerScoreCard player) {
        return player.equals(striker) || player.equals(nonStriker);
    }
}
