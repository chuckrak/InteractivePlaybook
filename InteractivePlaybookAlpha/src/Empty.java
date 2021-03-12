import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Empty extends JComponent implements Entity {
	private boolean movable = true;
	private boolean interactable = true;
	//public int isEmpty = 222;
	
	public Empty() {
//		setBackground(Color.WHITE);
	}
	
	@Override
	public void interact(ControlledDefensivePlayer player) {
	}

	@Override
	public void paintComponent(Graphics g) {	
		super.paintComponent(g);
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, 100, 100);
	}
	public String toString() {
		return " E ";
	}

	@Override
	public boolean getMovable() {
		// TODO Auto-generated method stub
		return movable;
	}

	@Override
	public boolean getInteractable() {
		// TODO Auto-generated method stub
		return interactable;
	}
}