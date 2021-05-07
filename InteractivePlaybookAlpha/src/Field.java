import java.awt.*;
import javax.swing.*;
import java.util.Random;
public class Field {
	public EntityHolder footballField[][] = new EntityHolder[53][45];
	public int player1X, player1Y, player2X, player2Y;
	private String center = "C", tackle = "T", guard = "G", quarterBack = "QB", runningBack = "RB", wideReciever = "WR", tightEnd = "TE";
	private String corner = "C", safety = "S",  linebacker = "B", defensiveEnd = "E"; 
	private JTextField moveFailure;
	private JFrame error;
//	public int defensiveFront;
	public RandomOffenseMaker offense;
	private Random rand;
	private boolean squeeze;
	public int defensiveShade = 1; // angle
//	private int angleShade = 4; //no actual shade
	public int controlledShade = 0;
	public boolean lineShift = true;
	public Field() {
//		makeEmptyPieceContent();
//		offense = new RandomOffenseMaker();
		rand = new Random();
		squeeze = rand.nextBoolean();
		makeNewPlay();
		footballField = offense.baseFootballField;
		makeControlledPlayers();
//		offense.makeDefensiveLineman(offense.isFlipped);
		makeDBs();
		makeLinebackers();
		moveFailure = new JTextField("That is out of bounds.");
		moveFailure.setEditable(false);
		moveFailure.setPreferredSize(new Dimension(400, 100));
	}
	public void makeDBs() {
		// System.out.println("mdbsLeft Strength: " + offense.leftStrength);
		// System.out.println("mdbs odd: " + offense.isOdd);
		if(!offense.isOdd) {
			if(offense.leftStrength/* && offense.defensiveFront != 2*/) {
				footballField[offense.off[0]][offense.off[1]] = new EntityHolder(new DefensivePlayer("$"));
			}
			else/* if(offense.defensiveFront!=2)*/ { 
				footballField[offense.mirror(offense.off)][offense.off[1]] = new EntityHolder(new DefensivePlayer("$"));
			}
		}
		else {
			footballField[32][30] = new EntityHolder(new DefensivePlayer("$"));
		}
		footballField[5][27] = new EntityHolder(new DefensivePlayer(corner));
		footballField[22][30] = new EntityHolder(new DefensivePlayer("F"));
		footballField[47][27] = new EntityHolder(new DefensivePlayer(corner));
	}
	public void makeControlledPlayers() {
		player1X = 20;
		player1Y = 35;
		player2X = 32;
		player2Y = 35;
		footballField[player1X][player1Y] = new EntityHolder(new ControlledDefensivePlayer(defensiveEnd));
		// if(offense.defensiveFront == 3) {
			// footballField[player2X][player2Y] = new EntityHolder(new ControlledDefensivePlayer("S"));	
		// }
		// else {
		footballField[player2X][player2Y] = new EntityHolder(new ControlledDefensivePlayer("E"));
		// }

	}
	public void makeLinebackers() {
		if(offense.defensiveFront == 3) {
			if(offense.isOdd && offense.leftStrength) {
				switch(offense.randForm) {
				case 0:
					footballField[26][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					footballField[30][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign[0] = 20;
					offense.dropAlign[1] = 25;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 1:
					footballField[25][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					footballField[38][23] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 2:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 3:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 4: 
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 5:
					footballField[16][24] = new EntityHolder(new DefensivePlayer(linebacker, 1));
					footballField[38][23] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				}
			}
			else if(offense.leftStrength){
				switch(offense.randForm) {
				case 0:
					footballField[20][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					footballField[26][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign[0] = 30;
					offense.dropAlign[1] = 25;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 1:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					offense.dropAlign[0] = offense.mirror(offense.off);
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 2:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 3:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 4: 
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				case 5:
					footballField[16][24] = new EntityHolder(new DefensivePlayer(linebacker, 1));
					footballField[38][23] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					if(offense.leftTightEnd) {
						offense.rushAlign = offense.tight;
					}
					else {
						offense.rushAlign = offense.ghost;
					}
					break;
				}
				
			}
			else if(offense.isOdd) {
				switch(offense.randForm) {
				case 0:
					footballField[26][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					footballField[22][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign[0] = 32;
					offense.dropAlign[1] = 25;
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				case 1:
					footballField[25][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					footballField[38][23] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					offense.dropAlign[0] = offense.mirror(offense.off);
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				case 2:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					offense.dropAlign[0] = offense.mirror(offense.splitOff);
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				case 3:
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					offense.dropAlign[0] = offense.mirror(offense.off);
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				case 4: 
					footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
					footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.off;
					offense.dropAlign[0] = offense.mirror(offense.off);
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				case 5:
					footballField[16][24] = new EntityHolder(new DefensivePlayer(linebacker, 1));
					footballField[38][23] = new EntityHolder(new DefensivePlayer(linebacker, 2));
					offense.dropAlign = offense.splitOff;
					offense.dropAlign[0] = offense.mirror(offense.splitOff);
					if(offense.rightTightEnd) {
						offense.rushAlign = offense.tight;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					else {
						offense.rushAlign = offense.ghost;
						offense.rushAlign[0] = offense.mirror(offense.tight);
					}
					break;
				}
			}
		}
		else {
			footballField[24][25] = new EntityHolder(new DefensivePlayer(linebacker, 0));
			footballField[28][25] = new EntityHolder(new DefensivePlayer(linebacker, 2));
		}
	}
	public boolean move(int currentX, int currentY, int direction) {
		try {
			int directionX = 0, directionY = 0;
			int otherX = currentX, otherY = currentY;
			switch(direction) {
			case 0:
				otherX = currentX;
				otherY = currentY-1;
//				playerY--;
				break;
			case 1:
				otherX = currentX+1;
				otherY = currentY;
//				playerX++;
				break;
			case 2:
				otherX = currentX;
				otherY = currentY+1;
//				playerY++;
				break;
			case 3:
				otherX = currentX-1;
				otherY = currentY;
//				playerX--;
				break;
			}
			otherX += directionX;
			otherY += directionY;
			if(footballField[currentX][currentY].swap(footballField[otherX][otherY])){
//				ControlledDefensivePlayer movingPlayer = (ControlledDefensivePlayer)((Entity)(footballField[otherX][otherY].getEntity()));
//				movingPlayer.changeCoordinates(direction);
				return true;
			}
			else {
				return false;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("That is out of bounds.");
			error = new JFrame("Error");
			error.isAlwaysOnTop();
			error.setVisible(true);
			error.setBounds(500, 500, 400, 100);
			error.add(moveFailure);
			return false;
		}
	}
	public ControlledDefensivePlayer getPlayer(int player) {
			if(player == 1) {
				return (ControlledDefensivePlayer)footballField[player1X][player1Y].getEntity();
			}
			else {
				return (ControlledDefensivePlayer)footballField[player2X][player2Y].getEntity();
		
			}
	}	
	public void makeNewPlay() {
		offense = new RandomOffenseMaker();
		footballField = offense.baseFootballField;
		makeControlledPlayers();
//		offense.makeDefensiveLineman(offense.isFlipped);
		makeDBs();
		makeLinebackers();
		
	}
}
	
