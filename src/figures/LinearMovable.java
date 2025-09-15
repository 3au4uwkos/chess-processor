package figures;

import field.Coordinates;
import field.Field;

import java.util.ArrayList;
import java.util.HashMap;

public interface LinearMovable {

    public Coordinates getCoordinates();

    public default ArrayList<Coordinates> getLinearMovements(Field field) {
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
                ans.add(coordinates);
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
                ans.add(coordinates);
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
                ans.add(coordinates);
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
                ans.add(coordinates);
                break;
            }
            ans.add(coordinates);
        }
        return ans;
    }
}
