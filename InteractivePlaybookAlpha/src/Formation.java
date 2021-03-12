public class Formation {
	public String formation;
	private int wideOut[], twins[], wing[], slot[], tightEnd[], proQb[], gunQb[], fullBack[], halfBack[], runningBack[], splitWR[], offHalfBack[], trips2[], trips3[];
	public Formation(String f) {
		formation = f;
		wideOut = new int [2];
		twins = new int [2];
		wing = new int [2];
		slot = new int [2];
		tightEnd = new int [2];
		proQb = new int [2];
		gunQb = new int [2];
		fullBack = new int [2];
		halfBack = new int [2];
		runningBack = new int [2];
		trips3 = new int[2];
		splitWR = new int[2];
		offHalfBack = new int[2];
		trips2 = new int[2];
		splitWR[0] = 39;
		splitWR[1] = 20;
		trips3 = slot;
		offHalfBack[0] = 25;
		offHalfBack[1] = 14;
		trips2[0] = 10;
		trips2[1] = 18;
		wideOut[0] = 7;
		wideOut[1] = 20;
		twins[0] = 13;
		twins[1] = 18;
		wing[0] = 19;
		wing[1] = 19;
		slot[0] = 20;
		slot[1] = 19;
		tightEnd[0] = 21;
		tightEnd[1] = 20;
		proQb[0] = 27;
		proQb[1] = 19;
		gunQb[0] = 27;
		gunQb[1] = 15;
		fullBack[0] = 27;
		fullBack[1] = 16;
		halfBack[0] = 27;
		halfBack[1] = 14;
		runningBack[0] = 29;
		runningBack[1] = 15;
	}
	
	public void mirrorAlignment(int[] alignment) {
		alignment[0] = 53 - alignment[0];
	}
}
