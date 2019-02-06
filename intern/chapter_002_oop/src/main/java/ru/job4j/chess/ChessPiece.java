package ru.job4j.chess;

/**
 * Тестовое задание. [#23963]<br>
 * 1. реализовать шахматную доску.<br>
 * 2. должна быть возможность двигать фигуру.<br>
 * 3. система должна проверять корректность движения фигуры.<br>
 * 4. каждая фигура должна двигаться в соответствии со своей логикой.<br>
 * 5. нельзя ставить на занятые поля.<br>
 * 6. нельзя перемещать фигуру, если на пути стоит другая фигура. кроме коня.<br>
 * 7. в этом задании не надо делать меню и пользователей. меня интересует только логика. а не ввод данных.<br>
 * 8. все выше описанные поведения должны быть проверены через Junit tests.<br>
 * <br>
 * Общая схема реализации. Вы можете добавлять свои методы.<br>
 * 1. Прочитать про шаблон проектирования стратегия.<br>
 * 2. Создать абстрактный класс Figure<br>
 * 2. В Figure сделать поля final Cell position - и конструктор. В классе не должно быть методов set get<br>
 * 3. Добавить метод Cell[] way(Cell dist) throws ImpossibleMoveException<br>
 * Метод должен работать так. dist - задают ячейку куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.<br>
 * Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException<br>
 * 4. Cell - описывает ячейки шахматной доски<br>
 * 5. Создать класс Board.<br>
 * 6. В классе создать массив Figure[] figures<br>
 * 7. Добавить метод boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException<br>
 * 8. Метод должен должен проверить<br>
 * - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение<br>
 * - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение<br>
 * - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение<br>
 * - Если все отлично. Записать в ячейку новое новое положение Figure figure.clone(Cell dist)<br>
 * 9. Изначально сделайте. только движения фигуры слон и покажите промежуточный результат.
 *
 * @author imoskovtsev
 */
public abstract class ChessPiece {

    /**
     * Сообщение исключения.
     */
    public static final String IMPOSSIBLE_MOVE = "Фигура не может туда пойти.";

    /**
     * Положение фигуры на доске.
     */
    private final ChessboardCell currentPosition;

    /**
     * get currentPosition.
     *
     * @return currentPosition
     */
    public ChessboardCell getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Конструктор.
     *
     * @param currentPosition клетка фигуры на доске
     */
    public ChessPiece(ChessboardCell currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Copy constructor.
     *
     * @param another фигура на доске
     */
    public ChessPiece(ChessPiece another) {
        this(another.currentPosition);
    }

    /**
     * Путь.
     *
     * @param destination задает ячейку куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    public abstract ChessboardCell[] way(ChessboardCell destination) throws ImpossibleMoveException;

    /**
     * Клонировать фигуру.
     *
     * @param destination новое положение фигуры
     * @return ChessPiece
     */
    public abstract ChessPiece clone(ChessboardCell destination);

    /**
     * Diagonal way.
     *
     * @param destination ячейка куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    public ChessboardCell[] diagonalWayTo(ChessboardCell destination) throws ImpossibleMoveException {
        ChessboardCell[] way = new ChessboardCell[verticalStepsTo(destination)];
        for (int i = 1; i <= verticalStepsTo(destination); i++) {
            way[i - 1] = new ChessboardCell(
                    getCurrentPosition().getLetter() + i * horizontalDirectionTo(destination),
                    getCurrentPosition().getNumber() + i * verticalDirectionTo(destination));
        }
        return way;
    }

    /**
     * Horizontal way.
     *
     * @param destination ячейка куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    public ChessboardCell[] horizontalWayTo(ChessboardCell destination) throws ImpossibleMoveException {
        ChessboardCell[] way = new ChessboardCell[horizontalStepsTo(destination)];
        for (int i = 1; i <= horizontalStepsTo(destination); i++) {
            way[i - 1] = new ChessboardCell(
                    getCurrentPosition().getLetter() + i * horizontalDirectionTo(destination),
                    getCurrentPosition().getNumber());
        }
        return way;
    }

    /**
     * Vertical way.
     *
     * @param destination ячейка куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    public ChessboardCell[] verticalWayTo(ChessboardCell destination) throws ImpossibleMoveException {
        ChessboardCell[] way = new ChessboardCell[verticalStepsTo(destination)];
        for (int i = 1; i <= verticalStepsTo(destination); i++) {
            way[i - 1] = new ChessboardCell(
                    getCurrentPosition().getLetter(),
                    getCurrentPosition().getNumber() + i * verticalDirectionTo(destination));
        }
        return way;
    }

    /**
     * Vertical direction to destination.
     *
     * @param destination ячейка куда следует пойти
     * @return int vertical direction to destination
     */
    public int verticalDirectionTo(ChessboardCell destination) {
        return destination.getNumber() - getCurrentPosition().getNumber() > 0 ? 1 : -1;
    }

    /**
     * Horizontal direction to destination.
     *
     * @param destination ячейка куда следует пойти
     * @return int horizontal direction to destination
     */
    public int horizontalDirectionTo(ChessboardCell destination) {
        return destination.getLetter() - getCurrentPosition().getLetter() > 0 ? 1 : -1;
    }

    /**
     * Vertical steps to destination.
     *
     * @param destination ячейка куда следует пойти
     * @return int number of vertical steps to destination
     */
    public int verticalStepsTo(ChessboardCell destination) {
        return Math.abs(destination.getNumber() - getCurrentPosition().getNumber());
    }

    /**
     * Horizontal steps to destination.
     *
     * @param destination ячейка куда следует пойти
     * @return int number of horizontal steps to destination
     */
    public int horizontalStepsTo(ChessboardCell destination) {
        return Math.abs(destination.getLetter() - getCurrentPosition().getLetter());
    }

    /**
     * Castle way.
     *
     * @param destination ячейка куда следует пойти
     * @return ChessboardCell[] Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
     */
    public ChessboardCell[] castleWay(ChessboardCell destination) throws ImpossibleMoveException {
        ChessboardCell[] way;
        if (verticalStepsTo(destination) == 0) {
            //horizontal movement
            way = horizontalWayTo(destination);
        } else if (horizontalStepsTo(destination) == 0) {
            //vertical movement
            way = verticalWayTo(destination);
        } else {
            throw new ImpossibleMoveException(IMPOSSIBLE_MOVE);
        }
        return way;
    }
}