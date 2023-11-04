import java.io.FileNotFoundException;
import java.util.*;

public class Innings {
    private final Team team;
    private final int type;
    private final Queue<Player> battingQueue;
    private final HashMap<Player, PlayerScoreCard> playerPlayerScoreCards;
    private int totalRuns;
    private final int totalOvers;
    private final int target;
    private int fours;
    private int sixes;
    private int wickets;
    private int wides;
    private int nobes;
    private int legs;
    private final ArrayList<Over> overs;
    private Batters batters;

    public Innings(Team team, int totalOvers, int target) {
        this.team = team;
        this.totalOvers = totalOvers;
        this.overs = new ArrayList<>(totalOvers);
        // for (int i = 0; i < totalOvers; i++) this.overs.add(new Over());
        if (target > -1) this.type = 1;
        else this.type = 0;
        this.battingQueue = new LinkedList<>();
        playerPlayerScoreCards = new HashMap<>();
        for (Player player : team.battingOrder()) {
            playerPlayerScoreCards.put(player, new PlayerScoreCard(player));
            battingQueue.add(player);
        }
        totalRuns = 0;
        this.target = target;
        fours = 0;
        sixes = 0;
        wickets = 0;
        wides = 0;
        nobes = 0;
        legs = 0;
    }

    public Innings(Team team, int overs) {
        this(team, overs, -1);
    }

    public void start() throws FileNotFoundException {
        System.out.println("Team batting: " + team.teamName() + "\n");
        Scanner scanner = SharedScanner.getInstance();
        this.batters = new Batters(this.playerPlayerScoreCards.get(battingQueue.remove()), this.playerPlayerScoreCards.get(battingQueue.remove()));
        for (int i = 1; i <= this.totalOvers; i++) {
            System.out.println("Over: " + (i) + "\n");
            overs.add(new Over());
            for (int j = 1; j <= 6; j++) {
                String ballString = scanner.nextLine();
                Ball ball = overs.get(i-1).setCurrentBall(ballString, batters.getStriker().getPlayer(), null);
                System.out.println(ball);

                batters.getStriker().playBall(ball);

                // WICKET
                if (ball.getBallType() == BallType.WICKET) {
                    wickets++;
                    batters.setStriker(null);
                    if (battingQueue.isEmpty()) return;
                    batters.setStriker(playerPlayerScoreCards.get(battingQueue.remove()));
                }


                // RUNS - strike rotates
                if ((ball.getRuns() & 1) == 1) batters.rotateStike();

                // Wide or Noball
                if (ball.getBallType() == BallType.NOBALL) {
                    nobes++;
                    j--;
                } else if (ball.getBallType() == BallType.WIDE) {
                    wides++;
                    j--;
                } else if (ball.getBallType() == BallType.LEGBYES) {
                    legs++;
                }

                // Boundary
                if (ball.getBallType() == BallType.FOUR) fours++;
                if (ball.getBallType() == BallType.SIX) sixes++;

                totalRuns += ball.getTotalRuns();
                if (type == 1 && totalRuns > target) return;
            }
            batters.rotateStike();

            printScoreCard(i);
        }
    }

    public int getFinalScore() {
        return totalRuns;
    }

    public int getWicketsRemaining() {
        return team.getNumberOfPlayers() - wickets - 1;
    }

    public void printScoreCard(int over) {
        StringBuilder res = new StringBuilder();
        res.append("Scorecard for Team ").append(team.teamName()).append(" after ").append(over).append(" overs:\n");
        res.append("Total score: ").append(getFinalScore()).append("-").append(getWickets()).append("\n");
        res.append("Overs played: ").append(over).append("\n");
        res.append("Player | Name | Score | 4s | 6s | Balls\n");
        for (PlayerScoreCard player : Arrays.asList(batters.getStriker(), batters.getNonStriker())) {
            res.append(player.getPlayer().name());
            if(batters.getStriker().equals(player)) res.append("*");
            res.append(" | ");
            res.append(player.getScore()).append(" | ");
            res.append(player.getFours()).append(" | ");
            res.append(player.getSixes()).append(" | ");
            res.append(player.getBallFaced()).append(" \n");
        }
        System.out.println(res);
    }

    public String getTotalScoreCard() {
        StringBuilder res = new StringBuilder();
        res.append("Scorecard for Team ").append(team.teamName()).append(":\n");
        res.append("Total score: ").append(getFinalScore()).append("-").append(getWickets()).append("\n");
        res.append("Overs played: ").append(getOversPlayed()).append("\n");
        res.append("Player | Name | Score | 4s | 6s | Balls\n");
        for (PlayerScoreCard player : this.playerPlayerScoreCards.values()) {
            res.append(player.getPlayer().name());
            if (batters.isBatter(player)) res.append("*");
            res.append(" | ");
            res.append(player.getScore()).append(" | ");
            res.append(player.getFours()).append(" | ");
            res.append(player.getSixes()).append(" | ");
            res.append(player.getBallFaced()).append(" \n");
        }
        return res.toString();
    }

    private String getOversPlayed() {
        int oversPlayed = overs.size() - 1;
        int currentBall = overs.get(oversPlayed).getCurrentBall();
        oversPlayed += currentBall/6;
        currentBall = currentBall%6;
        return oversPlayed + "." + currentBall;
    }

    public int getExtras() {
        return wides + nobes + legs;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getWickets() {
        return wickets;
    }
}
