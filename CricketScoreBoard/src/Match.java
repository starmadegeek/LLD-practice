public class Match {
    private final Team teamA, teamB;
    private final int overs;
    private String result;
    private GameStatus gameStatus;
    private final Team tossWinner;

    public Match(Team teamA, Team teamB, int overs, Team tossWinner) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.overs = overs;
        this.tossWinner = tossWinner;
        this.gameStatus = GameStatus.NOT_STARTED;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public String getResult() {
        return result;
    }

    public int getOvers() {
        return overs;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Team getTossWinner() {
        return tossWinner;
    }

    public void start() {
        this.gameStatus = GameStatus.FIRST_INNINGS_IN_PROGRESS;
        Innings teamAInnings = new Innings(teamA, teamB, overs);
        teamAInnings.start();
        System.out.println(teamAInnings.getTotalBattingScoreCard());
        System.out.println(teamAInnings.getTotalBowlingScoreCard());

        this.gameStatus = GameStatus.SECOND_INNINGS_IN_PROGRESS;
        System.out.println("Team " + teamB.teamName() + " needs " + (teamAInnings.getFinalScore()+1) + " runs to win.");
        Innings teamBInnings = new Innings(teamB, teamA, overs, teamAInnings.getFinalScore());
        teamBInnings.start();

        System.out.println(teamBInnings.getTotalBattingScoreCard());
        System.out.println(teamBInnings.getTotalBowlingScoreCard());

        this.gameStatus = GameStatus.FINISHED;

        if(teamBInnings.getFinalScore() > teamAInnings.getFinalScore()){
            result = "Result: Team " + teamB.teamName() + " won the match by " + teamBInnings.getWicketsRemaining() + " wickets";
        } else if (teamBInnings.getFinalScore() == teamAInnings.getFinalScore()) {
            result = "Result: Game is drawn due to tie";
        }
        else result = "Result: Team " + teamA.teamName() + " won the match by " + (teamAInnings.getFinalScore()  - teamBInnings.getFinalScore()) + " runs";
    }
}
