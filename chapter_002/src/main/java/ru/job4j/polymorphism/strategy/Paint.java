package ru.job4j.polymorphism.strategy;

/**
 * 4. Шаблон проектирования - стратегия
 * @author imoskovtsev
 */
public class Paint {
//    /**
//     * Фигура.
//     */
//    private Shape shape;
//
//    /**
//     * Конструктор.
//     * @param shape фигура
//     */
//    public Paint(Shape shape) {
//        this.shape = shape;
//    }

    /**
     * Нарисовать фигуру.
     * @param shape фигура
     */
    public void draw(Shape shape) {
        System.out.print(shape.pic());
    }
}
