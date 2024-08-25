package field;

import figures.*;

import java.util.HashMap;

public class FieldInitializer {
    public static Field init() {
        Field field = new Field();
        HashMap<Coordinates,Figure> figures = field.getFigures();
        for(int pos = 0; pos < 8; pos++){
            Piece blackPiece = new Piece(false,6,pos);
            Piece whitePiece = new Piece(true,1,pos);
            figures.put(blackPiece.getCoordinates(),blackPiece);
            figures.put(whitePiece.getCoordinates(),whitePiece);
        }

        for(int pos = 0; pos < 8; pos += 7){
            Rook whiteRook = new Rook(true,0,pos);
            Rook blackRook = new Rook(false,7,pos);
            figures.put(whiteRook.getCoordinates(), whiteRook);
            figures.put(blackRook.getCoordinates(), blackRook);
        }

        for(int pos = 1; pos < 8; pos += 5){
            Knight whiteKnight = new Knight(true, 0, pos);
            Knight blackKnight = new Knight(false,7, pos);
            figures.put(whiteKnight.getCoordinates(),whiteKnight);
            figures.put(blackKnight.getCoordinates(),blackKnight);
        }

        for(int pos = 2; pos < 8; pos += 3){
            Bishop whiteBishop = new Bishop(true,0, pos);
            Bishop blackBishop = new Bishop(false,7,pos);
            figures.put(whiteBishop.getCoordinates(),whiteBishop);
            figures.put(blackBishop.getCoordinates(),blackBishop);
        }

        King whiteKing = new King(true, 0, 4);
        King blackKing = new King(false,7,4);
        figures.put(whiteKing.getCoordinates(),whiteKing);
        figures.put(blackKing.getCoordinates(),blackKing);

        Queen whiteQueen = new Queen(true,0,3);
        Queen blackQueen = new Queen(false,7,3);
        figures.put(whiteQueen.getCoordinates(),whiteQueen);
        figures.put(blackQueen.getCoordinates(),blackQueen);

        return field;
    }
}
