package field;

import figures.Figure;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsoleRender {

    private static final String blackBackground = "\033[48;2;51;32;13m";
    private static final String whiteBackground = "\033[48;2;150;145;101m";
    private static final String blackFigure = "\033[38;2;0;0;0m";
    private static final String whiteFigure = "\033[38;5;250;250;250m";
    private static final String choice = "\033[48;2;201;4;219m";
    private static final String attack = "\033[48;2;44;160;209m";
    private static final String reset = "\033[0m";

    public static void showBlackPlayer(Field field){
        HashMap<Coordinates, Figure> figures = field.getFigures();
        for(int row = 0; row < 8; row++){
            for(int pos = 7; pos >= 0; pos--){
                System.out.print((row + pos) % 2 == 1 ? whiteBackground : blackBackground);
                System.out.print(" ");
                if(figures.containsKey(new Coordinates(row,pos))){
                    Figure figure = figures.get(new Coordinates(row,pos));
                    if (figure.isWhite()) System.out.print(whiteFigure + figure);
                    else System.out.print(blackFigure + figure);
                }
                else
                    System.out.print(" ");
                System.out.print(" " + reset);
            }
            System.out.println("");
        }
    }

    public static void showBlackPlayerChoice(Field field, ArrayList<Coordinates> movements, Coordinates begin){
        HashMap<Coordinates, Figure> figures = field.getFigures();
        for(int row = 0; row < 8; row++){
            for(int pos = 7; pos >= 0; pos--){
                Coordinates temp = new Coordinates(row,pos);
                if (temp.equals(begin)) System.out.print(choice);
                else if (movements.contains(temp)) System.out.print(attack);
                else System.out.print((row + pos) % 2 == 1 ? whiteBackground : blackBackground);
                System.out.print(" ");
                if(figures.containsKey(temp)){
                    Figure figure = figures.get(temp);
                    if (figure.isWhite()) System.out.print(whiteFigure + figure);
                    else System.out.print(blackFigure + figure);
                }
                else
                    System.out.print(" ");
                System.out.print(" " + reset);
            }
            System.out.println("");
        }
    }

    public static void showWhitePlayer(Field field){
        HashMap<Coordinates, Figure> figures = field.getFigures();
        for(int row = 7; row >= 0; row--){
            for(int pos = 0; pos < 8; pos++){
                System.out.print((row + pos) % 2 == 1 ? whiteBackground : blackBackground);
                System.out.print(" ");
                if(figures.containsKey(new Coordinates(row,pos))){
                    Figure figure = figures.get(new Coordinates(row,pos));
                    if (figure.isWhite()) System.out.print(whiteFigure + figure);
                    else System.out.print(blackFigure + figure);
                }
                else
                    System.out.print(" ");
                System.out.print(" " + reset);
            }
            System.out.println("");
        }
    }
    public static void showWhitePlayerChoice(Field field, ArrayList<Coordinates> movements, Coordinates begin){
        HashMap<Coordinates, Figure> figures = field.getFigures();
        for(int row = 7; row >= 0; row--){
            for(int pos = 0; pos < 8; pos++){
                Coordinates temp = new Coordinates(row,pos);
                if (temp.equals(begin)) System.out.print(choice);
                else if (movements.contains(temp)) System.out.print(attack);
                else System.out.print((row + pos) % 2 == 1 ? whiteBackground : blackBackground);
                System.out.print(" ");
                if(figures.containsKey(temp)){
                    Figure figure = figures.get(temp);
                    if (figure.isWhite()) System.out.print(whiteFigure + figure);
                    else System.out.print(blackFigure + figure);
                }
                else
                    System.out.print(" ");
                System.out.print(" " + reset);
            }
            System.out.println("");
        }
    }
}
