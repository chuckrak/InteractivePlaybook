
import java.awt.*;
import javax.swing.*;
public class DefensivePlayer extends JComponent implements Entity{
	private int fieldPosX, fieldPosY;
	private String position;
	private JTextField interactFailure;
	private JFrame failureOverlay;
	private int shade;
	
	
	public DefensivePlayer(String pos) {
		interactFailure = new JTextField("You cannot move where another one of your defensive players is.");
		interactFailure.setEditable(false);
		interactFailure.setPreferredSize(new Dimension(400, 100));
		position = pos;
	}
	public DefensivePlayer(String pos,int s) {
		shade = s;
		interactFailure = new JTextField("You cannot move where another one of your defensive players is.");
		interactFailure.setEditable(false);
		interactFailure.setPreferredSize(new Dimension(400, 100));
		position = pos;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, 100, 100);
		g.setFont(new Font("ss", Font.BOLD, 12));
		g.drawString(position, shade*5, 12);
//		g.fillPolygon(new int[] {0, 10, 5}, new int[]{0, 0, 10}, 3);

	}
	public void interact(ControlledDefensivePlayer p) {
		failureOverlay = new JFrame("Error");
		failureOverlay.isAlwaysOnTop();
		failureOverlay.setVisible(true);
		failureOverlay.setBounds(500, 500, 400, 100);
		failureOverlay.add(interactFailure);
	}
	public String toString() {
		return "D";
	}
	public boolean getInteractable() {
		return true;
	}
//	public void changeCoordinates(int direction) {
//		switch (direction){
//		case 0:
//			fieldPosY--;
//			break;
//		case 1:
//			fieldPosX++;
//			break;
//		case 2:
//			fieldPosY++;
//			break;
//		case 3:
//			fieldPosX--;
//			break;
//	}
//	}
	public boolean getMovable() {
		return false;
	}
}