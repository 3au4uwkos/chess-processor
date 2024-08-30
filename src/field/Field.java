package field;

import figures.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {

    private HashMap <Coordinates,Figure> figures = new HashMap<>(32);
    private ArrayList<Coordinates> isUnderWhiteAttack = new ArrayList<>(64);
    private ArrayList<Coordinates> isUnderBlackAttack = new ArrayList<>(64);

    public HashMap<Coordinates, Figure> getFigures() {
        return figures;
    }

    public Field(){

    }

    public Field(Field field) {
        this.figures = field.figures;
        this.isUnderWhiteAttack = field.isUnderWhiteAttack;
        this.isUnderBlackAttack = field.isUnderBlackAttack;
    }

    public void checkAttack(){
        isUnderBlackAttack.clear();
        isUnderWhiteAttack.clear();
        for(Coordinates key : this.figures.keySet()){
            Figure figure = figures.get(key);
            if (figure.getClass() == Piece.class){
                int currentRow = figure.getCoordinates().getRow();
                int currentPos = figure.getCoordinates().getPosition() - 1;
                if (figure.isWhite()){
                    currentRow++;
                    if(currentPos < 8 && currentPos >= 0 && currentRow < 8 && currentRow >= 0)
                        isUnderWhiteAttack.add(new Coordinates(currentRow,currentPos));
                    currentPos += 2;
                    if(currentPos < 8 && currentPos >= 0 && currentRow < 8 && currentRow >= 0)
                        isUnderWhiteAttack.add(new Coordinates(currentRow,currentPos));
                }
                else{
                    currentRow--;
                    if(currentPos < 8 && currentPos >= 0 && currentRow < 8 && currentRow >= 0)
                        isUnderBlackAttack.add(new Coordinates(currentRow,currentPos));
                    currentPos += 2;
                    if(currentPos < 8 && currentPos >= 0 && currentRow < 8 && currentRow >= 0)
                        isUnderBlackAttack.add(new Coordinates(currentRow,currentPos));
                }
            }
            else if(figure.getClass() == King.class){
                for(int i = -1; i < 2; i++){
                    for(int j = -1; j < 2; j++){
                        if(Math.abs(i) + Math.abs(j) == 0) continue;
                        int currentRow = figure.getCoordinates().getRow() + i;
                        int currentPos = figure.getCoordinates().getPosition() + j;
                        if(currentPos < 8 && currentPos >= 0 && currentRow < 8 && currentRow >= 0) {
                            if (figure.isWhite()) isUnderWhiteAttack.add(new Coordinates(currentRow, currentPos));
                            else isUnderBlackAttack.add(new Coordinates(currentRow, currentPos));
                        }
                    }
                }
            }
            else{
                ArrayList<Coordinates> cells = figure.getPossibleMovements(this);
                if (figure.isWhite()) isUnderWhiteAttack.addAll(cells);
                else isUnderBlackAttack.addAll(cells);
            }
        }
    }

    public ArrayList<Coordinates> getIsUnderWhiteAttack() {
        return isUnderWhiteAttack;
    }

    public ArrayList<Coordinates> getIsUnderBlackAttack() {
        return isUnderBlackAttack;
    }
}
