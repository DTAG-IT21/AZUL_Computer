package de.dhbw.dtag_it21.azul;

public class Tile {

    //A tile is defined by only its color
    private final String color;

    public Tile(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    //No setter for the color value as a tile's color cannot change
}
