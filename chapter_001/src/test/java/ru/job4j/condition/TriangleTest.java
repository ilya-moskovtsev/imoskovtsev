package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Треугольник по точкам.
 */
public class TriangleTest {
	/**
	* @throws Exception - точки совпадают, точки лежат на одной прямой.
	* Calculate the triangle area.
	* When Vertex A Vertex B Vertex C Then Triangle Has Area.
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
	* @throws Exception - точки совпадают, точки лежат на одной прямой.
	* When Vertices Coincide Then Triangle Throws Exception.
	*/
	@Test
	public void whenVerticesCoincideThenTriangleThrowsException() {
    Point vertexA = new Point(1, 1);
    Point vertexB = new Point(1, 1);
    Point vertexC = new Point(10, 1);
	    try {
	    	Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
	    	fail("Expected an Exception to be thrown");
		} catch (Exception e) {
			String result = e.getMessage();
			String expected = "точки совпадают";
			assertThat(result, is(expected));
		}
	}
	/**
	* @throws Exception - точки совпадают, точки лежат на одной прямой.
	* When Vertices Are On A Straight Line Then Triangle Throws Exception.
	*/
	@Test
	public void whenVerticesAreOnAStraightLineThenTriangleThrowsException() {
    Point vertexA = new Point(1, 1);
    Point vertexB = new Point(1, 2);
    Point vertexC = new Point(1, 3);
	    try {
	    	Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
	    	fail("Expected an Exception to be thrown");
		} catch (Exception e) {
			String result = e.getMessage();
			String expected = "точки лежат на одной прямой";
			assertThat(result, is(expected));
		}
	}
}