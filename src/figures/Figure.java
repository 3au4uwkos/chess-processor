package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Figure {

    private final boolean isWhite;
    private Coordinates coordinates;

    public boolean validateMovement(Coordinates coordinates, Field field){
        if (coordinates.getRow() > 7 || coordinates.getPosition() > 7) return false;
        if (coordinates.getRow() < 0 || coordinates.getPosition() < 0) return false;
        HashMap<Coordinates,Figure> figures = field.getFigures();
        if(figures.containsKey(coordinates)){
            return (figures.get(coordinates).isWhite() != this.isWhite() && figures.get(coordinates).getClass() != King.class);
        }
        return true;
    }

    public ArrayList<Coordinates> getAllowedMovements(Field field){
        ArrayList<Coordinates> movements = getPossibleMovements(field);
        ArrayList<Coordinates> ans = new ArrayList<>(28);
        for(Coordinates coordinates : movements) if (validateMovement(coordinates,field)) ans.add(coordinates);
        return ans;
    }

    public abstract ArrayList<Coordinates> getPossibleMovements(Field field);

    public boolean isWhite() {
        return isWhite;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Figure(boolean isWhite, int row, int pos) {
        this.isWhite = isWhite;
        this.coordinates = new Coordinates(row,pos);
    }

    public void move(Field field, int x, int y){

    }
}
