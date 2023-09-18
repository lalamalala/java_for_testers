package ru.stqa.geometry.figures;

// класс - группа функций свзана между собой по смыслу
public class Square {
    // void - ничего не возращает функци
    // static - функци глобальна
    public static void printSquareArea(double side) {
        System.out.println("Площадь квадрата cо cтороной " + side + " = " + squareArea(side));
    }

    // double в функции указываем тип возращаемого результата (выражени)
    private static double squareArea(double a) {
        return a * a;
    }
}
