package ru.job4j.chess;

/**
 * Шахматная доска.
 *
 * @author imoskovtsev
 */
class Chessboard {
    /**
     * Шахматные фигуры.
     */
    private Chesspiece[] chesspieces;

    /**
     * get chesspieces.
     *
     * @return chesspieces
     */
    Chesspiece[] getChesspieces() {
        return chesspieces;
    }

    /**
     * Конструктор.
     *
     * @param chesspieces фигуры
     */
    Chessboard(Chesspiece[] chesspieces) {
        this.chesspieces = chesspieces;
    }

    /**
     * Перемещение фигуры.
     * Метод должен должен проверить:
     * - Что в начальной ячейке есть фигура. Если нет, то выкинуть исключение
     * - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
     * - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
     * - Если все отлично. Записать в ячейку новое новое положение Figure figure.clone(Cell dist)
     *
     * @param source      начальная ячейка
     * @param destination конечная ячейка
     *                    Если все отлично. Записать в ячейку новое новое положение Figure figure.clone(Cell dist)
     * @throws FigureNotFoundException Если в начальной ячейке нет фигуры, то выкинуть исключение.
     * @throws ImpossibleMoveException Если фигура не может так двигаться, то выкинуть исключение.
     * @throws OccupiedWayException    Если путь занят фигурами, то выкинуть исключение.
     */
    void move(ChessboardCell source, ChessboardCell destination) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        Chesspiece currentChesspiece = null;
        int currentChesspieceIndex = -1;
        boolean isFound = false;
        for (int i = 0; i < chesspieces.length; i++) {
            if (source.getLetter() == chesspieces[i].getCurrentPosition().getLetter() && source.getNumber() == chesspieces[i].getCurrentPosition().getNumber()) {
                isFound = true;
                currentChesspiece = chesspieces[i];
                currentChesspieceIndex = i;
            }
        }
        if (!isFound) {
            throw new FigureNotFoundException("В начальной ячейке нет фигуры.");
        }

        final ChessboardCell[] way = currentChesspiece.way(destination);

        boolean isWayOccupied = false;
        for (Chesspiece chesspiece : chesspieces) {
            for (ChessboardCell chessboardCell : way) {
                if (chesspiece.getCurrentPosition().getLetter() == chessboardCell.getLetter()
                        && chesspiece.getCurrentPosition().getNumber() == chessboardCell.getNumber()) {
                    isWayOccupied = true;
                }
            }
        }
        if (isWayOccupied) {
            throw new OccupiedWayException("Путь занят фигурами.");
        }

        //Если все отлично. Записать в ячейку новое новое положение Figure figure.clone(Cell dist)
        chesspieces[currentChesspieceIndex] = chesspieces[currentChesspieceIndex].clone(destination);
    }
}
