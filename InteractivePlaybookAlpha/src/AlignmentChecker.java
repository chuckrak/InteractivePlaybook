import java.awt.*;
import java.util.Random;

import javax.swing.*;
public class AlignmentChecker extends JPanel{
	public JButton checkAlignment, getCoverage, changeShade, switchPlayer, newPlay, outCall, correctPosition, checkFormation;
	public JTextField currentPosition, averageTime, offensiveFormation;
	public String currentShade = "Left Shade";
	public String coverageCall;
	private int coverage = 0;
	private Random rand;
	public AlignmentChecker() {
		// MAKES A GRID LAYOUT, IF you add more buttons, increase the first int
		super(new GridLayout(11, 1, 10, 10));
		checkAlignment = new JButton("Check Alignment");
		getCoverage = new JButton("Get Coverage");
		changeShade = new JButton("Alignment: " + currentShade + " Click to Change");
		switchPlayer = new JButton("Switch Player");
		newPlay = new JButton("New Play");
		outCall = new JButton("Make Out Call");
		offensiveFormation = new JTextField("DELETE & enter offensive formation");
		checkFormation = new JButton("Check Offensive Formation");
		currentPosition = new JTextField("Current Position: ");
		averageTime = new JTextField("Average Time: ");
		correctPosition = new JButton("I give up!");
		averageTime.setEditable(false);
		currentPosition.setEditable(false);
		
//		changeShade.setPreferredSize(new Dimension(100, 100));
//		changeShade.setMinimumSize(new Dimension(100, 100));
		rand = new Random();
		setOpaque(false);
//		Box box = Box.createVerticalBox(); NOT NEEDED CAUSE GRID LAYOUT does the same thing
		add(getCoverage);
		add(checkAlignment);
		add(changeShade);
		add(switchPlayer);
		add(newPlay);
		add(offensiveFormation);
		add(checkFormation);
		add(outCall);
		add(currentPosition);
		add(averageTime);
		add(correctPosition);
		
//		checkAlignment.setBackground(new Color(52, 157, 223));
//		checkAlignment.setBorderPainted(false);
//		checkAlignment.setOpaque(true);
//		getCoverage.setBackground(new Color(52, 157, 223));
//		changeShade.setOpa 

		setMinimumSize(new Dimension(300, 600));
	}
	@Override
	public void paintComponent(Graphics g) {
//		g.setBackground(Color.BLUE);
	}

}
