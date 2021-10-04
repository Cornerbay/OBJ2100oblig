package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	public final static Color[] color = {Color.YELLOW, Color.GREEN, Color.MAGENTA};
	private Random random = new Random();
	private Color gameColor = color[random.nextInt(color.length)];		
	private JPanel topPanel = new JPanel();
	private int guessProgress = 0;
	private JPanel coloredPanelContainer = new JPanel();
	
	public GamePanel() {
		
		
		TheMouseListener theMouseListener = new TheMouseListener();
		
		topPanel.setBackground(ColoredPanel.defaultColor);
		
		setLayout(new BorderLayout());
		coloredPanelContainer.setLayout(new GridLayout(3,3,2,2));
		for(int i = 0; i<9; i++) {
			
			ColoredPanel coloredPanel = new ColoredPanel(theMouseListener , color[random.nextInt(color.length)]);
			coloredPanelContainer.add(coloredPanel);
		}
		add(topPanel,BorderLayout.NORTH);
		add(coloredPanelContainer,BorderLayout.CENTER);
	}
	
	void setTopPanelGameColor() {
		topPanel.setBackground(gameColor);
	}
	
	public int getPanelCount() {
		return coloredPanelContainer.getComponentCount();
	}
	
	void setPanelsToDefaultColor() {
		for(int i = 0; i<getPanelCount(); i++) {
			ColoredPanel coloredPanel = (ColoredPanel)coloredPanelContainer.getComponent(i);
			coloredPanel.setDefaultColor();
		}
	}
	
	private int getWinningPanels() {
		
		int winningPanels = 0;
		
		for(int i = 0; i<coloredPanelContainer.getComponentCount();i++) {
			ColoredPanel coloredPanel = (ColoredPanel)coloredPanelContainer.getComponent(i);
			
			if(coloredPanel.getPanelColor()==gameColor) {
				winningPanels+=1;				
			}
		}
		
		return winningPanels;
	}
	
	private void removeTheMouseListener() {
		
		for(int i = 0; i<coloredPanelContainer.getComponentCount();i++) {
			ColoredPanel coloredPanel = (ColoredPanel)coloredPanelContainer.getComponent(i);
			coloredPanel.removeMouseListener(coloredPanel.getMouseListeners()[0]);
		}
		
	}
	

	class TheMouseListener extends MouseAdapter{
		//Denne skal overleveres til hver instans av ColoredPanel
		
		public void mousePressed(MouseEvent e) {
			if(topPanel.getBackground()!=ColoredPanel.defaultColor) {
				ColoredPanel coloredPanel = (ColoredPanel)e.getSource();
				
				//System.out.println(coloredPanel.getPanelColor().toString());
				coloredPanel.setPanelColor();
				if(topPanel.getBackground()!=coloredPanel.getPanelColor()) {
					coloredPanel.add(new JLabel("you loose"));
					topPanel.add(new JLabel("Game over!"));
					invalidate();
					validate();
					removeTheMouseListener();
				}else {
					
					guessProgress += 1;
					
					if(getWinningPanels() == guessProgress) {
						topPanel.add(new JLabel("You won!"));
						invalidate();
						validate();
						removeTheMouseListener();
					}
					
				}
				
			}
            
        }
	}
}
