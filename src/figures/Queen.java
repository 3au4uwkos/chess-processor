package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;

public class Queen extends Figure implements LinearMovable, DiagonalMovable{

    public Queen(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }


    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> ret = getLinearMovements(field);
        ret.addAll(getDiagonalMoves(field));
        return ret;
    }

    @Override
    public String toString() {
        return "" + (char)(9819);
    }
}
