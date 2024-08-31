package field;

import java.util.Objects;

public class Coordinates {
    private final int row;
    private final int position;

    public Coordinates(int row, int position) {
        this.row = row;
        this.position = position;
    }

    public int getRow() {
        return row;
    }

    public int getPosition() {
        return position;
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

    @Override
    public String toString() {
        return "Coordinates{" +
                "row=" + row +
                ", position=" + position +
                '}';
    }
}
