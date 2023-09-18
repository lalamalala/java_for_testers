package ru.stqa.geometry.figures;

public record Triangle(
        double a,
        double b,
        double c
) {
    public static void printTriangleArea(Triangle t) {
        String text = String.format("Area of a Triangle with sides %f and %f, %f = %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public double area() {
        var p = this.perimeter() / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

}
