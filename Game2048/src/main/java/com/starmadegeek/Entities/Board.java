package com.starmadegeek.Entities;

import java.util.*;

/**
 * @author starmadegeek
 */

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
        boolean isMoved = false;
        for (int column = 0; column < dimension; column++) {
            List<Tile> columnTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[i][column] != null)
                    columnTiles.add(board[i][column]);
            }
            columnTiles = simplifyList(columnTiles);
            for (int i = 0; i < dimension; i++) {
                if(i < columnTiles.size()){
                    if(board[i][column] != columnTiles.get((i))) isMoved = true;
                    board[i][column] = columnTiles.get(i);
                }
                else {
                    if(board[i][column] != null) isMoved = true;
                    board[i][column] = null;
                }
            }
        }
        return isMoved;
    }

    public boolean moveDown() {
        boolean isMoved = false;
        for (int column = 0; column < dimension; column++) {
            List<Tile> columnTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[dimension - i - 1][column] != null)
                    columnTiles.add(board[dimension - i - 1][column]);
            }
            columnTiles = simplifyList(columnTiles);
            for (int i = 0; i < dimension; i++) {
                if(i < columnTiles.size()){
                    if(board[dimension-i-1][column] != columnTiles.get((i))) isMoved = true;
                    board[dimension-i-1][column] = columnTiles.get(i);
                }
                else {
                    if(board[dimension-i-1][column] != null) isMoved = true;
                    board[dimension-i-1][column] = null;
                }
            }
        }
        return isMoved;
    }

    public boolean moveLeft() {
        boolean isMoved = false;
        for (int row = 0; row < dimension; row++) {
            List<Tile> rowTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[row][i] != null)
                    rowTiles.add(board[row][i]);
            }
            rowTiles = simplifyList(rowTiles);
            for (int i = 0; i < dimension; i++) {
                if(i < rowTiles.size()){
                    if(board[row][i] != rowTiles.get((i))) isMoved = true;
                    board[row][i] = rowTiles.get(i);
                }
                else {
                    if(board[row][i] != null) isMoved = true;
                    board[row][i] = null;
                }
            }
        }
        return isMoved;
    }

    public boolean moveRight() {
        boolean isMoved = false;
        for (int row = 0; row < dimension; row++) {
            List<Tile> rowTiles = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if(board[row][dimension-i-1] != null)
                    rowTiles.add(board[row][dimension-i-1]);
            }
            rowTiles = simplifyList(rowTiles);
            for (int i = 0; i < dimension; i++) {
                if(i < rowTiles.size()){
                    if(board[row][dimension-i-1] != rowTiles.get((i))) isMoved = true;
                    board[row][dimension-i-1] = rowTiles.get(i);
                }
                else {
                    if(board[row][dimension-i-1] != null) isMoved = true;
                    board[row][dimension-i-1] = null;
                }
            }
        }
        return isMoved;
    }

    private List<List<Integer>> getEmptyTilesPositions(){
        List<List<Integer>> emptyTiles = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(board[i][j] == null) emptyTiles.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }
        return emptyTiles;
    }
    public void putRandomTile(){
        List<List<Integer>> emptyTiles = getEmptyTilesPositions();
        if(emptyTiles.isEmpty()) return;

        Random random = new Random();
        List<Integer> randomPosition = emptyTiles.get(random.nextInt(emptyTiles.size()));
        board[randomPosition.get(0)][randomPosition.get(1)] = new Tile(2);
    }

    public boolean checkIfLost() {
        if(!getEmptyTilesPositions().isEmpty()) return false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if(board[i][j] == null) return false;
                if(i < dimension-1 && board[i][j].getValue() == board[i+1][j].getValue()) return false;
                if(j < dimension-1 && board[i][j].getValue() == board[i][j+1].getValue()) return false;
            }
        }
        return true;
    }
    public void setTile(int i, int j, Tile tile) {
        this.board[i][j] = tile;
    }

    public Tile getTile(int i, int j) {
        return board[i][j];
    }

    public boolean isSolved() {
        return isSolved;
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

    @Override
    public Board clone() {
        Board boardNew;
        try{
            boardNew = (Board) super.clone();
        }catch(CloneNotSupportedException ex) {
            throw new IllegalStateException("did you forget to implement Cloneable?");
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardNew.setTile(i, j, board[i][j] != null? new Tile(board[i][j].getValue()) : null);
            }
        }
        return boardNew;
    }
}
