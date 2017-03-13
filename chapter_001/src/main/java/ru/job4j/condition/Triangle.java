package ru.job4j.condition;
/**
 * Треугольник по точкам.
 */
public class Triangle {
	/**
  	* Точка a.
  	*/
	private final Point a;
	/**
  	* Точка b.
  	*/
	private final Point b;
	/**
  	* Точка c.
  	*/
	private final Point c;
	/**
	* @param a - точка a.
	* @param b - точка b.
	* @param c - точка c.
	* Конструктор.
	* Проверяет возможность построить треугольник через точки a, b, c.
	* Устанавливает значения точек a, b, c.
	*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	* @throws Exception - трегуольник не существует.
	* @return triangle area
  	* Calculate the triangle area.
  	*/
	public double area() throws Exception {
		//расстояния между точками A и B на плоскости.
		double ab = distance(a, b);
		//расстояния между точками B и С на плоскости.
		double bc = distance(b, c);
		//расстояния между точками С и A на плоскости.
		double ca = distance(c, a);
		//трегуольник существует, если длина одной из сторон меньше суммы двух других сторон
		if (ab >= bc + ca || bc >= ab + ca || ca >= ab + bc) {
			throw new Exception("трегуольник не существует");
		}
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