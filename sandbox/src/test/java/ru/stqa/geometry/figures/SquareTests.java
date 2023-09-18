package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//annotations, notes that this is a test
public class SquareTests {

    @Test
    void canCalculateArea () {
        var result = Square.squareArea(5.0);
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter () {
        Assertions.assertEquals(20.0, Square.perimeter(5.0));
    }

}
