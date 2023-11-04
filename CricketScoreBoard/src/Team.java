import java.util.List;

public record Team(String teamName, List<Player> players) {

    public int getNumberOfPlayers() {
        return players.size();
    }
    public Player getPlayerByName(String name){
        return players.stream().filter(player -> player.name().equals(name)).findFirst().orElse(null);
    }
}
