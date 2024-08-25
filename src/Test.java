import field.*;
import figures.*;

import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Field field = new Field();
        Figure king = new King(false, 7,4);
        Figure rook = new Rook(false,7,0);
        HashMap<Coordinates,Figure> figures = field.getFigures();
        figures.put(king.getCoordinates(), king);
        figures.put(rook.getCoordinates(), rook);
        Figure secondRook = new Rook(false,7,7);
        figures.put(secondRook.getCoordinates(),secondRook);

    }
}
