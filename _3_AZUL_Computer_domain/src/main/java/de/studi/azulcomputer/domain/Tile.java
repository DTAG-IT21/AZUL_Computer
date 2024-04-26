package de.studi.azulcomputer.domain;

import java.util.HashMap;

public class Tile {
    private static final HashMap<String, Integer> colorToInt = new HashMap<>() {{
        put("null", -1);
        put("blue", 0);
        put("yellow", 1);
        put("red", 2);
        put("black", 3);
        put("green", 4);
        put("gameStone", 5);
    }};

    private static final HashMap<Integer, String> intToColor = new HashMap<>() {{
        put(-1, "null");
        put(0, "blue");
        put(1, "yellow");
        put(2, "red");
        put(3, "black");
        put(4, "green");
        put(5, "lightblue");
    }};

    public static int getIntColor(String color) {
        return colorToInt.get(color);
    }

    public static String getStringColor(int color) {
        return intToColor.get(color);
    }

    private final int color;

    public Tile(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
