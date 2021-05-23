package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Gianni Benavides, Sebastian Navia
 *
 */
public class Main {
	private Menu menu;

	public Main() {
		menu = new Menu();

	}

	public static void main(String args[]) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {

		Main m = new Main();
		m.menu();

	}

	private void menu() throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException {

		menu.menu("1");

	}

}
