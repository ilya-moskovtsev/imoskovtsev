package ru.job4j.array;

/**
 * Тестовое задание.
 * Создать программу проверяющую, что строка sub является подстрокой origin.
 * boolean contains(String origin, String sub).
 * Нельзя использовать String.indexOf String.contains.
 * Нужно преобразовать строку в массив символов и проверить.
 *
 * @author imoskovtsev
 */
public class SubString {
    /**
     * Метод проверяет, что строка sub является подстрокой origin.
     *
     * @param origin - строка.
     * @param sub    - подстрока.
     * @return boolean - sub является подстрокой origin.
     */
    public boolean contains(String origin, String sub) {
        char[] originChars = origin.toCharArray();
        char[] subChars = sub.toCharArray();
        boolean isSubstring = false;
        for (int i = 0; i < originChars.length; i++) {
            if (originChars[i] == subChars[0] && originChars.length - i + 1 > subChars.length) {
                int count = 0;
                for (int j = 0; j < subChars.length; j++) {
                    if (originChars[i + j] == subChars[j]) {
                        count++;
                    }
                }
                if (count == subChars.length) {
                    isSubstring = true;
                    break;
                }
            }
        }
        return isSubstring;
    }
}