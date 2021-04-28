package model;

public class Board {
	private Node first;
	private int numRows;
	private int numCols;
	public Board(int m,int n) {
		numRows = m;
		numCols = n;
		creatMatrix();
	}
	public void creatMatrix() {
		System.out.println("vamos a crear la matrix");
		first = new Node(0,0);
		System.out.println("se crea el first");
		createRow(0,0,first);
	}
	private void createRow(int i, int j, Node currentFirstRow) {
		System.out.println("en createRom con la fila "+i);
		createCol(i,j+1,currentFirstRow);
		if(i+1<numRows) {
			Node downFirstRow = new Node(i,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}
	private void createCol(int i, int j, Node prev) {
		if(j<numCols) {
			System.out.println(" en createCol con la columna "+j);
			Node current = new Node(i,j);
			current.setPrev(prev);
			prev.setPrev(current);
			createCol(i,j+1,current);
		}
		
	}
	public String toString() {
		String msg ;
		msg= toStringRow(first);
		return msg;
	}
	private String toStringRow(Node firstRow) {
		String msg="";
		if(firstRow!=null) {
		msg= toStringCol(firstRow)+"\n";
		msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}
	private String toStringCol(Node current) {
		String msg="";
		if(current != null) {
		msg= current.toString();
		msg += toStringCol(current.getNext());
		}
		return msg;
		
	}
}
