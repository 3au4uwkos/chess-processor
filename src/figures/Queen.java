package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public class Queen extends Figure{

    public Queen(boolean isWhite, int row, int pos) {
        super(isWhite, row, pos);
    }


    @Override
    public ArrayList<Coordinates> getPossibleMovements(Field field) {
        ArrayList<Coordinates> ans = new ArrayList<>(14);
        HashMap<Coordinates,Figure> figures = field.getFigures();
        int currentRow = this.getCoordinates().getRow();
        int currentPos = this.getCoordinates().getPosition();
        int i = currentRow;
        int j = currentPos;
        while(i > 0 && j > 0){
            i--;j--;
            Coordinates coordinates = new Coordinates(i,j);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentRow;
        j = currentPos;
        while(i < 7 && j > 0){
            i++;j--;
            Coordinates coordinates = new Coordinates(i,j);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentRow;
        j = currentPos;
        while(i > 0 && j < 7){
            i--;j++;
            Coordinates coordinates = new Coordinates(i,j);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentRow;
        j = currentPos;
        while(i < 7 && j < 7){
            i++;j++;
            Coordinates coordinates = new Coordinates(i,j);
            if(figures.containsKey(coordinates)){
                Figure temp = figures.get(coordinates);
                if(this.isWhite() != temp.isWhite() && temp.getClass() != King.class) ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        i = currentRow;
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

    @Override
    public String toString() {
        return "" + (char)(9819);
    }
}
