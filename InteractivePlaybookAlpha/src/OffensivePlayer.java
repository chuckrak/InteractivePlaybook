import java.awt.*;
import javax.swing.*;
public class OffensivePlayer extends JComponent implements Entity{
	private boolean movable = false;
	private boolean interactable = true;
	public boolean visible = true;
	private JTextField interactFailure;
	private JFrame failureOverlay;
	private boolean isCenter;
	private String position;
	private boolean shift = false;
	public OffensivePlayer() {
//		setBackground(Color.BLACK);
		setVisible(true);
		interactFailure = new JTextField("You cannot move where an Offensive Player is.");
		interactFailure.setEditable(false);
		interactFailure.setPreferredSize(new Dimension(400, 100));
//		position = positionName;
	}
	public OffensivePlayer(boolean shifted) {
		setVisible(true);
		interactFailure = new JTextField("You cannot move where an Offensive Player is.");
		interactFailure.setEditable(false);
		interactFailure.setPreferredSize(new Dimension(400, 100));
		position = "";
		shift = shifted;
	}
	public OffensivePlayer(boolean shifted, boolean center) {
		setVisible(true);
		interactFailure = new JTextField("You cannot move where an Offensive Player is.");
		interactFailure.setEditable(false);
		interactFailure.setPreferredSize(new Dimension(400, 100));
//		position = s;
		shift = shifted;
		isCenter = center;
		
	}
	
	
	@Override
	public void interact(ControlledDefensivePlayer player) {
		failureOverlay = new JFrame("Error");
		failureOverlay.isAlwaysOnTop();
		failureOverlay.setVisible(true);
		failureOverlay.setBounds(500, 500, 400, 100);
		failureOverlay.add(interactFailure);
		
//		System.out.println("You cannot move where an Offensive Player is.");
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, 50, 25);
		g.setColor(Color.BLACK);
		g.setFont(new Font("ss", Font.BOLD, 12));
//		g.drawString(position, 0, 20);
		if(shift) {
			if(isCenter) {
				g.fillRect(4, 0, 10, 10);
				isCenter = false;
				position = "";
			}
			else {
				g.fillOval(4, 0, 10, 10);
			}
		}
		else {
			g.fillOval(0, 0, 10, 10);
		}
	}
	public String toString() {
		return " W ";
	}

	@Override
	public boolean getInteractable() {
		// TODO Auto-generated method stub
		return interactable;
	}

	@Override
	public boolean getMovable() {
		// TODO Auto-generated method stub
		return movable;
	}


}