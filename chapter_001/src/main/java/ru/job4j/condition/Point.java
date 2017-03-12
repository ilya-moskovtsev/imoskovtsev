package ru.job4j.condition;
/**
 * Точка в системе координат.
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
  * @param x - координата x.
  * @param y - координата y.
  * Конструктор. Устанавливает значения x, y.
  */
  public  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
  * @return x - координата x.
  * Возвращает x.
  */
  public int getX() {
      return this.x;
  }
  /**
  * @return y - координата y.
  * Возвращает y.
  */
  public int getY() {
     return this.y;
  }
  /**
  * @param a - параметр фукнции y(x) = a * x + b.
  * @param b - параметр фукнции y(x) = a * x + b.
  * @return boolean - находится ли точка на фукнции.
  * Определяет находится ли точка на фукнции y(x) = a * x + b.
  */
  public boolean is(int a, int b) {
    return this.y == a * this.x + b ? true : false;
  }
}