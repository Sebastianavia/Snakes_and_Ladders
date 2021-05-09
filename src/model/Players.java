package model;

public class Players {
	private String nickName;
	private String symbol;
	private int score;
	private Players left;
	private Players right;
	private String caracters;
	public  Players(String n,String s,int sc) {
		nickName = n;
		symbol= s;
		score= sc;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Players getLeft() {
		return left;
	}
	public Players getRight() {
		return right;
	}
	public void setLeft(Players l) {
		left = l;
	}
	public void setRight(Players r) {
		right = r;
	}
	public String data() {
		String out =	"Nombre: "+nickName+" Simbolos: "+symbol+" Puntaje: "+score+"\n"+caracters ;
		return out;
		
	}
	public String getCaracters() {
		return caracters;
	}
	public void setCaracters(String caracters) {
		this.caracters = caracters;
	}
}
