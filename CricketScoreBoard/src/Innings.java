import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Innings {
    final Team team;
    HashMap<Player, PlayerScoreCard> playerPlayerScoreCards;
    int totalRuns, fours, sixes, wickets, wides, nobes, legs;
    List<Over> overs;

    public Innings(Team team, int overs) {
        this.team = team;
        this.overs = new ArrayList<>(overs);
        playerPlayerScoreCards = new HashMap<>();
        for (Player player : team.getBattingOrder()) {
            playerPlayerScoreCards.put(player, new PlayerScoreCard(player));
        }
        totalRuns = 0;
        fours = 0;
        sixes = 0;
        wickets = 0;
        wides = 0;
        nobes = 0;
        legs = 0;
    }

    public void start() {
    }

    public int getFinalScore() {
        return totalRuns;
    }

    public int getWicketsRemaining() {
        return team.getNumberOfPlayers() - wickets - 1;
    }
}
