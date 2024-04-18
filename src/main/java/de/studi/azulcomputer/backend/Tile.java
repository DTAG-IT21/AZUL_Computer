package de.studi.azulcomputer.backend;

import java.util.HashMap;

public class Tile {
    public static HashMap<String, Integer> colors = new HashMap<String, Integer>() {{
        put("gameStone", -1);
        put("blue", 0);
        put("yellow", 1);
        put("red", 2);
        put("black", 3);
        put("green", 4);
    }};
    private final int color;

    public Tile(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
