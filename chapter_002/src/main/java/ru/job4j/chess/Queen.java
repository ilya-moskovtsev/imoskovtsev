package ru.job4j.chess;

/**
 * Ферзь.
 *
 * @author imoskovtsev
 */
public class Queen extends ChessPiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    public Queen(ChessboardCell currentPosition) {
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
            way = castleWay(destination);
        }
        return way;
    }

    @Override
    public ChessPiece clone(ChessboardCell destination) {
        return new Queen(destination);
    }
}
