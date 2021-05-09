package ui;

import java.util.Scanner;

import model.Game;

public class Menu {
	public final static String SPLIT = " ";
	private Scanner scan = new Scanner(System.in);
	private Game game;
	private int movement;

	public Menu() {
		game = new Game();
		movement = 0;
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
				int value = 0;
				if (snakes == 0) {
					value = ladder * 2;
				} else if (ladder == 0) {
					value = snakes * 2;
				} else {
					value = (snakes * 2) + (ladder * 2);
				}
				int valueTo = columns * rows;

				if (valueTo - 4 >= value) {

					game.printBoard(columns, rows, num4);

					int n = (int) (Math.random() * value) + 2;
					game.snakerPosition(columns, rows, snakes, n, valueTo);
					game.laderPosition(columns, rows, ladder, n, valueTo);
					System.out.println(game.printB());
					scan.nextLine();
					System.out.println(game.printValue());
					boolean win = false;
					m(columns, rows, snakes, ladder, num4, players, 0, 'a', win);

				} else {
					System.out.println(
							"\nEl numero de serpientes y escalera es superior al numero de celdas o causa conflicto\n");
					menu(num);
				}

			}
			if (num.equals("2")) {
				printThree();
			}
			menu(num);
		} else {
			if (num.equals("3")) {
				System.out.println("Finaliz� el programa");

			}
		}
	}

	private void m(int columns, int rows, int snakes, int ladder, String num4, int players, int ini, char ch,
			boolean win) {
		String player = "";
		if (ini == 0) {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			System.out.println("Enter para tirar dados");// giving the user a chance to roll
			scan.nextLine();// waiting for enter key
			int numMoves = game.rollDice();
			System.out.println(numMoves + "<= resultado dado");
			// game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players,
			// ini, ch), numMoves);
			movement++;
			if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
					numMoves) == true) {
				System.out.println("gano");
				win = true;
				System.out.println(game.printValue());
			} else {
				System.out.println(game.printValue());
				ini++;
			}
		} else {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(

					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));

			System.out.println("Enter para tirar dados");// giving the user a chance to roll
			scan.nextLine();// waiting for enter key
			int numMoves = game.rollDice();
			System.out.println(numMoves + "<= resultado dado");
			if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
					numMoves) == true) {
				System.out.println("faaa");
				win = true;
				System.out.println("gano");
				System.out.println(game.printValue());
			} else {
				System.out.println(game.printValue());
				ini++;
				if (ini == players) {
					ini = 0;

				}
			}
		}
		if (win == false) {
			m(columns, rows, snakes, ladder, num4, players, ini, ch, win);
		} else {
			registerPlaye(player);
		}
	}

	public void registerPlaye(String player) {
		System.out.println("Digite el Nombre del jugador");
		String name = scan.nextLine();
		game.addPlayer(name, player, movement);
	}

	public void printThree() {
		System.out.println("hol");
		System.out.println(game.printOrder());
	}

}
