package de.studi.azulcomputer.backend;
import java.util.*;

public class TileBag {
    private List<Tile> tiles; // Liste zur Speicherung der Spielsteine

    // Konstruktor, initialisiert den Spielsteine-Sack mit den Standard-Spielsteinen
    public TileBag() {
        tiles = new ArrayList<>();
        initializeTiles(); // Spielsteine initialisieren
    }

    // Methode zum Initialisieren der Spielsteine im Sack
    private void initializeTiles() {
        for (int i = 0; i < 20; i++) {
            tiles.add(new Tile(Tile.colors.get("blue")));
            tiles.add(new Tile(Tile.colors.get("yellow")));
            tiles.add(new Tile(Tile.colors.get("red")));
            tiles.add(new Tile(Tile.colors.get("black")));
            tiles.add(new Tile(Tile.colors.get("green")));
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


    //Methode um gesamtanzahl der Steine im Sack zu erhalten
    public int getTotalTileCount() {
        return tiles.size();
    }



}