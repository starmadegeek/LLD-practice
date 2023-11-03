public class Match {
    Team teamA, teamB;
    GameStatus gameStatus;
    Innings teamAInnings, teamBInnings;
    int overs;
    String result;
    public Match(Team teamA, Team teamB, int overs) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.overs = overs;
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

    public void start(){
        this.gameStatus = GameStatus.IN_PROGRESS;
        teamAInnings = new Innings(teamA, overs);
        teamAInnings.start();
        teamBInnings = new Innings(teamB, overs);
        teamBInnings.start();
        if(teamBInnings.getFinalScore() > teamAInnings.getFinalScore()){
            result = "Result: Team 2 won the match by " + teamBInnings.getWicketsRemaining() + " wickets";
        } else if (teamBInnings.getFinalScore() == teamAInnings.getFinalScore()) {
            result = "Result: Game is drawn due to tie";
        }
        else result = "Result: Team 1 won the match by " + (teamAInnings.getFinalScore()  - teamBInnings.getFinalScore()) + " runs";
    }
}
