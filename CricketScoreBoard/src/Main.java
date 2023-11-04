import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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

        List<Player> team1 = new ArrayList<>(numPlayers);
        List<Player> team2 = new ArrayList<>(numPlayers);

        System.out.println("Enter number of overs to be bowled: ");
        int overs = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter team 1 name: ");
        String team1Name = scanner.nextLine();

        System.out.println("Enter team " + team1Name + " batting order");
        for (int i = 0; i < numPlayers; i++) {
            team1.add(new Player(scanner.nextLine()));
        }
        System.out.println("Team " + team1Name + " : " + team1);


        System.out.println("Enter team 2 name: ");
        String team2Name = scanner.nextLine();

        System.out.println("\nEnter team " + team2Name + " batting order");
        for (int i = 0; i < numPlayers; i++) {
            team2.add(new Player(scanner.nextLine()));
        }
        System.out.println("Team " + team2Name + " : " + team2);

        // scanner.close();

        Team teamA = new Team(team1Name, team1);
        Team teamB = new Team(team2Name, team2);
        Match match = new Match(teamA, teamB, overs);
        match.start();
        if(match.getGameStatus().equals(GameStatus.FINISHED)) System.out.println(match.getResult());
    }
}