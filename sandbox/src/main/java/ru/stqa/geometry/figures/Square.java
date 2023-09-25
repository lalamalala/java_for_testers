package ru.stqa.geometry.figures;

// class  - a group of functions are interconnected in meaning
public record Square (double side) {

    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }
    // description of the object structure, declaration of variables
    // void - the function returns nothing
    // static - function global
    public static void printSquareArea(Square s) {
        String text = String.format("Area of a square with sides %f = %f", s.side, s.area());
        System.out.println(text);
    }

    // double  - in the function, indicate the type of the returned result (expression)

    public double area() {

        return this.side * this.side;
    }

    public double perimeter() {

        return 4 * this.side;
    }
}
