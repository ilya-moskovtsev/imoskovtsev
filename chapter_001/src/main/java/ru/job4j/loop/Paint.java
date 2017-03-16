package ru.job4j.loop;
/**
* Построение пирамиды в псевдографике.
* @author imoskovtsev
*/
public class Paint {
	/**
  	* StringBuilder sb.
  	*/
	private final StringBuilder sb;
	/**
	* Конструктор.
	* Инициализирует StringBuilder sb.
	*/
	public Paint() {
		this.sb = new StringBuilder();
	}
	/**
	* Рисует пирамиду из символа ^ и пробелов.
	* @param h - высота пирамиды.
	* @return пирамида из символа ^ и пробелов.
	*/
	public String piramid(int h) {
		//высота пирамиды
		for (int i = 0; i < h; i++) {
			//пробелы слева от пирамиды
			sideSpaces(h, i);
			//тело пирамиды
			piramidBody(i);
			//пробелы справа от пирамиды
			sideSpaces(h, i);
			//перенос строки
			lineSeparator(h, i);
		}
		return sb.toString();
	}
	/**
	* Вычисляет ширину пирамиды по высоте.
	* @param h - высота пирамиды.
	* @return ширина пирамиды.
	*/
	private int width(int h) {
		int width = 1;
		for (int i = 1; i < h; i++) {
			width += 2;
		}
		return width;
	}
	/**
	* Добавляет пробелы к пирамиде.
	* @param h - высота пирамиды.
	* @param i - текущий уровень.
	*/
	private void sideSpaces(int h, int i) {
		int sideSpaces = (width(h - i) - 1) / 2;
		for (int k = 0; k < sideSpaces; k++) {
			sb.append(' ');
		}
	}
	/**
	* Добавляет тело пирамиды.
	* @param i - текущий уровень.
	*/
	private void piramidBody(int i) {
		if (i == 0) {
			//вершина пирамиды
			sb.append('^');
		} else {
			//остальная часть пирамиды
			for (int k = 0; k < width(i + 1); k++) {
				sb.append('^');
			}
		}
	}
	/**
	* Переносит строку.
	* @param h - высота пирамиды.
	* @param i - текущий уровень.
	*/
	private void lineSeparator(int h, int i) {
		//не добавляем line.separator в конце нижнего уровня
		if (i < h - 1) {
			sb.append(System.getProperty("line.separator"));
		}
	}
}