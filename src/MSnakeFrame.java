import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 *  <h1>MSnakeFrame</h1>
 * The frame of the game which contains the GUI
 * 
 * 
 * @author Maytham Sabbagh
 *
 */
public class MSnakeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// content panel for the game
	private JPanel contentPane;
	// gridbagconstraints for the gridbaglayout
	private GridBagConstraints gridConstraintsContent;
	// label array for the game's map
	private JLabel[] snakeLocations;

	/**
	 * 	Constructor for creating the frame
	 */
	public MSnakeFrame() {
		this.setName("MSnake");
		this.setTitle("MSnake");
		// set the clost operation when the program exits
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the bounds and the maximum size
		this.setBounds(150, 150, 700, 550);
		this.setMaximumSize(new Dimension(700,550));
		
		// set the gridbaglayout
		this.setGridBagLayout();
		
		// set visibility, pack the content and ensure focus in the window
		this.setVisible(true);
		this.pack();
		this.setFocusable(true);
	}
	
	/**
	 *  setGridBagLayout:
	 *  	- Set the gridbaglayout for the panels then add the panel to the frame
	 */
	private void setGridBagLayout(){
		this.setLayout(new GridBagLayout());
		
		gridConstraintsContent = new GridBagConstraints();
		gridConstraintsContent.fill = GridBagConstraints.HORIZONTAL;
		gridConstraintsContent.ipadx = 400;
		gridConstraintsContent.ipady = 400;
		gridConstraintsContent.gridx = 0;
		gridConstraintsContent.gridy = 0;
		this.createContentPanel();
		this.add(contentPane, gridConstraintsContent);
	}
	
	/**
	 *  createContentPanel:
	 *  	- Create the content panel and initialize it with the labels map
	 */
	private void createContentPanel(){
		contentPane = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		contentPane.setBorder(border);
		contentPane.setSize(400, 400);
		contentPane.setLayout(new GridLayout(10,10));
		
		snakeLocations = new JLabel[100];
		for(int i = 0; i < 100; i++){
			snakeLocations[i] = new JLabel("");
			snakeLocations[i].setSize(40,40);
			snakeLocations[i].setBorder(border);
			snakeLocations[i].setOpaque(true);
			snakeLocations[i].setBackground(Color.white);
			snakeLocations[i].setVisible(true);
			contentPane.add(snakeLocations[i]);
		}
		
	}
	
	/**
	 *  updateSnakeandBait:
	 *  	- Updates the GUI with the snake and the bait location
	 *  
	 * @param head 
	 * @param removed 
	 * @param baitLocation the location of the bait
	 * @param kneck
	 * @param filled the number of filled spots in the array
	 */
	public void updateSnakeandBait(int head, int removed, int baitLocation, int kneck, int filled){
		snakeLocations[removed].setBackground(Color.white);
		snakeLocations[head].setBackground(Color.black);
		snakeLocations[baitLocation].setBackground(Color.red);
		if (filled == 1){
			snakeLocations[kneck].setBackground(Color.white);
		}else{
			snakeLocations[kneck].setBackground(Color.blue);
		}
	}
	
	/**
	 *  updateKeyListener:
	 *  	- updates the content panel with the KeyListener
	 * @param listener
	 */
	public void updateKeyListener(KeyListener listener){
		contentPane.addKeyListener(listener);
	}
	
	/**
	 *  restartGui:
	 *  	- Restarts the Gui after restarting the game
	 */
	public void restartGui(){
		for (int i = 0; i < 100; i++){
			snakeLocations[i].setBackground(Color.white);
		}
	}


}
