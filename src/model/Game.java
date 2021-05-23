package model;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public final static String SPLIT = " ";

	Scanner scanner = new Scanner(System.in);
	private int positionA;
	private int positionB;
	private int positionALadder;
	private int positionBLadder;
	public final static String laders = "ABCDEFGHIJKLMÑOPQRSTW";
	public final static String snakes = "123456789";
	private Board lm;

	public Game() {
		positionA = 0;
		positionB = 1;
		positionALadder = 0;
		positionBLadder = 1;
	}

	/**
	 * just the board <br>
	 * <b> pre: the number of rows and columns </b>
	 * @param col
	 * @param row
	 * @param players
	 * @return
	 */
	public String printBoard(int col, int row, String players) {
		lm = new Board(row, col, players);
		String out = lm.prePrint();
		return out;
	}
	
	/**
	 * full board <br>
	 * <b> pre: all data </b>
	 * @return
	 */

	public String printB() {
		String out = lm.prePrint();
		return out;
	}
	/**
	 * // tablero con los valores  <br>
	 * <b> pre: all data </b>
	 * @return
	 */
	
	public String printValue() {
		String out = lm.prePrint2();
		return out;
	}
	
	/**
	 * Start <br>
	 * @param columns
	 * @param rows
	 * @param snakes
	 * @param ladder
	 * @param num4
	 * @param players
	 * @param ini
	 * @param ch
	 * @return out returns the string of characters, the symbol
	 */
	public String playSnake(int columns, int rows, int snakes, int ladder, String num4, int players, int ini, char ch) {
		String out = "";
		if (ini == 0) {
			ch = num4.charAt(num4.length() - players);
			out = (ch + "");
			ini++;
		} else {
			int value = players - ini;
			ch = num4.charAt(num4.length() - value);
			out = (ch + "");
			ini++;

			if (ini == players) {
				ini = 0;

			}
		}

		return out;
	}
	 
	/**
	 * generate a random number from 1 to 6 <br>
	 * <b> pre: you need the library and the method to automatically generate </b>
	 * @return num returns the number of the dice randomly
	 */
	public int rollDice() {
		Random rand = new Random();
		int num = rand.nextInt((6 - 1) + 1) + 1;

		return num;
	}
	
	/**
	 * print the board <br>
	 * <b> pre: rows and columns are needed  </b>
	 * @param rows
	 * @param columns
	 * @param rows1
	 * @param colums1
	 * @param cuadros
	 * @param out
	 * @param count
	 * @param out2
	 */
	
	public void printBoard(int rows, int columns, int rows1, int colums1, int cuadros, String out, int count,
			String out2) {
		if (cuadros >= 1) {
			if (rows >= rows1) {

				if (columns >= colums1) {
					colums1++;
					out = " " + count + " ";
					out2 = out2 + out;

				} else {

					rows1++;
					colums1 = 2;
				}
			}
			count++;
			cuadros--;
			printBoard(rows, columns, rows1, colums1, cuadros, out, count, out2);
		} else if (cuadros == 0) {

		}

	}

	public Board getLm() {
		return lm;
	}
	
	
	
	/**
	 * the other part of the serpent's position <br>
	 * <b> pre: empty boxes where they can be contained </b> 
	 * @param colum
	 * @param rows
	 * @param numSnackers
	 * @param rand
	 * @param value
	 */
	public void snakerPosition(int colum, int rows, int numSnackers, int rand, int value) {
		if (numSnackers > 0) {

			int position = rand;
			int total = colum * rows;
			if (position > 1 && position < total) {

				String let = snakes.substring(positionA, positionB);
				if (lm.positionSnake(position, let) == false) {

					rand = (int) (Math.random() * value) + 2;
					snakerPosition(colum, rows, numSnackers, rand, value);
				} else {
					numSnackers--;
					positionA++;
					positionB++;

					int mul = 1;
					int val = foundPostition(position, colum, mul);
					int positionMax = val * colum;
					int positionMin = 0;
					if (val > 1) {
						val = val - 1;
						positionMin = colum * val;
					} else {
						positionMin = 1;
					}

					snakerPositionB(colum, rows, position, let, value, positionMax, positionMin);

					rand = (int) (Math.random() * value) + 2;
					snakerPosition(colum, rows, numSnackers, rand, value);
				}
			} else {

				rand = (int) (Math.random() * value) + 2;
				snakerPosition(colum, rows, numSnackers, rand, value);
			}
		}

	}
	/**
	 * find the available position for a ladder or snake <br>
	 * <b> pre:  </b>
	 * @param position
	 * @param col
	 * @param value
	 * @return
	 */
	public int foundPostition(int position, int col, int value) {
		int num = col * value;
		if (num < position) {
			value++;
			value = foundPostition(position, col, value);
		}
		return value;
	}
	/**
	 * snake position<br>
	 * <b> pre:  </b>
	 * @param colum
	 * @param rows
	 * @param posA
	 * @param let
	 * @param value
	 * @param positionMax
	 * @param positionMin
	 */
	
	public void snakerPositionB(int colum, int rows, int posA, String let, int value, int positionMax,
			int positionMin) {
		int position = (int) (Math.random() * value) + 2;
		int total = colum * rows;
		if (position > 1 && position < total) {
			if (position < positionMin || position > positionMax) {

				if (lm.positionSnake(position, let) == false) {
					snakerPositionB(colum, rows, posA, let, value, positionMax, positionMin);
				}
			} else {
				snakerPositionB(colum, rows, posA, let, value, positionMax, positionMin);
			}
		} else {
			snakerPositionB(colum, rows, posA, let, value, positionMax, positionMin);
		}

	}

	
	
		/**
		 * is the position of the ladders <br>
		 * <b> pre: the board must be established </b>
		 * @param colum
		 * @param rows
		 * @param numSnackers
		 * @param rand
		 * @param value
		 */
	public void laderPosition(int colum, int rows, int numSnackers, int rand, int value) {
		if (numSnackers > 0) {

			int position = rand;
			int total = colum * rows;
			if (position > 1 && position < total) {

				String let = laders.substring(positionALadder, positionBLadder);
				if (lm.positionLader(position, let) == false) {

					rand = (int) (Math.random() * value) + 2;
					laderPosition(colum, rows, numSnackers, rand, value);
				} else {
					numSnackers--;
					positionALadder++;
					positionBLadder++;
					int mul = 1;
					int val = foundPostition(position, colum, mul);
					int positionMax = val * colum;
					int positionMin = 0;
					if (val > 1) {
						val = val - 1;
						positionMin = colum * val;
					} else {
						positionMin = 1;
					}

					laderPositionB(colum, rows, position, let, value, positionMax, positionMin);

					rand = (int) (Math.random() * value) + 2;
					laderPosition(colum, rows, numSnackers, rand, value);
				}
			} else {

				rand = (int) (Math.random() * value) + 2;
				laderPosition(colum, rows, numSnackers, rand, value);
			}
		}

	}

	/**
	 * is the position of the ladders <br>
	 * <b> pre: the board must be established </b>
	 * @param colum
	 * @param rows
	 * @param posA
	 * @param let
	 * @param value
	 * @param positionMax
	 * @param positionMin
	 */
	
	public void laderPositionB(int colum, int rows, int posA, String let, int value, int positionMax, int positionMin) {
		int position = (int) (Math.random() * value) + 2;
		int total = colum * rows;
		if (position > 1 && position < total) {

			if (position < positionMin || position > positionMax) {

				if (lm.positionLader(position, let) == false) {
					laderPositionB(colum, rows, posA, let, value, positionMax, positionMin);
				}
			} else {
				laderPositionB(colum, rows, posA, let, value, positionMax, positionMin);
			}
		} else {
			laderPositionB(colum, rows, posA, let, value, positionMax, positionMin);
		}
		
		
	
	}

	public boolean movePlayer(String symbol, int position) {
		return lm.foundPlayer(symbol, position);

	}
	
	
}
