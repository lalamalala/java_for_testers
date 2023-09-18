package ru.stqa.geometry.figures;

// класс - группа функций свзана между собой по смыслу
public class Square {
    // void - ничего не возращает функци
    // static - функци глобальна
    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата cо cтороной %f = %f", side, squareArea(side));
        System.out.println(text);
    }

    // double в функции указываем тип возращаемого результата (выражени)
    private static double squareArea(double a) {
        return a * a;
    }
}
