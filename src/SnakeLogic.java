import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;

public class SnakeLogic{

	// private variables
	private int[] mainboard;
	private int filled;
	private int baitLocation;
	private Random random;
	private MSnakeFrame snakeFrame;
	private String direction;
	
	// constructor
	public SnakeLogic(){
		mainboard = new int[100];
		filled = 0;
		direction = "";
		random = new Random();
		snakeFrame = new MSnakeFrame();
	}
	
	public void mainLogic(){
		mainboard[0] = 45;
		filled = 1;
		this.newBaitLocation();
		try {
	        while (true) {
	        	printSnake();
				Thread.sleep(1 * 100);
				snakeMovePublic(direction);
				Thread.sleep(1 * 100);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	/*
	 * 	method: public snakeMovePublic
	 * 
	 * 	parameters: isClick; lets the logic know if it is a button click. buttonClick; tells the logic which button was clicked
	 */
	public void snakeMovePublic(String buttonClick){
		int firstMove = 0;
		if(buttonClick.equals("right")){
			firstMove = 1;
		}else if(buttonClick.equals("left")){
			firstMove = -1;
		}else if(buttonClick.equals("up")){
			firstMove = -10;
		}else if(buttonClick.equals("down")){
			firstMove = 10;
		}
		isEaton(firstMove);
		moveSnake(firstMove);
		isWon();
		isDead();
	}

	/*
	 * 	method: private moveSnake
	 * 
	 * parameters: isClick; tells the logic is a button click occured. firstMove; tells the logic which directon to move the head
	 */
	private void moveSnake(int firstMove){
		if(isOutside(firstMove)){
			JOptionPane.showMessageDialog(snakeFrame, "You Lost");
			System.exit(0);
		}
		for(int i = filled-1; i >= 0; i--){
			if(i == 0){
				mainboard[i] += firstMove;
			}else{
				mainboard[i] = mainboard[i-1];
			}
		}
		if(mainboard[0] == baitLocation){
			newBaitLocation();
		}
	}
	
	/*
	 * 	method: private isEaton
	 * 
	 * 	parameters: firstMove; tells the direction of the head move
	 */
	private void isEaton(int firstMove){
		if((mainboard[0]+firstMove) == baitLocation){
			filled++;
			newBaitLocation();
		}
	}
	
	/*
	 * 	method: newBaitLocation
	 */
	private void newBaitLocation(){
		int rand=0;
		boolean isEat = true;
		while(isEat){
			rand = random.nextInt(100);
			for(int i = filled-1; i >= 0; i--){
				if(rand == mainboard[i]){
					isEat = true;
					break;
				}
				isEat = false;
			}
		}
		baitLocation = rand;
	}
	
	/*
	 * 
	 */
	private void printSnake(){
		int done = filled-1;
		for(int i = 0; i < 100; i+=10){
			System.out.println("");
			for(int j = 0; j < 10; j++){
				System.out.print(".");
			}
		}
		snakeFrame.updateSnakeandBait(mainboard, filled, baitLocation);
	}
	
	private boolean isOutside(int firstMove){
		int head = 0;
		if(mainboard[head] < 10){
			if(((mainboard[head] != 0)&&(firstMove == -10))||((mainboard[head] == 0)&&(firstMove < 0))){
				return true;
			}
		}else if(mainboard[head] > 90){
			if(((mainboard[head] != 99)&&(firstMove == 10))||((mainboard[head] == 99)&&(firstMove > 0))){
				return true;
			}
		}else if((mainboard[head]%10) == 9){
			if(firstMove > 0){
				return true;
			}
		}else if((mainboard[head]%10) == 0){
			if(firstMove < 0){
				return true;
			}
		}
		return false;
	}
	
	public void updateDirection(String newDirection){
		int head = 0;
		if((newDirection.equals("right")) && ((mainboard[head]-mainboard[head+1]) == -1)){
			return;
		}else if((newDirection.equals("up")) && ((mainboard[head]-mainboard[head+1]) == 10)){
			return;
		}else if((newDirection.equals("left")) && ((mainboard[head]-mainboard[head+1]) == 1)){
			return;
		}else if((newDirection.equals("down")) && ((mainboard[head]-mainboard[head+1]) == -10)){
			return;
		}
		direction = newDirection;
	}
	
	public void updateKeyListener(KeyListener listener){
		snakeFrame.updateKeyListener(listener);
		snakeFrame.addKeyListener(listener);
	}
	
	private void isWon() {
		if(filled == 100){
			JOptionPane.showMessageDialog(snakeFrame, "You Won");
			System.exit(0);
		}
	}
	
	private void isDead(){
		int head = 0;
		for(int i = 1; i < filled; i++){
			if(mainboard[i] == mainboard[head]){
				JOptionPane.showMessageDialog(snakeFrame, "You Lost");
				System.exit(0);
			}
		}
	}
	
	
}
