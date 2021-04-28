package ui;

public class Main {
	private Menu menu;
	public Main() {
		menu = new Menu();
		
	}
	public static void main(String args[]) {
		
		Main m=new Main();
		m.menu();
		
	}
	private void menu() {
		menu.menu("1");
		
	}

	
	
}
