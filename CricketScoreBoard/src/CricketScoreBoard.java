import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CricketScoreBoard {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello, I'm Cricket ScoreBoard!");
        System.out.println("Interactive or file based (1|2)? ");
        Scanner initScanner = new Scanner(System.in);

        if(initScanner.nextInt() == 2) {
            System.out.println("Enter sample name: ");
            initScanner.nextLine();
            SharedScanner.setStreamInstance("CricketScoreBoard/src/" + initScanner.nextLine() + ".txt");
        }
        else SharedScanner.setInteractiveInstance();

        System.out.println("Enter number of players in each team: ");

        Scanner scanner = SharedScanner.getInstance();
        int numPlayers = scanner.nextInt();

        List<Player> team1Players = new ArrayList<>(numPlayers);
        List<Player> team2Players = new ArrayList<>(numPlayers);

        System.out.println("Enter number of overs to be bowled: ");
        int overs = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter team 1 name: ");
        String team1Name = scanner.nextLine();

        System.out.println("Enter team " + team1Name + " batting order");
        for (int i = 0; i < numPlayers; i++) {
            team1Players.add(new Player(scanner.nextLine()));
        }
        System.out.println("Team " + team1Name + " : " + team1Players);

        System.out.println("Enter team 2 name: ");
        String team2Name = scanner.nextLine();

        System.out.println("\nEnter team " + team2Name + " batting order");
        for (int i = 0; i < numPlayers; i++) {
            team2Players.add(new Player(scanner.nextLine()));
        }
        System.out.println("Team " + team2Name + " : " + team2Players);

        // scanner.close();

        Team team1 = new Team(team1Name, team1Players);
        Team team2 = new Team(team2Name, team2Players);

        System.out.println("Toss won by: " + team1.teamName() + " (1) / (2) " + team2.teamName());
        int tossWin = scanner.nextInt();
        Team tossWinner = tossWin == 1 ? team1 : team2;

        System.out.println("First Batting: " + team1.teamName() + " (1) / (2) " + team2.teamName());
        int teamBattingFirst = scanner.nextInt();
        Team firstBattingTeam = teamBattingFirst == 1 ? team1 : team2;
        Team secondBattingTeam = teamBattingFirst == 1 ? team2 : team1;

        scanner.nextLine();
        Match match = new Match(firstBattingTeam, secondBattingTeam, overs, tossWinner);
        match.start();

        if(match.getGameStatus().equals(GameStatus.FINISHED)) System.out.println(match.getResult());
    }
}