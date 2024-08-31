package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class King extends Figure {

    private boolean wasUnderAttack = false;

    public King(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    public boolean isWasUnderAttack() {
        return wasUnderAttack;
    }

    public void setWasUnderAttack() {
        this.wasUnderAttack = true;
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> moves = new ArrayList<>();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] direction : directions) {
            Coordinates move = new Coordinates(getCoordinates().getRow() + direction[0], getCoordinates().getPosition() + direction[1]);
            if (validatePosition(move)) moves.add(move);
        }

        // Castling logic
        if (!wasUnderAttack) {
            castling(moves, field, true);  // Kingside
            castling(moves, field, false); // Queenside
        }

        return moves;
    }

    private void castling(ArrayList<Coordinates> moves, Field field, boolean kingside) {
        HashMap<Coordinates, Figure> figures = field.getFigures();
        ArrayList<Coordinates> attackPositions = isWhite() ? field.getIsUnderBlackAttack() : field.getIsUnderWhiteAttack();
        int row = isWhite() ? 0 : 7;
        int rookCol = kingside ? 7 : 0;
        int[] path = kingside ? new int[]{5, 6} : new int[]{3, 2, 1};

        for (int col : path) {
            if (figures.containsKey(new Coordinates(row, col)) || attackPositions.contains(new Coordinates(row, col))) return;
        }

        Figure rook = figures.get(new Coordinates(row, rookCol));
        if (rook instanceof Rook && !((Rook) rook).isMoved()) {
            moves.add(new Coordinates(row, kingside ? 6 : 2));
        }
    }

    @Override
    public boolean validateMovement(Coordinates coordinates, Field field) {
        if (!validatePosition(coordinates)) return false;

        if (isWhite() && field.getIsUnderBlackAttack().contains(coordinates)) return false;
        if (!isWhite() && field.getIsUnderWhiteAttack().contains(coordinates)) return false;

        return super.validateMovement(coordinates, field);
    }

    @Override
    public String toString() {
        return "" + (char) 9818;
    }
}
