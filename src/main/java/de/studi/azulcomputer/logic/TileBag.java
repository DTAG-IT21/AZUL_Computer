package de.studi.azulcomputer.logic;
import java.util.*;

public class TileBag {
    private List<String> tiles; // Liste zur Speicherung der Spielsteine

    // Konstruktor, initialisiert den Spielsteine-Sack mit den Standard-Spielsteinen
    public TileBag() {
        tiles = new ArrayList<>();
        initializeTiles(); // Spielsteine initialisieren
    }

    // Methode zum Initialisieren der Spielsteine im Sack
    private void initializeTiles() {
        // Standard-Spielsteine für Azul: Blau, Gelb, Türkis, Rot, Schwarz
        for (int i = 0; i < 20; i++) {
            tiles.add("Blau");
            tiles.add("Gelb");
            tiles.add("Türkis");
            tiles.add("Rot");
            tiles.add("Schwarz");
        }
        // Spielsteine mischen
        Collections.shuffle(tiles);
    }

    // Methode zum Entfernen von Spielsteinen bestimmter Farben aus dem Sack basierend auf Benutzereingabe
    public void removeTiles(String color, int count) {
        int removedCount = 0;
        Iterator<String> iterator = tiles.iterator();
        while (iterator.hasNext() && removedCount < count) {
            String tile = iterator.next();
            if (tile.equals(color)) {
                iterator.remove();
                removedCount++;
            }
        }
    }

    public int getTileCount(String color) {
        int count = 0;
        for (String tile : tiles) {
            if (tile.equals(color)) {
                count++;
            }
        }
        return count;
    }


    // Methode zum Ziehen eines Spielsteins aus dem Sack
    public String drawTile() {
        if (tiles.isEmpty()) {
            return null; // Rückgabe null, wenn der Sack leer ist
        } else {
            // Zufälligen Spielstein auswählen und entfernen
            Random rand = new Random();
            int index = rand.nextInt(tiles.size());
            return tiles.remove(index);
        }
    }



    // Methode zur Berechnung der Wahrscheinlichkeit für eine bestimmte Farbe
    public double calculateProbability(String color) {
        if (tiles.isEmpty()) {
            return 0.0; // Rückgabe 0, wenn der Sack leer ist
        } else {
            int count = 0;
            for (String tile : tiles) {
                if (tile.equals(color)) {
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


    public int getTotalTileCount() {
        return tiles.size();
    }



}