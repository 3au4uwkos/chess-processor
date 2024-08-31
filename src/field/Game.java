package field;
import figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private static Coordinates whiteKing = new Coordinates(0,4);
    private static Coordinates blackKing = new Coordinates(7,4);

    private static final Scanner scanner = new Scanner(System.in);
    private static  Field field = FieldInitializer.init();
    private static final HashMap<Coordinates,Figure> figures = field.getFigures();

    public static boolean checkmateWhite(Field field){
        ArrayList<Coordinates> whiteFigures = new ArrayList<>(16);
        for(Coordinates key : figures.keySet()) if(figures.get(key).isWhite()) whiteFigures.add(key);
        for(Coordinates key : whiteFigures){
            Figure temp = figures.get(key);
            Field copy = new Field(field);
            ArrayList<Coordinates> movements = temp.getAllowedMovements(field);
            for(Coordinates movement : movements){
                figures.remove(key);
                temp.setCoordinates(movement);
                figures.put(temp.getCoordinates(),temp);
                field.checkAttack();
                if(!field.getIsUnderBlackAttack().contains(whiteKing)) return false;
                field = new Field(copy);
            }
        }
        return true;
    }

    public static boolean checkmateBlack(Field field){
        ArrayList<Coordinates> blackFigures = new ArrayList<>(16);
        for(Coordinates key : figures.keySet()) if(!figures.get(key).isWhite()) blackFigures.add(key);
        for(Coordinates key : blackFigures){
            Figure temp = figures.get(key);
            Field copy = new Field(field);
            ArrayList<Coordinates> movements = temp.getAllowedMovements(field);
            for(Coordinates movement : movements){
                figures.remove(key);
                temp.setCoordinates(movement);
                figures.put(temp.getCoordinates(),temp);
                field.checkAttack();
                if(!field.getIsUnderWhiteAttack().contains(blackKing)) return false;
                field = new Field(copy);
            }
        }
        return true;
    }

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
                            if(((Rook) rook).isMoved()) continue;
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(0,5));
                        }
                        else{
                            rook = figures.get(new Coordinates(0, 0));
                            if(((Rook) rook).isMoved()) continue;
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(0,3));
                        }
                        figures.put(rook.getCoordinates(),rook);
                    }
                    else if(!figure.isWhite() && begin.equals(new Coordinates(7,4))){
                        Figure rook;
                        if(input.getPosition() > begin.getPosition()){
                            rook = figures.get(new Coordinates(7, 7));
                            if(((Rook) rook).isMoved()) continue;
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(7,5));
                        }
                        else{
                            rook = figures.get(new Coordinates(7, 0));
                            if(((Rook) rook).isMoved()) continue;
                            figures.remove(rook.getCoordinates());
                            rook.setCoordinates(new Coordinates(7,3));
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
        while(true){
            field.checkAttack();
            if(field.getIsUnderBlackAttack().contains(whiteKing)){
                if(checkmateWhite(field)){
                    System.out.println("Black has won");
                    break;
                }
            }
            Field previous = new Field(field);
            ConsoleRender.showWhitePlayer(field);
            move(chooseWhite());
            field.checkAttack();
            while (field.getIsUnderBlackAttack().contains(whiteKing)){
                System.out.println("Make another move");
                field = new Field(previous);
                ConsoleRender.showWhitePlayer(field);
                move(chooseWhite());
                field.checkAttack();
            }

            System.out.println("\n\n\n");

            if(field.getIsUnderWhiteAttack().contains(blackKing)){
                if(checkmateBlack(field)){
                    System.out.println("White has won");
                    break;
                }
            }
            previous = new Field(field);
            ConsoleRender.showBlackPlayer(field);
            move(chooseBlack());
            field.checkAttack();
            while (field.getIsUnderWhiteAttack().contains(blackKing)){
                System.out.println("Make another move");
                field = new Field(previous);
                ConsoleRender.showBlackPlayer(field);
                move(chooseBlack());
                field.checkAttack();
            }
        }
    }
}