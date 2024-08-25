package field;

import java.util.Objects;

public class Coordinates {
    private int row;
    private int position;

    public int getRow() {
        return row;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "" + (char)(position + 65) + Integer.toString(row + 1);
    }

    public Coordinates(int row, int position) {
        this.row = row;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return row == that.row && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, position);
    }
}
