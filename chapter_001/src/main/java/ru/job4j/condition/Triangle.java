package ru.job4j.condition;
/**
 * Треугольник по точкам.
 */
public class Triangle {
	/**
  	* Точка a.
  	*/
	private Point a;
	/**
  	* Точка b.
  	*/
	private Point b;
	/**
  	* Точка c.
  	*/
	private Point c;
	/**
	* @param a - точка a.
	* @param b - точка b.
	* @param c - точка c.
	* @throws Exception - точки совпадают, точки лежат на одной прямой.
	* Конструктор.
	* Проверяет возможность построить треугольник через точки a, b, c.
	* Устанавливает значения точек a, b, c.
	*/
	public Triangle(Point a, Point b, Point c) throws Exception {
		//Проверяем, что точки не совпадают
		//Проверяем, что точки не лежат на одной прямой
		//уравнение прямой (y1 - y2)x + (x2 - x1)y + (x1y2 - x2y1) = 0
		if (
				(a.getX() == b.getX() && a.getY() == b.getY())
				|| (b.getX() == c.getX() && b.getY() == c.getY())
				|| (c.getX() == a.getX() && c.getY() == a.getY())
			) {
			throw new Exception("точки совпадают");
		} else if ((a.getY() - b.getY()) * c.getX() + (b.getX() - a.getX()) * c.getY() + (a.getX() * b.getY() - b.getX() * a.getY()) == 0) {
			throw new Exception("точки лежат на одной прямой");
		} else {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	/**
	* @return triangle area
  	* Calculate the triangle area.
  	*/
	public double area() {
		// расстояния между точками A и B на плоскости
		double ab = distance(a, b);
		// расстояния между точками B и С на плоскости
		double bc = distance(b, c);
		// расстояния между точками С и A на плоскости
		double ca = distance(c, a);
		double pp = (ab + bc + ca) / 2.0;  // полупериметр
        return Math.sqrt(pp * (pp - ab) * (pp - bc) * (pp - ca)); // полощадь по формуле Герона
	}
	/**
	* @param a - точка a.
	* @param b - точка b.
	* @return расстояние между точками A и B на плоскости
  	*/
	private double distance(Point a, Point b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
	}
}