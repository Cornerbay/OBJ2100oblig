package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MemoryGameWindow extends JFrame implements ActionListener {

	private GameMenubar menuBar = new GameMenubar();
	private GamePanel gamePanel = null;
	private boolean isPlaying;
	
	public MemoryGameWindow() {	
		
		setJMenuBar(menuBar);
		menuBar.newBoard.addActionListener(this);
		menuBar.play.addActionListener(this);
		menuBar.endProgram.addActionListener(this);
		
		newGamePanel();
		
		setTitle("Memorize Colours");
		this.setSize(600,600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

	private void newGamePanel() {
		if(gamePanel != null) {
			this.remove(gamePanel);
		}
		gamePanel = new GamePanel();
		add(gamePanel, BorderLayout.CENTER);
		SwingUtilities.updateComponentTreeUI(this);
		isPlaying = false;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String arg = e.getActionCommand();

		switch (arg) {
			case ("New Board"):
				newGamePanel();
				menuBar.play.setEnabled(true);
				break;
				
			case ("Play"):
				if(isPlaying==false) {
					gamePanel.setTopPanelGameColor();
					gamePanel.setPanelsToDefaultColor();
					menuBar.play.setEnabled(isPlaying);
					isPlaying = true;					
				}
				break;
				
			case ("End Program"):
				System.exit(0);
				break;
			
		}
	}
	
	public interface ActionListener extends EventListener{
		public void actionPerformed(ActionEvent e);
	}


	class GameMenubar extends JMenuBar{
		
		JMenuItem newBoard = new JMenuItem("New Board");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem endProgram = new JMenuItem("End Program");
		
		private GameMenubar() {
			JMenu menu = new JMenu("File");

			menu.add(newBoard);
			menu.add(play);
			menu.add(endProgram);
			this.add(menu);
			
		}

		
	}
	
}
