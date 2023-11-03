import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Cricket ScoreBoard!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of players in each team: ");
        int numPlayers = scanner.nextInt();

        List<Player> team1 = new ArrayList<>(numPlayers);
        List<Player> team2 = new ArrayList<>(numPlayers);

        System.out.println("Enter number of overs to be bowled: ");
        int overs = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter team 1 batting order");
        for (int i = 0; i < numPlayers; i++) {
            team1.add(new Player(scanner.nextLine() + "t1"));
        }

        System.out.println("Team 1: " + team1);

        System.out.println("\nEnter team 2 batting order");
        for (int i = 0; i < numPlayers; i++) {
            team2.add(new Player(scanner.nextLine() + "t2"));
        }
        System.out.println("Team 2: " + team2);

        Team teamA = new Team(team1);
        Team teamB = new Team(team2);
        Match match = new Match(teamA, teamB, overs);
        match.start();
        System.out.println(match.getResult());
    }
}