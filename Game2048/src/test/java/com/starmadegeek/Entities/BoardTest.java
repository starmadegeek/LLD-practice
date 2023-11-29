package com.starmadegeek.Entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class BoardTest {

    

    @Test
    void simplifyList() {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));

        Board board = new Board();
        List<Tile> result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(4), new Tile(4), new Tile(4), new Tile(4) ), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(4), new Tile(4), new Tile(4) ), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(4));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(4), new Tile(4), new Tile(2), new Tile(4) ), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(8));
        tiles.add(new Tile(4));
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(8), new Tile(4), new Tile(4) ), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(2));
        tiles.add(new Tile(2));
        tiles.add(new Tile(4));
        tiles.add(new Tile(8));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(4), new Tile(4), new Tile(8) ), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(2));
        tiles.add(new Tile(4));
        tiles.add(new Tile(4));
        tiles.add(new Tile(8));
        tiles.add(new Tile(8));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(2), new Tile(8), new Tile(16)), result);

        tiles = new ArrayList<>();
        tiles.add(new Tile(8));
        tiles.add(new Tile(8));
        tiles.add(new Tile(4));
        tiles.add(new Tile(4));
        tiles.add(new Tile(2));

        result = board.simplifyList(tiles);
        System.out.println(result);
        assertEquals(Arrays.asList(new Tile(16), new Tile(8), new Tile(2)), result);
    }

    @Test
    void moveUp() {
        Board board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(1, 0, new Tile(2));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());

        board.moveUp();

        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(8, board.getTile(2, 0).getValue());
        assertNull(board.getTile(3, 0));

        board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(8));
        board.setTile(1, 0, new Tile(4));
        board.setTile(2, 0, new Tile(2));
        board.setTile(3, 0, new Tile(2));

        // Assert the expected state before the move
        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(2, board.getTile(2, 0).getValue());
        assertEquals(2, board.getTile(3, 0).getValue());

        board.moveUp();

        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertNull(board.getTile(3, 0));

        // ------------------------- //
        board = new Board(5);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(1, 0, new Tile(4));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(8));
        board.setTile(4, 0, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());
        assertEquals(8, board.getTile(4, 0).getValue());

        board.moveUp();

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(8, board.getTile(1, 0).getValue());
        assertEquals(16, board.getTile(2, 0).getValue());
        assertNull(board.getTile(3, 0));
        assertNull(board.getTile(4, 0));

        // ------------------------- //
        board = new Board(5);

        // Set up the initial state
        board.setTile(0, 0, new Tile(8));
        board.setTile(1, 0, new Tile(8));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(4));
        board.setTile(4, 0, new Tile(2));

        // Assert the expected state before the move
        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(8, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(4, board.getTile(3, 0).getValue());
        assertEquals(2, board.getTile(4, 0).getValue());

        board.moveUp();

        assertEquals(16, board.getTile(0, 0).getValue());
        assertEquals(8, board.getTile(1, 0).getValue());
        assertEquals(2, board.getTile(2, 0).getValue());
        assertNull(board.getTile(3, 0));
        assertNull(board.getTile(4, 0));
    }

    @Test
    void moveDown() {
        Board board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(1, 0, new Tile(2));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());

        board.moveDown();

        assertNull(board.getTile(0, 0));
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());

        board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(8));
        board.setTile(1, 0, new Tile(4));
        board.setTile(2, 0, new Tile(2));
        board.setTile(3, 0, new Tile(2));

        // Assert the expected state before the move
        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(2, board.getTile(2, 0).getValue());
        assertEquals(2, board.getTile(3, 0).getValue());

        board.moveDown();

        assertNull(board.getTile(0, 0));
        assertEquals(8, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(4, board.getTile(3, 0).getValue());

        // ------------------------- //
        board = new Board(5);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(1, 0, new Tile(4));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(8));
        board.setTile(4, 0, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());
        assertEquals(8, board.getTile(4, 0).getValue());

        board.moveDown();

        assertNull(board.getTile(0, 0));
        assertNull(board.getTile(1, 0));
        assertEquals(2, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());
        assertEquals(16, board.getTile(4, 0).getValue());

        // ------------------------- //
        board = new Board(5);

        // Set up the initial state
        board.setTile(0, 0, new Tile(8));
        board.setTile(1, 0, new Tile(8));
        board.setTile(2, 0, new Tile(4));
        board.setTile(3, 0, new Tile(4));
        board.setTile(4, 0, new Tile(2));

        // Assert the expected state before the move
        assertEquals(8, board.getTile(0, 0).getValue());
        assertEquals(8, board.getTile(1, 0).getValue());
        assertEquals(4, board.getTile(2, 0).getValue());
        assertEquals(4, board.getTile(3, 0).getValue());
        assertEquals(2, board.getTile(4, 0).getValue());

        board.moveDown();

        assertNull(board.getTile(0, 0));
        assertNull(board.getTile(1, 0));
        assertEquals(16, board.getTile(2, 0).getValue());
        assertEquals(8, board.getTile(3, 0).getValue());
        assertEquals(2, board.getTile(4, 0).getValue());
    }
    @Test
    void moveLeft() {
        Board board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(2));
        board.setTile(0, 2, new Tile(4));
        board.setTile(0, 3, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(4, board.getTile(0, 2).getValue());
        assertEquals(8, board.getTile(0, 3).getValue());

        board.moveLeft();

        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(8, board.getTile(0, 2).getValue());
        assertNull(board.getTile(0, 3));

        // Additional test
        board = new Board(4);
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(4));
        board.setTile(0, 2, new Tile(2));
        board.setTile(0, 3, new Tile(2));

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(2, board.getTile(0, 2).getValue());
        assertEquals(2, board.getTile(0, 3).getValue());

        board.moveLeft();

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(4, board.getTile(0, 2).getValue());
        assertNull(board.getTile(0, 3));
    }

    @Test
    void moveRight() {
        Board board = new Board(4);

        // Set up the initial state
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(2));
        board.setTile(0, 2, new Tile(4));
        board.setTile(0, 3, new Tile(8));

        // Assert the expected state before the move
        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(4, board.getTile(0, 2).getValue());
        assertEquals(8, board.getTile(0, 3).getValue());

        board.moveRight();

        assertNull(board.getTile(0, 0));
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(4, board.getTile(0, 2).getValue());
        assertEquals(8, board.getTile(0, 3).getValue());

        // Additional test
        board = new Board(4);
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(4));
        board.setTile(0, 2, new Tile(2));
        board.setTile(0, 3, new Tile(2));

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
        assertEquals(2, board.getTile(0, 2).getValue());
        assertEquals(2, board.getTile(0, 3).getValue());

        board.moveRight();

        assertNull(board.getTile(0, 0));
        assertEquals(2, board.getTile(0, 1).getValue());
        assertEquals(4, board.getTile(0, 2).getValue());
        assertEquals(4, board.getTile(0, 3).getValue());
    }
}