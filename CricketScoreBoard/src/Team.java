import java.util.List;

public class Team {
    List<Player> battingOrder;

    public Team(List<Player> battingOrder) {
        this.battingOrder = battingOrder;
    }

    public int getNumberOfPlayers(){
        return battingOrder.size();
    }

    public List<Player> getBattingOrder() {
        return battingOrder;
    }
}
