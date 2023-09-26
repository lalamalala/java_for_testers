package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea() {
        var t = new Triangle(6.0, 5.0, 2.2);
        double result = Math.round(t.area() * 100.0) / 100.0;
        Assertions.assertEquals(5.28, result);
    }

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(6.0, 5.0, 2.2);
        var result = t.perimeter();
        Assertions.assertEquals(13.2, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithWrongSide() {
        try {
            new Triangle(5.0, 3.0, 1.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(4.0, 5.0, 3.0);
     // 1  var t2 = new Triangle(4.0, 3.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }
}
