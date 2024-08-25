package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Figure{

    private boolean moved = false;

    public boolean isMoved() {
        return moved;
    }

    public void setIsMoved() {
        this.moved = true;
    }

    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> ans = new ArrayList<>(14);
        HashMap<Coordinates,Figure> figures = field.getFigures();
        int currentRow = this.getCoordinates().getRow();
        int currentPos = this.getCoordinates().getPosition();
        int i = currentRow;
        while(i > 0){
            i--;
            Coordinates coordinates = new Coordinates(i,currentPos);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentRow;
        while(i < 7){
            i++;
            Coordinates coordinates = new Coordinates(i,currentPos);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentPos;
        while(i > 0){
            i--;
            Coordinates coordinates = new Coordinates(currentRow,i);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentPos;
        while(i < 7){
            i++;
            Coordinates coordinates = new Coordinates(currentRow,i);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        return ans;
    }

    public Rook(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }

    @Override
    public String toString() {
        return "" + (char)(9820);
    }
}
