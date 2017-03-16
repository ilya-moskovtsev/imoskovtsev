package ru.job4j.condition;
/**
 * Точка в системе координат.
 * @author imoskovtsev
 */
public class Point {
  /**
  * Координата x.
  */
  private int x;
  /**
  * Координата y.
  */
  private int y;
  /**
  * Конструктор. Устанавливает значения x, y.
  * @param x - координата x.
  * @param y - координата y.
  */
  public  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
  * Возвращает x.
  * @return x - координата x.
  */
  public int getX() {
      return this.x;
  }
  /**
  * Возвращает y.
  * @return y - координата y.
  */
  public int getY() {
     return this.y;
  }
  /**
  * Определяет находится ли точка на фукнции y(x) = a * x + b.
  * @param a - параметр фукнции y(x) = a * x + b.
  * @param b - параметр фукнции y(x) = a * x + b.
  * @return boolean - находится ли точка на фукнции.
  */
  public boolean is(int a, int b) {
    return this.y == a * this.x + b;
  }
}