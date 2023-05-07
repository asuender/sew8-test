package test2022a.sockets.game;

/**
 * Eine Himmelsrichtung: von einem {@linkplain Tile Feld} aus gesehen kann in einer Himmelsrichtung eine Mauer sein,
 * oder sich bewegt werden.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Liefert true, falls dieses Objekt Osten oder Westen repräsentiert.
     *
     * @return ob diese Himmelsrichtung in Ost-West-Richtung zeigt
     */
    public boolean isHorizontal() {
        switch (this) {
            case NORTH:
            case SOUTH:
                return false;
            case EAST:
            case WEST:
                return true;
            // the cases above are exhaustive
            default: throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return "Norden";
            case EAST:
                return "Osten";
            case SOUTH:
                return "Süden";
            case WEST:
                return "Westen";
            // the cases above are exhaustive
            default: throw new AssertionError();
        }
    }
}
