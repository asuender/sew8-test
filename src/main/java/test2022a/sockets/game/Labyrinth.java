package test2022a.sockets.game;

import static test2022a.sockets.game.Direction.*;

/**
 * Ein Labyrinth besteht aus {@linkplain Tile Feldern}, angeordnet in einem quadratischen Raster (Schachbrettmuster).
 * Zwischen zwei benachbarten Zellen ist entweder eine Mauer oder ein Durchgang.
 */
public class Labyrinth {
    /**
     * Die Felder des Labyrinths
     */
    private final Tile[][] tiles;

    /**
     * Erzeugt ein Labyrinth der angegebenen Größe, wobei alle Felder komplett von Mauern umgeben sind.
     *
     * @param width die Breite des Labyrinths
     * @param height die Höhe des Labyrinths
     */
    public Labyrinth(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("the labyrinth must have positive dimensions");
        }

        tiles = new Tile[height][width];
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                tiles[y][x] = new Tile();
            }
        }
    }

    /**
     * Liefert die Breite des Labyrinths zurück.
     */
    public int getWidth() {
        return tiles[0].length;
    }

    /**
     * Liefert die Höhe des Labyrinths zurück.
     */
    public int getHeight() {
        return tiles.length;
    }

    /**
     * Liefert das Feld an der angegebenen Position.
     *
     * @param x die x-Koordinate
     * @param y die y-Koordinate
     * @return das Feld; niemals null
     */
    private Tile getTile(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("coordinates are outside the labyrinth");
        }
        return tiles[y][x];
    }

    /**
     * Liefert das Feld, an der die angegebene Mauer gespeichert wird. Im Gegensatz zu {@link #getTile(int, int)}
     * liefert diese Methode null zurück, wenn es sich um eine Außenmauer des Labyrinths handelt,
     * konkret also unter folgenden Umständen:
     *
     * <ul>
     * <li>
     *     Die Mauer im Westen wird abgefragt, und die x-Koordinate ist 0: in diesem Fall existiert das Feld zwar,
     *     aber die Mauer ist <em>immer</em> da; der Inhalt des Felds wird also ignoriert.
     * </li>
     * <li>
     *     Die Mauer im Osten wird abgefragt, und die x-Koordinate ist {@code getWidth() - 1}: in diesem Fall existiert
     *     das Feld zwar östlich vom abgefragten nicht; die Mauer ist immer da.
     * </li>
     * <li>
     *     Analog für Norden und y-Koordinate gleich 0;
     * </li>
     * <li>
     *     ebenso für Süden und y-Koordinate gleich {@code getHeight() - 1}.
     * </li>
     * </ul>
     *
     * @param x die x-Koordinate
     * @param y die y-Koordinate
     * @return das Feld
     */
    private Tile getTileForWall(int x, int y, Direction dir) {
        // do bounds checks before any other shenanigans;
        // if the original coordinates are out of bounds, we don't like it.
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("coordinates are outside the labyrinth");
        }

        // canonicalize the coordinates for east/south walls
        if (dir == EAST) x += 1;
        if (dir == SOUTH) y += 1;

        // special cases for outer walls; note that we skip the -1 from the javadocs because of prior canonicalization
        if (dir == WEST && x == 0) return null;
        if (dir == EAST && x == getWidth()) return null;
        if (dir == NORTH && y == 0) return null;
        if (dir == SOUTH && y == getHeight()) return null;

        // return the tile
        return tiles[y][x];
    }

    /**
     * Liefert, ob sich von den gegebenen Koordinaten aus in der gegebenen Richtung eine Mauer befindet.
     *
     * @param x die x-Koordinate
     * @param y die y-Koordinate
     * @param dir die Himmelsrichtung vom Feld aus
     * @return ob an der gegebenen Stelle eine Mauer ist
     */
    public boolean isWall(int x, int y, Direction dir) {
        Tile t = getTileForWall(x, y, dir);

        // outer walls always exist
        if (t == null) return true;

        if (dir.isHorizontal()) {
            return t.isWallWest();
        } else {
            return t.isWallNorth();
        }
    }

    /**
     * Liefert, ob sich von den gegebenen Koordinaten aus in der gegebenen Richtung eine Mauer befindet.
     *
     * @param x die x-Koordinate
     * @param y die y-Koordinate
     * @param dir die Himmelsrichtung vom Feld aus
     * @param wall ob an der gegebenen Stelle eine Mauer sein soll
     */
    public void setWall(int x, int y, Direction dir, boolean wall) {
        Tile t = getTileForWall(x, y, dir);

        // outer walls always exist
        if (t == null) {
            throw new IllegalArgumentException("outer walls can not be changed");
        }

        if (dir.isHorizontal()) {
            t.setWallWest(wall);
        } else {
            t.setWallNorth(wall);
        }
    }

    /**
     * Erzeugt eine String-Darstellung des Labyrinths zum Debuggen.
     *
     * @return eine String-Darstellung des Labyrinths
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                sb.append('+').append(isWall(x, y, NORTH) ? "--" : "  ");
            }
            sb.append('+').append(System.lineSeparator());
            for (int x = 0; x < tiles[y].length; x++) {
                sb.append(isWall(x, y, WEST) ? '|' : ' ').append("  ");
            }
            sb.append('|').append(System.lineSeparator());
        }
        for (int x = 0; x < getWidth(); x++) {
            sb.append('+').append("--");
        }
        sb.append('+');

        return sb.toString();
    }
}
