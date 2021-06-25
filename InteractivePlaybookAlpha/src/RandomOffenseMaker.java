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
	public EntityHolder[][] baseFootballField = new EntityHolder[53][45];
	public boolean lineShift = true; 
	public int[] rushAlign, dropAlign;
	public boolean rightFormation, isGun, rbRight, isSplit;
	public boolean squeeze, cardinal, under, eagle, angle, gee;
	public boolean leftTightEnd, rightTightEnd;
	private String tackle = "T", guard = "G";
	public String coverageCall;
	public boolean isOdd, bothEndsDrop, isTrips, outCall, isBlue, isGreen;
	private String leftOrRight, front;
	public int coverage;
	public String playCall, offensiveFormation;
	private String geeHaw;
	public int randForm;
	public int defensiveFront;
	private Random rand;
	public int[] rock, tight, ghost, fiveTech, sevenTech, mask, off, splitOff, hip, green, blue, sixty, fifty; // defensive
	private int[] wideOutOn, wideOutOff, twins, wing, slot, tightEnd, proQb, gunQb, fullBack, halfBack, runningBack, split, offHalfBack, trips2, trips3;
	private String formationName;
	public RandomOffenseMaker() {
		rand = new Random();
		rightFormation = rand.nextBoolean();
		isGun = rand.nextBoolean();
		rbRight = rand.nextBoolean();
		isSplit = rand.nextBoolean();
		randForm = rand.nextInt(6);
		defensiveFront = rand.nextInt(4);
		squeeze = rand.nextBoolean();
		offensiveFormation = "";
		
//		MAKING OFFENSE ALIGNMENTS
		wideOutOn = new int [] {6,20};
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
		rushAlign = new int[] {0, 0};
		dropAlign = new int[] {0, 0};
		rock = new int [] {18, 22};
		ghost = new int[] {21, 21};
		fiveTech = new int[] {22, 21};
		sevenTech = new int[]{20, 21};
		tight = new int[] {19, 21};
		hip = new int [] {18, 25};
		off = new int [] {14, 23};
		splitOff = new int[] {11, 23};
		mask = new int [] {15, 22};
		green = new int[] {10, 23};
		blue = new int[] {16, 24};
		sixty = new int[] {20, 25};
		fifty = new int[] {22, 25};

//		MAKING FIELD
		makeEmptyPieceContent();
		makeOffensiveLineman(rightFormation);
		switch(randForm) {
			case 0:
				makePro(rightFormation, isSplit);
				isTrips = false;
				break;
			case 1:
				makeLightning(rightFormation, isGun, rbRight);
				isTrips = false;
				break;
			case 2:
				makeKings(rightFormation, isSplit, isGun, rbRight);
				isTrips = true;
				break;
			case 3:
				makeQueens(rightFormation, isGun, rbRight);
				isTrips = false;
				break;
			case 4: 
				isTrips = false;
				makeAces(rightFormation, isGun, rbRight);
				break;
			case 5:
				makeEmpty(rightFormation, isGun);
				isTrips = true;
				break;
		}
		getStrength();
		//because coverage call needs to know if cardinal is happening
		switch(defensiveFront){
			case 0:
				angle = true;
				break;
			case 1:
				eagle = true;
				break;
			case 2:
				cardinal = true;
				break;
			case 3:
				under = true;
				break;
		}
		coverageCall = randomCoverageCall(leftOrRight, isTrips);
		gee = isGee();
		makeDefensiveLineman(rightFormation);
		// System.out.println("right " +  rightFormation +" under " + under + " cardinal " + cardinal + " eagle " + eagle + " odd " + isOdd + " split " + isSplit + " blue " + isBlue + " green " + isGreen);
		setCorrectAlignments(rightFormation, isSplit, isOdd);
		nameFront();
		if(gee) {
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
	public void makeOffensiveLineman(boolean right) {
		int j = 20;
		if(!right) {
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
	public void makeDefensiveLineman(boolean right) {
		// if(outCall) {
		// 	defensiveFront = 1;
		// }
		switch(defensiveFront){
			case 0: // angle 
				for(int i = 22, j = 21; i<=30; i+=4){
					baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(tackle, 1));
					if(i ==26) {
						baseFootballField[i][j] = new EntityHolder(new DefensivePlayer(guard, 1));
					}
				}
				// angle = true;
		        break;
			case 1: // eagle
				if(!gee) {
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
				// eagle = true;
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
				// cardinal = true;
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
				// under = true;
				break;
		}				
	}
	public void makeDefensiveLineman(boolean right, boolean outCall) {
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
				if(!right) {
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
	public void makePro(boolean right, boolean isSplit) {
		offensiveFormation = "Pro";
		if(!right) {
			offensiveFormation += " Left";
			baseFootballField[wideOutOff[0]][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			if(isSplit) {
				baseFootballField[split[0]][split[1]] = new EntityHolder(new OffensivePlayer());
				leftTightEnd = true;
				rightTightEnd = false;
			}
			else { // BOXCARS NOT PRO
				offensiveFormation = "Boxcars Left"; 
				baseFootballField[flipAlign(tightEnd[0])][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
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
			baseFootballField[flipAlign(wideOutOff[0])][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			if(isSplit) {
				baseFootballField[flipAlign(split[0])][split[1]] = new EntityHolder(new OffensivePlayer());
				leftTightEnd = false;
				rightTightEnd = true;
			
			}
			else { //BOXCARS
				offensiveFormation = "Boxcars Right";
				baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));		
				leftTightEnd = true;
				rightTightEnd = true;
			}
			baseFootballField[flipAlign(tightEnd[0])][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(proQb[0])][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(fullBack[0])][fullBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(halfBack[0])][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));

		}
//		System.out.println(wideOutOn[0]);
	}
	public void makeLightning(boolean right, boolean gun, boolean rbRight) {
		offensiveFormation = "Lightning";
		int rbX, rbXFlipped;
		if(rbRight) {
			rbX = 24;
			rbXFlipped = 52-rbX;
		}
		else {
			rbX = 28;
			rbXFlipped = 52-rbX;
		}
		if(!right) {
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
			if (rbRight){
				offensiveFormation += " Weak";
			}
			else if (gun){
				offensiveFormation += " Strong";
			}
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
			leftTightEnd = false;
			rightTightEnd = false;
			if (rbRight){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}
		}
	}	
	public void makeKings(boolean right, boolean isSplit, boolean gun, boolean rbRight) {
		int rbX, rbXFlipped;
		offensiveFormation = "Kings";
		if(!rbRight) {
			rbX = flipAlign(runningBack[0]);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!right) {
			if(isSplit) {
				offensiveFormation += " Split Left";
				baseFootballField[split[0]][split[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				rushAlign[0] = 31; //ghost
				rushAlign[1] = 21;
			}
			else {
				offensiveFormation += " Left";
				baseFootballField[flipAlign(tightEnd[0])][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
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
			if(rbRight && gun){
				offensiveFormation += " Weak";
			}
			else if(gun){
				offensiveFormation += " Strong";
			}
			
		}
		else {
			if(isSplit) {
				offensiveFormation += " Split Right";
				baseFootballField[flipAlign(split[0])][split[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				// rushAlign[0] = 21; //ghost
				// rushAlign[1] = 21;
			}
			else {
				offensiveFormation += " Right";
				baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
				// rushAlign[0] = 19; //tight
				// rushAlign[1] = 21;
				leftTightEnd = true;
			}
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[flipAlign(gunQb[0])][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[flipAlign(proQb[0])][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[flipAlign(halfBack[0])][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[flipAlign(wideOutOn[0])][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(trips2[0])][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(trips3[0])][trips3[1]] = new EntityHolder(new OffensivePlayer());
			if(rbRight && gun){
				offensiveFormation += " Strong";
			}
			else if(gun){
				offensiveFormation += " Weak";
			}
		}
		
	}
	public void makeQueens(boolean right, boolean gun, boolean rbRight) {
		int rbX, rbXFlipped;
		offensiveFormation = "Queens";
		if(!rbRight) {
			rbX = flipAlign(runningBack[0]);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!right) {
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
			baseFootballField[flipAlign(tightEnd[0])][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[twins[0]][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(wideOutOff[0])][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			rightTightEnd = true;
			if (rbRight){
				offensiveFormation += " Weak";
			}
			else if (gun){
				offensiveFormation += " Strong";
			}
			
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[flipAlign(gunQb[0])][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[flipAlign(proQb[0])][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[flipAlign(halfBack[0])][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}

			baseFootballField[tightEnd[0]][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(wideOutOn[0])][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(twins[0])][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[wideOutOff[0]][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			leftTightEnd = true;
			if (rbRight){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}

		}
	}
	public void makeAces(boolean right, boolean gun, boolean rbRight) {
		int rbX, rbXFlipped;
		offensiveFormation = "Aces";
		if(!rbRight) {
			rbX = flipAlign(runningBack[0]);
			rbXFlipped =52-rbX;
		}
		else {
			rbX = runningBack[0];
			rbXFlipped =52-rbX;
		}
		if(!right) {
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
			baseFootballField[flipAlign(wideOutOn[0])][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			leftTightEnd = true;
			if (rbRight){
				offensiveFormation += " Weak";
			}
			else if (gun){
				offensiveFormation += " Strong";
			}
			
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[flipAlign(gunQb[0])][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[rbXFlipped][runningBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			
			}
			else {
				baseFootballField[flipAlign(proQb[0])][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
				baseFootballField[flipAlign(halfBack[0])][halfBack[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			}
			baseFootballField[flipAlign(tightEnd[0])][tightEnd[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(wideOutOff[0])][wideOutOff[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(twins[0])][twins[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			rightTightEnd = true;
			if (rbRight){
				offensiveFormation += " Strong";
			}
			else if (gun){
				offensiveFormation += " Weak";
			}
			
		}
	}
	public void makeEmpty(boolean right, boolean gun) {
		offensiveFormation = "Jokers";
		if(!right) {
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
			baseFootballField[flipAlign(twins[0])][twins[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[flipAlign(wideOutOn[0])][wideOutOn[1]] = new EntityHolder(new OffensivePlayer(lineShift));
		}
		else {
			offensiveFormation += " Right";
			if(gun) {
				offensiveFormation += " Gun";
				baseFootballField[flipAlign(gunQb[0])][gunQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 			
			}
			else {
				baseFootballField[flipAlign(proQb[0])][proQb[1]] = new EntityHolder(new OffensivePlayer(lineShift)); 
			}
			baseFootballField[flipAlign(wideOutOn[0])][wideOutOn[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(trips2[0])][trips2[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[flipAlign(trips3[0])][trips3[1]] = new EntityHolder(new OffensivePlayer());
			baseFootballField[twins[0]][twins[1]] = new EntityHolder(new OffensivePlayer(lineShift));
			baseFootballField[wideOutOn[0]][wideOutOn[1]] = new EntityHolder(new OffensivePlayer(lineShift));
		}
		leftTightEnd = false;
		rightTightEnd = false;
	}
	public boolean checkRushAlign(int x, int y) {
		return rushAlign[0] == x && rushAlign[1] == y;
	}
	public boolean checkDropAlign(int x, int y) {
		return dropAlign[0] == x && dropAlign[1] == y;
	}
	public int[] mirror(int[] alignment) {
		int[] mirroredAlignment;
		mirroredAlignment = new int[]{52 - alignment[0], alignment[1]};
		return mirroredAlignment;

	}
	public int flipAlign(int align){
		return 52 - align;
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
		if (rightFormation) {	
			leftOrRight = "Right";
			}
		else {
			leftOrRight = "Left";
		}
	}
	public void nameFront(){
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
	}
	public void setCorrectAlignments(boolean right, boolean split, boolean odd){
		switch(randForm){
			case 0: //Pro and Boxcars
				if(split){ //PRO
					if(right){ // Pro Right
						if(odd){ // 5 Right
							if(cardinal){
								rushAlign = hip;
								dropAlign = mirror(rock);
							}
							else if(under){ // under pitt
								rushAlign = mirror(tight);
								dropAlign = mirror(sixty);
							}
							else if(eagle){
								dropAlign = mirror(rock);
								rushAlign = fiveTech; 
							}
							else{ // angle
								dropAlign = mirror(rock);
								rushAlign = ghost;
							}
						}
						else{ // 0 Right, No Cardinal
							if(under){ // Under Wide
								rushAlign = mirror(tight);
								dropAlign = fifty;
							}
							else if(eagle){
								rushAlign = mirror(sevenTech);
								dropAlign = hip;
							}
							else{//angle
								dropAlign = hip;
								rushAlign = mirror(tight);
							}
						}
					}
					else{ // Pro Left
						if(odd){ // 5 Left
							if(cardinal){
								rushAlign = mirror(hip);
								dropAlign = rock;
							}
							else if(under){ // under pitt
								rushAlign = mirror(ghost);
								dropAlign = sixty;
							}
							else if(eagle){
								dropAlign = rock;
								rushAlign = mirror(fiveTech); 
							}
							else{ // angle
								dropAlign = rock;
								rushAlign = mirror(ghost);
							}
						}
						else{ // 0 Left, No Cardinal
							if(under){ // Under Wide vs Pro Left
								rushAlign = mirror(tight);
								dropAlign = mirror(fifty);
							}
							else if(eagle){
								rushAlign = mirror(sevenTech);
								dropAlign = hip;
							}
							else{//angle
								dropAlign = hip;
								rushAlign = mirror(tight);
							}
						}
					}
				}				
				else{ //BOXCARS
					if(right){ // Boxcars right
						if(odd){ //5 right vs boxcars right
							if(cardinal){
								rushAlign = tight;
								dropAlign = mirror(rock);
							}
							else if(under){ // under pitt
								rushAlign = mirror(tight);
								dropAlign = mirror(sixty);
							}
							else if(eagle){
								dropAlign = mirror(rock);
								rushAlign = sevenTech; 
							}
							else{ // angle
								dropAlign = mirror(rock);
								rushAlign = tight;
							}
						}
						else{ //0 Right vs Boxcars Right
							if(under){ // Under Wide
								rushAlign = mirror(tight);
								dropAlign = sixty;
							}
							else if(eagle){
								rushAlign = mirror(sevenTech);
								dropAlign = tight;
							}
							else{//angle
								dropAlign = tight;
								rushAlign = mirror(tight);
							}
						}

					}
					else{
						if(odd){ //5 left vs boxcars left
							if(cardinal){
								rushAlign = mirror(tight);
								dropAlign = rock;
							}
							else if(under){ // under pitt
								rushAlign = mirror(tight);
								dropAlign = sixty;
							}
							else if(eagle){
								dropAlign = rock;
								rushAlign = mirror(sevenTech); 
							}
							else{ // angle
								dropAlign = rock;
								rushAlign = mirror(tight);
							}
						}
						else{ //0 Left vs Boxcars Left
							if(under){ // Under Wide
								rushAlign = mirror(tight);
								dropAlign = mirror(sixty);
							}
							else if(eagle){
								rushAlign = sevenTech;
								dropAlign = mirror(sevenTech);
							}
							else{//angle
								dropAlign = mirror(tight);
								rushAlign = tight;
							}
						}
					}
				}
				break;
			case 1: //Lightning
				if(right){ //Lightning Right
					if(odd){ // 5 Right
						if(cardinal){
							rushAlign = off;
							dropAlign = mirror(off);
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = mirror(off);
						}
						else if(eagle){
							dropAlign = mirror(off);
							rushAlign = mask; 
						}
						else{ // angle
							dropAlign = mirror(off);
							rushAlign = mask;
						}
					}
					else{ // 0 Right
						if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = off;
						}
						else if(eagle){
							dropAlign = off;
							rushAlign = mirror(fiveTech); 
						}
						else{ // angle
							dropAlign = off;
							rushAlign = mirror(ghost);
						}
					}			
				}
				else{ // Lightning Left
					if(odd){ // 5 Left
						if(cardinal){
							rushAlign = mirror(off);
							dropAlign = off;
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = off;
						}
						else if(eagle){
							dropAlign = off;
							rushAlign = mirror(mask); 
						}
						else{ // angle
							dropAlign = off;
							rushAlign = mirror(mask);
						}
					}
					else{ // 0 Left
						if(under){ // under wide
							rushAlign = mirror(ghost);
							dropAlign = mirror(off);
						}
						else if(eagle){
							dropAlign = mirror(off);
							rushAlign = fiveTech; 
						}
						else{ // angle
							dropAlign = mirror(off);
							rushAlign = ghost;
						}
					}
				}
				break;
			case 2: //Kings
				if(split){ //Kings Split
					if(right){ // Kings Split Right
						if(odd){ // 5 Right
							if(cardinal){
								rushAlign = hip;
								dropAlign = mirror(splitOff);
							}
							else if(under){ // under pitt
								rushAlign = mirror(ghost);
								dropAlign = mirror(splitOff);
							}
							else if(eagle){
								dropAlign = mirror(splitOff);
								rushAlign = fiveTech; 
							}
							else{ // angle
								dropAlign = mirror(splitOff);
								rushAlign = ghost;
							}
							if(isBlue){
								dropAlign = mirror(blue);
								if(under){
									rushAlign = mirror(ghost);
								}
								else{
									rushAlign = ghost;
								}
							}
							else if(isGreen){
								dropAlign = mirror(green);
								if(under){
									rushAlign = mirror(ghost);
								}
								else{
									rushAlign = ghost;
								}
							}
						}
						else{ // 0 Right, No Cardinal
							if(under){ // Under Wide
								rushAlign = mirror(ghost);
								dropAlign = fifty;
							}
							else if(eagle){
								rushAlign = mirror(fiveTech);
								dropAlign = hip;
							}
							else{ //angle
								dropAlign = hip;
								rushAlign = mirror(ghost);
							}
						}
					}
					else{ // Kings Split Left
						if(odd){ // 5 Left
							if(cardinal){
								rushAlign = mirror(hip);
								dropAlign = splitOff;
							}
							else if(under){ // under pitt
								rushAlign = mirror(ghost);
								dropAlign = splitOff;
							}
							else if(eagle){
								dropAlign = splitOff;
								rushAlign = mirror(fiveTech); 
							}
							else{ // angle
								dropAlign = splitOff;
								rushAlign = mirror(ghost);
							}
							if(isBlue){
								dropAlign = blue;
								rushAlign = mirror(ghost);
							}
							else if(isGreen){
								dropAlign = green;
								rushAlign = mirror(ghost);
							}
						}
						else{ // 0 Left, No Cardinal
							if(under){ // Under Wide vs Kings Split Left
								rushAlign = mirror(ghost);
								dropAlign = mirror(fifty);
							}
							else if(eagle){
								rushAlign = fiveTech;
								dropAlign = mirror(hip);
							}
							else{//angle
								dropAlign = mirror(hip);
								rushAlign = ghost;
							}
						}
					}
				}	
				else{ //Kings Regular
					if(right){ // Kings right
						if(odd){ //5 right vs Kings right
							if(cardinal){
								rushAlign = tight;
								dropAlign = mirror(splitOff);
							}
							else if(under){ // under pitt
								rushAlign = mirror(ghost);
								dropAlign = mirror(splitOff);
							}
							else if(eagle){
								dropAlign = mirror(splitOff);
								rushAlign = sevenTech; 
							}
							else{ // angle
								dropAlign = mirror(splitOff);
								rushAlign = tight;
							}
							if(isBlue){
								dropAlign = mirror(blue);
								if(under){
									rushAlign = mirror(tight);
								}
								else{
									rushAlign = tight;
								}
							}
							else if(isGreen){
								dropAlign = mirror(green);
								if(under){
									rushAlign = mirror(tight);
								}
								else{
									rushAlign = tight;
								}
							}
						}
						else{ //0 Right vs Kings Right
							if(under){ // Under Wide
								rushAlign = mirror(ghost);
								dropAlign = sixty;
							}
							else if(eagle){
								rushAlign = mirror(fiveTech);
								dropAlign = sevenTech;
							}
							else{ //angle
								dropAlign = tight;
								rushAlign = mirror(ghost);
							}
						}

					}
					else{
						if(odd){ //5 left vs Kings left
							if(cardinal){
								rushAlign = mirror(tight);
								dropAlign = splitOff;
							}
							else if(under){ // under pitt
								rushAlign = mirror(tight);
								dropAlign = splitOff;
							}
							else if(eagle){
								dropAlign = splitOff;
								rushAlign = mirror(sevenTech); 
							}
							else{ // angle
								dropAlign = splitOff;
								rushAlign = mirror(tight);
							}
							if(isBlue){
								dropAlign = blue;
								if(under){
									rushAlign = mirror(tight);
								}
								else{
									rushAlign = tight;
								}
							}
							else if(isGreen){
								dropAlign = green;
								if(under){
									rushAlign = mirror(tight);
								}
								else{
									rushAlign = tight;
								}
							}
						}
						else{ //0 Left vs Kings Left
							if(under){ // Under Wide
								rushAlign = mirror(tight);
								dropAlign = mirror(sixty);
							}
							else if(eagle){
								rushAlign = fiveTech;
								dropAlign = mirror(sevenTech);
							}
							else{//angle
								dropAlign = mirror(tight);
								rushAlign = ghost;
							}
						}
					}
				}
				break;
			case 3: //Queens
				if(right){ //Queens Right
					if(odd){ // 5 Right
						if(cardinal){
							rushAlign = rock;
							dropAlign = mirror(off);
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = mirror(off);
						}
						else if(eagle){
							dropAlign = mirror(off);
							rushAlign = sevenTech; 
						}
						else{ // angle
							dropAlign = mirror(off);
							rushAlign = tight;
						}
					}
					else{ // 0 Right
						if(under){ // under wide
							rushAlign = mirror(ghost);
							dropAlign = sixty;
						}
						else if(eagle){
							dropAlign = rock;
							rushAlign = mirror(fiveTech); 
						}
						else{ // angle
							dropAlign = rock;
							rushAlign = mirror(ghost);
						}
					}			
				}
				else{ // Queens Left
					if(odd){ // 5 Left
						if(cardinal){
							rushAlign = mirror(rock);
							dropAlign = off;
						}
						else if(under){ // under pitt
							rushAlign = mirror(tight);
							dropAlign = off;
						}
						else if(eagle){
							dropAlign = off;
							rushAlign = mirror(sevenTech); 
						}
						else{ // angle
							dropAlign = off;
							rushAlign = mirror(tight);
						}
					}
					else{ // 0 Left
						if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = mirror(sixty);
						}
						else if(eagle){
							dropAlign = mirror(rock);
							rushAlign = fiveTech; 
						}
						else{ // angle
							dropAlign = mirror(rock);
							rushAlign = ghost;
						}
					}
				}
				break;
			case 4: //Aces
				if(right){ //Aces Right
					if(odd){ // 5 Right
						if(cardinal){
							rushAlign = hip;
							dropAlign = mirror(off);
						}
						else if(under){ // under pitt
							rushAlign = mirror(tight);
							dropAlign = mirror(off);
						}
						else if(eagle){
							dropAlign = mirror(off);
							rushAlign = fiveTech; 
						}
						else{ // angle
							dropAlign = mirror(off);
							rushAlign = ghost;
						}
					}
					else{ // 0 Right
						if(under){ // under wide
							rushAlign = mirror(ghost);
							dropAlign = fifty;
						}
						else if(eagle){
							dropAlign = hip;
							rushAlign = mirror(sevenTech); 
						}
						else{ // angle
							dropAlign = hip;
							rushAlign = mirror(tight);
						}
					}			
				}
				else{ // Aces Left
					if(odd){ // 5 Left
						if(cardinal){
							rushAlign = mirror(hip);
							dropAlign = off;
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = off;
						}
						else if(eagle){
							dropAlign = off;
							rushAlign = mirror(fiveTech); 
						}
						else{ // angle
							dropAlign = off;
							rushAlign = mirror(ghost);
						}
					}
					else{ // 0 Right
						if(under){ // under wide
							rushAlign = mirror(ghost);
							dropAlign = fifty;
						}
						else if(eagle){
							dropAlign = mirror(hip);
							rushAlign = sevenTech; 
						}
						else{ // angle
							dropAlign = mirror(hip);
							rushAlign = tight;
						}
					}	
				}
				break;
			case 5: // Jokers
				if(right){ // Jokes Right
					if(odd){ // 5 Right
						if(cardinal){
							rushAlign = off;
							dropAlign = mirror(splitOff);
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = mirror(splitOff);
						}
						else if(eagle){
							dropAlign = mirror(splitOff);
							rushAlign = mask; 
						}
						else{ // angle
							dropAlign = mirror(splitOff);
							rushAlign = mask;
						}
						if(isBlue){
							dropAlign = mirror(blue);
							if(under){
								rushAlign = mirror(ghost);
							}
							else{
								rushAlign = mask;
							}
						}
						else if(isGreen){
							dropAlign = mirror(green);
							if(under){
								rushAlign = mirror(ghost);
							}
							else{
								rushAlign = mask;
							}
						}
					}
					else{ // 0 Right, No Cardinal
						if(under){ // Under Wide
							rushAlign = mirror(ghost);
							dropAlign = off;
						}
						else if(eagle){
							rushAlign = mirror(fiveTech);
							dropAlign = off;
						}
						else{ //angle
							dropAlign = off;
							rushAlign = mirror(ghost);
						}
					}
				}
				else{ // Jokers Left
					if(odd){ // 5 Right
						if(cardinal){
							rushAlign = mirror(off);
							dropAlign = splitOff;
						}
						else if(under){ // under pitt
							rushAlign = mirror(ghost);
							dropAlign = splitOff;
						}
						else if(eagle){
							dropAlign = splitOff;
							rushAlign = mirror(mask); 
						}
						else{ // angle
							dropAlign = splitOff;
							rushAlign = mirror(mask);
						}
						if(isBlue){
							dropAlign = blue;
							if(under){
								rushAlign = mirror(ghost);
							}
							else{
								rushAlign = mirror(mask);
							}
						}
						else if(isGreen){
							dropAlign = green;
							if(under){
								rushAlign = mirror(ghost);
							}
							else{
								rushAlign = mirror(mask);
							}
						}
					}
					else{ // 0 Right, No Cardinal
						if(under){ // Under Wide
							rushAlign = mirror(ghost);
							dropAlign = mirror(off);
						}
						else if(eagle){
							rushAlign = fiveTech;
							dropAlign = mirror(off);
						}
						else{ //angle
							dropAlign = mirror(off);
							rushAlign = ghost;
						}
					}
				}
		}
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
		if(isOdd && !rightFormation) {
			return false;
		}
		else if(!rightFormation) {
			return true;
		}
		else if(isOdd) {
			return true;
		}
		else {
			return false;
		}
	}	
}