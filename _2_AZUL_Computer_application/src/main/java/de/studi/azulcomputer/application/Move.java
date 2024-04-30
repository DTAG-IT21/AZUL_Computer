package de.studi.azulcomputer.application;

import java.util.LinkedList;

public class Move {

    private final int manufacture;
    private final int color;
    private final LinkedList<Integer> rows;

    public Move(int manufacture, int color, LinkedList<Integer> rows){
        this.manufacture = manufacture;
        this.color = color;
        this.rows = rows;
    }

    public int getManufacture() {
        return manufacture;
    }

    public int getColor() {
        return color;
    }

    public LinkedList<Integer> getRows() {
        return rows;
    }
}
