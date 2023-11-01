public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Board board = new Board(100);
        Entity s1 = new Snake(88, 26);
        Entity s2 = new Snake(72, 64);
        Entity s3 = new Snake(35, 21);
        Entity s4 = new Snake(18, 2);
        Entity s5 = new Snake(51, 6);

        Entity l1 = new Ladder(6, 55);
        Entity l2 = new Ladder(21, 86);
        Entity l3 = new Ladder(15, 68);
        Entity l4 = new Ladder(81, 96);
        Entity l5 = new Ladder(66, 77);
        
        board.addEntity(s1.getStart(), s1);
        board.addEntity(s2.getStart(), s2);
        board.addEntity(s3.getStart(), s3);
        board.addEntity(s4.getStart(), s4);
        board.addEntity(s5.getStart(), s5);

        board.addEntity(l1.getStart(), l1);
        board.addEntity(l2.getStart(), l2);
        board.addEntity(l3.getStart(), l3);
        board.addEntity(l4.getStart(), l4);
        board.addEntity(l5.getStart(), l5);

        Game game = new Game(board);
        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        Player player3 = new Player("p3");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        System.out.println(game.toString());
        game.startGame();
        System.out.println(game.toString());
    }
}
