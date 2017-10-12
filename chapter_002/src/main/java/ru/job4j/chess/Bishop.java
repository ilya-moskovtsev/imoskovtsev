package ru.job4j.chess;

/**
 * Слон.
 *
 * @author imoskovtsev
 */
class Bishop extends Chesspiece {
    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    Bishop(ChessboardCell currentPosition) {
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

        final int numbersDirection = destinationNumber - currentNumber > 0 ? 1 : -1;
        final int lettersDirection = destinationLetter - currentLetter > 0 ? 1 : -1;

        int numbersSteps = Math.abs(destinationNumber - currentNumber);
        int lettersSteps = Math.abs(destinationLetter - currentLetter);
        ChessboardCell[] way;

        if (numbersSteps == lettersSteps) {
            way = new ChessboardCell[numbersSteps];
            for (int i = 1; i <= numbersSteps; i++) {
                way[i - 1] = new ChessboardCell(currentLetter + i * lettersDirection, currentNumber + i * numbersDirection);
            }
        } else {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }
        return way;
    }

    @Override
    Chesspiece clone(ChessboardCell destination) {
        return new Bishop(destination);
    }
}
