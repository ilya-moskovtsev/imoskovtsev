package ru.job4j.polymorphism.strategy;

/**
 * 4. Шаблон проектирования - стратегия
 *
 * @author imoskovtsev
 */
public class Triangle implements Shape {
    /**
     * Внешний вид фигуры.
     *
     * @return String псевдокартинка
     */
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                stringBuilder.append("* ");
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
