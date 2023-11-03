import java.util.Scanner;

public class Game {
    Board board;
    Player[] players;
    Status status;

    public Game(Board board, Player players[]) {
        // System.out.println(board.toString() + players[0].getName() + players[1].getName());

        this.board = board;
        this.players = players;
        this.status = Status.NOT_STARTED;
    }

    public void start(){
        this.status = Status.IN_PROGRESS;
        int current = 0;
        Scanner scanner = new Scanner(System.in);
        while(this.status == Status.IN_PROGRESS) {
            System.out.println(board.toString());
            System.out.println("Player" + (current + 1) + " position: ");

            Position position = new Position(scanner.nextInt(), scanner.nextInt());
            if(board.getPosition(position) != null) 
                System.out.println("Invalid choice! Choose again");
            else {
                if(board.setPosition(position, players[current])) {
                    System.out.println(board.toString());
                    System.out.println("\nPlayer" + (current+1) + " wins!");
                    this.status = Status.FINISHED;
                }
                current = (current + 1) % 2;
            }
        }
        scanner.close();
    }    
}
