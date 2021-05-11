package model;

public class Board {
	private Node first;
	private int numRows;
	private int numCols;
	private int snakers;
	private int laders;
	private int max;
	
	/**
	 * constructor method <br>
	 * <b> pre: we need the atributes </b> 
	 * @param n row numbers
	 * @param m column numbers
	 * @param players song artist
	 */
	public Board(int n, int m, String players) {
		setNumRows(n);
		numCols = m;
		setSnakers(0);
		setLaders(0);
		max = n * m;
		createMatrix(players);
	}

	
	/**
	 * depending on the option chosen, the program performs an option <br>
	 * <b> pre: the data that the user enters, columns, rows, players, siblos. </b> 
	 * @param players menu option
	 * 
	 */
	public void createMatrix(String players) {
	
		first = new Node(1);
		first.setPlayers(players);
		
		createdBoard(first, 1);

	}
	/**
	 * print matrices <br>
	 * <b> pre:the number of rows and columns </b>
	 * @return out
	 */
	
	public String prePrint() {
		String out2 = "";
		String out = printBoard(out2, first, 1, 1, 0);
		return out;
	}
	
	/**
	 * print the matrix with all the information <br>
	 * <b> pre: you need all the information of the columns rows and players  </b>
	 * @return out
	 */
	public String prePrint2() {
		String out2 = "";
		String out = printBoard2(out2, first, 1, 1, 0);
		return out;
	}

	/**
	 * create the board <br>
	 * <b> pre: need the number of rows and columns</b>
	 * @param first1
	 * @param i
	 * @return first1
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
	/**
	 * print current board <br>
	 * <b> pre: dashboard data </b> 
	 * @param out
	 * @param first2
	 * @param num
	 * @param num2
	 * @param num3
	 * @return out
	 */
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
	
	
	/**
	 ** record of space next <br>
	 * <b> pre: make use this space </b>
	 * @param first2
	 * @param num2
	 * @param out
	 * @return the route returns to the right
	 */
	public String recordNext(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = out + " " + first2.toString();
			first2 = first2.getNext();
			num2++;
			out = recordNext(first2, num2, out);
		}

		return out;
	}

	
	/**
	 * record of space previuos <br>
	 * <b> pre: make use this space </b>
	 * @param first2
	 * @param num2
	 * @param out
	 * @return 
	 */
	public String recordPrev(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = first2.toString() + " " + out;
			num2++;
			out = recordPrev(first2.getNext(), num2, out);
		}
		return out;
	}
	
	
	/**
	 * record of nodo <br>
	 * <b> pre: make use of nodo </b>  
	 * @param first2
	 * @param num2
	 * @param out
	 * @return the nodo record
	 */
	public Node recordNode(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			first2 = first2.getNext();
			num2++;
			first2 = recordNode(first2, num2, out);
		}
		return first2;
	}
	
	
	/**
	 * get method <br>
	 * <b> pre: constructor method </b>  
	 * @return numRows
	 */
	public int getNumRows() {
		return numRows;
	}
	
	
	/**
	 * set method <br>
	 * <b> pre: constructor method </b> 
	 * @param numRows: position
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	
	
	/**
	 * set the position of the snake <br>
	 * <b> pre: constructor method </b> 
	 * @param position  snake position
	 * @param let
	 * @return out 
	 */
	public boolean positionSnake(int position, String let) {
		boolean out = positionSnake(first.getNext(), position, let);
		return out;
	}

	/**
	 * is the position of a part of the snake end / beginning<br>
	 * <b> pre: the number of snakes to be created, a free space </b>  
	 * @param first2
	 * @param position
	 * @param let
	 * @return
	 */
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
	
	
	/**
	 * print the board <br>
	 * <b> pre: the data is needed to create it  </b> 
	 * @param out
	 * @param first2
	 * @param num
	 * @param num2
	 * @param num3
	 * @return out the board
	 */
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
    
	
	
	/**
	 * remember the next move <br>
	 * <b> pre: make the move  </b> 
	 * @param first2
	 * @param num2
	 * @param out
	 * @return out 
	 */
	public String recordNext2(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = out + " " + first2.toString2();
			first2 = first2.getNext();
			num2++;
			out = recordNext2(first2, num2, out);
		}

		return out;
	}
	
	
	
	/**
	 * remember previous movements<br>
	 * <b> pre: having previously moved </b>  
	 * @param first2
	 * @param num2
	 * @param out
	 * @return
	 */
	public String recordPrev2(Node first2, int num2, String out) {
		if (num2 <= numCols) {
			out = first2.toString2() + " " + out;
			num2++;
			out = recordPrev2(first2.getNext(), num2, out);
		}
		return out;
	}
	
	
	/**
	 * Find the player on the board<br>
	 * <b> pre: We need the game to have started </b>  
	 * @param symbol
	 * @param position
	 * @return returns, if it finds the desired player
	 */
	public boolean foundPlayer(String symbol, int position) {
		boolean out1 =false;
		boolean out = movePlayer(foundPlayer(first, symbol, position), position, symbol, out1);
		return out;
	}
	/**
	 * Find the player on the board<br>
	 * <b> pre: We need the game to have started </b>  
	 * @param node node where the player is
	 * @param symbol player symbol
	 * @param position postion player
	 * @return returns the node where the player is located
	 */
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
	
	
	/**
	 * move the player token<br>
	 * <b> pre:you need to correctly define the board  </b>  
	 * @param node next nodo
	 * @param position postion player
	 * @param symbol player symbol
	 * @param out false/throw
	 * @return out allows the movement of the player's token
	 */
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
	
	
	
	/**
	 * position of the ladder beginning/end <br>
	 * <b> pre:  need two squares to connect </b>  
	 * @param position
	 * @param let
	 * @return returns if it finds the snake position
	 */
	public boolean positionLader(int position, String let) {
		boolean out = positionLader(first.getNext(), position, let);
		return out;
	}
	
	
	/**
	 * position of the ladder beginning/end <br>
	 * <b> pre: need two squares to connect </b> 
	 * @param first2
	 * @param position position ladder
	 * @param let
	 * @return returns if it finds the ladder position
	 */
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
	
	
	/**
	 * method of going forward in search of stairs <br>
	 * <b> pre: the location of the ladder </b>  
	 * @param first2
	 * @param let
	 * @param symbol
	 * @return returns if it finds the ladder
	 */
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
	
	
	
	/**
	 * method to go back in search of the snake <br>
	 * <b> pre: the location of the snake </b> 
	 * @param first2
	 * @param let
	 * @param symbol
	 * @return returns if it finds the snake
	 */
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
	
	/**
	 * get method <br>
	 * <b> pre: constructor method </b>  
	 * @return snakers
	 */
	public int getSnakers() {
		return snakers;
	}
	
	
	/**
	 * set method <br>
	 * <b> pre: constructor method </b> 
	 * @param snakers: snakes
	 */
	public void setSnakers(int snakers) {
		this.snakers = snakers;
	}
	
	
	/**
	 * get method <br>
	 * <b> pre: constructor method </b>  
	 * @return laders
	 */
	public int getLaders() {
		return laders;
	}
	
	
	/**
	 * set method <br>
	 * <b> pre: constructor method </b> 
	 * @param laders: ladders
	 */
	public void setLaders(int laders) {
		this.laders = laders;
	}
}
