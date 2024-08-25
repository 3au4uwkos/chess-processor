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
        ArrayList<Coordinates> ans = new ArrayList<>(8);
        int currentRow = this.getCoordinates().getRow();
        int currentPos = this.getCoordinates().getPosition();
        for(int i = -2; i <= 2; i++){
            for(int j = -2; j <= 2; j++){
                if (Math.abs(i) + Math.abs(j) == 3) ans.add(new Coordinates(currentRow + i, currentPos + j));
            }
        }
        return ans;

    }

    @Override
    public String toString() {
        return "" + (char)(9822);
    }
}
