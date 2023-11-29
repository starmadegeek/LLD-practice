package com.starmadegeek.Entities;

import java.util.Scanner;

/**
 * @author starmadegeek
 */
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
            boolean isBoardChanged = false;
            switch (move) {
                case 'w':
                    if (board.moveUp()) isBoardChanged = true;
                    break;
                case 's':
                    if (board.moveDown()) isBoardChanged = true;
                    break;
                case 'a':
                    if (board.moveLeft()) isBoardChanged = true;
                    break;
                case 'd':
                    if (board.moveRight()) isBoardChanged = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            if(board.isSolved()) gameStatus = GameStatus.WON;
            if(isBoardChanged) board.putRandomTile();
            if(board.checkIfLost()) gameStatus = GameStatus.LOST;
        }

        if(gameStatus == GameStatus.WON) System.out.println("Congratulations! You Won.");
        else if (gameStatus == GameStatus.LOST) System.out.println("Sorry, You lost!");
        else System.out.println("Something went wrong.");
    }
}
