import java.awt.*;
import java.util.Random;

import javax.swing.*;
public class AlignmentChecker extends JPanel{
	public JButton checkAlignment, getCoverage, changeShade, switchPlayer, newPlay, outCall;
	public String currentShade = "Left Shade";
	public String coverageCall;
	private int coverage = 0;
	private Random rand;
	public AlignmentChecker() {
		super(new GridLayout(6, 1, 10, 10));
		checkAlignment = new JButton("Check Alignment");
		getCoverage = new JButton("Get Coverage");
		changeShade = new JButton("Alignment: " + currentShade + " Click to Change");
		switchPlayer = new JButton("Switch Player");
		newPlay = new JButton("New Play");
		outCall = new JButton("Make Out Call");
		changeShade.setPreferredSize(new Dimension(100, 300));
		changeShade.setMinimumSize(new Dimension(100, 300));
		rand = new Random();
//		setLayout();
		setOpaque(false);
		setBackground(Color.BLUE);
//		Box box = Box.createVerticalBox();
		add(getCoverage);
		add(checkAlignment);
		add(changeShade);
		add(switchPlayer);
		add(newPlay);
		add(outCall);
//		box.add(layout);
//		add(box);
	}
	@Override
	public void paintComponent(Graphics g) {
//		g.setBackground(Color.BLUE);
	}

}
