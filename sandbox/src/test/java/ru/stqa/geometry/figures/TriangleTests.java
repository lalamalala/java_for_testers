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
}
