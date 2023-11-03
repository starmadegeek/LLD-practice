public class Board {
    int dimension;
    Player[][] board;
    
    public Board(int n){
        this.dimension = n;
        this.board = new Player[dimension][dimension];
    }

    public boolean setPosition(Position position, Player player){
        board[position.getX()][position.getY()] = player;
        return checkWin(position, player);
    }

    public Player getPosition(Position position) {
        return board[position.getX()][position.getY()];
    }

    public boolean checkWin(Position position, Player player){
        if(checkRow(position.getX(), player)) return true;
        if(checkColumn(position.getY(), player)) return true;
        if(position.isDiagonal() && checkDiagonals(player)) return true;
        return position.isCrossDiagonal(dimension) && checkCrossDiagonals(player);
    }

    private boolean checkRow(int x, Player checkPlayer){
        for(Player player: board[x]) 
            if(player == null || !player.equals(checkPlayer)) return false;
        return true;
    }

    private boolean checkColumn(int y, Player checkPlayer){
        for(int i = 0; i<board[0].length; i++ ) {
            Player player = board[i][y];
            if(player == null || !player.equals(checkPlayer)) return false;
        }
            
        return true;
    }

    private boolean checkDiagonals(Player checkPlayer){
        for(int i = 0; i<board[0].length; i++) {
            Player player = board[i][i];
            if(player == null || !player.equals(checkPlayer)) return false;
        }

        return true;
    }

    private boolean checkCrossDiagonals(Player checkPlayer){
        for(int i = board[0].length - 1; i>=0; i-- ) {
            Player player = board[i][board[0].length - 1 - i];
            if(player == null || !player.equals(checkPlayer)) return false;
        }   

        return true;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Player[] players : board) {
            for (Player value : players) {
                String player = value == null ? "X" : value.toString();
                res.append(player).append(" | ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
