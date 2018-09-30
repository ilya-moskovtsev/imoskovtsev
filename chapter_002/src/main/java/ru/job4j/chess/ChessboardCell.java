package ru.job4j.chess;

/**
 * Описывает ячейки шахматной доски.
 *
 * @author imoskovtsev
 */
public class ChessboardCell {
    /**
     * Буква.
     */
    private int letter;
    /**
     * Цифра.
     */
    private int number;

    /**
     * get letter.
     *
     * @return letter
     */
    public int getLetter() {
        return letter;
    }

    /**
     * get number.
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Конструктор.
     *
     * @param letter буква
     * @param number цифра
     * @throws ImpossibleMoveException Координата за пределами шахматной доски
     */
    public ChessboardCell(int letter, int number) throws ImpossibleMoveException {
        if (number > 7 || letter > 7) {
            throw new ImpossibleMoveException("Координата за пределами шахматной доски");
        }
        if (number < 0 || letter < 0) {
            throw new ImpossibleMoveException("Координата за пределами шахматной доски");
        }
        this.letter = letter;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(getLetterByInt(letter))
                + getNumberByInt(number);
    }

    private char getLetterByInt(int i) {
        switch (i) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            default:
                return '?';
        }
    }

    private char getNumberByInt(int i) {
        switch (i) {
            case 0:
                return '1';
            case 1:
                return '2';
            case 2:
                return '3';
            case 3:
                return '4';
            case 4:
                return '5';
            case 5:
                return '6';
            case 6:
                return '7';
            case 7:
                return '8';
            default:
                return '?';
        }
    }
}
