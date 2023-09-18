package ru.stqa.geometry.figures;

// class  - a group of functions are interconnected in meaning
public class Square {
    // void - the function returns nothing
    // static - function global
    public static void printSquareArea(double side) {
        String text = String.format("Area of a square with sides %f = %f", side, squareArea(side));
        System.out.println(text);
    }

    // double  - in the function, indicate the type of the returned result (expression)
    public static double squareArea(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
        return 4 * a;
    }
}
