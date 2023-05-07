package test2022a.sockets.game;

/**
 * Ein Feld eines Labyrinths. Für jedes Feld ist gespeichert, ob links/westlich bzw. oberhalb/nördlich
 * des Felds eine Mauer ist. Um zu ermitteln, ob rechts/östlich bzw. unterhalb/südlich eines Felds eine Mauer ist,
 * muss im Nachbarfeld nachgeschaut werden.
 */
public class Tile {
    /**
     * Speichert, ob oberhalb des Felds eine Mauer ist.
     */
    private boolean wallNorth;
    /**
     * Speichert, ob links des Felds eine Mauer ist.
     */
    private boolean wallWest;

    /**
     * Erstellt ein Feld mit Mauern in Norden und Westen.
     */
    public Tile() {
        this(true, true);
    }

    /**
     * Erstellt ein Feld mit Mauern entsprechend den Parametern.
     *
     * @param wallNorth oberhalb des Felds eine Mauer ist
     * @param wallWest ob links des Felds eine Mauer ist
     */
    public Tile(boolean wallNorth, boolean wallWest) {
        this.wallNorth = wallNorth;
        this.wallWest = wallWest;
    }

    public boolean isWallNorth() {
        return wallNorth;
    }

    public void setWallNorth(boolean wallNorth) {
        this.wallNorth = wallNorth;
    }

    public boolean isWallWest() {
        return wallWest;
    }

    public void setWallWest(boolean wallWest) {
        this.wallWest = wallWest;
    }
}
