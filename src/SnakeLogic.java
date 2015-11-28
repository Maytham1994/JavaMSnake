import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JOptionPane;

/**
* <h1>SnakeLogic</h1>
* The main logic behind the Snake Game designed
* 
*
* @author  Maytham Sabbagh
*/
public class SnakeLogic{

	// private variables
	// mainboard is the spots in the game where the snake is located
	private int[] mainboard;
	// filled tells how many spots are filled in the board
	private int filled;
	// baitLocation contains the location of the bait which the snake needs to eat
	private int baitLocation;
	// Random object used for selecting the next bait location
	private Random random;
	//MSnakeFrame object which houses the game's GUI
	private MSnakeFrame snakeFrame;
	// array to contain the center 4 spots
	private int[] randomCenter;
	// direction string to tell the snake where to move next (works with the keyboard presses)
	private String direction;
	
	/**
	 * 	This is the constructor for the SnakeLogic class. 
	 * 	It creates the SnakeLogic which the game is based on
	 */
	public SnakeLogic(){
		// initialize the mainboard array with 100
		mainboard = new int[100];
		// initialize the filled with 0
		filled = 0;
		// initialize the new random object
		random = new Random();
		// initialize the center 4 spots in an array
		randomCenter = new int[]{44,45,54,55};
		// initialize the MSnakeFrame which has the frame, panels and so on
		snakeFrame = new MSnakeFrame();
	}
	
	/**
	 * 	This method runs the main logic of the game and is used to restart the game at win or loss
	 *	
	 */
	public void mainLogic(){
		// initialize the head of the snake with one of the 4 center spots
		// initialize the filled with 1 (head of the snake)
		// initialize the direction with nothing (will work at first button click)
		// add the bait to the map
		// run the while loop and use sleep for timed rotation
		mainboard[0] = randomCenter[random.nextInt(4)];
		filled = 1;
		direction = "";
		this.newBaitLocation();
		try {
	        while (true) {
	        	printSnake();
				Thread.sleep(9 * 10);
				snakeMove(direction);
				Thread.sleep(9 * 10);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 	snakeMove:
	 * 	finds the direction for the snake to move in and then check and sees if the 
	 * 	snake eats the bait, it then moves the snake and checks to see if the player won or is dead
	 * 
	 * 	@param buttonClick String that contains the direction of the snake movement
	 */
	private void snakeMove(String buttonClick){
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

	/**
	 * 	moveSnake:
	 * 		- Checks to see if the player is outside the playing area and outputs a loss if true
	 * 		- Moved the snake using a for loop
	 * 
	 * @param firstMove the direction of the move the snake's head needs to do
	 */
	private void moveSnake(int firstMove){
		if(isOutside(firstMove)){
			int reply = JOptionPane.showConfirmDialog(snakeFrame,
					"You Lost, would you like to restart the game?",
					"YOU LOST !!!",
					JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				this.mainLogic();
			}
			System.exit(0);
		}
		for(int i = filled-1; i >= 0; i--){
			if(i == 0){
				mainboard[i] += firstMove;
			}else{
				mainboard[i] = mainboard[i-1];
			}
		}
	}
	
	/**
	 * 	isEaton:
	 * 		- finds if the player ate the bait
	 * 		- increases the size of the snake and creates a new bait
	 * 
	 * 	@param firstMove the direction of the head's next movement
	 */
	private void isEaton(int firstMove){
		if((mainboard[0]+firstMove) == baitLocation){
			filled++;
			newBaitLocation();
		}
	}
	
	/**
	 * 	newBaitLocation:
	 * 		- creates a new bait location based on the random generator
	 * 		- ensures that the bait location doesn't fall on the snake
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
	
	/**
	 * 	printSnake:
	 * 		- calls the updateSnakeandBait method in the frame to 
	 * 			update the GUI with the new snake
	 * 
	 */
	private void printSnake(){
		snakeFrame.updateSnakeandBait(mainboard, filled, baitLocation);
	}
	
	/**
	 * 	isOutside:
	 * 		- find if the snake's next location is outside the playing area
	 * 
	 * 	@param firstMove the head's next movement direction
	 */
	private boolean isOutside(int firstMove){
		int head = 0;
		if(mainboard[head] < 10){
			if(((mainboard[head] != 0)&&(firstMove == -10))
					||((mainboard[head] == 0)&&(firstMove < 0))
					||((mainboard[head] == 9)&&(firstMove == 1))){
				return true;
			}
		}else if(mainboard[head] >= 90){
			if(((mainboard[head] != 99)&&(firstMove == 10))
					||((mainboard[head] == 99)&&(firstMove > 0))
					||((mainboard[head] == 90)&&(firstMove == -1))){
				return true;
			}
		}else if((mainboard[head]%10) == 9){
			if(firstMove == 1){
				return true;
			}
		}else if((mainboard[head]%10) == 0){
			if(firstMove == -1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  updateDirection:
	 *  	- Updates the direction of the snake's head movement based on the button click
	 *  	- Called by the button click listening method
	 *  
	 * @param newDirection the direction based on the button click
	 */
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
	
	/**
	 * 	updateKeyListener:
	 * 		- Update the frame and the panels under it with the new KeyListener
	 * 
	 * @param listener the KeyListener instance
	 */
	public void updateKeyListener(KeyListener listener){
		snakeFrame.updateKeyListener(listener);
		snakeFrame.addKeyListener(listener);
	}
	
	/**
	 *  isWon:
	 *  	- Checks to see if the player won when filled reaches maximum
	 */
	private void isWon() {
		if(filled == 100){
			int reply = JOptionPane.showConfirmDialog(snakeFrame,
					"You Won, would you like to restart the game?",
					"YOU WON !!!",
					JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				this.mainLogic();
			}
			System.exit(0);
		}
	}
	
	/**
	 *  isDead:
	 *  	- Checks to see if the player lost by having the snake eat itself
	 */
	private void isDead(){
		int head = 0;
		for(int i = 1; i < filled; i++){
			if(mainboard[i] == mainboard[head]){
				int reply = JOptionPane.showConfirmDialog(snakeFrame,
						"You Lost, would you like to restart the game?",
						"YOU LOST !!!",
						JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					this.mainLogic();
				}
				System.exit(0);
			}
		}
	}
	
	
}
