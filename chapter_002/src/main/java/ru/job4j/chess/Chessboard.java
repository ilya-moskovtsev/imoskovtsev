package ru.job4j.chess;

import java.util.Arrays;

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

        ChessPiece currentChessPiece = Arrays.stream(chessPieces)
                .filter(chessPiece -> source.getLetter() == chessPiece.getCurrentPosition().getLetter() && source.getNumber() == chessPiece.getCurrentPosition().getNumber())
                .findFirst().orElseThrow(FigureNotFoundException::new);

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
        chessPieces[Arrays.asList(chessPieces).indexOf(currentChessPiece)] = currentChessPiece.clone(destination);
    }
}
