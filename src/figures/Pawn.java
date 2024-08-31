package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class Pawn extends Figure {

    private boolean isPrevMovementDouble = false;

    public boolean isPrevMovementDouble() {
        return isPrevMovementDouble;
    }

    public void setPrevMovementDouble(boolean prevMovementDouble) {
        isPrevMovementDouble = prevMovementDouble;
    }

    public Pawn(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> moves = new ArrayList<>(4);
        HashMap<Coordinates, Figure> figures = field.getFigures();
        int row = getCoordinates().getRow();
        int pos = getCoordinates().getPosition();
        int direction = isWhite() ? 1 : -1;

        // Forward move
        Coordinates forwardOne = new Coordinates(row + direction, pos);
        if (!figures.containsKey(forwardOne)) {
            moves.add(forwardOne);

            // Double move on first move
            if ((isWhite() && row == 1) || (!isWhite() && row == 6)) {
                Coordinates forwardTwo = new Coordinates(row + 2 * direction, pos);
                if (!figures.containsKey(forwardTwo)) {
                    moves.add(forwardTwo);
                }
            }
        }

        // Capture moves
        int[] captures = {-1, 1};
        for (int capture : captures) {
            Coordinates captureMove = new Coordinates(row + direction, pos + capture);
            if (validatePosition(captureMove)) {
                Figure target = figures.get(captureMove);
                if (target != null && target.isWhite() != this.isWhite()) {
                    moves.add(captureMove);
                }
            }
        }

        // En passant
        int enPassantRow = isWhite() ? 4 : 3;
        if (row == enPassantRow) {
            for (int capture : captures) {
                Coordinates sidePawn = new Coordinates(row, pos + capture);
                Figure adjacent = figures.get(sidePawn);
                if (adjacent instanceof Pawn && adjacent.isWhite() != this.isWhite() && ((Pawn) adjacent).isPrevMovementDouble()) {
                    moves.add(new Coordinates(row + direction, pos + capture));
                }
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return "" + (char) 9823;
    }
}
