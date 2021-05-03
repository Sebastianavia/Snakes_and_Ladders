package model;

public class Board {
	private Node first;
	private int numRows;
	private int numCols;
	private int max;

	public Board(int n, int m) {
		setNumRows(n);
		numCols = m;
		max = n * m;
		createMatrix();
	}

	public void createMatrix() {
		// System.out.println("vamos a crear la matrix");
		first = new Node(1);

		// System.out.println("se crea el first");
		createdBoard(first, 1);

	}

	public String prePrint() {
		String out2 = "";
		String out = printBoard(out2, first, 1, 1, 0);
		return out;
	}

	/*
	 * private void createRow(int i, int j, Node firstRow) {
	 * //System.out.println("en creatRow con la fila " + i);
	 * createCol(i,j+1,firstRow,firstRow.getUp() ); if(i+1<numRows) { Node
	 * downFirstRow = new Node(i+1,j); downFirstRow.setUp(firstRow);
	 * firstRow.setDown(downFirstRow); createRow(i+1, j, downFirstRow); }
	 * 
	 * }
	 * 
	 * private void createCol(int i, int j, Node prev,Node rowPrev) { if(j<numCols)
	 * { //System.out.println("en createCol con la columna "+j); Node current = new
	 * Node(i,j); current.setPrev(prev); prev.setNext(current); if(rowPrev!= null) {
	 * rowPrev = rowPrev.getNext(); current.setUp(rowPrev);
	 * rowPrev.setDown(current);
	 * 
	 * }
	 * 
	 * createCol(i,j+1,current,rowPrev); }
	 * 
	 * } public String toString() { String msg; msg=toStringRow(first); return msg;
	 * 
	 * }
	 * 
	 * private String toStringRow(Node firstRow) { String msg=""; if(firstRow!=null)
	 * { msg = toStringCol(firstRow)+"\n"; msg+= toStringRow(firstRow.getDown()); }
	 * return msg; }
	 * 
	 * private String toStringCol(Node current) { String msg=""; if(current!=null) {
	 * msg = current.toString(); msg+= toStringCol(current.getNext()); } return msg;
	 * }
	 * 
	 * public String imprimir() {
	 * 
	 * String out="" ; int i =1; out=nextCol(nodo(first),i); return out; } private
	 * Node nodo(Node first2) {
	 * 
	 * if(first2.getDown()!=null) {
	 * 
	 * first2 =nodo(first2.getDown()); }
	 * 
	 * return first2; } private String nextCol(Node first2,int i) { String out="";
	 * 
	 * if(first2.getNext()!= null) { first2.setPos(i++); out=
	 * "["+first2.getPos()+"]"; out+=nextCol(first2.getNext(),i++);
	 * 
	 * }if(first2.getUp()== null) { first2.setPos(i++); out=
	 * "["+first2.getPos()+"]"; out+=nextCol(first2.getNext(),i++);
	 * System.out.println("entro");
	 * 
	 * }
	 * 
	 * return out; }
	 */
	private Node createdBoard(Node first1, int i) {
		if (i < max) {
			i++;
			Node current = new Node(i);
			first1.setNext(current);
			current.setPrev(first1);
			createdBoard(current, i);
		}
		return first1;
	}

	public String printBoard(String out, Node first2, int num, int num2, int num3) {
		if (num3 < max - 2) {
			num3++;
			if (num2 == 1) {
				String out3 = "";
				String out2 = recordNext(first2, num, out3);
				out = out2 + "\n" + out;
				num2 = 2;
				first2 = recordNode(first2, 1, out);
				if (first2 != null) {
					out = printBoard(out, first2, num, num2, num3);
				}
			} else {
				String out3 = "";
				String out2 = recordPrev(first2, num, out3);
				out = out2 + "\n" + out;
				num2 = 1;
				first2 = recordNode(first2, 1, out);
				if (first2 != null) {
					out = printBoard(out, first2, num, num2, num3);
				}
			}
		}
		return out;
	}

	public String recordNext(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = out + " " + first2.toString();
			first2 = first2.getNext();
			num2++;
			out = recordNext(first2, num2, out);
		}

		return out;
	}

	public String recordPrev(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = first2.toString() + " " + out;
			num2++;
			out = recordPrev(first2.getNext(), num2, out);
		}
		return out;
	}

	public Node recordNode(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			first2 = first2.getNext();
			num2++;
			first2 = recordNode(first2, num2, out);
		}
		return first2;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public boolean positionSnake(int position, String let) {
		boolean out = positionSnake(first.getNext(), position, let);
		return out;
	}

	private boolean positionSnake(Node first2, int position, String let) {
		
		boolean out = false;
		if (first2.getPos() == position) {
			
			if (first2.getSnakeColum().equals(" ")) {
				
				first2.setSnakeColum(let);
				
				out = true;
			}

		} else {
			
			out=positionSnake(first2.getNext(), position, let);
		}
	
		return out;
	}
}
