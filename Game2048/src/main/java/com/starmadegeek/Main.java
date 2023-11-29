package com.starmadegeek;

import com.starmadegeek.Entities.Board;
import com.starmadegeek.Entities.My2048Game;
import com.starmadegeek.Entities.Tile;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, I'm 2048 game!");

        Tile[][] tiles = {
            {new Tile(2), null, null, null},
            {null, null, null, new Tile(4)},
            {null, null, null, new Tile(2)},
            {null, null, null, null}
        };

        Board board = new Board(tiles);
        My2048Game game = new My2048Game(board);
        game.start();
    }
}