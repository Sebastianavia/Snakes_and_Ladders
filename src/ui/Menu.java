package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.Game;
import model.Players;

public class Menu {
	private final static String SAVE_PLAYER = "src/data/player.txt";
	public final static String SPLIT = " ";
	public final static String opcionPlayers = ",.-{}´+'¿=!#$%&/°|";
	private Scanner scan = new Scanner(System.in);
	private int positionA;
	private int positionB;
	private Game game;
	private int movement;
	private Players playerN;
	
	public Menu() throws FileNotFoundException, ClassNotFoundException, IOException {
		loadData();
		game = new Game();
		movement = 0;
		positionA = 0;
		positionB = 1;
	}

	/**
	 * prints on screen the options to use the application, choose an option and
	 * activate the operation of each of the functions <br>
	 * <b> pre: we need the user to choose what action to perform </b>
	 * 
	 * @param num menu option
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public void menu(String num) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {

		if (num.equals("2") || num.equals("1")) {
			System.out.println("********BIENVENIDO A SNAKE AND LADDERS********\n"
					+ "********* Para iniciar selecione una opcion *********\n" + "** 1- Iniciar juego\n"
					+ "** 2- Mostrar tablero de posiciones\n" + "** 3- Salir del juego");
			System.out.println("");
			positionA = 0;
			positionB = 1;
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
				if(isNumeric(num4)) {
					
					int numPlayers = Integer.parseInt(num4);
					String out = "";
					if(numPlayers != 0) {
					num4 =players(out,numPlayers );
					players = numPlayers;
					}else {
						System.out.println("Tomaremos el signo del jugador como 0");
					}
				}
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
				
				getScore();
			}
			menu(num);
		} else {
			if (num.equals("3")) {
				System.out.println("fINALIZO EL JUEGO");

			}
		}
	}
	public String players(String out, int numP) {
		if(numP >= 1) {
			out+=opcionPlayers.substring(positionA, positionB);
			numP--;
			positionA++;
			positionB++;
			out=players(out, numP);
		}
		return out;
	}
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){		
			return false;
		}
	}

	// _________________________________________________________________________

	/**
	 * the game starts <br>
	 * <b> pre: all data </b>
	 * 
	 * @param columns column number
	 * @param rows    row number
	 * @param snakes  snake number
	 * @param ladder  ladder number
	 * @param num4
	 * @param players players number
	 * @param ini
	 * @param ch
	 * @param win     the player who won the game
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void m(int columns, int rows, int snakes, int ladder, String num4, int players, int ini, char ch,
			boolean win) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {
		String player = "";
		if (ini == 0) {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			System.out.println("Enter para tirar dados");// giving the user a chance to roll
			String read = scan.nextLine();// waiting for enter key
			if (read.equalsIgnoreCase("menu")) {
				menu("1");
			} else {
				if (read.equalsIgnoreCase("simul")) {
					menuAuto(columns, rows, snakes, ladder, num4, players, ini, ch, win);
				} else {
					if (read.equalsIgnoreCase("num")) {
						System.out.println(game.printB());
						scan.nextLine();
					}

					int numMoves = game.rollDice();
					System.out.println(numMoves + "<= resultado dado");
					// game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players,
					// ini, ch), numMoves);
					movement++;
					if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
							numMoves) == true) {
						System.out.println("****  GANASTE  ****");
						win = true;
						System.out.println(game.printValue());
					} else {
						System.out.println(game.printValue());
						if (players > 1) {
							ini++;
						}
					}
				}
			}
		} else {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			System.out.println("Enter para tirar dados");// giving the user a chance to roll
			String read = scan.nextLine();// waiting for enter key
			if (read.equalsIgnoreCase("menu")) {
				menu("1");
			} else {
				if (read.equalsIgnoreCase("simul")) {
					menuAuto(columns, rows, snakes, ladder, num4, players, ini, ch, win);
				} else {
					if (read.equalsIgnoreCase("num")) {
						System.out.println(game.printB());
						scan.nextLine();
					}
					int numMoves = game.rollDice();
					System.out.println(numMoves + "<= resultado dado");
					if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
							numMoves) == true) {

						win = true;
						System.out.println("****  GANASTE  ****");
						System.out.println(game.printValue());
					} else {
						System.out.println(game.printValue());
						ini++;
						if (ini == players) {
							ini = 0;

						}

					}
				}
			}
		}
		if (win == false) {
			
			m(columns, rows, snakes, ladder, num4, players, ini, ch, win);
		} else {
			registerScore(player,columns,rows,num4);
		}
	}

	
	// ______________________________________________________________________________________________

	/**
	 * so that it runs through it automatically <br>
	 * <b> pre: you need the indicated command </b>
	 * 
	 * @param columns column number
	 * @param rows    row number
	 * @param snakes  snake number
	 * @param ladder  ladder number
	 * @param num4
	 * @param players players number
	 * @param ini
	 * @param ch
	 * @param win     the player who won the game
	 * @throws InterruptedException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	// para q lo recorra automaticamente
	private void menuAuto(int columns, int rows, int snakes, int ladder, String num4, int players, int ini, char ch,
			boolean win) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {
		String player = "";
		if (ini == 0) {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(
					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));
			TimeUnit.SECONDS.sleep(2);
			int numMoves = game.rollDice();
			System.out.println(numMoves + "<= resultado dado");
			// game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players,
			// ini, ch), numMoves);
			movement++;
			if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
					numMoves) == true) {
				System.out.println("***** GANASTE *****");
				win = true;
				System.out.println(game.printValue());
			} else {
				System.out.println(game.printValue());
				if (players > 1) {
					ini++;
				}
			}
		} else {
			player = game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch);
			System.out.println(

					game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch) + "jugador N°" + (ini + 1));

			TimeUnit.SECONDS.sleep(2);
			int numMoves = game.rollDice();
			System.out.println(numMoves + "<= resultado dado");
			if (game.movePlayer(game.playSnake(columns, rows, snakes, ladder, num4, players, ini, ch),
					numMoves) == true) {

				win = true;
				System.out.println("***** GANASTE *****");
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
			
			menuAuto(columns, rows, snakes, ladder, num4, players, ini, ch, win);
		} else {		
			registerScore(player,columns,rows,num4);
			menu("1");
			
		}
	}

	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(SAVE_PLAYER));
		ob.writeObject(playerN);
		ob.close();
	}

	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(SAVE_PLAYER);
		if (f.exists()) {
			ObjectInputStream ob = new ObjectInputStream(new FileInputStream(f));
			playerN = (Players) ob.readObject();
			ob.close();
		}
	}
	// _______________________________________________________________________________________
	
	/**
	 * record averages <br>
	 * <b> pre: player averages </b>
	 * @param symbol
	 * @param column
	 * @param rows
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void registerScore(String symbol,int column,int rows,String characters) throws FileNotFoundException, IOException {
		System.out.println("Digite el Nombre del jugador");
		String name = scan.nextLine();
		int mult = column * rows;
		int out = mult*movement;
		
		movement = 0;
		Players player1 = new Players(name,symbol,out,characters);
		if (playerN == null) {
			playerN = player1;
		} else {
			registerScore( playerN,player1);
		}
		saveData();
	}
	
	
	
	/**
	 * store them in the nodes </b>
	 * <b> pre: each player's scores </b>
	 * @param player1
	 * @param newPlayer
	 */
	private void registerScore(Players player1, Players newPlayer) {

		if (newPlayer.getScore() >= player1.getScore()) {
			if (player1.getLeft() == null) {

				player1.setLeft(newPlayer);
			} else {
				registerScore(player1.getLeft(), newPlayer);
			}

		} else {
			if (player1.getRight() == null) {

				player1.setRight(newPlayer);
			} else {
				registerScore(player1.getRight(), newPlayer);
			}
		}
	}
	public void getScore() {
		getScore(playerN);
	}
	
	
	/**
	 * get the scores </b>
	 * <b> pre: that the players end the game </b>
	 * @param current
	 */
	private void getScore(Players current) {
		
		if(current != null) {
			
			getScore(current.getLeft());
			
			System.out.println(current.data());
			
			getScore(current.getRight());
		}
	}
}
