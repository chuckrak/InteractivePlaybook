import java.awt.*;

import javax.swing.*;
public class AlignmentChecker extends JPanel{
	public JButton checkAlignment, getCoverage, changeShade, switchPlayer, newPlay, outCall, correctPosition, checkFormation;
	public JTextField currentPosition, averageTime, offensiveFormation;
	public String currentShade = "Left Shade";
	public String coverageCall;
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
		setOpaque(false);
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

		setMinimumSize(new Dimension(300, 600));
	}
// 	@Override
// 	public void paintComponent(Graphics g) {
// //		g.setBackground(Color.BLUE);
// 	}

}
