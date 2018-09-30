package ru.job4j.chess;

/**
 * Шахматная доска.
 *
 * @author imoskovtsev
 */
public class Chessboard {
    /**
     * Шахматные фигуры.
     */
    private final ChessPiece[] chessPieces;

    /**
     * get chessPieces.
     *
     * @return chessPieces
     */
    public ChessPiece[] getChessPieces() {
        return chessPieces;
    }

    /**
     * Конструктор.
     *
     * @param chessPieces фигуры
     */
    public Chessboard(ChessPiece[] chessPieces) {
        this.chessPieces = chessPieces;
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
    public void move(ChessboardCell source, ChessboardCell destination) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        ChessPiece currentChessPiece = null;
        int currentChessPieceIndex = -1;
        boolean isFound = false;
        for (int i = 0; i < chessPieces.length; i++) {
            if (source.getLetter() == chessPieces[i].getCurrentPosition().getLetter() && source.getNumber() == chessPieces[i].getCurrentPosition().getNumber()) {
                isFound = true;
                currentChessPiece = chessPieces[i];
                currentChessPieceIndex = i;
            }
        }
        if (!isFound) {
            throw new FigureNotFoundException("В начальной ячейке нет фигуры.");
        }

        final ChessboardCell[] way = currentChessPiece.way(destination);

        boolean isWayOccupied = false;
        for (ChessPiece chesspiece : chessPieces) {
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
        chessPieces[currentChessPieceIndex] = chessPieces[currentChessPieceIndex].clone(destination);
    }
}
