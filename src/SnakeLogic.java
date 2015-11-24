
public class SnakeLogic {

	// private variables
	private int[] mainboard;
	private int[] filled;
	
	// constructor
	public SnakeLogic(){
		mainboard = new int[100];
		filled = new int[100];
	}
	
	public void snakeMovePublic(boolean isClick,String buttonClick){
		int firstMove = 0;
		if (isClick){
			if(buttonClick.equals("right")){
				firstMove = 1;
				moveSnake(isClick, firstMove);
			}else if(buttonClick.equals("left")){
				firstMove = -1;
				moveSnake(isClick, firstMove);
			}else if(buttonClick.equals("up")){
				firstMove = -10;
				moveSnake(isClick, firstMove);
			}else if(buttonClick.equals("down")){
				firstMove = 10;
				moveSnake(isClick, firstMove);
			}
		}else{
			moveSnake(isClick, firstMove);
		}
	}
	
	private void moveSnake(boolean isClick, int firstMove){

	}
}
