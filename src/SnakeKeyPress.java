import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  <h1>SnakeKeyPress</h1>
 * The listener for the keypresses, implements the KeyListener interface
 * 
 * 
 * @author Maytham Sabbagh
 *
 */
public class SnakeKeyPress implements KeyListener {
	
	// SnakeLogic instance for the game
	private SnakeLogic snakelogic;
	
	/**
	 *  Main function which gets called at game start
	 *  
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String [] args)
	{
		SnakeKeyPress snakekeypress = new SnakeKeyPress();
	}
	
	/**
	 * 	SnakeKeyPress constructor
	 * 		- creates the new game logic object
	 * 		- updates the listener with this instance
	 * 		- runs the mainlogic method for the game to start
	 */
	public SnakeKeyPress(){
		snakelogic = new SnakeLogic();
		snakelogic.updateKeyListener(this);
		snakelogic.mainLogic();
	}
	
	/**
	 *  keyPressed:
	 *  	- Gets called at key press and updates the game accordingly
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP){
			snakelogic.updateDirection("up");
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			snakelogic.updateDirection("right");
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			snakelogic.updateDirection("down");
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			snakelogic.updateDirection("left");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	
}
