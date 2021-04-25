package ui;

import java.util.Scanner;

import model.Game;

public class Menu {
	public final static String SPLIT = " ";
	private Scanner scan = new Scanner(System.in);
	private Game game;

	Menu() {
		game = new Game();
	}

	public void menu(String num) {
		if (num.equals("2") || num.equals("1")) {
			System.out.println("********BIENVENIDO A SNAKE AND LADDERS********\n"
					+ "********* Para iniciar selecione una opcion *********\n" + "** 1- Iniciar juego\n"
					+ "** 2- Mostrar tablero de posiciones\n" + "** 3- Salir del juego");
			num = scan.nextLine();
			if (num.equals("1")) {
				System.out.println("Ingrese los valores separados por");
				String num4 = scan.nextLine();
				String[] parts = num4.split(SPLIT);
				int columns = Integer.parseInt(parts[0]);
				int rows = Integer.parseInt(parts[1]);
				int snakes = Integer.parseInt(parts[2]);
				int ladder = Integer.parseInt(parts[3]);
				num4 = parts[4];
				int players = num4.length();

				m(columns, rows, snakes, ladder, num4, players, 0, 'a');
				System.out.println("Press enter to roll");// giving the user a chance to roll
				scan.nextLine();// waiting for enter key
				System.out.println(game.rollDice() + "<= resultado dado");
			}
			if (num.equals("2")) {

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
	System.out.println(game.playSnake(columns, rows, snakes, ladder, num4, players,	ini, ch) + "jugador N°" + (ini + 1));
			ini++;
		} else {
	System.out.println(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			ini++;
			if (ini == players) {
				ini = 0;

			}
		}
		m(columns,rows,snakes,ladder,num4,players,ini,ch);

	}
}
