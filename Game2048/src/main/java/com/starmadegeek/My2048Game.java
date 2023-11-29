package com.starmadegeek;

import com.starmadegeek.Entities.Board;

import java.util.Scanner;

public class My2048Game {
    private final Board board;
    private GameStatus gameStatus;

    public My2048Game(Board board) {
        this.board = board;
        this.gameStatus = GameStatus.NOT_STARTED;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        gameStatus = GameStatus.IN_PROGRESS;

        while (gameStatus == GameStatus.IN_PROGRESS) {
            System.out.println(board);
            System.out.println("Select move: Up(w) | Down(s) | Left(a) | Right(d)");
            char move = scanner.next().charAt(0);
            switch (move) {
                case 'w':
                    if (board.moveUp()) gameStatus = GameStatus.WON;
                    break;
                case 's':
                    if (board.moveDown()) gameStatus = GameStatus.WON;
                    break;
                case 'a':
                    if (board.moveLeft()) gameStatus = GameStatus.WON;
                    break;
                case 'd':
                    if (board.moveRight()) gameStatus = GameStatus.WON;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            if(!board.putRandomTile()) {
                gameStatus = GameStatus.LOST;
            }
        }

        if(gameStatus == GameStatus.WON) System.out.println("Congratulations! You Won.");
        else if (gameStatus == GameStatus.LOST) System.out.println("Sorry, You lost!");
        else System.out.println("Something went wrong.");
    }
}
