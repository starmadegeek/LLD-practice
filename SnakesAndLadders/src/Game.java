import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Game {
    Status status;
    Board board;
    Queue<Player> players = new LinkedList<>();
    List<Player> winners = new ArrayList<>();

    public Game(Board board){
        this.board = board;
        this.status = Status.NOT_STARTED;
    }

    public void addPlayer(Player player){
        if(this.status != Status.NOT_STARTED) throw new IllegalStateException();
        if(player.getPosition() != 0) throw new IllegalArgumentException();

        this.players.add(player);
    }

    public void startGame(){
        this.status = Status.IN_PROGRESS;
        int totalMoves = 0;
        while (!players.isEmpty()) {
            Player player = players.poll();
            makeMove(player);
            totalMoves++;
            if(player.getPosition() >= board.getDimension()){
                winners.add(player);
            }
            else players.add(player);
        }

        this.status = Status.FINISHED;
        System.out.println("Game ended! Moves played: " + totalMoves);
    }

    private int rollDice(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void makeMove(Player player){
        int newPosition = player.getPosition() + rollDice();
        if(board.entities.containsKey(newPosition)){
            newPosition = board.entities.get(newPosition).getEnd();
        }
        player.setPosition(newPosition);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(board.toString());
        res.append("\n");
        for(Player player: players) res.append(" Player: ").append(player.getName()).append("\n");
        for(Player player: winners) res.append(" Winner: ").append(player.getName()).append("\n");
        return res.toString();
    }

}
