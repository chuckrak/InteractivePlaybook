import java.awt.*;
import javax.swing.InputMap;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.AbstractAction;
import javax.swing.Box.Filler;
import javax.swing.text.Keymap;
public class InteractivePlaybook extends JFrame implements ActionListener {
	private static PlayManager play;
	private static final int InFocusedWindow = play.WHEN_IN_FOCUSED_WINDOW;
	private AlignmentChecker checker;
	private Box wall;
	private JFrame alignmentOverlay, coverageOverlay, outCallOverlay;
	private JTextField coverage, alignment, outCall;
	private Action pressedLeft, pressedRight, pressedUp, pressedDown;
	private int player = 1;
	private int[] playerX, playerY;
//	private String leftOrRight;
	private String strength;
	private String playCall;
	private boolean tripsFormation;
//	private Filler fill;
//	private JPanel wall3;
//	private JButton reset, clear, instructions, size, start, AIbutton;
	public InteractivePlaybook() {
		super("Interactive Playbook");
//		add(listener);
		//instantiating objects
//		setBackground(Color.GREEN);
		play = new PlayManager();
		checker = new AlignmentChecker();
		playerX = new int[2];
		playerY = new int[2];
		playCall = play.field.offense.playCall;
		coverage = new JTextField(playCall);
		coverage.setEditable(false);
		coverage.setPreferredSize(new Dimension(100, 100));
		coverage.setHorizontalAlignment(JTextField.CENTER);
		alignment = new JTextField("You are not in the right alignment");
		alignment.setEditable(false);
		alignment.setHorizontalAlignment(JTextField.CENTER);
		alignment.setPreferredSize(new Dimension(400, 100));
		outCall = new JTextField("An out call was not warranted");
		outCall.setEditable(false);
		outCall.setHorizontalAlignment(JTextField.CENTER);
		outCall.setPreferredSize(new Dimension(400, 100));
		checker.checkAlignment.addActionListener(this);
		checker.getCoverage.addActionListener(this);
		checker.changeShade.addActionListener(this);
		checker.switchPlayer.addActionListener(this);
		checker.newPlay.addActionListener(this);
		checker.outCall.addActionListener(this);
		//setting up key bindings
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
//				System.out.println("play.move called with player " + player);
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
//				System.out.println("play.move called with player " + player);
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
//				System.out.println("play.move called with player " + player);
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

		//setting up panel
		Container c = getContentPane();
		setBackground(Color.GREEN);
		setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		wall = Box.createHorizontalBox();
		wall.add(play);
		checker.setPreferredSize(new Dimension(300, 500));
		checker.setMaximumSize(new Dimension(300, 500));
		checker.setOpaque(false);
		wall.add(checker);
//		wall.setBackground(Color.GREEN);
		c.add(wall);

	}
	public static void main(String[] args) {
		
		InteractivePlaybook window = new InteractivePlaybook();
		window.setBounds(100, 100, 1200, 700);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
//		window.setLayout(new GridLayout());
	

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if(b == checker.checkAlignment) {
			alignment.setText("The players are not both in the correct aligment");
			alignmentOverlay = new JFrame("Instructions");
			alignmentOverlay.isAlwaysOnTop();
			alignmentOverlay.setVisible(true);
			if(correctAlignment(1) && correctAlignment(2))  {
					alignment.setText("Both players are in the correct aligment");
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
//			coverageOverlay = new JFrame("Play Call");
//			getStrength();
//			tripsFormation = isTrips();
//			playCall = play.field.offense.randomCoverageCall(leftOrRight, tripsFormation);
			playCall = play.field.offense.playCall;
			coverage.setText(playCall);
			play.updateMap();
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
}
