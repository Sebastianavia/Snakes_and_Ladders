package model;

public class Node {
	private String players;
	private int row;
	private int col;
	private String lader;
	private String snake;
	private int pos;
	private Node next;
	private Node prev;
	private Node up;
	private Node down;

	public Node(int po) {
		pos = po;
		lader = " ";
		snake = " ";
		players = "";
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public char getNameCol() {
		return (char) ('A' + col);
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node n) {
		next = n;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node p) {
		prev = p;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node u) {
		up = u;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node d) {
		down = d;
	}

	public String toString() {
		return "[" + pos + snake+lader + "\t]";
	}

	public String toString2() {
		return "[" + players + snake+lader + "\t]";
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getSnake() {
		return snake;
	}

	public void setSnake(String snakeColum) {
		this.snake = snakeColum;
	}
	public String getLader() {
		return lader;
	}

	public void setLader(String lader) {
		this.lader = lader;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}
}
