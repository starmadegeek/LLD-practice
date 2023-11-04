import java.util.*;

public class Innings {
    private final Team battingTeam;
    private final Team bowlingTeam;
    private final int type;
    private final Queue<Player> battingQueue;
    private final HashMap<Player, BatsmanScoreCard> batsmenScoreCards;
    private final HashMap<Player, BowlerScoreCard> bowlerScoreCards;
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
    private Batsmen batsmen;

    public Innings(Team battingTeam, Team bowlingTeam, int totalOvers, int target) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.totalOvers = totalOvers;
        this.overs = new ArrayList<>(totalOvers);
        // for (int i = 0; i < totalOvers; i++) this.overs.add(new Over());
        if (target > -1) this.type = 1;
        else this.type = 0;

        this.battingQueue = new LinkedList<>();

        batsmenScoreCards = new HashMap<>();
        for (Player player : battingTeam.players()) {
            batsmenScoreCards.put(player, new BatsmanScoreCard(player));
            battingQueue.add(player);
        }

        bowlerScoreCards = new HashMap<>();

        totalRuns = 0;
        this.target = target;
        fours = 0;
        sixes = 0;
        wickets = 0;
        wides = 0;
        nobes = 0;
        legs = 0;
    }

    public Innings(Team battingTeam, Team bowlingTeam, int overs) {
        this(battingTeam, bowlingTeam, overs, -1);
    }

    public void start() {
        System.out.println("Team batting: " + battingTeam.teamName() + "\n");
        Scanner scanner = SharedScanner.getInstance();
        this.batsmen = new Batsmen(this.batsmenScoreCards.get(battingQueue.remove()), this.batsmenScoreCards.get(battingQueue.remove()));
        for (int i = 1; i <= this.totalOvers; i++) {
            System.out.println("Over: " + (i) + "\n");
            Over over = new Over();
            overs.add(over);
            for (int j = 1; j <= 6; j++) {
                String input = scanner.nextLine();
                String bowlerName = input.split("-")[0];
                String ballString = input.split("-")[1];
                Player bowler = bowlingTeam.getPlayerByName(bowlerName);
                Ball ball = over.setCurrentBall(ballString, batsmen.getStriker().getBatsman(), bowler);

                System.out.println(ball);

                batsmen.getStriker().playBall(ball);

                if (!bowlerScoreCards.containsKey(bowler)) bowlerScoreCards.put(bowler, new BowlerScoreCard(bowler));
                bowlerScoreCards.get(bowler).playBall(ball);

                // WICKET
                if (ball.getBallType() == BallType.WICKET) {
                    wickets++;
                    batsmen.setStriker(null);
                    if (battingQueue.isEmpty()) return;
                    batsmen.setStriker(batsmenScoreCards.get(battingQueue.remove()));
                }


                // RUNS - strike rotates
                if ((ball.getRuns() & 1) == 1) batsmen.rotateStrike();

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
            batsmen.rotateStrike();
            if (over.isMaiden())
                bowlerScoreCards.get(over.getBowlers().iterator().next()).addMaiden();

            printBattingScoreCard(i, overs.get(i - 1).getOverSummary());
        }
    }

    public int getFinalScore() {
        return totalRuns;
    }

    public int getWicketsRemaining() {
        return battingTeam.getNumberOfPlayers() - wickets - 1;
    }

    public void printBattingScoreCard(int over, String overSummary) {
        StringBuilder res = new StringBuilder();
        res.append("\nScorecard for Team ").append(battingTeam.teamName()).append(" after ").append(over).append(" overs:\n");
        res.append("Total score: ").append(getFinalScore()).append("-").append(getWickets()).append("\n");
        res.append("Overs played: ").append(over).append("\n");
        res.append(overSummary).append("\n");
        res.append("Batsman | Score | 4s | 6s | Balls | StrikeRate\n");
        for (BatsmanScoreCard batsman : Arrays.asList(batsmen.getStriker(), batsmen.getNonStriker())) {
            res.append(batsman.getBatsman().name());
            if (batsmen.getStriker().equals(batsman)) res.append("*");
            res.append(" | ");
            res.append(batsman.getScore()).append(" | ");
            res.append(batsman.getFours()).append(" | ");
            res.append(batsman.getSixes()).append(" | ");
            res.append(String.format("%.2f", batsman.getStrikeRate())).append(" | ");
            res.append(batsman.getBallFaced()).append(" \n");
        }
        System.out.println(res);
    }

    public String getTotalBattingScoreCard() {
        StringBuilder res = new StringBuilder();
        res.append("Batting Scorecard for Team ").append(battingTeam.teamName()).append(":\n");
        res.append("Total score: ").append(getFinalScore()).append("-").append(getWickets()).append("\n");
        res.append("Overs played: ").append(getOversPlayed()).append("\n");
        res.append("Extras: ").append(getExtras()).append("\n");
        res.append("Batsman | Score | Balls | 4s | 6s | StrikeRate\n");
        for (BatsmanScoreCard batsman : this.batsmenScoreCards.values()) {
            res.append(batsman.getBatsman().name());
            if (batsmen.isBatter(batsman)) res.append("*");
            res.append(" | ");
            res.append(batsman.getScore()).append(" | ");
            res.append(batsman.getBallFaced()).append(" | ");
            res.append(batsman.getFours()).append(" | ");
            res.append(batsman.getSixes()).append(" | ");
            res.append(String.format("%.2f", batsman.getStrikeRate())).append(" \n");
        }
        res.append("Yet to bat: ").append(battingQueue.toString()).append("\n");
        return res.toString();
    }

    public String getTotalBowlingScoreCard() {
        StringBuilder res = new StringBuilder();
        res.append("Bowling Scorecard for Team ").append(battingTeam.teamName()).append(":\n");
        res.append("Bowler | Overs | Maidens | Runs | Wickets | 4s | 6s | Extras | ER\n");
        for (BowlerScoreCard bowler : this.bowlerScoreCards.values()) {
            res.append(bowler.getBowler().name()).append(" | ");
            res.append(bowler.getOversBowled()).append(" | ");
            res.append(bowler.getMaidens()).append(" | ");
            res.append(bowler.getRunsConceded()).append(" | ");
            res.append(bowler.getWicketsTaken()).append(" | ");
            res.append(bowler.getFoursConceded()).append(" | ");
            res.append(bowler.getSixesConceded()).append(" | ");
            res.append(bowler.getExtras()).append(" | ");
            res.append(String.format("%.2f", bowler.getEconomy())).append(" \n");
        }
        return res.toString();
    }

    private String getOversPlayed() {
        int oversPlayed = overs.size() - 1;
        int currentBall = overs.get(oversPlayed).getCurrentBall();
        oversPlayed += currentBall / 6;
        currentBall = currentBall % 6;
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
