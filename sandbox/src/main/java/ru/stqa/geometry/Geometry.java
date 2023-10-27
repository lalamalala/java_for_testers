//packages (unique name) combine classes (prefixes)
// no visibility mod, which means it is visible only within the current package
package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
       var squares = List.of(new Square(7.0),new Square(5.0),new Square(3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
        squares.forEach(Square::printSquareArea);

//        Square.printSquareArea(new Square(7.0));
//        Square.printSquareArea(new Square(5.0));
//        Square.printSquareArea(new Square(3.0));

//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7., 8.);
//
//        Triangle.printTriangleArea(new Triangle(6.0,5.0, 2.2));
//        Triangle.printTriangleArea(new Triangle(4.0,5.0, 6.0));
    }

}
