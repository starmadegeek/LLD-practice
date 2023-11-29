package com.starmadegeek.Entities;

import java.util.*;

public class Board {
    private final Tile[][] board;
    private final int dimension;
    private boolean isSolved = false;

    public Board(int n) {
        this.dimension = n;
        this.board = new Tile[dimension][dimension];
    }

    public Board() {
        this.dimension = 0;
        this.board = new Tile[dimension][dimension];
    }

    public Board(Tile[][] board) {
        this.dimension = board.length;
        this.board = board;
    }

    public List<Tile> simplifyList(List<Tile> tiles) {
        List<Tile> tilesSimplified = new ArrayList<>();
        Tile prev = null;
        for(Tile tile: tiles){
            if(tile.equals(prev)){
                prev.doubleValue();
                if(prev.getValue() == 2048) this.isSolved = true;
                tilesSimplified.add(prev);
                prev = null;
            }
            else {
                if(prev != null) tilesSimplified.add(prev);
                prev = tile;
            }
        }
        if(prev != null) tilesSimplified.add(prev);
        return tilesSimplified;
    }

    public boolean moveUp() {
        for (int column = 0; column < dimension; column++) {
            List<Tile> columnTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[i][column] != null)
                    columnTiles.add(board[i][column]);
            }
            columnTiles = simplifyList(columnTiles);
            for (int i = 0; i < dimension; i++) {
                board[i][column] = i < columnTiles.size() ? columnTiles.get(i) : null;
            }
        }
        return isSolved;
    }

    public boolean moveDown() {
        for (int column = 0; column < dimension; column++) {
            List<Tile> columnTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[dimension - i - 1][column] != null)
                    columnTiles.add(board[dimension - i - 1][column]);
            }
            columnTiles = simplifyList(columnTiles);
            for (int i = 0; i < dimension; i++) {
                board[dimension - i - 1][column] = i < columnTiles.size() ? columnTiles.get(i) : null;
            }
        }
        return isSolved;
    }

    public boolean moveLeft() {
        for (int row = 0; row < dimension; row++) {
            List<Tile> rowTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[row][i] != null)
                    rowTiles.add(board[row][i]);
            }
            rowTiles = simplifyList(rowTiles);
            for (int i = 0; i < dimension; i++) {
                board[row][i] = i < rowTiles.size() ? rowTiles.get(i) : null;
            }
        }
        return isSolved;
    }

    public boolean moveRight() {
        for (int row = 0; row < dimension; row++) {
            List<Tile> rowTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[row][dimension-i-1] != null)
                    rowTiles.add(board[row][dimension-i-1]);
            }
            rowTiles = simplifyList(rowTiles);
            for (int i = 0; i < dimension; i++) {
                board[row][dimension-i-1] = i < rowTiles.size() ? rowTiles.get(i) : null;
            }
        }
        return isSolved;
    }

    public boolean putRandomTile(){
        List<List<Integer>> emptyTiles = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(board[i][j] == null) emptyTiles.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }
        if(emptyTiles.isEmpty()) return false;

        Random random = new Random();
        List<Integer> randomPosition = emptyTiles.get(random.nextInt(emptyTiles.size()));
        board[randomPosition.get(0)][randomPosition.get(1)] = new Tile(2);
        return true;
    }

    public void setTile(int i, int j, Tile tile) {
        this.board[i][j] = tile;
    }

    public Tile getTile(int i, int j) {
        return board[i][j];
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            buffer.append("|\t");
            for (int j = 0; j < dimension; j++) {
                if(board[i][j] != null) buffer.append(board[i][j].getValue());
                else buffer.append(" ");
                buffer.append("\t|\t");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
