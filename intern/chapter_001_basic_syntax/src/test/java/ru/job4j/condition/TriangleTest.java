package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Треугольник по точкам.
 *
 * @author imoskovtsev
 */
public class TriangleTest {
    /**
     * Calculate the triangle area.
     * When Vertex A Vertex B Vertex C Then Triangle Has Area.
     *
     * @throws Exception - точки совпадают, точки лежат на одной прямой.
     */
    @Test
    public void whenVertexAVertexBVertexCThenTriangleHasArea() throws Exception {
        Point vertexA = new Point(1, 1);
        Point vertexB = new Point(1, 10);
        Point vertexC = new Point(10, 1);
        Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
        double result = triangle.area();
        double expected = 40.5;
        assertThat(result, closeTo(expected, 0.01));
    }

    /**
     * When Vertices Coincide Then Triangle Throws Exception.
     */
    @Test
    public void whenVerticesCoincideThenTriangleThrowsException() {
        Point vertexA = new Point(1, 1);
        Point vertexB = new Point(1, 1);
        Point vertexC = new Point(10, 1);
        Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
        try {
            triangle.area();
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            String result = e.getMessage();
            String expected = "трегуольник не существует";
            assertThat(result, is(expected));
        }
    }

    /**
     * When Vertices Are On A Straight Line Then Triangle Throws Exception.
     */
    @Test
    public void whenVerticesAreOnAStraightLineThenTriangleThrowsException() {
        Point vertexA = new Point(1, 1);
        Point vertexB = new Point(1, 2);
        Point vertexC = new Point(1, 3);
        Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
        try {
            triangle.area();
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            String result = e.getMessage();
            String expected = "трегуольник не существует";
            assertThat(result, is(expected));
        }
    }
}