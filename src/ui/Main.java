package ui;

public class Main {
	private Menu menu;

	public Main() {
		menu = new Menu();

	}

	public static void main(String args[]) throws InterruptedException {

		Main m = new Main();
		m.menu();

	}

	private void menu() throws InterruptedException {

		menu.menu("1");

	}

}
