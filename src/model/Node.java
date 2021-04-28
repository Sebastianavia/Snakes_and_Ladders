package model;

public class Node {
	private int row;
	private int col;
	
	
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	public Node(int r,int c) {
		row = r;
		col = c;
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
		return "("+ row +","+col +")";
	}
}
