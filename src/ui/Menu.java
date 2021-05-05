package ui;


import java.util.Scanner;

import model.Game;

public class Menu {
	public final static String SPLIT = " ";
	private Scanner scan = new Scanner(System.in);
	private Game game;
	
	public Menu() {
		game = new Game();
	}

	public void menu(String num) {
		
		if (num.equals("2") || num.equals("1")) {
			System.out.println("********BIENVENIDO A SNAKE AND LADDERS********\n"
					+ "********* Para iniciar selecione una opcion *********\n" + "** 1- Iniciar juego\n"
					+ "** 2- Mostrar tablero de posiciones\n" + "** 3- Salir del juego");
			System.out.println("");
			num = scan.nextLine();
			if (num.equals("1")) {
				System.out.println("Ingrese los valores separados por espacios");
				System.out.println("");
				String num4 = scan.nextLine();
				String[] parts = num4.split(SPLIT);
				int rows = Integer.parseInt(parts[0]);
				int columns = Integer.parseInt(parts[1]);
				int snakes = Integer.parseInt(parts[2]);
				int ladder = Integer.parseInt(parts[3]);
				num4 = parts[4];
				int players = num4.length();
				int value=0;
				if(snakes == 0) {
					 value =ladder*2;
				}else if(ladder == 0) {
				  value =snakes*2;
				}else {
					value = (snakes*2)+(ladder*2);
				}
				int valueTo =columns*rows;
				
				if(valueTo-4>=value) {
					
				 System.out.println( game.printBoard(columns, rows));
				
				
				int n =   (int) (Math.random() * value) + 2;
				 game.snakerPosition(columns,rows,snakes,n,valueTo);
				 game.laderPosition(columns,rows,ladder,n,valueTo);
				 System.out.println(game.printB() );
					m(columns, rows, snakes, ladder, num4, players, 0, 'a');
					System.out.println("Press enter to roll");// giving the user a chance to roll
					scan.nextLine();// waiting for enter key
					System.out.println(game.rollDice() + "<= resultado dado");
					// System.out.println(game.printBoard(rows,columns,1,1,(rows*columns), ""));
					// game.printBoard(rows,columns,1,1,(rows*columns), "",1,"");
				}else {
					System.out.println("\nEl numero de serpientes y escalera es superior al numero de celdas o causa conflicto\n");
					menu(num);
				}
				if (num.equals("2")) {

				}

				
				}
			
			menu(num);
		} else {
			if (num.equals("3")) {
				System.out.println(":v");

			}
		}
	}

	private void m(int columns, int rows, int snakes, int ladder, String num4, int players, int ini, char ch) {
		if (ini == 0) {
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			ini++;
		} else {
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			ini++;
			if (ini == players) {
				ini = 0;

			}
		}
		// m(columns,rows,snakes,ladder,num4,players,ini,ch);

	}
	
}
