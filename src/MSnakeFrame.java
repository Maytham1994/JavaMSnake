import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MSnakeFrame extends JFrame {

	private JPanel contentPane;
	private JPanel menuPane;
	private GridBagConstraints gridConstraintsContent;
	private GridBagConstraints gridConstraintsMenu;
	private JLabel[] snakeLocations;
	private JButton[] buttons;

	/**
	 * Create the frame.
	 */
	public MSnakeFrame() {
		this.setName("MSnake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(150, 150, 700, 550);
		this.setMaximumSize(new Dimension(700,550));
		
		this.setGridBagLayout();
		
		this.setVisible(true);
		this.pack();
	}
	
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
		
		gridConstraintsMenu = new GridBagConstraints();
		gridConstraintsMenu.fill = GridBagConstraints.HORIZONTAL;
		gridConstraintsMenu.insets = new Insets(5, 5, 5, 5);
		gridConstraintsMenu.ipadx = 60;
		gridConstraintsMenu.ipady = 30;
		gridConstraintsMenu.gridx = 1;
		gridConstraintsMenu.gridy = 0;
		this.createMenuPanel();
		this.add(menuPane, gridConstraintsMenu);
	}
	
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
	
	private void createMenuPanel(){
		menuPane = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		menuPane.setBorder(border);
		menuPane.setLayout(new GridLayout(2,1));
		menuPane.setSize(new Dimension(120, 60));
		menuPane.setBackground(Color.white);
		
		this.createButtons();		
	}
	
	private void createButtons(){
		buttons = new JButton[2];
		Dimension dimension = new Dimension(30, 10);
		
		buttons[0] = new JButton("Start");
		buttons[0].setPreferredSize(dimension);
		menuPane.add(buttons[0]);
		
		buttons[1] = new JButton("Stop");
		buttons[1].setPreferredSize(dimension);
		menuPane.add(buttons[1]);
	}
	
	public void updateSnake(int[] mainBoard, int filled){
		for(int i = 0; i < 100; i++){
			snakeLocations[i].setBackground(Color.white);
		}
		for(int i = 0; i < filled; i++){
			snakeLocations[mainBoard[i]].setBackground(Color.blue);
		}
	}

}
