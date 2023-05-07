package test2022a.sockets.game;

import java.util.Random;

/**
 * Ein Game erlaubt es, in einem gegebenen Labyrinth das Ziel zu suchen.
 * Start- und Zielposition werden dabei zufällig generiert.
 */
public class Game {
    private final Labyrinth labyrinth;
    private final int goalX, goalY;

    private int x, y;
    private int moves = 0;

    /**
     * Erzeugt ein Game, mit dem im gegebenen Labyrinth gespielt wird.  Start- und Zielposition werden zufällig generiert.
     * Das Labyrinth muss mindestens zwei Felder haben, damit Start- und Zielposition unterschiedlich sein können.
     *
     * @param labyrinth Das Labyrinth, in dem gespielt werden soll.
     */
    public Game(Labyrinth labyrinth) {
        if (labyrinth.getWidth() <= 1 && labyrinth.getHeight() <= 1) {
            throw new IllegalArgumentException("labyrinth too small: player will always start at the goal");
        }

        this.labyrinth = labyrinth;

        Random r = new Random();
        goalX = r.nextInt(labyrinth.getWidth());
        goalY = r.nextInt(labyrinth.getHeight());

        do {
            x = r.nextInt(labyrinth.getWidth());
            y = r.nextInt(labyrinth.getHeight());
        } while (isWon());
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    public int getGoalX() {
        return goalX;
    }

    public int getGoalY() {
        return goalY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMoves() {
        return moves;
    }

    /**
     * Ermittelt, ob der/die Spieler*in an der vorgegebenen Zielposition angekommen ist.
     *
     * @return ob der/die Spieler*in das Ziel gefunden hat
     */
    public boolean isWon() {
        return x == goalX && y == goalY;
    }

    /**
     * Liefert zurück, ob sich in der angegebenen Richtung von der aktuellen Position aus eine Mauer befindet.
     *
     * @param dir die zu prüfende Richtung
     * @return ob in der Richtung eine Mauer ist
     */
    public boolean isWall(Direction dir) {
        return labyrinth.isWall(x, y, dir);
    }

    /**
     * Setzt von der aktuellen Position einen Schritt in die gegebene Richtung.
     * Bei Erfolg (keine Mauer) wird true zurückgegeben, sonst false.
     *
     * @param dir die Richtung, in die gegangen werden soll
     * @return ob ein Schritt in diese Richtung gemacht werden konnte
     */
    public boolean move(Direction dir) {
        if (isWall(dir)) return false;

        switch (dir) {
            case NORTH:
                y -= 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case WEST:
                x -= 1;
                break;
        }
        moves += 1;

        return true;
    }
}
