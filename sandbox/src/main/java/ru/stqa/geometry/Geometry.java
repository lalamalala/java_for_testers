//packages (unique name) combine classes (prefixes)
// no visibility mod, which means it is visible only within the current package
package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.0);
        Square.printSquareArea(5.0);
        Square.printSquareArea(3.0);
        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7., 8.);
        System.out.println(Square.perimeter(5.0));

    }

}
