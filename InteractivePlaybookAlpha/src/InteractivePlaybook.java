import java.awt.*;
// import javax.swing.InputMap;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.AbstractAction;
// import javax.swing.Box.Filler;
// import javax.swing.text.Keymap;
public class InteractivePlaybook extends JFrame implements ActionListener {
	private static PlayManager play;
	private static final int InFocusedWindow = play.WHEN_IN_FOCUSED_WINDOW;
	private AlignmentChecker checker;
	private Box wall;
	private JFrame alignmentOverlay, coverageOverlay, outCallOverlay, correctPosOverlay, formCheckOverlay;
	private JTextField coverage, alignment, outCall, correctPos, formation;
	private Action pressedLeft, pressedRight, pressedUp, pressedDown;
	private int player = 1;
	private int[] playerX, playerY;
	private long start;
	private long totalTime = 0, averageTime;
	private int numPlays = 0;
	public Color green = new Color(42, 110, 42);
	public Color blue = new Color(52, 157, 223);
//	private String leftOrRight;
	// private String strength;
	private String playCall;
	// private boolean tripsFormation;
//	private Filler fill;
//	private JPanel wall3;
//	private JButton reset, clear, instructions, size, start, AIbutton;
	public InteractivePlaybook() {
		super("Interactive Playbook");
//		add(listener);
		//instantiating objects
//		setBackground(Color.GREEN);
		play = new PlayManager();
		start = System.currentTimeMillis();
		checker = new AlignmentChecker();
		playerX = new int[2];
		playerY = new int[2];
		playCall = play.field.offense.playCall;
		coverage = new JTextField(playCall);
		coverage.setEditable(false);
		coverage.setPreferredSize(new Dimension(100, 100));
		coverage.setHorizontalAlignment(JTextField.CENTER);
		addButtons();
		defineActions();
		//setting up panel
		Container c = getContentPane();
//		setBackground(Color.GREEN);
		setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		wall = Box.createHorizontalBox();
		wall.add(play);
		checker.setPreferredSize(new Dimension(300, 500));
		checker.setMaximumSize(new Dimension(300, 500));
		checker.setOpaque(false);
		wall.add(checker);
		c.add(wall);
		c.setBackground(new Color(31,80,31));

	}
	public static void main(String[] args) {
		
		InteractivePlaybook window = new InteractivePlaybook();
		window.setBounds(100, 100, 1200, 732);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(false);
//		window.pack();
		window.setVisible(true);
//		window.setBackground(Color.blue);
//		System.out.println("width : " + play.getWidth() + " height: " + play.getHeight());
//		window.setLayout(new GridLayout());
	

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == checker.checkAlignment) {
			alignment = new JTextField();
			alignment.setEditable(false);
			alignment.setHorizontalAlignment(JTextField.CENTER);
			alignment.setPreferredSize(new Dimension(600, 100));
			alignment.setText("The players are not both in the correct aligment");
			alignmentOverlay = new JFrame("Instructions");
			alignmentOverlay.isAlwaysOnTop();
			alignmentOverlay.setVisible(true);
			if(correctAlignment(1) && correctAlignment(2))  {
					double time = elapsedTime();
					alignment.setText("Both players are in the correct aligment. You solved this in " + time + " seconds");
					averageTime = totalTime / numPlays;
					checker.averageTime.setText("Average Time: " + averageTime + " seconds" );
			}
			else {
//				System.out.println("P1" + playerX[0] + playerY[0]);
//				System.out.println("P2" + playerX[1] + playerY[1]);

			}

			alignmentOverlay.add(alignment);
			alignmentOverlay.setBounds(500, 500, 500, 500);
			alignmentOverlay.pack();
		}
		if(b == checker.getCoverage) {
			coverageOverlay = new JFrame("Play Call");
			coverageOverlay.isAlwaysOnTop();
			coverageOverlay.setVisible(true);
			coverageOverlay.add(coverage);
			coverageOverlay.setBounds(500, 500, 500, 500);
			coverageOverlay.setMinimumSize(new Dimension(400, 100));
			coverageOverlay.pack();
		}
		if(b == checker.changeShade) {
			switch(play.field.getPlayer(player).shade) {
				case 0:
					play.field.getPlayer(player).shade++;
					checker.currentShade = "Head Up";
					checker.changeShade.setText("Alignment: " + checker.currentShade + " Click to Change");
					repaint();
					break;
				case 1:
					play.field.getPlayer(player).shade++;
					checker.currentShade = "Right Shade";
					checker.changeShade.setText("Alignment: " + checker.currentShade + " Click to Change");
					repaint();
					break;
				case 2:
					play.field.getPlayer(player).shade = 0;
					checker.currentShade = "Left Shade";
					checker.changeShade.setText("Alignment: " + checker.currentShade + " Click to Change");
					repaint();
					break;
		
					}

			}
		if(b == checker.switchPlayer) {
			if(player == 1) {
				player = 2;
			}
			else {
				player = 1;
			}
		}
		
		if(b == checker.newPlay) {
			play.field.makeNewPlay();
			start = System.currentTimeMillis();
//			coverageOverlay = new JFrame("Play Call");
//			getStrength();
//			tripsFormation = isTrips();
//			playCall = play.field.offense.randomCoverageCall(leftOrRight, tripsFormation);
			playCall = play.field.offense.playCall;
			coverage.setText(playCall);
			play.updateMap();
			checker.offensiveFormation.setText("DELETE & enter offensive formation");
			coverageOverlay = new JFrame("Play Call");
			coverageOverlay.isAlwaysOnTop();
			coverageOverlay.setVisible(true);
			coverageOverlay.add(coverage);
			coverageOverlay.setBounds(500, 500, 500, 500);
			coverageOverlay.setMinimumSize(new Dimension(400, 100));
			coverageOverlay.pack();
//			System.out.println(play.field.offense.isFlipped);
			repaint();
		}
		
		if(b == checker.outCall) {
//			outCallOverlay = new JFrame("Out Call");
			outCall = new JTextField();
			outCall.setEditable(false);
			outCall.setHorizontalAlignment(JTextField.CENTER);
			outCall.setPreferredSize(new Dimension(400, 100));
			if(play.field.offense.makeOutCall()) {
				play.field.offense.makeDefensiveLineman(play.field.offense.isFlipped, true);
				play.updateMap();
				repaint();
				outCall.setText("You correctly made an out call");
				outCallOverlay = new JFrame("Out Call");
				outCallOverlay.setVisible(true);
				outCallOverlay.add(outCall);
				outCallOverlay.setBounds(500, 500, 500, 500);
//				outCall.setText("You correctly made an out call");
				outCallOverlay.setMinimumSize(new Dimension(400, 100));
				outCallOverlay.pack();
				
			}
			else {
				outCallOverlay = new JFrame("Out Call");
				outCall.setText("An out call was not warranted");
				outCallOverlay.add(outCall);
				outCallOverlay.setBounds(500, 500, 500, 500);
				outCallOverlay.setVisible(true);
//				outCall.setText("You correctly made an out call");
				outCallOverlay.setMinimumSize(new Dimension(400, 100));
				outCallOverlay.pack();
			}
		}
		if(b == checker.correctPosition){
			correctPos = new JTextField();
			correctPos.setEditable(false);
			correctPos.setHorizontalAlignment(JTextField.CENTER);
			correctPos.setPreferredSize(new Dimension(400,100));
			correctPosOverlay = new JFrame("Correct Positions");
			if (play.field.offense.cardinal) {
				correctPos.setText("Position 1: " + makeCoords(play.field.offense.dropAlign[0], play.field.offense.dropAlign[1]) + 
						"\nPosition 2: " + makeCoords(play.field.offense.secondDropAlign[0], play.field.offense.secondDropAlign[1]));	
			}
			else {
				correctPos.setText("Position 1: " + makeCoords(play.field.offense.dropAlign[0], play.field.offense.dropAlign[1]) + 
						"\nPosition 2: " + makeCoords(play.field.offense.rushAlign[0], play.field.offense.rushAlign[1]));	

			}
			correctPosOverlay.add(correctPos);
			correctPosOverlay.setBounds(500,500,500,500);
			correctPosOverlay.setVisible(true);
			correctPosOverlay.setMinimumSize(new Dimension(400,100));
			correctPosOverlay.pack();
		}
		if(b == checker.checkFormation){
			formation = new JTextField();
			formation.setEditable(false);
			formation.setHorizontalAlignment(JTextField.CENTER);
			formation.setPreferredSize(new Dimension(400,100));
			formCheckOverlay = new JFrame("Offensive Formation Check");
			String oForm = checker.offensiveFormation.getText();
			oForm = oForm.strip().toLowerCase();
			if (oForm.equals(play.field.offense.offensiveFormation.toLowerCase().strip())){
				formation.setText("Correct!");			
			}
			else{
				formation.setText("Incorrect. Formation is " + play.field.offense.offensiveFormation);
			}
			formCheckOverlay.add(formation);
			formCheckOverlay.setBounds(500,500,500,500);
			formCheckOverlay.setVisible(true);
			formCheckOverlay.setMinimumSize(new Dimension(400,100));
			formCheckOverlay.pack();
		}
		


		
	}
	public boolean correctAlignment(int player) {
		if(playerX[player-1] == play.field.offense.rushAlign[0] && playerY[player-1] == play.field.offense.rushAlign[1]) {
			return true;
		}
		else if(playerX[player-1] == play.field.offense.dropAlign[0] && playerY[player-1] == play.field.offense.dropAlign[1]) {
			return true;
		}
		else if(playerX[player-1] == play.field.offense.secondDropAlign[0] && playerY[player-1] == play.field.offense.secondDropAlign[1]) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private String makeCoords(int a, int b) {
		return "(" + a + ", " + b + ")";
	}
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        totalTime += (now - start) / 1000.0;
        numPlays += 1;
        return (now - start) / 1000.0;
    }
    
    private void addButtons() {
		checker.checkAlignment.addActionListener(this);
		checker.getCoverage.addActionListener(this);
		checker.changeShade.addActionListener(this);
		checker.switchPlayer.addActionListener(this);
		checker.newPlay.addActionListener(this);
		checker.outCall.addActionListener(this);
		checker.correctPosition.addActionListener(this);
		checker.currentPosition.addActionListener(this);
		checker.checkFormation.addActionListener(this);
    }
    
    private void defineActions() {
    	pressedLeft = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.move(3, player);
				if(player - 1 ==0) {
					playerX[player-1] = play.field.player1X;
					playerY[player-1] = play.field.player1Y;
				}
				else {
					playerX[player-1] = play.field.player2X;
					playerY[player-1] = play.field.player2Y;
				}
				checker.currentPosition.setText("Player 1: " + makeCoords(playerX[0], playerY[0]) + "\nPlayer 2: " + makeCoords(playerX[1], playerY[1]));
				repaint();
			}
		};
		pressedRight = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.move(1, player);
				if(player - 1 ==0) {
					playerX[player-1] = play.field.player1X;
					playerY[player-1] = play.field.player1Y;
				}
				else {
					playerX[player-1] = play.field.player2X;
					playerY[player-1] = play.field.player2Y;
				}
				checker.currentPosition.setText("Player 1: " + makeCoords(playerX[0], playerY[0]) + "\nPlayer 2: " + makeCoords(playerX[1], playerY[1]));
				repaint();
			}
		};
		pressedDown = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.move(2, player);
				if(player - 1 ==0) {
					playerX[player-1] = play.field.player1X;
					playerY[player-1] = play.field.player1Y;
				}
				else {
					playerX[player-1] = play.field.player2X;
					playerY[player-1] = play.field.player2Y;
				}
				checker.currentPosition.setText("Player 1: " + makeCoords(playerX[0], playerY[0]) + "\nPlayer 2: " + makeCoords(playerX[1], playerY[1]));
				repaint();
			}
		};
		pressedUp = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play.move(0, player);
				if(player - 1 ==0) {
					playerX[player-1] = play.field.player1X;
					playerY[player-1] = play.field.player1Y;
				}
				else {
					playerX[player-1] = play.field.player2X;
					playerY[player-1] = play.field.player2Y;
				}
				checker.currentPosition.setText("Player 1: " + makeCoords(playerX[0], playerY[0]) + "\nPlayer 2: " + makeCoords(playerX[1], playerY[1]));
				repaint();
			}
		};
		play.getInputMap(InFocusedWindow).put(KeyStroke.getKeyStroke("pressed LEFT"), "pressedLeft");	
		play.getInputMap(InFocusedWindow).put(KeyStroke.getKeyStroke("pressed DOWN"), "pressedDown");
		play.getInputMap(InFocusedWindow).put(KeyStroke.getKeyStroke("pressed RIGHT"), "pressedRight");
		play.getInputMap(InFocusedWindow).put(KeyStroke.getKeyStroke("pressed UP"), "pressedUp");
		play.getActionMap().put("pressedLeft", pressedLeft);
		play.getActionMap().put("pressedUp", pressedUp);
		play.getActionMap().put("pressedRight", pressedRight);
		play.getActionMap().put("pressedDown", pressedDown);

    }
		// DEBUGGING
		// System.out.println("cardinal : " + play.field.offense.cardinal);
		// System.out.println("formation : " + play.field.offense.offensiveFormation);
		// System.out.println("flipped : " + play.field.offense.isFlipped);
		// System.out.println("odd : " + play.field.offense.isOdd);
		// System.out.println("Left Strength: " + play.field.offense.leftStrength);
}

	
