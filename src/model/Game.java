package model;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private int movement;
;
	public final static String SPLIT =" ";
	 Scanner scanner= new Scanner(System.in);
	 
	 public Game() {
		 setMovement(0);
	 }
	public String playSnake(int columns, int rows, int snakes, int ladder, String num4, int players,int ini,char ch) {
		String out = "";
		if(ini==0) {
			ch = num4.charAt(num4.length()-players);
			out=(ch+"");
			ini++;
		}else{
			int value = players - ini; 
			ch = num4.charAt(num4.length()-value);
			out=(ch+"");
			ini++;
		
		if(ini == players) {
			ini = 0;
			movement++;
		}
		}
		//playSnake(columns,rows,snakes,ladder,num4,players,ini,ch);
		return out;
		}
	 public int rollDice(){
	        Random rand = new Random();
	        int totalMoves=0;
	        int num=6;

	        while(num==6){
	            
	            num= rand.nextInt((6-1)+1) +1;
	            totalMoves++;
	        }
	        return num;
	    }
	public int getMovement() {
		return movement;
	}
	public void setMovement(int movement) {
		this.movement = movement;
	}

}
