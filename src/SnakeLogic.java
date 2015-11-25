import java.util.Random;

public class SnakeLogic {

	// private variables
	private int[] mainboard;
	private int filled;
	private int baitLocation;
	private Random random;
	
	
	// constructor
	public SnakeLogic(){
		mainboard = new int[100];
		filled = 0;
		baitLocation = 0;
		random = new Random();
	}
	
	public static void main(String [] args)
	{
		SnakeLogic snakelogic = new SnakeLogic();
		snakelogic.mainLogic();
	}
	
	public void mainLogic(){
		mainboard[0] = 45;
		mainboard[1] = 55;
		filled = 2;
		int timer = 0;
		for(;;){
			timer++;
			if(timer == 100000){	
				snakeMovePublic(false,"non");
				printSnake();
				timer = 0;
				System.out.println("");
			}
		}
	}
	
	/*
	 * 	method: public snakeMovePublic
	 * 
	 * 	parameters: isClick; lets the logic know if it is a button click. buttonClick; tells the logic which button was clicked
	 */
	public void snakeMovePublic(boolean isClick,String buttonClick){
		int firstMove = 0;
		if (isClick){
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
			moveSnake(isClick, firstMove);
		}else if(!isClick){
			int head = 0;
			if((mainboard[head]+1) == mainboard[head+1]){
				firstMove = -1;
			}else if((mainboard[head]+10) == mainboard[head+1]){
				firstMove = -10;
			}else if((mainboard[head]-1) == mainboard[head+1]){
				firstMove = 1;
			}else if((mainboard[head]-10) == mainboard[head+1]){
				firstMove = 10;
			}
			isEaton(firstMove);
			moveSnake(isClick, firstMove);
		}
	}
	
	/*
	 * 	method: private moveSnake
	 * 
	 * parameters: isClick; tells the logic is a button click occured. firstMove; tells the logic which directon to move the head
	 */
	private void moveSnake(boolean isClick, int firstMove){
		if(isOutside(firstMove)){
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
		}
	}
	
	/*
	 * 	method: newBaitLocation
	 */
	private void newBaitLocation(){
		int rand;
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
}
