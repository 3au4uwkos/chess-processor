package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Figure implements LinearMovable {

    private boolean moved = false;

    public boolean isMoved() {
        return moved;
    }

    public void setIsMoved() {
        this.moved = true;
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        return getLinearMovements(field);
    }

    public Rook(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public String toString() {
        return "" + (char)(9820);
    }
}
