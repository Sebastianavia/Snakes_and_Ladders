package model;

public class Board {
	private Node first;
	private int numRows;
	private int numCols;
	private int snakers;
	private int laders;
	private int max;

	public Board(int n, int m, String players) {
		setNumRows(n);
		numCols = m;
		setSnakers(0);
		setLaders(0);
		max = n * m;
		createMatrix(players);
	}

	public void createMatrix(String players) {
	
		first = new Node(1);
		first.setPlayers(players);
		
		createdBoard(first, 1);

	}

	public String prePrint() {
		String out2 = "";
		String out = printBoard(out2, first, 1, 1, 0);
		return out;
	}

	public String prePrint2() {
		String out2 = "";
		String out = printBoard2(out2, first, 1, 1, 0);
		return out;
	}

	
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

			if (first2.getSnake().equals(" ")) {
				if (first2.getLader().equals(" ")) {
					first2.setSnake(let);

					out = true;
				}
			}

		} else {

			out = positionSnake(first2.getNext(), position, let);
		}

		return out;
	}

	public String printBoard2(String out, Node first2, int num, int num2, int num3) {
		if (num3 < max - 2) {
			num3++;
			if (num2 == 1) {
				String out3 = "";
				String out2 = recordNext2(first2, num, out3);
				out = out2 + "\n" + out;
				num2 = 2;
				first2 = recordNode(first2, 1, out);
				if (first2 != null) {
					out = printBoard2(out, first2, num, num2, num3);
				}
			} else {
				String out3 = "";
				String out2 = recordPrev2(first2, num, out3);
				out = out2 + "\n" + out;
				num2 = 1;
				first2 = recordNode(first2, 1, out);
				if (first2 != null) {
					out = printBoard2(out, first2, num, num2, num3);
				}
			}
		}
		return out;
	}

	public String recordNext2(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = out + " " + first2.toString2();
			first2 = first2.getNext();
			num2++;
			out = recordNext2(first2, num2, out);
		}

		return out;
	}

	public String recordPrev2(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = first2.toString2() + " " + out;
			num2++;
			out = recordPrev2(first2.getNext(), num2, out);
		}
		return out;
	}

	public boolean foundPlayer(String symbol, int position) {
		boolean out1 =false;
		boolean out = movePlayer(foundPlayer(first, symbol, position), position, symbol, out1);
		return out;
	}

	private Node foundPlayer(Node node, String symbol, int position) {
		if (node.getPos() + position <= max) {
			if (node.getPlayers().indexOf(symbol) != -1) {
				String player = node.getPlayers();
				player = player.replace(symbol, "");
				node.setPlayers(player);
			} else {
				node = foundPlayer(node.getNext(), symbol, position);
			}
		}
		return node;
	}

	private boolean movePlayer(Node node, int position, String symbol, boolean out) {

		if (node.getPos() + position <= max) {
			if (position >= 1) {
				if (node.getNext() != null) {
				
					if (node.getNext().getPos() == max) {
						
						
						out= true;
					}
					position--;
					
					out =movePlayer(node.getNext(), position, symbol, out);
					
				}
			} else {
				if (node.getPos() == max) {
					out= true;
				}
				if (node.getSnake() != " ") {
					
					if(foundSnake(node.getPrev(), node.getSnake(),symbol)==false) {
						
						String player = node.getPlayers();
						node.setPlayers(symbol + player);
					}
				} else {
					if (node.getLader() != " ") {
						
						if(foundLader(node.getNext(), node.getLader(),symbol)==false) {
							
							String player = node.getPlayers();
							node.setPlayers(symbol + player);
							
						}
					} else {
						String player = node.getPlayers();
						node.setPlayers(symbol + player);
					}
				}
			}

		}
		return out;
	}

	public boolean positionLader(int position, String let) {
		boolean out = positionLader(first.getNext(), position, let);
		return out;
	}

	private boolean positionLader(Node first2, int position, String let) {

		boolean out = false;
		if (first2.getPos() == position) {

			if (first2.getLader().equals(" ")) {
				if (first2.getSnake().equals(" ")) {
					first2.setLader(let);

					out = true;
				}
			}

		} else {

			out = positionLader(first2.getNext(), position, let);
		}

		return out;
	}

	// metodo para recorrer hacia adelante en busqueda de escalera
	public boolean foundLader(Node first2, String let,String symbol) {
		boolean out = false;
		if (first2.getLader().equals(let)) {
			
			String lader = first2.getPlayers();
			first2.setPlayers(symbol + lader);
			out = true;
		} else {
			if (first2.getNext() != null) {
				out = foundLader(first2.getNext(), let,symbol);
			}
		}
		return out;
	}

	// metodo para recorrer hacia atras en busqueda de la serpiente
	public boolean foundSnake(Node first2, String let,String symbol) {
		boolean out = false;
		if (first2.getSnake().equals(let)) {
			
			String lader = first2.getPlayers();
			first2.setPlayers(symbol + lader);
			out = true;
		} else {
			if (first2.getPrev() != null) {
				out = foundSnake(first2.getPrev(), let,symbol);
			}
		}
		return out;
	}

	public int getSnakers() {
		return snakers;
	}

	public void setSnakers(int snakers) {
		this.snakers = snakers;
	}

	public int getLaders() {
		return laders;
	}

	public void setLaders(int laders) {
		this.laders = laders;
	}
}
