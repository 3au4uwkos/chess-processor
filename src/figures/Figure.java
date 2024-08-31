package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Figure {

    private final boolean isWhite;
    private Coordinates coordinates;

    public Figure(boolean isWhite, int row, int pos) {
        this.isWhite = isWhite;
        this.coordinates = new Coordinates(row, pos);
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract ArrayList<Coordinates> getPossibleMovements(Field field);

    protected boolean validatePosition(Coordinates coordinates) {
        return coordinates.getRow() >= 0 && coordinates.getRow() < 8 &&
                coordinates.getPosition() >= 0 && coordinates.getPosition() < 8;
    }

    public boolean validateMovement(Coordinates coordinates, Field field) {
        if (!validatePosition(coordinates)) return false;

        HashMap<Coordinates, Figure> figures = field.getFigures();
        Figure targetFigure = figures.get(coordinates);

        if (targetFigure != null) {
            if (targetFigure.isWhite() == this.isWhite() || targetFigure instanceof King) return false;
        }

        return true;
    }

    public ArrayList<Coordinates> getAllowedMovements(Field field) {
        ArrayList<Coordinates> movements = getPossibleMovements(field);
        ArrayList<Coordinates> validMovements = new ArrayList<>();
        for (Coordinates coordinates : movements) {
            if (validateMovement(coordinates, field)) validMovements.add(coordinates);
        }
        return validMovements;
    }

    protected ArrayList<Coordinates> linearMovements(Field field, int[][] directions) {
        ArrayList<Coordinates> moves = new ArrayList<>();
        int row = getCoordinates().getRow();
        int pos = getCoordinates().getPosition();
        HashMap<Coordinates, Figure> figures = field.getFigures();

        for (int[] direction : directions) {
            int i = row;
            int j = pos;
            while (true) {
                i += direction[0];
                j += direction[1];
                Coordinates coordinates = new Coordinates(i, j);
                if (!validatePosition(coordinates)) break;

                if (figures.containsKey(coordinates)) {
                    if (figures.get(coordinates).isWhite() != this.isWhite()) moves.add(coordinates);
                    break;
                }
                moves.add(coordinates);
            }
        }
        return moves;
    }
}

