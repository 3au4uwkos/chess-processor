package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class King extends Figure{

    private boolean wasUnderAttack = false;

    public boolean isWasUnderAttack() {
        return wasUnderAttack;
    }

    public void setWasUnderAttack() {
        this.wasUnderAttack = true;
    }

    public King(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        HashMap<Coordinates,Figure> figures = field.getFigures();
        ArrayList<Coordinates> ans = new ArrayList<>(10);
        for(int i = -1; i  < 2; i++){
            for(int j = -1; j < 2; j++){
                if(Math.abs(i) + Math.abs(j) == 0) continue;
                ans.add(new Coordinates(this.getCoordinates().getRow() + i,this.getCoordinates().getPosition()+j));
            }
        }
        if(this.isWhite()){
            if(!this.wasUnderAttack &&
            !figures.containsKey(new Coordinates(0,5)) &&
            !figures.containsKey(new Coordinates(0,6)) &&
            figures.containsKey(new Coordinates(0,7)) &&
            !field.getIsUnderBlackAttack().contains(new Coordinates(0,5)) &&
            !field.getIsUnderBlackAttack().contains(new Coordinates(0,6))){
                if(figures.get(new Coordinates(0,0)).getClass() == Rook.class){
                    Rook rook = (Rook)figures.get(new Coordinates(0,7));
                    if(!rook.isMoved()) ans.add(new Coordinates(0,6));
                }
            }
            if(!this.wasUnderAttack &&
            !figures.containsKey(new Coordinates(0,3)) &&
            !figures.containsKey(new Coordinates(0,2)) &&
            !figures.containsKey(new Coordinates(0,1)) &&
            figures.containsKey(new Coordinates(0,0)) &&
            !field.getIsUnderBlackAttack().contains(new Coordinates(0,1)) &&
            !field.getIsUnderBlackAttack().contains(new Coordinates(0,2)) &&
            !field.getIsUnderBlackAttack().contains(new Coordinates(0,3)) ){
                if(figures.get(new Coordinates(0,0)).getClass() == Rook.class){
                    Rook rook = (Rook)figures.get(new Coordinates(0,0));
                    if(!rook.isMoved()) ans.add(new Coordinates(0,1));
                }
            }
        }
        else{
            if(!this.wasUnderAttack &&
                    !figures.containsKey(new Coordinates(7,5)) &&
                    !figures.containsKey(new Coordinates(7,6)) &&
                    figures.containsKey(new Coordinates(7,7))&&
                    !field.getIsUnderWhiteAttack().contains(new Coordinates(7,5)) &&
                    !field.getIsUnderWhiteAttack().contains(new Coordinates(7,6))){
                if(figures.get(new Coordinates(7, 7)).getClass() == Rook.class) {
                    Rook rook = (Rook) figures.get(new Coordinates(7, 7));
                    if (!rook.isMoved()) ans.add(new Coordinates(7, 6));
                }
            }
            if(!this.wasUnderAttack &&
                    !figures.containsKey(new Coordinates(7,3)) &&
                    !figures.containsKey(new Coordinates(7,2)) &&
                    !figures.containsKey(new Coordinates(7,1)) &&
                    figures.containsKey(new Coordinates(7,0))&&
                    !field.getIsUnderWhiteAttack().contains(new Coordinates(7,1)) &&
                    !field.getIsUnderWhiteAttack().contains(new Coordinates(7,2)) &&
                    !field.getIsUnderWhiteAttack().contains(new Coordinates(7,3)) ){
                if (figures.get(new Coordinates(7, 0)).getClass() == Rook.class) {
                    Rook rook = (Rook)figures.get(new Coordinates(7,0));
                    if(!rook.isMoved()) ans.add(new Coordinates(1,1));
                }
            }
        }
        return ans;
    }

    @Override
    public boolean validateMovement(Coordinates coordinates, Field field) {
        if (coordinates.getRow() > 7 || coordinates.getPosition() > 7) return false;
        if (coordinates.getRow() < 0 || coordinates.getPosition() < 0) return false;
        HashMap<Coordinates,Figure> figures = field.getFigures();
        ArrayList<Coordinates> check = this.isWhite()? field.getIsUnderBlackAttack() : field.getIsUnderWhiteAttack();
        if(figures.containsKey(coordinates)){
            return (figures.get(coordinates).isWhite() != this.isWhite() && figures.get(coordinates).getClass() != King.class && !check.contains(coordinates));
        }
        return !check.contains(coordinates);
    }

    @Override
    public String toString() {
        return "" + (char)(9818);
    }
}
