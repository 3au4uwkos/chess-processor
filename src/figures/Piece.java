package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class Piece extends Figure{

    private boolean isPrevMovementDouble = false;

    public boolean isPrevMovementDouble() {
        return isPrevMovementDouble;
    }

    public void setPrevMovementDouble(boolean prevMovementDouble) {
        isPrevMovementDouble = prevMovementDouble;
    }

    public Piece(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> ans = new ArrayList<>(4);
        int currentRow = this.getCoordinates().getRow();
        int currentPos = this.getCoordinates().getPosition();
        for(int i = -1; i <= 1; i++){
            if(this.isWhite()) ans.add(new Coordinates(currentRow + 1, currentPos + i));
            else ans.add(new Coordinates(currentRow - 1, currentPos + i));
        }
        if(this.isWhite() && currentRow == 1 && !(field.getFigures().containsKey(new Coordinates(2,currentPos)))) ans.add(new Coordinates(3,currentPos));
        else if(currentRow == 6 && !(field.getFigures().containsKey(new Coordinates(5,currentPos)))) ans.add(new Coordinates(4,currentPos));
        return ans;
    }

    @Override
    public boolean validateMovement(Coordinates coordinates, Field field) {
        if (coordinates.getRow() > 7 || coordinates.getPosition() > 7) return false;
        if (coordinates.getRow() < 0 || coordinates.getPosition() < 0) return false;
        HashMap<Coordinates, Figure> figures = field.getFigures();
        int currentRow = this.getCoordinates().getRow();
        int currentPos = this.getCoordinates().getPosition();
        if ((this.isWhite() && currentRow == 4) || (!this.isWhite() && currentRow == 3)) {
            if (figures.containsKey(new Coordinates(currentRow, coordinates.getPosition()))) {
                Figure figure = figures.get(new Coordinates(currentRow, coordinates.getPosition()));
                if (figure.getClass() == Piece.class && figure.isWhite() != this.isWhite()) {
                    if (((Piece) figure).isPrevMovementDouble()) return true;
                }
            }
        }
        if (figures.containsKey(coordinates)) {
            if (currentPos != figures.get(coordinates).getCoordinates().getPosition()) return
                    (figures.get(coordinates).isWhite() != this.isWhite()
                            && figures.get(coordinates).getClass() != King.class);
            else return false;
        }
        return currentPos == coordinates.getPosition();
    }

    @Override
    public String toString() {
        return "" + (char)(9823);
    }
}
