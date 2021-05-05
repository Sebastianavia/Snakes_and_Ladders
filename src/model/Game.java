package model;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private int movement;

	public final static String SPLIT = " ";

	Scanner scanner = new Scanner(System.in);
	private int positionA;
	private int positionB;
	private int positionALadder;
	private int positionBLadder;
	public final static String snakes = "ABCDEFGHIJKLMÑOPQRSTW";
	public final static String laders = "0123456789";
	private Board lm;

	public Game() {
		positionA = 0;
		positionB = 1;
		positionALadder =0;
		positionBLadder = 1;
	}

	public String printBoard(int col, int row) {
		lm = new Board(row, col);
		String out = lm.prePrint();
		return out;
	}
	public String printB() {
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

	public void snakerPosition(int colum, int rows, int numSnackers,int rand,int value) {
		System.out.println(numSnackers);
		if (numSnackers > 0) {
			
			//int numCol = (int) Math.floor(Math.random() * (1 - colum) + colum);
			//int numRow = (int) Math.floor(Math.random() * (1 - rows) + rows);
			int position = rand;
			int total =colum * rows;
			if (position > 1 && position < total) {
			
				String let = snakes.substring(positionA, positionB);
				if (lm.positionSnake(position, let) == false) {
					
					
					rand =  (int) (Math.random() * value) + 2;
					snakerPosition(colum, rows, numSnackers,rand,value);
				} else {
					numSnackers--;
					positionA++;
					positionB++;
					// tomar el valor de getpos, luego restarlo con num colum 
					//()()()()()   restar una posicion de la multiplicacion de las columnas por el numero que sale de las veces que esta 	
					//()()()()()	columnas en el numero 
					//()()()()()
					//System.out.println("antes de asignar "+ position);
					
					//float value1 = (float) (9.0/2.0);
					//System.out.println(value1 + "asas ");
					//int ou = (int) Math.round(value1);
					//System.out.println("assa  "+ou + "division       as");
					//position= position+colum;
					//System.out.println("valor que envio " +position);
					int mul =1;
					int val =foundPostition(position, colum, mul);
					int positionMax = val*colum;
					int positionMin=0;
					if(val >1) {
						val = val-1;
						 positionMin = colum*val;
					}else {
						 positionMin=1;
					}
					//System.out.println("valor superior "+positionMax);
					//System.out.println("valor menor "+ positionMin);
					snakerPositionB(colum, rows, position, let,value,positionMax,positionMin);
					
					rand = (int) (Math.random() * value) + 2;
					snakerPosition(colum, rows, numSnackers,rand,value);
				}
			}else {
				
				rand = (int) (Math.random() * value) + 2;
				snakerPosition(colum, rows, numSnackers,rand,value);
			}
		}

	}
	public int foundPostition(int position,int col,int value) {
		int num= col*value;
	if(num<position) {
		value++;
		value= foundPostition(position, col, value);
	}
		return value;
	}

	public void snakerPositionB(int colum, int rows, int posA, String let,int value,int positionMax,int positionMin) {	
		int position = (int) (Math.random() * value) + 2;		
		int total =colum * rows;
		if (position > 1 && position < total) {
			//System.out.println(value + "valor");
			//System.out.println(position+"  posicion");
			
			if (position < positionMin  || position > positionMax ) {
				
				if (lm.positionSnake(position, let) == false) {
					snakerPositionB(colum, rows, posA, let,value,positionMax,positionMin);
				}
			} else {
				snakerPositionB(colum, rows, posA, let,value,positionMax,positionMin);
			}
		}else {
			snakerPositionB(colum, rows, posA, let,value,positionMax,positionMin);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void laderPosition(int colum, int rows, int numSnackers,int rand,int value) {
		System.out.println(numSnackers);
		if (numSnackers > 0) {
			
			//int numCol = (int) Math.floor(Math.random() * (1 - colum) + colum);
			//int numRow = (int) Math.floor(Math.random() * (1 - rows) + rows);
			int position = rand;
			int total =colum * rows;
			if (position > 1 && position < total) {
			
				String let = laders.substring(positionALadder, positionBLadder);
				if (lm.positionSnake(position, let) == false) {
					
					
					rand =  (int) (Math.random() * value) + 2;
					laderPosition(colum, rows, numSnackers,rand,value);
				} else {
					numSnackers--;
					positionALadder++;
					positionBLadder++;
					// tomar el valor de getpos, luego restarlo con num colum 
					//()()()()()   restar una posicion de la multiplicacion de las columnas por el numero que sale de las veces que esta 	
					//()()()()()	columnas en el numero 
					//()()()()()
					//System.out.println("antes de asignar "+ position);
					
					//float value1 = (float) (9.0/2.0);
					//System.out.println(value1 + "asas ");
					//int ou = (int) Math.round(value1);
					//System.out.println("assa  "+ou + "division       as");
					//position= position+colum;
					//System.out.println("valor que envio " +position);
					int mul =1;
					int val =foundPostition(position, colum, mul);
					int positionMax = val*colum;
					int positionMin=0;
					if(val >1) {
						val = val-1;
						 positionMin = colum*val;
					}else {
						 positionMin=1;
					}
					//System.out.println("valor superior "+positionMax);
					//System.out.println("valor menor "+ positionMin);
					laderPositionB(colum, rows, position, let,value,positionMax,positionMin);
					
					rand = (int) (Math.random() * value) + 2;
					laderPosition(colum, rows, numSnackers,rand,value);
				}
			}else {
				
				rand = (int) (Math.random() * value) + 2;
				laderPosition(colum, rows, numSnackers,rand,value);
			}
		}

	}

	public void laderPositionB(int colum, int rows, int posA, String let,int value,int positionMax,int positionMin) {	
		int position = (int) (Math.random() * value) + 2;		
		int total =colum * rows;
		if (position > 1 && position < total) {
			//System.out.println(value + "valor");
			//System.out.println(position+"  posicion");
			
			if (position < positionMin  || position > positionMax ) {
				
				if (lm.positionSnake(position, let) == false) {
					laderPositionB(colum, rows, posA, let,value,positionMax,positionMin);
				}
			} else {
				laderPositionB(colum, rows, posA, let,value,positionMax,positionMin);
			}
		}else {
			laderPositionB(colum, rows, posA, let,value,positionMax,positionMin);
		}

	}

}
