package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;

public class Rook extends Figure {

    private boolean moved = false;

    public boolean isMoved() {
        return moved;
    }

    public void setIsMoved() {
        this.moved = true;
    }

    public Rook(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        return linearMovements(field, directions);
    }

    @Override
    public String toString() {
        return "" + (char) 9820;
    }
}
