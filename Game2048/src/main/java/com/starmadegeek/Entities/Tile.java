package com.starmadegeek.Entities;

public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public void doubleValue() {
        this.value *= 2;
    }
    @Override
    public boolean equals(Object argObject) {
        if(!(argObject instanceof Tile)) return false;
        Tile t = (Tile) argObject;
        return t.getValue() == this.value;
    }
    @Override
    public String toString() {
        return "" + value;
    }
}
