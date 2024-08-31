package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;

public class Knight extends Figure {

    public Knight(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> moves = new ArrayList<>();
        int[][] jumps = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] jump : jumps) {
            Coordinates move = new Coordinates(getCoordinates().getRow() + jump[0], getCoordinates().getPosition() + jump[1]);
            if (validatePosition(move)) moves.add(move);
        }

        return moves;
    }

    @Override
    public String toString() {
        return "" + (char) 9822;
    }
}
