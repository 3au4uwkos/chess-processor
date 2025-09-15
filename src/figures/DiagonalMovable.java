package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public interface DiagonalMovable {

    public Coordinates getCoordinates();

    public default ArrayList<Coordinates> getDiagonalMoves(Field field) {
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
                ans.add(coordinates);
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
                ans.add(coordinates);
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
                ans.add(coordinates);
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
                ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        return ans;
    };
}
