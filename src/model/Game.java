package model;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public final static String SPLIT = " ";

	Scanner scanner = new Scanner(System.in);
	private int positionA;
	private int positionB;
	private Players players;
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

	public String printBoard(int col, int row, String players) {
		lm = new Board(row, col, players);
		String out = lm.prePrint();
		return out;
	}

	public String printB() {
		String out = lm.prePrint();
		return out;
	}

	public String printValue() {
		String out = lm.prePrint2();
		return out;
	}

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

	public int rollDice() {
		Random rand = new Random();
		int num = rand.nextInt((6 - 1) + 1) + 1;

		return num;
	}

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

	public int foundPostition(int position, int col, int value) {
		int num = col * value;
		if (num < position) {
			value++;
			value = foundPostition(position, col, value);
		}
		return value;
	}

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

	public void addPlayer(String name, String symbol, int score) {
		Players player = new Players(name, symbol, score);
		if (players == null) {

			players = player;
		} else {
			addPlayer(players, player);
		}

	}

	private void addPlayer(Players player1, Players newPlayer) {

		if (newPlayer.getScore() >= player1.getScore()) {
			if (player1.getLeft() == null) {

				player1.setLeft(newPlayer);
			} else {
				addPlayer(player1, newPlayer);
			}

		} else {
			if (player1.getRight() == null) {

				player1.setRight(newPlayer);
			} else {
				addPlayer(player1.getRight(), newPlayer);
			}
		}
	}

	public String printOrder() {
		String out = "";

		return inOrden(players, out);
	}

	public String inOrden(Players player1, String out) {
		if (player1 != null) {

			inOrden(player1.getLeft(), out);
			System.out.println(player1.data());
			inOrden(player1.getRight(), out);

		}
		return out;
	}
}
