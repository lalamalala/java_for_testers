package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//annotations, notes that this is a test
public class SquareTests {

    @Test
    void canCalculateArea () {
        var s = new Square(5.0);
        var result = s.area();
        Assertions.assertEquals(25.0, result);
    }


    @Test
    void canCalculateTriangleArea () {
        var t = new Triangle(6.0,5.0, 2.2);
        double result = Math.round(t.area() * 100.0) / 100.0;
        Assertions.assertEquals(5.28, result);
    }

    @Test
    void canCalculateTrianglePerimeter () {
        var t = new Triangle(6.0,5.0, 2.2);
        var result = t.perimeter();
        Assertions.assertEquals(13.2, result);
    }
}
