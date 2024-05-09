package de.studi.azulcomputer.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TileBag {
    private final List<Tile> tiles; // Liste zur Speicherung der Spielsteine
    private final List<Tile> discardedTiles;

    // Konstruktor, initialisiert den Spielsteine-Sack mit den Standard-Spielsteinen
    public TileBag() {
        tiles = new LinkedList<>();
        discardedTiles = new LinkedList<>();
        initializeTiles(); // Spielsteine initialisieren
    }

    public TileBag(TileBag tileBag){
        tiles = new LinkedList<>();
        for (Tile tile : tileBag.tiles){
            tiles.add(new Tile(tile));
        }
        discardedTiles = new LinkedList<>();
        for (Tile tile : tileBag.discardedTiles){
            discardedTiles.add(new Tile(tile));
        }
    }

    // Methode zum Initialisieren der Spielsteine im Sack
    private void initializeTiles() {
        for (int i = 0; i < 20; i++) {
            tiles.add(new Tile(Tile.getIntColor("blue")));
            tiles.add(new Tile(Tile.getIntColor("yellow")));
            tiles.add(new Tile(Tile.getIntColor("red")));
            tiles.add(new Tile(Tile.getIntColor("black")));
            tiles.add(new Tile(Tile.getIntColor("green")));
        }
        // Spielsteine mischen (evtl. unnötig)
        Collections.shuffle(tiles);
    }

    // Methode zum Entfernen von Spielsteinen bestimmter Farben aus dem Sack basierend auf Benutzereingabe
    public void removeTiles(int color, int count) {
        int removedCount = 0;
        Iterator<Tile> iterator = tiles.iterator();
        //Iteration der Liste bis die Fliese mit entsprechender Farbe gefunden wurde
        while (iterator.hasNext() && removedCount < count) {
            Tile tile = iterator.next();
            //Farbe gefunden bedeutet entfernen des Steins
            if (tile.getColor() == color) {
                iterator.remove();
                removedCount++;
            }
        }
    }

    // Anzahl bestimmter Farben im Beutel
    public int getTileCount(int color) {
        int count = 0;
        for (Tile tile : tiles) {
            if (tile.getColor() == color) {
                count++;
            }
        }
        return count;
    }


    // Methode zur Berechnung der Wahrscheinlichkeit für eine bestimmte Farbe
    public double calculateProbability(int color) {
        if (tiles.isEmpty()) {
            return 0.0; // Rückgabe 0, wenn der Sack leer ist
        } else {
            int count = 0;
            for (Tile tile : tiles) {
                if (tile.getColor() == color) {
                    count++;
                }
            }
            return (double) count / tiles.size();
        }
    }

    public void reset() {
        tiles.clear(); // Liste leeren
        initializeTiles(); // Spielsteine neu initialisieren
    }

    public LinkedList<Tile> draw(int count) {
        LinkedList<Tile> drawnTiles = new LinkedList<>();
        int i = 0;
        while (i < count && !tiles.isEmpty()) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, tiles.size());
            drawnTiles.add(tiles.get(randomNum));
            tiles.remove(randomNum);
            i++;
        }
        if (drawnTiles.size() < count){
            refill();
            drawnTiles.addAll(draw(count - drawnTiles.size()));
        }

        return drawnTiles;
    }

    //Methode um gesamtanzahl der Steine im Sack zu erhalten
    public int getTotalTileCount() {
        return tiles.size();
    }

    public void refill() {
        this.tiles.addAll(discardedTiles);
        discardedTiles.clear();
    }

    public void discard(LinkedList<Tile> tiles) {
        discardedTiles.addAll(tiles);
    }
}