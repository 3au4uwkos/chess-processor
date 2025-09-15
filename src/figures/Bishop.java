package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;

public class Bishop extends Figure implements DiagonalMovable {
    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        return getDiagonalMoves(field);
    }

    public Bishop(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public String toString() {
        return "" + (char)(9821);
    }
}