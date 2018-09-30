package ru.job4j.chess;

/**
 * Слон.
 *
 * @author imoskovtsev
 */
public class Bishop extends ChessPiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    public Bishop(ChessboardCell currentPosition) {
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
        ChessboardCell[] way;
        if (verticalStepsTo(destination) == horizontalStepsTo(destination)) {
            //diagonal movement
            way = diagonalWayTo(destination);
        } else {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }
        return way;
    }

    @Override
    public ChessPiece clone(ChessboardCell destination) {
        return new Bishop(destination);
    }
}
