package ru.job4j.polymorphism.strategy;

/**
 * 4. Шаблон проектирования - стратегия
 * @author imoskovtsev
 */
public class Square implements Shape {
    /**
     * Внешний вид фигуры.
     * @return String псевдокартинка
     */
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append("* * * * *").append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
