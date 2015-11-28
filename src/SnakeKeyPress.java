import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeKeyPress implements KeyListener {
	
	private SnakeLogic snakelogic;
	
	public static void main(String [] args)
	{
		SnakeKeyPress snakekeypress = new SnakeKeyPress();
	}
	
	public SnakeKeyPress(){
		snakelogic = new SnakeLogic();
		snakelogic.updateKeyListener(this);
		snakelogic.mainLogic();
	}
	
	
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
