public class Geometry {
    public static void main(String[] args) {
        printSquareArea(7.0);
        printSquareArea(5.0);
        printSquareArea(3.0);
        printRectangleArea(3.0, 5.0);
        printRectangleArea(7., 8.);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прмоугольника cо cторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    // void - ничего не возращает функци
    // static - функци глобальна
    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата cо cтороной " + side + " = " + squareArea(side));
    }

    // double в функции указываем тип возращаемого результата (выражени)
    private static double squareArea(double a) {
        return a * a;
    }
}
