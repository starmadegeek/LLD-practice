public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, I'm TicTacToe Game!");
        Board board = new Board(3);
        Player[] players = {new Player("p1"), new Player("p2")};
        Game game = new Game(board, players);

        game.start();
    }
}
