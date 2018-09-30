package ru.job4j.chess;

/**
 * Ладья.
 *
 * @author imoskovtsev
 */
public class Castle extends ChessPiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    public Castle(ChessboardCell currentPosition) {
        super(currentPosition);
    }

    /**
     * Путь.
     *
     * @param destination задает ячейку куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    @Override
    public ChessboardCell[] way(ChessboardCell destination) throws ImpossibleMoveException {
        return castleWay(destination);
    }

    @Override
    public ChessPiece clone(ChessboardCell destination) {
        return new Castle(destination);
    }
}
