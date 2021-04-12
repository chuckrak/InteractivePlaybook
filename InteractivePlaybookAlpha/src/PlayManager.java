import java.awt.*;
import java.util.Random;

import javax.swing.*;
public class PlayManager extends JPanel{
	private int width = 900, height = 704, fieldYards = 44; 
	private double pixelsPerYard = height/fieldYards;
	public Field field;
//	private Box wall;
//	private AlignmentChecker checker;
//	private JPanel play;
	private Random rand;
	public PlayManager() {
		super(new GridLayout(45, 53));
		
		field = new Field();
//		checker = new AlignmentChecker();
		setBackground(new Color(42, 110, 42));
//		setLayout(new GridLayout(45,50));
//		play = new JPanel(new GridLayout(45, 50));
		updateMap();
//		wall = Box.createHorizontalBox();
//		wall.add(play);
//		wall.add(Box.createHorizontalStrut(500));
//		wall.add(checker);

//		add(wall);
//		drawFieldLines();
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFieldLines(g);
		drawNumbers(g);
		drawEndzone(g);
		signature(g);
	}
	public void updateMap() {
		removeAll();
		revalidate();
		repaint();

		for(int x = 0; x < 45; x++) {
			for(int y = 0; y < 53; y++) {
				JComponent current = (JComponent) field.footballField[y][x].getEntity();
				if(x==20 & y==26) {
					current = (JComponent) new OffensivePlayer(true, true);
				}
				add(current);
			}
		}
	}

	public void move(int direction, int player) {//TODO
		switch(player) {
			case 1:
				if(field.move(field.player1X, field.player1Y, direction)) {	
					switch (direction){
						case 0:
							field.player1Y--;
							break;
						case 1:
							field.player1X++;
							break;
						case 2:
							field.player1Y++;
							break;
						case 3:
							field.player1X--;
							break;
					}
					updateMap();
				}
				break;
			case 2:
				if(field.move(field.player2X, field.player2Y, direction)) {	
					switch (direction){
						case 0:
							field.player2Y--;
							break;
						case 1:
							field.player2X++;
							break;
						case 2:
							field.player2Y++;
							break;
						case 3:
							field.player2X--;
							break;
					}
					updateMap();
				}
				break;
		}
//		System.out.println("P1X : " + field.player1X + " P1Y " + field.player1Y);
//		System.out.println("P2X : " + field.player2X + " P2Y " + field.player2Y);
	}
	public void drawFieldLines(Graphics g) {
		g.setColor(Color.WHITE);
		for(int i = (int) pixelsPerYard*10; i <= width+1; i += pixelsPerYard*5) { // 5 yard lines
			g.setColor(Color.WHITE);
			g.drawLine(0, i, width, i);
		}
		for(int i = (int) pixelsPerYard*10; i < 1000; i += pixelsPerYard) { // hashes
			g.drawLine(0, i, width/40, i);//leftmost hash
			g.drawLine((int)((4 * (double) width) / 10), i, (int)(4.25 * (double) width / 10), i);//left middle hash
			g.drawLine((int)((5.75 * (double) width) / 10), i, (int)((6 * (double) width) / 10), i);//right middle hash
			g.drawLine((39*width)/40, i, width, i);//rightmost hash
		}
	}
	public void drawEndzone(Graphics g) {
		g.setColor(new Color(52, 157, 223));
		g.fillRect(0, 0, width, (int) pixelsPerYard*10);
		g.setColor(new Color(232, 159, 32));
		g.setFont(new Font("Bold", Font.PLAIN, 100));
		g.drawString("POMONA-PITZER", width/20, 120);
	}
	public void drawNumbers(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		int fieldNumber1 = 10;
		int fieldNumber2 = 10;
		Graphics2D g2 = (Graphics2D)g;
		for(int i = (int)pixelsPerYard*20 - 47; i < width; i += pixelsPerYard*10) { // left numbers
			drawRotate(g2, width/10, i, 90, "" + fieldNumber1);
//			drawRotate(g2, 799, i, 270, "" + fieldNumber1);
//			g.drawString("" + fieldNumber, 101, i);
//			g.drawString("" + fieldNumber, 799, i);
			fieldNumber1 += 10;	
		}
		for(int i = (int)pixelsPerYard*20 + 47; i < width; i += pixelsPerYard*10) { // right numbers
//			drawRotate(g2, 101, i, 90, "" + fieldNumber1);
			drawRotate(g2, 9 * width / 10, i, 270, "" + fieldNumber2);
//			g.drawString("" + fieldNumber, 101, i);
//			g.drawString("" + fieldNumber, 799, i);
			fieldNumber2 += 10;	
		}
	}
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) 
	{    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	}    
	public void signature(Graphics g) {
		g.setColor(Color.ORANGE);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
		g.drawString("by Chuck Rak", 15, 15);
	}
	
}
