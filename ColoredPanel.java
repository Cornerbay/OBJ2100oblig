package gui;

import java.awt.Color;
import javax.swing.JPanel;
import gui.GamePanel.TheMouseListener;

public class ColoredPanel extends JPanel {
	
	public final static Color defaultColor = Color.LIGHT_GRAY;
	private Color panelColor;
	private TheMouseListener mouseListener = null;
	
	public ColoredPanel(TheMouseListener mouseListener, Color color){
		panelColor = color;
		setBackground(color);
		addMouseListener(mouseListener);
	}
	
	public Color getPanelColor() {
		return panelColor;
	}
	
	public void setDefaultColor() {
		setBackground(defaultColor);
	}
	
	public void setPanelColor() {
		setBackground(panelColor);
	}

	public void unsetMouseListener() {
		// TODO Auto-generated method stub
		removeMouseListener(mouseListener);
		
	}
	
}
