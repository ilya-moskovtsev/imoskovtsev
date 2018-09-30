package ru.job4j.chess;

/**
 * Пешка.
 *
 * @author imoskovtsev
 */
public class Pawn extends ChessPiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    public Pawn(ChessboardCell currentPosition) {
        super(currentPosition);
    }

    /**
     * Copy constructor.
     *
     * @param another фигура на доске
     */
    public Pawn(ChessPiece another) {
        super(another);
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
        //проверка стартовой позиции
        final boolean isInInitialPosition = getCurrentPosition().getNumber() == 1 || getCurrentPosition().getNumber() == 6;

        if (verticalStepsTo(destination) > 2 || horizontalStepsTo(destination) > 0) {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }

        ChessboardCell[] way = new ChessboardCell[verticalStepsTo(destination)];

        if (verticalStepsTo(destination) == 1) {
            //перемещение на одну клетку по вертикали
            way[0] = new ChessboardCell(
                    getCurrentPosition().getLetter(),
                    getCurrentPosition().getNumber() + verticalDirectionTo(destination));
        } else if (verticalStepsTo(destination) == 2 && isInInitialPosition) {
            //перемещение со стартовой позиции на две клетки по вертикали
            for (int i = 1; i <= verticalStepsTo(destination); i++) {
                way[i - 1] = new ChessboardCell(
                        getCurrentPosition().getLetter(),
                        getCurrentPosition().getNumber() + i * verticalDirectionTo(destination));
            }
        } else {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }
        return way;
    }

    @Override
    public ChessPiece clone(ChessboardCell destination) {
        return new Pawn(destination);
    }
}
