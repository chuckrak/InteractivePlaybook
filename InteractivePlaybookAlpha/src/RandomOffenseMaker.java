import java.util.Random;
/* Offensive Formations Key:
 *  0 = Pro/Boxcars
 *  1 = Lightning
 *  2 = Kings
 *  3 = Queens
 *  4 = Aces
 *  5 = Jokers
 * 	Default is left: boolean returns true means left formation
 * Defensive Fronts Key:
 *	0 = Angle
 *  1 = Eagle
 *  2 = Cardinal
 *	3 = Under
 *
 */
public class RandomOffenseMaker {
//	private Formation formation;
	public EntityHolder[][] baseFootballField = new EntityHolder[53][45];
//	private String center = "C", tackle = "T", guard = "G", quarterBack = "QB", runningBack = "RB", wideReciever = "WR", tightEnd = "TE";
	public boolean lineShift = true; 
	public int[] rushAlign, dropAlign, secondDropAlign;
	public boolean isFlipped, isGun, rbStrong, isSplit;
	public boolean leftStrength, squeeze, cardinal, under, eagle, angle;
	public boolean leftTightEnd, rightTightEnd;
	private String tackle = "T", guard = "G";
	public String coverageCall;
	public boolean isOdd, bothEndsDrop, isTrips, outCall, isBlue, isGreen;
	private String leftOrRight, front;
	public int coverage;
	public String playCall;
	public String offensiveFormation;
	private String geeHaw;
	public int randForm;
	public int defensiveFront;
	private Random rand;
	public int[] rock, tight, ghost, mask, off, splitOff, hip, green, blue; // defensive
	private int wideOutOn[], wideOutOff[], twins[], wing[], slot[], tightEnd[], proQb[], gunQb[], fullBack[], halfBack[], runningBack[], split[], offHalfBack[], trips2[], trips3[];
	private String formationName;
	public RandomOffenseMaker() {
		rand = new Random();
		isFlipped = rand.nextBoolean();
		isGun = rand.nextBoolean();
		rbStrong = rand.nextBoolean();
		isSplit = rand.nextBoolean();
		randForm = rand.nextInt(6);
		defensiveFront = rand.nextInt(4);
		squeeze = rand.nextBoolean();
		offensiveFormation = "";
		
//		MAKING OFFENSE ALIGNMENTS
		wideOutOn = new int [] {6,20};
//		wideOutOn = {6, 20};
		wideOutOff = new int[] {6,19};
		
		
		twins = new int [] {12, 18};
		wing = new int [] {18,19};
		slot = new int [] {19,19};
		tightEnd = new int [] {20, 20};
		proQb = new int [] {26,19};
		gunQb = new int [] {26,15};
		fullBack = new int [] {26,17};
		halfBack = new int [] {26,15};
		runningBack = new int [] {28,15};
		trips3 = new int[] {13,18};
		split = new int[] {38,20};
		offHalfBack = new int[] {24,14};
		trips2 = new int[] {9,18};		
//		MAKING DEFENSE ALIGNMENTS
		cardinal = false;
		eagle = false;
		under = false;
		angle = false;
		outCall = false;
		isBlue = false;
		isGreen = false;
		leftTightEnd = false;
		rightTightEnd = false;
		rushAlign = new int[2];
		rushAlign[0] = 0;
		rushAlign[1] = 0;
		dropAlign = new int[2];
		secondDropAlign = new int [2];
		secondDropAlign[0] = 0;
		secondDropAlign[1] = 0;
		dropAlign[0] = 0;
		dropAlign[1] = 0;
		rock = new int [2];
		ghost = new int[2];
		tight = new int[2];
		hip = new int [2];
		off = new int [2];
		splitOff = new int[2];
		mask = new int [2];
		rock[0] = 18;
		rock[1] = 22;
		ghost[0] = 21;
		ghost[1] = 21;
		tight[0] = 19;
		tight[1] = 21;
		mask[0] = 16;
		mask[1] = 22;
		off[0] = 14;
		off[1] = 23;
		splitOff[0] = 11;
		splitOff[1] = 23;
		hip[0] = 18;
		hip[1] = 25;
		green = new int[2];
		green[0] = 10;
		green[1] = 23;
		blue = new int[2];
		blue[0] = 16;
		blue[1] = 24;
		
//		MAKING FIELD
		makeEmptyPieceContent();
		makeOffensiveLineman(isFlipped);
		// makeDefensiveLineman(isFlipped); // moved here because coverage call needs to know if cardinal is happening
		switch(randForm) {
			case 0:
				makePro(isFlipped, isSplit);
				isTrips = false;
				break;
			case 1:
				makeLightning(isFlipped, isGun, rbStrong);
				isTrips = false;
				break;
			case 2:
				makeKings(isFlipped, isSplit, isGun, rbStrong);
				isTrips = true;
				break;
			case 3:
				makeQueens(isFlipped, isGun, rbStrong);
				isTrips = false;
				break;
			case 4: 
				isTrips = false;
				makeAces(isFlipped, isGun, rbStrong);
				break;
			case 5:
				makeEmpty(isFlipped, isGun);
				isTrips = true;
				break;
		}
		getStrength();
		makeDefensiveLineman(isFlipped); // moved here because coverage call needs to know if cardinal is happening
		coverageCall = randomCoverageCall(leftOrRight, isTrips);
		// makeDefensiveLineman(isFlipped); // moved here because coverage call needs to know if cardinal is happening
//		makeDefensiveLineman(isFlipped, makeOutCall());



		makeAdjustments(isOdd, isSplit, isFlipped);
		if(isGee()) {
			geeHaw = "Gee";
		}
		else {
			geeHaw = "Haw";
		}
		playCall = front + coverageCall;
	}
	public EntityHolder[][] makeEmptyPieceContent(){
		for(int x = 0; x < 53; x++) {
			for(int y = 0; y < 45; y++) {
				baseFootballField[x][y] = new EntityHolder(new Empty()); 
			}
		}
		return baseFootballField;
	}
	public void makeOffensiveLineman(boolean flipped) {
		int j = 20;
		if(!flipped) {
			for(int i = 22; i <=30; i+=2) {
				if(i == 22 || i == 30) {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, false));
				}
				if(i == 24 || i == 28) {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, false));
				}
				if(i==26) {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, true));
				}
			}
		}
		else {
			for(int i = 22; i <=30; i+=2) {
				if(i == 22 || i == 30) {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, false));
				}
				if(i == 24 || i == 28) {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, false));
				}
				else {
					baseFootballField[i][j] = new EntityHolder(new OffensivePlayer(lineShift, true));
				}
			}
			
		}

	}
	public void makeDefensiveLineman(boolean flipped) {
		// defensiveFront = rand.nextInt(4);
		if(outCall) {
			defensiveFront = 1;
		}
//		System.out.println("odd " + isOdd);
		// if(!isOdd) {
		// 	if(defensiveFront == 3) {
		// 		defensiveFront = 2;
		// 	}
		// }
		switch(defensiveFront){
			case 0: // angle 
				for(int i = 22, j = 21; i<=30; i+=4){
					baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle, 1));
					if(i ==26) {
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, 1));
					}
				}
				angle = true;
		        break;
			case 1: // eagle
				// for(int i = 22, j = 21; i<=30; i++) {
				// 	baseFootballField[i][j] = new EntityHolder(new Empty());
				// }
				if(!isGee()) {
					if(outCall) {
						baseFootballField[30][21] = new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					else {
						baseFootballField[28][21] = new EntityHolder(new DefensivePlayer(tackle, 2));
					}
					baseFootballField[26][21] = new EntityHolder(new DefensivePlayer(guard, 0));
					baseFootballField[22][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
				}
				else {
					if(outCall) {
						baseFootballField[22][21] = new EntityHolder(new DefensivePlayer(tackle, 2));
					}
					else {
						baseFootballField[24][21] = new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					baseFootballField[26][21] = new EntityHolder(new DefensivePlayer(guard, 2));
					baseFootballField[30][21] =  new EntityHolder(new DefensivePlayer(tackle, 2));
				}
				eagle = true;
				break;
			case 2: //cardinal
				if(!squeeze) {
					for(int i = 22, j = 21, s = 0; i<=30; i+=4){
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle, s));
						if(i == 26) {
							baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, s));
						}
						s++;
					}
				}
				else {
					for(int i = 22, j = 21, s = 2; i<=30; i+=4, s--){
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle,s));
						if(i == 26) {
							baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, s));
						}
					}
				}
				cardinal = true;
		        break;
			case 3: //under
				baseFootballField[24][21] = new EntityHolder(new DefensivePlayer(tackle, 0));//interior
				baseFootballField[28][21] = new EntityHolder(new DefensivePlayer(tackle, 0));//interior
				if(leftTightEnd) {
					baseFootballField[19][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
				}
				else {
					baseFootballField[21][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
				}
				under = true;
				break;
		}				
	}
	public void makeDefensiveLineman(boolean flipped, boolean outCall) {
		defensiveFront = rand.nextInt(4);
		if(outCall) {
			defensiveFront = 1;
		}
		if(defensiveFront == 3 && !isOdd) {
			defensiveFront = 2;
		}
		switch(defensiveFront){
			case 0: // angle 
				for(int i = 22, j = 21; i<=30; i+=4){
					baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle, 1));
					if(i ==26) {
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, 1));
					}
				}
		        break;
			case 1: // eagle
				for(int i = 22, j = 21; i<=30; i++) {
					baseFootballField[i][j] = new EntityHolder(new Empty());
				}
				if(isGee()) {
					if(outCall) {
						baseFootballField[22][21] = new EntityHolder(new DefensivePlayer(tackle, 2));
					}
					else {
						baseFootballField[24][21] = new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					baseFootballField[26][21] = new EntityHolder(new DefensivePlayer(guard, 2));
					baseFootballField[30][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
				}
				else {
					if(outCall) {
						baseFootballField[30][21] = new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					else {
						baseFootballField[28][21] = new EntityHolder(new DefensivePlayer(tackle, 2));
					}
					baseFootballField[26][21] = new EntityHolder(new DefensivePlayer(guard, 0));
					baseFootballField[22][21] =  new EntityHolder(new DefensivePlayer(tackle, 2));
				}
				eagle = true;
				break;
			case 2: //cardinal
				if(!squeeze) {
					for(int i = 22, j = 21, s = 0; i<=30; i+=4){
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle, s));
						if(i == 26) {
							baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, s));
						}
						s++;
					}
				}
				else {
					for(int i = 22, j = 21, s = 2; i<=30; i+=4, s--){
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle,s));
						if(i == 26) {
							baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, s));
						}
					}
				}
				cardinal = true;
		        break;
			case 3: //under
				if(!flipped) {
					baseFootballField[24][21] = new EntityHolder(new DefensivePlayer(tackle, 0));//interior
					baseFootballField[28][21] = new EntityHolder(new DefensivePlayer(tackle, 0));//interior
					if(rightTightEnd) {
						baseFootballField[33][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					else {
						baseFootballField[31][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
					}
				}
				else {
					baseFootballField[28][21] = new EntityHolder(new DefensivePlayer(tackle, 2));//interior
					baseFootballField[24][21] = new EntityHolder(new DefensivePlayer(tackle, 2));//interior
					if(leftTightEnd) {
						baseFootballField[19][21] =  new EntityHolder(new DefensivePlayer(tackle, 0));
					}
					else {
						baseFootballField[21][21] = new EntityHolder(new DefensivePlayer(tackle, 2));
					}
				}
				under = true;
				break;
		}				
	}
	//Pro Also contains BoxCars	
	public void makePro(boolean flipped, boolean isSplit) {
		offensiveFormation = "Pro";
		if(!flipped) {
			offensiveFormation += " Left";
			baseFootballField[wideOutOff[0]][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			if(isSplit) {
				baseFootballField[split[0]][split[1]] = new EntityHolder(new OffensivePlayer());
				rushAlign[0] = 31; //ghost
				rushAlign[1] = 21;
				dropAlign[0] = 18; // rock
				dropAlign[1] = 22;
				leftStrength = true;
				leftTightEnd = true;
				rightTightEnd = false;
			}
			else { // BOXCARS NOT PRO
				offensiveFormation = "Boxcars Left"; 
				baseFootballField[mirror(tightEnd)][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				rushAlign[0] = 33; //tight
				rushAlign[1] = 21;
				dropAlign[0] = 18; // rock
				dropAlign[1] = 22;
				leftStrength = true;
				leftTightEnd = true;
				rightTightEnd = true;
			}
			baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[proQb[0]][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[fullBack[0]][fullBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[halfBack[0]][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
		}
		else {
			offensiveFormation += " Right";
			baseFootballField[mirror(wideOutOff)][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			if(isSplit) {
				baseFootballField[mirror(split)][split[1]] = new EntityHolder(new OffensivePlayer());
				rushAlign[0] = 21;
				rushAlign[1] = 21;
				dropAlign[0] = 34; // rock
				dropAlign[1] = 22;
				leftStrength = false;
				leftTightEnd = false;
				rightTightEnd = true;
			
			}
			else { //BOXCARS
				offensiveFormation = "Boxcars Right";
				baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 19;
				rushAlign[1] = 21;
				dropAlign[0] = 34; // rock
				dropAlign[1] = 22;
				leftStrength = false;			
				leftTightEnd = true;
				rightTightEnd = true;
			}
			baseFootballField[mirror(tightEnd)][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(proQb)][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(fullBack)][fullBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(halfBack)][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));

		}
//		System.out.println(wideOutOn[0]);
	}
	public void makeLightning(boolean flipped, boolean gun, boolean rbStrong) {
		offensiveFormation = "Lightning";
		int rbX, rbXFlipped;
		if(rbStrong) {
			rbX = 24;
			rbXFlipped = 52-rbX;
		}
		else {
			rbX = 28;
			rbXFlipped = 52-rbX;
		}
		if(!flipped) {
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[26][15] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbX][15] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			else {
				baseFootballField[26][19] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[26][15] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[6][20] = new EntityHolder(new OffensivePlayer());
			baseFootballField[12][18] = new EntityHolder(new OffensivePlayer());
			baseFootballField[40][18] = new EntityHolder(new OffensivePlayer());
			baseFootballField[46][20] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 37; //mask
			rushAlign[1] = 22;
			dropAlign[0] = 14; //off
			dropAlign[1] = 23;
			leftStrength = true;
		}
		else {
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[26][15] = new EntityHolder(new OffensivePlayer(lineShift));
				baseFootballField[rbXFlipped][15] = new EntityHolder(new OffensivePlayer(lineShift));

			}
			else {
				baseFootballField[26][19] = new EntityHolder(new OffensivePlayer(lineShift));
				baseFootballField[26][15] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[6][20] = new EntityHolder(new OffensivePlayer());
			baseFootballField[12][18] = new EntityHolder(new OffensivePlayer());
			baseFootballField[40][18] = new EntityHolder(new OffensivePlayer());
			baseFootballField[46][20] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 37; //mask
			rushAlign[1] = 22;
			dropAlign[0] = 14; //off
			dropAlign[1] = 23;
			leftStrength = true;
			leftTightEnd = false;
			rightTightEnd = false;
		}
		if (rbStrong){
			offensiveFormation += " Strong";
		}
		else if (gun){
			offensiveFormation += " Weak";
		}
	}	
	public void makeKings(boolean flipped, boolean isSplit, boolean gun, boolean rbStrong) {
		int rbX, rbXFlipped;
		offensiveFormation = "Kings";
		if(!rbStrong) {
			rbX = mirror(runningBack);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!flipped) {
			if(isSplit) {
				offensiveFormation += " Split Left";
				baseFootballField[split[0]][split[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 31; //ghost
				rushAlign[1] = 21;
			}
			else {
				offensiveFormation += " Left";
				baseFootballField[mirror(tightEnd)][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 33; //tight
				rushAlign[1] = 21;
				rightTightEnd = true;
			}
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[gunQb[0]][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbX][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[proQb[0]][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[halfBack[0]][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[trips2[0]][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[trips3[0]][trips3[1]] = new EntityHolder(new OffensivePlayer());
			dropAlign[0] = 11; //split off
			dropAlign[1] = 23;
			leftStrength = true;
			if(rbStrong){
				offensiveFormation += " Strong";
			}
			else if(gun){
				offensiveFormation += " Weak";
			}
			
		}
		else {
			if(isSplit) {
				offensiveFormation += " Split Right";
				baseFootballField[mirror(split)][split[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 21; //ghost
				rushAlign[1] = 21;
			}
			else {
				offensiveFormation += " Right";
				baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 19; //tight
				rushAlign[1] = 21;
				leftTightEnd = true;
			}
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[mirror(gunQb)][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[mirror(proQb)][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[mirror(halfBack)][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[mirror(wideOutOn)][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(trips2)][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(trips3)][trips3[1]] = new EntityHolder(new OffensivePlayer());
			dropAlign[0] = 41; //split off
			dropAlign[1] = 23;
			leftStrength = false;
			if(rbStrong){
				offensiveFormation += " Strong";
			}
			else if(gun){
				offensiveFormation += " Weak";
			}
		}
		
	}
	public void makeQueens(boolean flipped, boolean gun, boolean rbStrong) {
		int rbX, rbXFlipped;
		offensiveFormation = "Queens";
		if(!rbStrong) {
			rbX = mirror(runningBack);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!flipped) {
			offensiveFormation += " Left";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[gunQb[0]][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbX][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			else {
				baseFootballField[proQb[0]][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[halfBack[0]][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[mirror(tightEnd)][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[twins[0]][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(wideOutOff)][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 33; //tight
			rushAlign[1] = 21;
			dropAlign[0] = 14; //off
			dropAlign[1] = 23;
			leftStrength = true;
			rightTightEnd = true;
			if (rbStrong){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}
			
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[mirror(gunQb)][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[mirror(proQb)][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[mirror(halfBack)][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}

			baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(wideOutOn)][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(twins)][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[wideOutOff[0]][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 19; //tight
			rushAlign[1] = 21;
			dropAlign[0] = 38; //off
			dropAlign[1] = 23;
			leftStrength = false;
			leftTightEnd = true;
			if (rbStrong){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}

		}
	}
	public void makeAces(boolean flipped, boolean gun, boolean rbStrong) {
		int rbX, rbXFlipped;
		offensiveFormation = "Aces";
		if(!rbStrong) {
			rbX = mirror(runningBack);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!flipped) {
			offensiveFormation += " Left"; 
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[gunQb[0]][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbX][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[proQb[0]][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[halfBack[0]][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[wideOutOff[0]][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[twins[0]][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(wideOutOn)][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 31; //ghost
			rushAlign[1] = 21;
			dropAlign[0] = 14; //off
			dropAlign[1] = 23;
			leftStrength = true;
			leftTightEnd = true;
			if (rbStrong){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}
			
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[mirror(gunQb)][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[mirror(proQb)][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[mirror(halfBack)][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[mirror(tightEnd)][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(wideOutOff)][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(twins)][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			rushAlign[0] = 21; //ghost
			rushAlign[1] = 21;
			dropAlign[0] = 38; //off
			dropAlign[1] = 23;
			leftStrength = false;
			rightTightEnd = true;
			if (rbStrong){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}
			
		}
	}
	public void makeEmpty(boolean flipped, boolean gun) {
		offensiveFormation = "Jokers";
		if(!flipped) {
			offensiveFormation += " Left";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[gunQb[0]][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
			}
			else {
				baseFootballField[proQb[0]][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
			}
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[trips2[0]][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[trips3[0]][trips3[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(twins)][twins[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[mirror(wideOutOn)][wideOutOn[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			rushAlign[0] = 37; //mask
			rushAlign[1] = 22;
			dropAlign[0] = 11; //split off
			dropAlign[1] = 23;
			leftStrength = true;
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[mirror(gunQb)][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 			
			}
			else {
				baseFootballField[mirror(proQb)][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
			}
			baseFootballField[mirror(wideOutOn)][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(trips2)][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[mirror(trips3)][trips3[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[twins[0]][twins[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			rushAlign[0] = 15; //mask
			rushAlign[1] = 22;
			dropAlign[0] = 41; //split off
			dropAlign[1] = 23;
			leftStrength = false;
		}
		leftTightEnd = false;
		rightTightEnd = false;
	}
	public boolean checkRushAlign(int x, int y) {
		if(rushAlign[0] == x && rushAlign[1] == y) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkDropAlign(int x, int y) {
		if(dropAlign[0] == x && dropAlign[1] == y) {
			return true;
		}
		else {
			return false;
		}
	}
	public int mirror(int[] alignment) {
		return 52 - alignment[0];
	}
	public String getFormation() {
//		randForm = rand.nextInt(6);
		switch(randForm) {
		case 0:
			return "Pro";
		case 1:
			return "Lightning";
		case 2:
			return "Kings";
		case 3:
			return "Queens";
		case 4: 
			return "Aces";
		case 5:
			return "Jokers";
	}
		return "";
	}
	public String randomCoverageCall(String strengthCall, boolean trips) {
		if(randForm == 2 /* KINGS */ || randForm == 5 /*JOKERS*/) { // TRIPS CHECK	
			coverage = rand.nextInt(12);
			switch(coverage) {
				case 0:
					if(!cardinal){
						coverageCall = "0 ";
						isOdd = false;
					}
					else{
						coverageCall = "5 ";
						isOdd = true;
					}
					break;
				case 1:
					coverageCall = "1 ";
					isOdd = true;
					break;
				case 2:
					coverageCall = "5 ";
					isOdd = true;
					break;
				case 3:
					coverageCall = "5 Tough "; 
					isOdd = true;
					break;
				case 4:
					coverageCall = "7 ";
					isOdd = true;
					break;
				case 5:
					if(!cardinal){
						coverageCall = "8 ";
						isOdd = false;
					}
					else{
						coverageCall = "5 Tough ";
						isOdd = true;
					}
					break;
				case 6:
					coverageCall = "Club ";
					isOdd = true;
					break;
				case 7:
					coverageCall = "Hang ";
					isOdd = true;
					break;
				case 8:
					coverageCall = "Blue ";
					isBlue = true;
					isOdd = true;
					break;
				case 9:
					coverageCall = "Green ";
					isGreen = true;
					isOdd = true;
					break;
				case 10:
					if(cardinal) {
						coverageCall = "White ";
					}
					else {
						coverageCall = "5 ";
					}
					isOdd = true;
					break;
				case 11:
					if(cardinal) {
						coverageCall = "Black ";
					}
					else {
						coverageCall = "5 Tough ";
					}
					isOdd = true;
					break;
			}
		}
		else {
			coverage = rand.nextInt(8);
			switch(coverage) {
				case 0:
					if(!cardinal){
						coverageCall = "0 ";
						isOdd = false;
					}
					else{
						coverageCall = "5 ";
						isOdd = true;
					}
					break;
				case 1:
					coverageCall = "1 ";
					isOdd = true;
					break;
				case 2:
					coverageCall = "5 ";
					isOdd = true;
					break;
				case 3:
					coverageCall = "5 Tough "; 
					isOdd = true;
					break;
				case 4:
					coverageCall = "7 ";
					isOdd = true;
					break;
				case 5:
					if(!cardinal){
						coverageCall = "8 ";
						isOdd = false;
					}
					else{
						coverageCall = "5 Tough ";
						isOdd = true;
					}
					break;
				case 6:
					if(cardinal) {
						coverageCall = "White ";
					}
					else {
						coverageCall = "5 ";
					}
					isOdd = true;
					break;
				case 7:
					if(cardinal) {
						coverageCall = "Black ";
					}
					else {
						coverageCall = "5 Tough ";
					}
					isOdd = true;
					break;
			}
			
		}	
		coverageCall = coverageCall + strengthCall;
		return coverageCall;
	}
	public void getStrength() {
		if (leftStrength) {	
			leftOrRight = "left";
			}
		else {
			leftOrRight = "right";
		}
	}
	public void makeAdjustments(boolean odd, boolean split, boolean flipped) {
		if(!odd) {
//			System.out.println("!odd adjustments trigerred");
			if(!flipped) {
				switch(randForm) {
				
				case 0:
					//PRO
					if(split) {
						dropAlign = hip;
						dropAlign[0] = mirror(dropAlign);
						rushAlign = tight;			
					}
					//BOXCARS
					else {
						dropAlign = rock;
						dropAlign[0] = mirror(dropAlign);
						rushAlign = tight;
					}
					break;
				case 1:
					dropAlign = off;
					dropAlign[0] = mirror(dropAlign);
					rushAlign = mask;
				case 2:
					if(split) {
						dropAlign = hip; 
						dropAlign[0] = mirror(dropAlign);
						rushAlign = mask;
					}
					else {
						dropAlign = rock;
						dropAlign[0] = mirror(rock);
						rushAlign = mask;
					}
					break;
				case 3:
					dropAlign = rock;
					dropAlign[0] = mirror(dropAlign);
					rushAlign = ghost;
				case 4:
					dropAlign = hip;
					dropAlign[0] = mirror(dropAlign);
					rushAlign = tight;
					break;
				case 5:
					dropAlign = off;
					rushAlign = mask;
					dropAlign[0] = mirror(dropAlign);
				}
			}
			else {
				switch(randForm) {
				case 0:
					if(split) {
						dropAlign = hip;
						rushAlign = tight;
						rushAlign[0] = mirror(rushAlign);
					}
					else {
						dropAlign = rock;
						rushAlign = tight;
						rushAlign[0] = mirror(rushAlign);
					}
					break;
				case 1:
					dropAlign = off;
					rushAlign = ghost;
					rushAlign[0] = mirror(rushAlign);
					break;
				case 2:
					if(split) {
						dropAlign = hip;
						rushAlign = mask;
						rushAlign[0] = mirror(rushAlign);
					}
					else {
						dropAlign = rock;
						rushAlign = mask;
						rushAlign[0] = mirror(rushAlign);
					}
					break;
				case 3:
					dropAlign = rock;
					rushAlign = mask;
					rushAlign[0] = mirror(rushAlign);
					break;
				case 4:
					dropAlign = hip;
					rushAlign = tight;
					rushAlign[0] = mirror(rushAlign);
					break;
				case 5:
					dropAlign = off;
					rushAlign = mask;
					rushAlign[0] = mirror(rushAlign);
					
				}
			}
		}
		if(cardinal) {
				if(!flipped) {
					switch(randForm) {
					case 0:
						if(split) {
							dropAlign = hip;
							dropAlign[0] = mirror(dropAlign);
							secondDropAlign = rock;
							secondDropAlign[0] = 18;				
						}
						else {
							dropAlign = rock;
							dropAlign[0] = 18;
							secondDropAlign = rock;
							secondDropAlign[0] = 34;
						}
						break;
					case 1:
						secondDropAlign = off;
						dropAlign = off;
						dropAlign[0] = mirror(dropAlign);
						break;
					case 2:
						if(split) {
							dropAlign = hip; 
							dropAlign[0] = mirror(dropAlign);
							secondDropAlign = splitOff;
						}
						else {
							dropAlign = rock; 
							dropAlign[0] = mirror(dropAlign);
							secondDropAlign = splitOff;
						}
						break;
					case 3: 
						secondDropAlign = off;
						dropAlign = rock;
						dropAlign[0] = mirror(dropAlign);
						break;
					case 4:
						dropAlign = hip;
						dropAlign[0] = mirror(dropAlign);
						secondDropAlign = rock;
						break;
					case 5:
						secondDropAlign = splitOff;
						dropAlign = off;
						dropAlign[0] = mirror(dropAlign);
						break;
					}
				}
				else {
					switch(randForm) {
					case 0:
						if(split) {
							dropAlign = hip;
//							dropAlign[0] = mirror(dropAlign);
							secondDropAlign = rock;
							secondDropAlign[0] = 34;				
						}
						else {
							dropAlign = rock;
							dropAlign[0] = 34;
//							dropAlign[0] = mirror(dropAlign);
							secondDropAlign = rock;
							secondDropAlign[0] = 18;
						}
						break;
					case 1:
						secondDropAlign = off;
						dropAlign = off;
						dropAlign[0] = mirror(dropAlign);
						break;
					case 2:
						if(split) {
							secondDropAlign = hip; 
							dropAlign = splitOff;
							dropAlign[0] = mirror(dropAlign);
						}
						else {
							secondDropAlign = rock; 
							dropAlign = splitOff;
							dropAlign[0] = mirror(dropAlign);
						}
						break;
					case 3: 
						dropAlign = off;
						secondDropAlign = rock;
						dropAlign[0] = mirror(dropAlign);
						break;
					case 4:
						dropAlign = off;
						dropAlign[0] = mirror(dropAlign);
						secondDropAlign = hip;
						break;
					case 5:
						secondDropAlign = off;
						dropAlign = splitOff;
						dropAlign[0] = mirror(dropAlign);
						break;
					}
				}
			}
//		System.out.println("cardinal " + cardinal);
//		System.out.print("The players are supposed to be at X1: ");
//		if(cardinal) {	
//			System.out.print(secondDropAlign[0] + " Y1: " + secondDropAlign[1] );
//		}
//		else {
//			System.out.print(rushAlign[0] + " Y1: " + rushAlign[1]);
//		}
//		System.out.println("and X2: " + dropAlign[0] + " Y2: " + dropAlign[1]);
//		System.out.println("rock " + rock[0] + rock[1]);
		if(cardinal && squeeze) {
			front = "Cardinal Squeeze ";
		}
		else if (cardinal) {
			front = "Cardinal ";
		}
		else if(eagle) {
			front = "Eagle ";
		}
		else if(under && isOdd) {
			front = "Under Pittsburgh ";
		}
		else if(under){
			front = "Under Wide ";
		}
		else if (angle){
			front = "Angle ";
		}
		if(isBlue) {
			if(!flipped) {
				secondDropAlign = blue;
			}
			else {
				dropAlign = blue;
				dropAlign[0] = mirror(blue);
			}
		}
		if(isGreen) {
			if(!flipped) {
				secondDropAlign = green;
			}
			else {
				dropAlign = green;
				dropAlign[0] = mirror(green);
			}
		}
//		System.out.println("under " + under);
		if(under) {
			switch(randForm) {
				case 0:
					rushAlign = tight;
					rushAlign[0] = mirror(tight);
					break;
				case 1:
					rushAlign = ghost;
					rushAlign[0] = mirror(ghost);
					break;
				case 2: 
					rushAlign = ghost;
					rushAlign[0] = mirror(ghost);
					break;
				case 3:
					rushAlign = ghost;
					rushAlign[0] = mirror(ghost);
					break;
				case 4:
					rushAlign = tight;
					rushAlign[0] = mirror(tight);
					break;
				case 5:
					rushAlign = ghost;
					rushAlign[0] = mirror(ghost);
			}
		}
//		System.out.print("The players are supposed to be at X1: ");
		if(cardinal) {	
//			System.out.print(secondDropAlign[0] + " Y1: " + secondDropAlign[1] );
		}
		else {
//			System.out.print(rushAlign[0] + " Y1: " + rushAlign[1]);
		}
//		System.out.println("and X2: " + dropAlign[0] + " Y2: " + dropAlign[1]);
		cardinal = false;
		under = false;
		eagle = false;
		isBlue = false;
		isGreen = false;
//		System.out.println("ls " + leftStrength);
//		System.out.println("odd" + isOdd);
//		System.out.println(isGee());
//		System.out.println("flipped " + isFlipped);
//		System.out.println(leftTightEnd + ", " + rightTightEnd);
//		System.out.println("and X2: " + dropAlign[0] + " Y2: " + dropAlign[1]);
	}
	public boolean makeOutCall(){
		if(defensiveFront == 1) {
			if(isOdd && (randForm == 1 || randForm == 5)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public boolean isGee() {
		if(isOdd && leftStrength) {
			return false;
		}
		else if(leftStrength && !isOdd) {
			return true;
		}
		else if(!leftStrength && isOdd) {
			return true;
		}
		else {
			return false;
		}
	}
}
	
