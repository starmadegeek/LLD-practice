import java.util.List;

public record Team(String teamName, List<Player> battingOrder) {

    public int getNumberOfPlayers() {
        return battingOrder.size();
    }
}
