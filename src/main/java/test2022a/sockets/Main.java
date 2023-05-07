package test2022a.sockets;

import test2022a.sockets.game.Direction;
import test2022a.sockets.game.Game;
import test2022a.sockets.game.Labyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            Game game = new Game(createLabyrinth());

            // nach dem ersten Zug fragen
            System.out.println(askForMove(game));

            while (true) {
                // input holen & verarbeiten
                String input = in.readLine();
                Direction dir = null;
                switch (input) {
                    case "N":
                        dir = Direction.NORTH;
                        break;
                    case "W":
                        dir = Direction.WEST;
                        break;
                    case "S":
                        dir = Direction.SOUTH;
                        break;
                    case "O":
                        dir = Direction.EAST;
                        break;
                }

                // checken ob der Zug legal war
                if (dir == null) {
                    // Fehler ausgeben und nach nächstem Zug fragen
                    System.out.println("ungültiger Zug: " + input + ". " + askForMove(game));
                    continue;
                }

                // versuchen, den Zug durchzuführen
                boolean success = game.move(dir);

                if (!success) {
                    // Fehler ausgeben und nach nächstem Zug fragen
                    System.out.println("Da ist eine Mauer im Weg. " + askForMove(game));
                    continue;
                }

                if (game.isWon()) {
                    // Schleife Abbrechen, bevor nach dem nächsten Zu gefragt wird
                    break;
                }

                // nach dem nächsten Zug fragen
                System.out.println(askForMove(game));
            }

            System.out.println("Du bist am Ziel, nach " + game.getMoves() + " Zügen!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String askForMove(Game game) {
        Set<Direction> dirs = EnumSet.noneOf(Direction.class);
        for (Direction dir : Direction.values()) {
            if (!game.isWall(dir)) dirs.add(dir);
        }

        return String.format(
                "Du bist bei (%d, %d) und willst nach (%d, %d). Du kannst nach %s - was tust du?",
                game.getX(), game.getY(), game.getGoalX(), game.getGoalY(), dirs
        );
    }

    /**
     * Erzeugt ein Labyrinth folgender Form:
     *
     * <pre>
     * +--+--+--+
     * |  |     |
     * +  +  +  +
     * |     |  |
     * +--+--+--+
     * </pre>
     *
     * @return das erzeugte Labyrinth
     */
    private static Labyrinth createLabyrinth() {
        Labyrinth l = new Labyrinth(3, 2);
        l.setWall(0, 0, Direction.SOUTH, false);
        l.setWall(0, 1, Direction.EAST, false);
        l.setWall(1, 1, Direction.NORTH, false);
        l.setWall(1, 0, Direction.EAST, false);
        l.setWall(2, 0, Direction.SOUTH, false);
        return l;
    }
}
