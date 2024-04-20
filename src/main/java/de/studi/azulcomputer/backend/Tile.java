package de.studi.azulcomputer.backend;

import java.util.HashMap;

public class Tile {
    public static HashMap<String, Integer> colorToInt = new HashMap<String, Integer>() {{
        put("null", -1);
        put("blue", 0);
        put("yellow", 1);
        put("red", 2);
        put("black", 3);
        put("green", 4);
        put("gameStone", 5);
    }};

    public static HashMap<Integer, String> intToColor = new HashMap<Integer, String>() {{
        put(-1, "null");
        put(0, "blue");
        put(1, "yellow");
        put(2, "red");
        put(3, "black");
        put(4, "green");
        put(5, "lightblue");
    }};

    private final int color;

    public Tile(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
