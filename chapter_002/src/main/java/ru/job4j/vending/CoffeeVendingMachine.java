package ru.job4j.vending;

/**
 * Реализовать метод выдачи сдачи для автомата.[#35883]<br>
 * int[] changes(int value, int price)<br>
 * value = купюра. 50 100 и тд.<br>
 * price = цена кофе<br>
 * в автомате монеты номиналом 1 2 5 10<br>
 * Пример. Мы засунули 50 рублей выбрали кофе за 35. Сдачу автомат должен дать 15 рублей<br>
 * Алгоритм должен вернуть наименьшее количество монет.<br>
 * Метод вернет массив {10, 5} = 15 рублей<br>
 * создать задачу, залить в репозиторий, добавить ссылку<br>
 *
 * @author imoskovtsev
 */
public class CoffeeVendingMachine {

    /**
     * Метод выдачи сдачи.
     * Монеты номиналом 1 2 5 10.
     * Алгоритм должен вернуть наименьшее количество монет.
     *
     * @param value купюра
     * @param price цена кофе
     * @return массив {10, 5} = 15 рублей.
     */
    int[] giveChange(int value, int price) {
        // Номиналы монет.
        int one = 1;
        int two = 2;
        int five = 5;
        int ten = 10;
        // Кол-во монет.
        int ones = 0;
        int twos = 0;
        int fives = 0;
        int tens = 0;

        // Сдача.
        int change = value - price;

        // Монеты в сдаче.
        if (change % ten == 0) {
            tens = change / ten;
        } else {
            tens = change / ten;
            change %= ten;
            if (change % five == 0) {
                fives = change / five;
            } else {
                fives = change / five;
                change %= five;
                if (change % 2 == 0) {
                    twos = change / 2;
                } else {
                    twos = change / 2;
                    ones = change % two;
                }
            }
        }

        // Собираем массив с монетами.
        int numberOfCoins = ones + twos + fives + tens;
        if (numberOfCoins > 0) {
            int[] coins = new int[numberOfCoins];
            int coinCounter = 0;

            for (int i = 0; i < tens; i++) {
                coins[coinCounter] = ten;
                coinCounter++;
            }
            for (int i = 0; i < fives; i++) {
                coins[coinCounter] = five;
                coinCounter++;
            }
            for (int i = 0; i < twos; i++) {
                coins[coinCounter] = two;
                coinCounter++;
            }
            for (int i = 0; i < ones; i++) {
                coins[coinCounter] = one;
                coinCounter++;
            }

            return coins;
        } else {
            return new int[0];
        }
    }
}
