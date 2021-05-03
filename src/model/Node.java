package model;

public class Node {
	private int row;
	private int col;
	private String snakeColum;
	private int pos;
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	public Node(int po) {
		pos = po;
		snakeColum = " ";
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public char getNameCol() {
		return (char)('A'+col);
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
		return "["+ pos +snakeColum+"\t]";
	}
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getSnakeColum() {
		return snakeColum;
	}
	public void setSnakeColum(String snakeColum) {
		this.snakeColum = snakeColum;
	}
}
