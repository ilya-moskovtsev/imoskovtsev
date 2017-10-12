package ru.job4j.chess;

/**
 * Пешка.
 *
 * @author imoskovtsev
 */
class Pawn extends Chesspiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    Pawn(ChessboardCell currentPosition) {
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
    ChessboardCell[] way(ChessboardCell destination) throws ImpossibleMoveException {

        final int currentNumber = this.getCurrentPosition().getNumber();
        final int currentLetter = this.getCurrentPosition().getLetter();
        final int destinationNumber = destination.getNumber();
        final int destinationLetter = destination.getLetter();

        //направление по вертикали
        final int numbersDirection = destinationNumber - currentNumber > 0 ? 1 : -1;
        //направление по горизонтали
        final int lettersDirection = destinationLetter - currentLetter > 0 ? 1 : -1;
        //число клеток по вертикали
        final int numbersSteps = Math.abs(destinationNumber - currentNumber);
        //число клеток по горизонтали
        final int lettersSteps = Math.abs(destinationLetter - currentLetter);
        //проверка стартовой позиции
        final boolean isInInitialPosition = currentNumber == 1 || currentNumber == 6;

        if (numbersSteps > 2 || lettersSteps > 0) {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }

        ChessboardCell[] way = new ChessboardCell[numbersSteps];

        if (numbersSteps == 1) {
            //перемещение на одну клетку по вертикали
            way[0] = new ChessboardCell(currentLetter, currentNumber + numbersDirection);
        } else if (numbersSteps == 2 && isInInitialPosition) {
            //перемещение со стартовой позиции на две клетки по вертикали
            for (int i = 1; i <= numbersSteps; i++) {
                way[i - 1] = new ChessboardCell(currentLetter, currentNumber + i * numbersDirection);
            }
        } else {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }
        return way;
    }

    @Override
    Chesspiece clone(ChessboardCell destination) {
        return new Pawn(destination);
    }
}
