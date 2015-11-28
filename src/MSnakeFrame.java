import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MSnakeFrame extends JFrame {

	private JPanel contentPane;
	private GridBagConstraints gridConstraintsContent;
	private JLabel[] snakeLocations;

	/**
	 * Create the frame.
	 */
	public MSnakeFrame() {
		this.setName("MSnake");
		this.setTitle("MSnake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(150, 150, 700, 550);
		this.setMaximumSize(new Dimension(700,550));
		
		this.setGridBagLayout();
		
		this.setVisible(true);
		this.pack();
		this.setFocusable(true);
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
	
	public void updateSnakeandBait(int[] mainBoard, int filled, int baitLocation){
		for(int i = 0; i < 100; i++){
			snakeLocations[i].setBackground(Color.white);
		}
		snakeLocations[mainBoard[0]].setBackground(Color.black);
		snakeLocations[baitLocation].setBackground(Color.red);
		for(int i = 1; i < filled; i++){
			snakeLocations[mainBoard[i]].setBackground(Color.blue);
		}
	}
	
	public void updateKeyListener(KeyListener listener){
		contentPane.addKeyListener(listener);
	}


}
