package field;
import figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private static Coordinates whiteKing = new Coordinates(0,4);
    private static Coordinates blackKing = new Coordinates(7,4);

    private static final Scanner scanner = new Scanner(System.in);
    private static final Field field = FieldInitializer.init();
    private static final HashMap<Coordinates,Figure> figures = field.getFigures();

    public static Coordinates chooseWhite(){
        while(true){
            String temp = scanner.nextLine();
            int pos = temp.charAt(0) - 'A';
            int row = (temp.charAt(1) - '0') - 1;
            Coordinates input = new Coordinates(row,pos);
            if(figures.containsKey(input)) {
                if (figures.get(input).isWhite()){
                    if(!figures.get(input).getAllowedMovements(field).isEmpty()) return input;
                }
            }
            System.out.println("Choose another checkerboard cell");
        }
    }

    public static void move(Coordinates begin){
        ArrayList<Coordinates> movements = figures.get(begin).getAllowedMovements(field);
        if(figures.get(begin).isWhite()) ConsoleRender.showWhitePlayerChoice(field,movements,begin);
        else ConsoleRender.showBlackPlayerChoice(field,movements,begin);
        while(true){
            String temp = scanner.nextLine();
            int pos = temp.charAt(0) - 'A';
            int row = (temp.charAt(1) - '0') - 1;
            Coordinates input = new Coordinates(row,pos);
            if(!movements.contains(input)) {
                System.out.println("Choose another checkerboard cell");
                continue;
            }
            Figure figure = figures.get(begin);
            if(figure.getClass() == Rook.class)((Rook) figure).setIsMoved();
            if(figure.getClass() == King.class){
                ((King) figure).setWasUnderAttack();
                if(Math.abs(input.getPosition() - begin.getPosition()) == 2){
                    if(figure.isWhite() && begin.equals(new Coordinates(0,4))){
                        Figure rook;
                        if(input.getPosition() > begin.getPosition()){
                            rook = figures.get(new Coordinates(0, 7));
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(0,5));
                        }
                        else{
                            rook = figures.get(new Coordinates(0, 0));
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(0,2));
                        }
                        figures.put(rook.getCoordinates(),rook);
                    }
                    else if(!figure.isWhite() && begin.equals(new Coordinates(7,4))){
                        Figure rook;
                        if(input.getPosition() > begin.getPosition()){
                            rook = figures.get(new Coordinates(7, 7));
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(7,5));
                        }
                        else{
                            rook = figures.get(new Coordinates(7, 0));
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(7,2));
                        }
                        figures.put(rook.getCoordinates(),rook);
                    }
                }
                if(figure.isWhite()) whiteKing = input;
                else blackKing = input;
            }
            figure.setCoordinates(input);
            figures.put(input,figure);
            figures.remove(begin);
            break;
        }
    }

    public static Coordinates chooseBlack(){
        while(true){
        String temp = scanner.nextLine();
        int pos = temp.charAt(0) - 'A';
        int row = (temp.charAt(1) - '0') - 1;
        Coordinates input = new Coordinates(row,pos);
        if(figures.containsKey(input)) {
            if (!figures.get(input).isWhite()){
                if(!figures.get(input).getAllowedMovements(field).isEmpty()) return input;
            }
        }
        System.out.println("Choose another checkerboard cell");
    }
}


    public static void start(){
        while (true) {

            field.checkAttack();
            ConsoleRender.showWhitePlayer(field);

            if(field.getIsUnderBlackAttack().contains(whiteKing)){
                Figure king = figures.get(whiteKing);
                if(king.getAllowedMovements(field).isEmpty()){
                    System.out.println("Game over!");
                    System.out.println("Black has defeated white!");
                    break;
                }
                move(whiteKing);
            }
            else move(chooseWhite());

            System.out.println("\n\n\n");
            ConsoleRender.showBlackPlayer(field);
            field.checkAttack();

            if(field.getIsUnderWhiteAttack().contains(blackKing)){
                Figure king = figures.get(blackKing);
                if(king.getAllowedMovements(field).isEmpty()){
                    System.out.println("Game over!");
                    System.out.println("White has defeated black!");
                    break;
                }
                move(blackKing);
            }
            else{
                move(chooseBlack());
                System.out.println("\n\n\n");
            }
        }
    }
}
