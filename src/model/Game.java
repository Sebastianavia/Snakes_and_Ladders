package model;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private int movement;

	public final static String SPLIT = " ";

	Scanner scanner = new Scanner(System.in);
	private int positionA;
	private int positionB;
	public final static String snakes = "ABCDEFGHIJKLMÑOPQRSTW";
	private Board lm;

	public Game() {
		positionA = 0;
		positionB = 1;
	}

	public String printBoard(int col, int row) {
		lm = new Board(row, col);
		String out = lm.prePrint();
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
				movement++;
			}
		}
		// playSnake(columns,rows,snakes,ladder,num4,players,ini,ch);
		return out;
	}

	public int rollDice() {
		Random rand = new Random();
		int num = rand.nextInt((6 - 1) + 1) + 1;

		return num;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	public void printBoard(int rows, int columns, int rows1, int colums1, int cuadros, String out, int count,
			String out2) {
		if (cuadros >= 1) {
			if (rows >= rows1) {

				if (columns >= colums1) {
					colums1++;
					out = " " + count + " ";
					out2 = out2 + out;
					// printBoard(rows,columns,rows1,colums1,cuadros,out);
				} else {
					// out2 = "\n "+count+" "+out2;
					rows1++;
					colums1 = 2;
				}
			}
			count++;
			cuadros--;
			printBoard(rows, columns, rows1, colums1, cuadros, out, count, out2);
		} else if (cuadros == 0) {
			System.out.println(out2);
		}

	}

	public Board getLm() {
		return lm;
	}

	public void snakerPosition(int colum, int rows, int numSnackers) {
		if (numSnackers > 0) {
			int numCol = (int) Math.floor(Math.random() * (0 - colum) + colum);
			int numRow = (int) Math.floor(Math.random() * (0 - rows) + rows);
			int position = numCol * numRow;
			if (position > 1 && position < colum * rows) {
				String let = snakes.substring(positionA, positionB);

				if (lm.positionSnake(position, let) == false) {
					snakerPosition(colum, rows, numSnackers);
				} else {
					numSnackers--;
					positionA++;
					positionB++;
					snakerPositionB(colum, rows, position, let);
				}
			}
		}

	}

	public void snakerPositionB(int colum, int rows, int posA, String let) {

		int numCol = (int) Math.floor(Math.random() * (0 - colum) + colum);
		int numRow = (int) Math.floor(Math.random() * (0 - rows) + rows);
		int position = numCol * numRow;
		if (position > 1 && position < colum * rows) {
			if (position < posA + colum || position > posA + colum) {
				if (lm.positionSnake(position, let) == false) {
					snakerPositionB(colum, rows, posA, let);
				}
			} else {
				snakerPositionB(colum, rows, posA, let);
			}
		}

	}

}
