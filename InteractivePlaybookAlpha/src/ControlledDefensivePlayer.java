import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ControlledDefensivePlayer extends JComponent implements Entity{
//	private Image playerPic = (new ImageIcon("Sagehen.jpg")).getImage();
	private String position;
	private int fieldPosX, fieldPosY;
	public int shade = 0;
	private boolean movable = true;
	private boolean interactable = true;
	
	public ControlledDefensivePlayer(String s) {
		position = s;
	}
	public ControlledDefensivePlayer(String s, int alignment) {
		shade = alignment;
		position = s;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
//		g.fillRect(0, 0, 100, 100);
		g.setFont(new Font("ss", Font.BOLD, 12));
		g.drawString(position, 6*shade, 12);
//		g.drawImage(playerPic, 0, 0, null);
//		g.fillPolygon(new int[] {0, 100, 50}, new int[]{0, 0, 100}, 3);
	}
	public void interact(ControlledDefensivePlayer p) {
		
	}

	@Override
	public boolean getMovable() {
		return movable;
	}
	@Override
	public boolean getInteractable() {
		return interactable;
	}
}
