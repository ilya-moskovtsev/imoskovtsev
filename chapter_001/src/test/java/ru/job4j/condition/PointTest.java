package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверяет нахождение точки на фукнции y(x) = a * x + b.
 */
public class PointTest {
  /**
  * When X One Y Two A One B One Then Point Belongs To Function.
  */
  @Test
  public void whenXOneYTwoAOneBOneThenPointBelongsToFunction() {
    Point point = new Point(1, 2);
    boolean result = point.is(1, 1);
    boolean expected = true;
    assertThat(result, is(expected));
  }
  /**
  * When X One Y Two A Two B Two Then Point Does Not Belong To Function.
  */
  @Test
  public void whenXOneYTwoATwoBTwoThenPointDoesNotBelongToFunction() {
    Point point = new Point(1, 2);
    boolean result = point.is(2, 2);
    boolean expected = false;
    assertThat(result, is(expected));
  }
}