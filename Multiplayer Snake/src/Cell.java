import java.awt.Color;

import javax.swing.JPanel;

public class Cell {

	private JPanel panel;
	private int value;
	
	public Cell() {
		this.setPanel(new JPanel());
		this.panel.setBackground(Color.LIGHT_GRAY);
		this.setValue(0);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	


}
