import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

/**
 * SortingAnimation.java
 * This animation sorts a series of colors in a gradient and later
 * displays the techlab logo.
 * @author Dean Stratakos, Tejas Bhat
 * @version 1.0
 * @since 06/10/2016
 */
@SuppressWarnings("serial")
public class SortingAnimation extends JFrame {
	
	// for double buffering
	private Image dbImage;
	private Graphics dbg;
	
	int timer = 0;
	
	// Create an instance of this JFrame on run. No need to modify this part of the
	// program unless you are adding new sorting algorithms that can be selected.
	public static void main(String[] args) {

		int size = 10000;
		int width = 800;

		float hue = 0.56425744f;
		float saturation = 0.32298258f;

		SortingAnimation sort = new SortingAnimation(width, hue, saturation);

		int[] array = sort.randomize(size);

		sort.bubbleSort(array);
	}

	// Reference to the current array being drawn.
	private int[] currentArray = { 0 };

	// Drawing options
	private int width = 800;
	private float hue = 0.5f;
	private float saturation = 0.5f;
	
	boolean[][] techlab = new boolean[width][width + 22];
	boolean doneSorting = false;
	
	/**
	 * Constructs the JFrame for the sorting animation.
	 * @param width - the width of the window
	 * @param buffer - a number n such that every nth frame is drawn (default 1)
	 */
	public SortingAnimation(int width, float hue, float saturation) {
		// Create the window.
		super("Sorting Animation");
		setSize(width, 22 + width);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// Store display parameters
		this.width = width;
		this.hue = hue;
		this.saturation = saturation;
		
		setTechlab();
		
		setVisible(true);
		setResizable(false);
        System.out.println("Hue: " + hue + " Saturation: " + saturation);
	}
	
	/**
	 * Creates a random array of values, where each value is between 0 and 255.
	 * @param size - the length of the array
	 */
	private int[] randomize(int size) {
		// Create an array of that size
		int[] array = new int[size];
		// Go to each position in the array
		for (int i = 0 ; i < array.length ; i++) {
			array[i] = (int) (Math.random() * 256);
		}
		return array;
	}

	/**
	 * Bubble sort with display parameters.
	 */
	private void bubbleSort(int[] array) {

		for (int repeat = 0 ; repeat < array.length ; repeat++) {
			// Go to every index in the list except the last one
			for (int checkIndex = array.length - 1; checkIndex > 0; checkIndex--) {
				if (array[checkIndex] > array[checkIndex - 1]) {
					// Swap if there is an unordered pair
					int save = array[checkIndex];
					array[checkIndex] = array[checkIndex - 1];
					array[checkIndex - 1] = save;

					currentArray = array;
					repaint();
				}
			}
		}
		doneSorting = true;
	}
	
	public void paint(Graphics g) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	/**
	 * Draws each value in the array onto a square in the grid.
	 */
	public void paintComponent(Graphics g) {
		int size = (int) Math.ceil(Math.sqrt(currentArray.length));
		int scale = width / size;

		// Go to each index in the array, and fill a square in the
		// animation with the brightness representing the magnitude of the value.
		for (int i = 0 ; i < currentArray.length ; i++ ) {
			g.setColor(Color.getHSBColor(hue, saturation, currentArray[i] / 255f));
			if (doneSorting) {
				// set techlab pixels to different color
				if (techlab[i % size][i / size]) {
					if ((i % size) < 60) {
						g.setColor(new Color(172, 184, 189));		// "tech"
					}
					else {
						g.setColor(new Color(12, 189, 247));		// "lab"
					}
					
				}
			}
			g.fillRect((i % size) * scale, 22 + i / size * scale, scale, scale);
		}

	}
    
	/**
     * Sets the techlab 2d array values to true wherever the techlab
     * logo should be drawn.
     */
	private void setTechlab() {
		// first row
		// h
		techlab[44][30] = true;
		techlab[45][30] = true;
		techlab[46][30] = true;
		techlab[47][30] = true;
		//l
		techlab[60][30] = true;
		techlab[61][30] = true;
		techlab[62][30] = true;
		techlab[63][30] = true;
		//b
		techlab[82][30] = true;
		techlab[83][30] = true;
		techlab[84][30] = true;
		techlab[85][30] = true;
		
		// second row
		//h
		techlab[44][31] = true;
		techlab[45][31] = true;
		techlab[46][31] = true;
		techlab[47][31] = true;
		//l
		techlab[60][31] = true;
		techlab[61][31] = true;
		techlab[62][31] = true;
		techlab[63][31] = true;
		//b
		techlab[82][31] = true;
		techlab[83][31] = true;
		techlab[84][31] = true;
		techlab[85][31] = true;
		
		// third row
		// top of 't'
		techlab[6][32] = true;
		techlab[7][32] = true;
		techlab[8][32] = true;
		techlab[9][32] = true;
		//h
		techlab[44][32] = true;
		techlab[45][32] = true;
		techlab[46][32] = true;
		techlab[47][32] = true;
		//l
		techlab[60][32] = true;
		techlab[61][32] = true;
		techlab[62][32] = true;
		techlab[63][32] = true;
		//b
		techlab[82][32] = true;
		techlab[83][32] = true;
		techlab[84][32] = true;
		techlab[85][32] = true;
		
		// fourth row
		//t
		techlab[6][33] = true;
		techlab[7][33] = true;
		techlab[8][33] = true;
		techlab[9][33] = true;
		//h
		techlab[44][33] = true;
		techlab[45][33] = true;
		techlab[46][33] = true;
		techlab[47][33] = true;
		//l
		techlab[60][33] = true;
		techlab[61][33] = true;
		techlab[62][33] = true;
		techlab[63][33] = true;
		//b
		techlab[82][33] = true;
		techlab[83][33] = true;
		techlab[84][33] = true;
		techlab[85][33] = true;
		
		// fifth row
		//t
		techlab[6][34] = true;
		techlab[7][34] = true;
		techlab[8][34] = true;
		techlab[9][34] = true;
		//h
		techlab[44][34] = true;
		techlab[45][34] = true;
		techlab[46][34] = true;
		techlab[47][34] = true;
		//l
		techlab[60][34] = true;
		techlab[61][34] = true;
		techlab[62][34] = true;
		techlab[63][34] = true;
		//b
		techlab[82][34] = true;
		techlab[83][34] = true;
		techlab[84][34] = true;
		techlab[85][34] = true;
		
		// sixth row
		//t
		techlab[6][35] = true;
		techlab[7][35] = true;
		techlab[8][35] = true;
		techlab[9][35] = true;
		//h
		techlab[44][35] = true;
		techlab[45][35] = true;
		techlab[46][35] = true;
		techlab[47][35] = true;
		//l
		techlab[60][35] = true;
		techlab[61][35] = true;
		techlab[62][35] = true;
		techlab[63][35] = true;
		//b
		techlab[82][35] = true;
		techlab[83][35] = true;
		techlab[84][35] = true;
		techlab[85][35] = true;
		
		// seventh row
		// left part of t
		techlab[5][36] = true;
		techlab[6][36] = true;
		techlab[7][36] = true;
		techlab[8][36] = true;
		techlab[9][36] = true;
		techlab[10][36] = true;
		techlab[11][36] = true;
		techlab[12][36] = true;
		techlab[13][36] = true;
		//e
		techlab[18][36] = true;
		techlab[19][36] = true;
		techlab[20][36] = true;
		techlab[21][36] = true;
		techlab[22][36] = true;
		techlab[23][36] = true;
		techlab[24][36] = true;
		techlab[25][36] = true;
		//c
		techlab[32][36] = true;
		techlab[33][36] = true;
		techlab[34][36] = true;
		techlab[35][36] = true;
		techlab[36][36] = true;
		techlab[37][36] = true;
		techlab[38][36] = true;
		techlab[39][36] = true;
		techlab[40][36] = true;
		//h
		techlab[44][36] = true;
		techlab[45][36] = true;
		techlab[46][36] = true;
		techlab[47][36] = true;
		techlab[48][36] = true;
		techlab[49][36] = true;
		techlab[50][36] = true;
		techlab[51][36] = true;
		techlab[52][36] = true;
		techlab[53][36] = true;
		techlab[54][36] = true;
		//l
		techlab[60][36] = true;
		techlab[61][36] = true;
		techlab[62][36] = true;
		techlab[63][36] = true;
		//a
		techlab[67][36] = true;
		techlab[68][36] = true;
		techlab[69][36] = true;
		techlab[70][36] = true;
		techlab[71][36] = true;
		techlab[72][36] = true;
		techlab[73][36] = true;
		techlab[74][36] = true;
		techlab[75][36] = true;
		techlab[76][36] = true;
		//b
		techlab[82][36] = true;
		techlab[83][36] = true;
		techlab[84][36] = true;
		techlab[85][36] = true;
		techlab[86][36] = true;
		techlab[87][36] = true;
		techlab[88][36] = true;
		techlab[89][36] = true;
		techlab[90][36] = true;
		techlab[91][36] = true;
		techlab[92][36] = true;
		
		// eighth row
		//t
		techlab[5][37] = true;
		techlab[6][37] = true;
		techlab[7][37] = true;
		techlab[8][37] = true;
		techlab[9][37] = true;
		techlab[10][37] = true;
		techlab[11][37] = true;
		techlab[12][37] = true;
		techlab[13][37] = true;
		//e
		techlab[17][37] = true;
		techlab[18][37] = true;
		techlab[19][37] = true;
		techlab[20][37] = true;
		techlab[21][37] = true;
		techlab[22][37] = true;
		techlab[23][37] = true;
		techlab[24][37] = true;
		techlab[25][37] = true;
		techlab[26][37] = true;
		//c
		techlab[31][37] = true;
		techlab[32][37] = true;
		techlab[33][37] = true;
		techlab[34][37] = true;
		techlab[35][37] = true;
		techlab[36][37] = true;
		techlab[37][37] = true;
		techlab[38][37] = true;
		techlab[39][37] = true;
		techlab[40][37] = true;
		//h
		techlab[44][37] = true;
		techlab[45][37] = true;
		techlab[46][37] = true;
		techlab[47][37] = true;
		techlab[48][37] = true;
		techlab[49][37] = true;
		techlab[50][37] = true;
		techlab[51][37] = true;
		techlab[52][37] = true;
		techlab[53][37] = true;
		techlab[54][37] = true;
		techlab[55][37] = true;
		//l
		techlab[60][37] = true;
		techlab[61][37] = true;
		techlab[62][37] = true;
		techlab[63][37] = true;
		//a
		techlab[67][37] = true;
		techlab[68][37] = true;
		techlab[69][37] = true;
		techlab[70][37] = true;
		techlab[71][37] = true;
		techlab[72][37] = true;
		techlab[73][37] = true;
		techlab[74][37] = true;
		techlab[75][37] = true;
		techlab[76][37] = true;
		techlab[77][37] = true;
		//b
		techlab[82][37] = true;
		techlab[83][37] = true;
		techlab[84][37] = true;
		techlab[85][37] = true;
		techlab[86][37] = true;
		techlab[87][37] = true;
		techlab[88][37] = true;
		techlab[89][37] = true;
		techlab[90][37] = true;
		techlab[91][37] = true;
		techlab[92][37] = true;
		techlab[93][37] = true;
		
		// ninth row
		//t
		techlab[5][38] = true;
		techlab[6][38] = true;
		techlab[7][38] = true;
		techlab[8][38] = true;
		techlab[9][38] = true;
		techlab[10][38] = true;
		techlab[11][38] = true;
		techlab[12][38] = true;
		techlab[13][38] = true;
		//e
		techlab[16][38] = true;
		techlab[17][38] = true;
		techlab[18][38] = true;
		techlab[19][38] = true;
		techlab[20][38] = true;
		techlab[21][38] = true;
		techlab[22][38] = true;
		techlab[23][38] = true;
		techlab[24][38] = true;
		techlab[25][38] = true;
		techlab[26][38] = true;
		techlab[27][38] = true;
		//c
		techlab[31][38] = true;
		techlab[32][38] = true;
		techlab[33][38] = true;
		techlab[34][38] = true;
		techlab[35][38] = true;
		techlab[36][38] = true;
		techlab[37][38] = true;
		techlab[38][38] = true;
		techlab[39][38] = true;
		techlab[40][38] = true;
		//h
		techlab[44][38] = true;
		techlab[45][38] = true;
		techlab[46][38] = true;
		techlab[47][38] = true;
		techlab[48][38] = true;
		techlab[49][38] = true;
		techlab[50][38] = true;
		techlab[51][38] = true;
		techlab[52][38] = true;
		techlab[53][38] = true;
		techlab[54][38] = true;
		techlab[55][38] = true;
		//l
		techlab[60][38] = true;
		techlab[61][38] = true;
		techlab[62][38] = true;
		techlab[63][38] = true;
		//a
		techlab[67][38] = true;
		techlab[68][38] = true;
		techlab[69][38] = true;
		techlab[70][38] = true;
		techlab[71][38] = true;
		techlab[72][38] = true;
		techlab[73][38] = true;
		techlab[74][38] = true;
		techlab[75][38] = true;
		techlab[76][38] = true;
		techlab[77][38] = true;
		techlab[78][38] = true;
		//b
		techlab[82][38] = true;
		techlab[83][38] = true;
		techlab[84][38] = true;
		techlab[85][38] = true;
		techlab[86][38] = true;
		techlab[87][38] = true;
		techlab[88][38] = true;
		techlab[89][38] = true;
		techlab[90][38] = true;
		techlab[91][38] = true;
		techlab[92][38] = true;
		techlab[93][38] = true;
		techlab[94][38] = true;
		
		// tenth row
		techlab[6][39] = true;
		techlab[6][39] = true;
		techlab[7][39] = true;
		techlab[7][39] = true;
		techlab[8][39] = true;
		techlab[8][39] = true;
		techlab[9][39] = true;
		techlab[9][39] = true;
		techlab[16][39] = true;
		techlab[17][39] = true;
		techlab[17][39] = true;
		techlab[18][39] = true;
		techlab[18][39] = true;
		techlab[19][39] = true;
		techlab[19][39] = true;
		techlab[23][39] = true;
		techlab[24][39] = true;
		techlab[24][39] = true;
		techlab[25][39] = true;
		techlab[25][39] = true;
		techlab[26][39] = true;
		techlab[26][39] = true;
		techlab[27][39] = true;
		techlab[31][39] = true;
		techlab[31][39] = true;
		techlab[32][39] = true;
		techlab[32][39] = true;
		techlab[33][39] = true;
		techlab[33][39] = true;
		techlab[34][39] = true;
		techlab[34][39] = true;
		techlab[35][39] = true;
		techlab[35][39] = true;
		techlab[44][39] = true;
		techlab[44][39] = true;
		techlab[45][39] = true;
		techlab[45][39] = true;
		techlab[46][39] = true;
		techlab[46][39] = true;
		techlab[47][39] = true;
		techlab[47][39] = true;
		techlab[48][39] = true;
		techlab[51][39] = true;
		techlab[52][39] = true;
		techlab[52][39] = true;
		techlab[53][39] = true;
		techlab[53][39] = true;
		techlab[54][39] = true;
		techlab[54][39] = true;
		techlab[55][39] = true;
		techlab[55][39] = true;
		techlab[60][39] = true;
		techlab[60][39] = true;
		techlab[61][39] = true;
		techlab[61][39] = true;
		techlab[62][39] = true;
		techlab[62][39] = true;
		techlab[63][39] = true;
		techlab[74][39] = true;
		techlab[75][39] = true;
		techlab[75][39] = true;
		techlab[76][39] = true;
		techlab[76][39] = true;
		techlab[77][39] = true;
		techlab[77][39] = true;
		techlab[78][39] = true;
		techlab[82][39] = true;
		techlab[83][39] = true;
		techlab[83][39] = true;
		techlab[84][39] = true;
		techlab[84][39] = true;
		techlab[85][39] = true;
		techlab[85][39] = true;
		techlab[86][39] = true;
		techlab[86][39] = true;
		techlab[89][39] = true;
		techlab[90][39] = true;
		techlab[90][39] = true;
		techlab[91][39] = true;
		techlab[91][39] = true;
		techlab[92][39] = true;
		techlab[92][39] = true;
		techlab[93][39] = true;
		techlab[93][39] = true;
		techlab[94][39] = true;
		
		// eleventh row
		techlab[6][40] = true;
		techlab[7][40] = true;
		techlab[7][40] = true;
		techlab[8][40] = true;
		techlab[8][40] = true;
		techlab[9][40] = true;
		techlab[9][40] = true;
		techlab[16][40] = true;
		techlab[16][40] = true;
		techlab[17][40] = true;
		techlab[17][40] = true;
		techlab[18][40] = true;
		techlab[18][40] = true;
		techlab[19][40] = true;
		techlab[24][40] = true;
		techlab[25][40] = true;
		techlab[25][40] = true;
		techlab[26][40] = true;
		techlab[26][40] = true;
		techlab[27][40] = true;
		techlab[27][40] = true;
		techlab[30][40] = true;
		techlab[31][40] = true;
		techlab[31][40] = true;
		techlab[32][40] = true;
		techlab[32][40] = true;
		techlab[33][40] = true;
		techlab[33][40] = true;
		techlab[34][40] = true;
		techlab[44][40] = true;
		techlab[44][40] = true;
		techlab[45][40] = true;
		techlab[45][40] = true;
		techlab[46][40] = true;
		techlab[46][40] = true;
		techlab[47][40] = true;
		techlab[52][40] = true;
		techlab[53][40] = true;
		techlab[53][40] = true;
		techlab[54][40] = true;
		techlab[54][40] = true;
		techlab[55][40] = true;
		techlab[55][40] = true;
		techlab[60][40] = true;
		techlab[60][40] = true;
		techlab[61][40] = true;
		techlab[61][40] = true;
		techlab[62][40] = true;
		techlab[62][40] = true;
		techlab[63][40] = true;
		techlab[75][40] = true;
		techlab[75][40] = true;
		techlab[76][40] = true;
		techlab[76][40] = true;
		techlab[77][40] = true;
		techlab[77][40] = true;
		techlab[78][40] = true;
		techlab[82][40] = true;
		techlab[83][40] = true;
		techlab[83][40] = true;
		techlab[84][40] = true;
		techlab[84][40] = true;
		techlab[85][40] = true;
		techlab[85][40] = true;
		techlab[90][40] = true;
		techlab[91][40] = true;
		techlab[91][40] = true;
		techlab[92][40] = true;
		techlab[92][40] = true;
		techlab[93][40] = true;
		techlab[93][40] = true;
		techlab[94][40] = true;
		techlab[94][40] = true;
		
		// Twelfth row
		//t
		techlab[6][41] = true;
		techlab[7][41] = true;
		techlab[8][41] = true;
		techlab[9][41] = true;
		//e
		techlab[16][41] = true;
		techlab[17][41] = true;
		techlab[18][41] = true;
		techlab[19][41] = true;
		
		techlab[24][41] = true;
		techlab[25][41] = true;
		techlab[26][41] = true;
		techlab[27][41] = true;
		//c
		techlab[30][41] = true;
		techlab[31][41] = true;
		techlab[32][41] = true;
		techlab[33][41] = true;
		techlab[34][41] = true;
		//h
		techlab[44][41] = true;
		techlab[45][41] = true;
		techlab[46][41] = true;
		techlab[47][41] = true;
		
		techlab[52][41] = true;
		techlab[53][41] = true;
		techlab[54][41] = true;
		techlab[55][41] = true;
		//l
		techlab[60][41] = true;
		techlab[61][41] = true;
		techlab[62][41] = true;
		techlab[63][41] = true;
		//a
		techlab[72][41] = true;
		techlab[73][41] = true;
		techlab[74][41] = true;
		techlab[75][41] = true;
		techlab[76][41] = true;
		techlab[77][41] = true;
		techlab[78][41] = true;
		//b
		techlab[82][41] = true;
		techlab[83][41] = true;
		techlab[84][41] = true;
		techlab[85][41] = true;
		
		techlab[91][41] = true;
		techlab[92][41] = true;
		techlab[93][41] = true;
		techlab[94][41] = true;
		
		// thirteenth row
		techlab[6][42] = true;
		techlab[7][42] = true;
		techlab[8][42] = true;
		techlab[9][42] = true;
		techlab[15][42] = true;
		techlab[16][42] = true;
		techlab[17][42] = true;
		techlab[18][42] = true;
		techlab[19][42] = true;
		techlab[20][42] = true;
		techlab[21][42] = true;
		techlab[22][42] = true;
		techlab[23][42] = true;
		techlab[24][42] = true;
		techlab[25][42] = true;
		techlab[26][42] = true;
		techlab[27][42] = true;
		techlab[30][42] = true;
		techlab[31][42] = true;
		techlab[32][42] = true;
		techlab[33][42] = true;
		techlab[44][42] = true;
		techlab[45][42] = true;
		techlab[46][42] = true;
		techlab[47][42] = true;
		techlab[52][42] = true;
		techlab[53][42] = true;
		techlab[54][42] = true;
		techlab[55][42] = true;
		techlab[60][42] = true;
		techlab[61][42] = true;
		techlab[62][42] = true;
		techlab[63][42] = true;
		techlab[68][42] = true;
		techlab[69][42] = true;
		techlab[70][42] = true;
		techlab[71][42] = true;
		techlab[72][42] = true;
		techlab[73][42] = true;
		techlab[74][42] = true;
		techlab[75][42] = true;
		techlab[76][42] = true;
		techlab[77][42] = true;
		techlab[78][42] = true;
		techlab[82][42] = true;
		techlab[83][42] = true;
		techlab[84][42] = true;
		techlab[85][42] = true;
		techlab[91][42] = true;
		techlab[92][42] = true;
		techlab[93][42] = true;
		techlab[94][42] = true;
		
		// fourteenth row
		techlab[6][43] = true;
		techlab[7][43] = true;
		techlab[8][43] = true;
		techlab[9][43] = true;
		techlab[15][43] = true;
		techlab[16][43] = true;
		techlab[17][43] = true;
		techlab[18][43] = true;
		techlab[19][43] = true;
		techlab[20][43] = true;
		techlab[21][43] = true;
		techlab[22][43] = true;
		techlab[23][43] = true;
		techlab[24][43] = true;
		techlab[25][43] = true;
		techlab[26][43] = true;
		techlab[27][43] = true;
		techlab[30][43] = true;
		techlab[31][43] = true;
		techlab[32][43] = true;
		techlab[33][43] = true;
		techlab[44][43] = true;
		techlab[45][43] = true;
		techlab[46][43] = true;
		techlab[47][43] = true;
		techlab[52][43] = true;
		techlab[53][43] = true;
		techlab[54][43] = true;
		techlab[55][43] = true;
		techlab[60][43] = true;
		techlab[61][43] = true;
		techlab[62][43] = true;
		techlab[63][43] = true;
		techlab[67][43] = true;
		techlab[68][43] = true;
		techlab[69][43] = true;
		techlab[70][43] = true;
		techlab[71][43] = true;
		techlab[72][43] = true;
		techlab[73][43] = true;
		techlab[74][43] = true;
		techlab[75][43] = true;
		techlab[76][43] = true;
		techlab[77][43] = true;
		techlab[78][43] = true;
		techlab[82][43] = true;
		techlab[83][43] = true;
		techlab[84][43] = true;
		techlab[85][43] = true;
		techlab[91][43] = true;
		techlab[92][43] = true;
		techlab[93][43] = true;
		techlab[94][43] = true;
		
		// fifteenth row
		techlab[6][44] = true;
		techlab[7][44] = true;
		techlab[8][44] = true;
		techlab[9][44] = true;
		techlab[15][44] = true;
		techlab[16][44] = true;
		techlab[17][44] = true;
		techlab[18][44] = true;
		techlab[19][44] = true;
		techlab[20][44] = true;
		techlab[21][44] = true;
		techlab[22][44] = true;
		techlab[23][44] = true;
		techlab[24][44] = true;
		techlab[25][44] = true;
		techlab[26][44] = true;
		techlab[27][44] = true;
		techlab[30][44] = true;
		techlab[31][44] = true;
		techlab[32][44] = true;
		techlab[33][44] = true;
		techlab[44][44] = true;
		techlab[45][44] = true;
		techlab[46][44] = true;
		techlab[47][44] = true;
		techlab[52][44] = true;
		techlab[53][44] = true;
		techlab[54][44] = true;
		techlab[55][44] = true;
		techlab[60][44] = true;
		techlab[61][44] = true;
		techlab[62][44] = true;
		techlab[63][44] = true;
		techlab[67][44] = true;
		techlab[68][44] = true;
		techlab[69][44] = true;
		techlab[70][44] = true;
		techlab[71][44] = true;
		techlab[75][44] = true;
		techlab[76][44] = true;
		techlab[77][44] = true;
		techlab[78][44] = true;
		techlab[82][44] = true;
		techlab[83][44] = true;
		techlab[84][44] = true;
		techlab[85][44] = true;
		techlab[91][44] = true;
		techlab[92][44] = true;
		techlab[93][44] = true;
		techlab[94][44] = true;
		
		// sixteenth row
		techlab[6][45] = true;
		techlab[7][45] = true;
		techlab[8][45] = true;
		techlab[9][45] = true;
		techlab[16][45] = true;
		techlab[17][45] = true;
		techlab[18][45] = true;
		techlab[19][45] = true;
		techlab[30][45] = true;
		techlab[31][45] = true;
		techlab[32][45] = true;
		techlab[33][45] = true;
		techlab[34][45] = true;
		techlab[44][45] = true;
		techlab[45][45] = true;
		techlab[46][45] = true;
		techlab[47][45] = true;
		techlab[52][45] = true;
		techlab[53][45] = true;
		techlab[54][45] = true;
		techlab[55][45] = true;
		techlab[60][45] = true;
		techlab[61][45] = true;
		techlab[62][45] = true;
		techlab[63][45] = true;
		techlab[66][45] = true;
		techlab[67][45] = true;
		techlab[68][45] = true;
		techlab[69][45] = true;
		techlab[70][45] = true;
		techlab[75][45] = true;
		techlab[76][45] = true;
		techlab[77][45] = true;
		techlab[78][45] = true;
		techlab[82][45] = true;
		techlab[83][45] = true;
		techlab[84][45] = true;
		techlab[85][45] = true;
		techlab[91][45] = true;
		techlab[92][45] = true;
		techlab[93][45] = true;
		techlab[94][45] = true;
		
		// seventeenth row
		techlab[6][46] = true;
		techlab[7][46] = true;
		techlab[8][46] = true;
		techlab[9][46] = true;
		techlab[16][46] = true;
		techlab[17][46] = true;
		techlab[18][46] = true;
		techlab[19][46] = true;
		techlab[30][46] = true;
		techlab[31][46] = true;
		techlab[32][46] = true;
		techlab[33][46] = true;
		techlab[34][46] = true;
		techlab[44][46] = true;
		techlab[45][46] = true;
		techlab[46][46] = true;
		techlab[47][46] = true;
		techlab[52][46] = true;
		techlab[53][46] = true;
		techlab[54][46] = true;
		techlab[55][46] = true;
		techlab[60][46] = true;
		techlab[61][46] = true;
		techlab[62][46] = true;
		techlab[63][46] = true;
		techlab[66][46] = true;
		techlab[67][46] = true;
		techlab[68][46] = true;
		techlab[69][46] = true;
		techlab[70][46] = true;
		techlab[75][46] = true;
		techlab[76][46] = true;
		techlab[77][46] = true;
		techlab[78][46] = true;
		techlab[82][46] = true;
		techlab[83][46] = true;
		techlab[84][46] = true;
		techlab[85][46] = true;
		techlab[90][46] = true;
		techlab[91][46] = true;
		techlab[92][46] = true;
		techlab[93][46] = true;
		techlab[94][46] = true;
		
		// eighteenth row
		techlab[6][47] = true;
		techlab[7][47] = true;
		techlab[8][47] = true;
		techlab[9][47] = true;
		techlab[10][47] = true;
		techlab[11][47] = true;
		techlab[12][47] = true;
		techlab[13][47] = true;
		techlab[16][47] = true;
		techlab[17][47] = true;
		techlab[18][47] = true;
		techlab[19][47] = true;
		techlab[20][47] = true;
		techlab[21][47] = true;
		techlab[22][47] = true;
		techlab[23][47] = true;
		techlab[24][47] = true;
		techlab[25][47] = true;
		techlab[26][47] = true;
		techlab[27][47] = true;
		techlab[31][47] = true;
		techlab[32][47] = true;
		techlab[33][47] = true;
		techlab[34][47] = true;
		techlab[35][47] = true;
		techlab[36][47] = true;
		techlab[37][47] = true;
		techlab[38][47] = true;
		techlab[39][47] = true;
		techlab[40][47] = true;
		techlab[44][47] = true;
		techlab[45][47] = true;
		techlab[46][47] = true;
		techlab[47][47] = true;
		techlab[52][47] = true;
		techlab[53][47] = true;
		techlab[54][47] = true;
		techlab[55][47] = true;
		techlab[60][47] = true;
		techlab[61][47] = true;
		techlab[62][47] = true;
		techlab[63][47] = true;
		techlab[67][47] = true;
		techlab[68][47] = true;
		techlab[69][47] = true;
		techlab[70][47] = true;
		techlab[71][47] = true;
		techlab[72][47] = true;
		techlab[73][47] = true;
		techlab[74][47] = true;
		techlab[75][47] = true;
		techlab[76][47] = true;
		techlab[77][47] = true;
		techlab[78][47] = true;
		techlab[82][47] = true;
		techlab[83][47] = true;
		techlab[84][47] = true;
		techlab[85][47] = true;
		techlab[86][47] = true;
		techlab[87][47] = true;
		techlab[88][47] = true;
		techlab[89][47] = true;
		techlab[90][47] = true;
		techlab[91][47] = true;
		techlab[92][47] = true;
		techlab[93][47] = true;
		techlab[94][47] = true;
		
		// nineteenth row
		techlab[7][48] = true;
		techlab[8][48] = true;
		techlab[9][48] = true;
		techlab[10][48] = true;
		techlab[11][48] = true;
		techlab[12][48] = true;
		techlab[13][48] = true;
		techlab[16][48] = true;
		techlab[17][48] = true;
		techlab[18][48] = true;
		techlab[19][48] = true;
		techlab[20][48] = true;
		techlab[21][48] = true;
		techlab[22][48] = true;
		techlab[23][48] = true;
		techlab[24][48] = true;
		techlab[25][48] = true;
		techlab[26][48] = true;
		techlab[27][48] = true;
		techlab[31][48] = true;
		techlab[32][48] = true;
		techlab[33][48] = true;
		techlab[34][48] = true;
		techlab[35][48] = true;
		techlab[36][48] = true;
		techlab[37][48] = true;
		techlab[38][48] = true;
		techlab[39][48] = true;
		techlab[40][48] = true;
		techlab[44][48] = true;
		techlab[45][48] = true;
		techlab[46][48] = true;
		techlab[47][48] = true;
		techlab[52][48] = true;
		techlab[53][48] = true;
		techlab[54][48] = true;
		techlab[55][48] = true;
		techlab[60][48] = true;
		techlab[61][48] = true;
		techlab[62][48] = true;
		techlab[63][48] = true;
		techlab[67][48] = true;
		techlab[68][48] = true;
		techlab[69][48] = true;
		techlab[70][48] = true;
		techlab[71][48] = true;
		techlab[72][48] = true;
		techlab[73][48] = true;
		techlab[74][48] = true;
		techlab[75][48] = true;
		techlab[76][48] = true;
		techlab[77][48] = true;
		techlab[78][48] = true;
		techlab[79][48] = true;
		techlab[82][48] = true;
		techlab[83][48] = true;
		techlab[84][48] = true;
		techlab[85][48] = true;
		techlab[86][48] = true;
		techlab[87][48] = true;
		techlab[88][48] = true;
		techlab[89][48] = true;
		techlab[90][48] = true;
		techlab[91][48] = true;
		techlab[92][48] = true;
		techlab[93][48] = true;
		
		// twentieth row
		techlab[7][49] = true;
		techlab[8][49] = true;
		techlab[9][49] = true;
		techlab[10][49] = true;
		techlab[11][49] = true;
		techlab[12][49] = true;
		techlab[13][49] = true;
		techlab[17][49] = true;
		techlab[18][49] = true;
		techlab[19][49] = true;
		techlab[20][49] = true;
		techlab[21][49] = true;
		techlab[22][49] = true;
		techlab[23][49] = true;
		techlab[24][49] = true;
		techlab[25][49] = true;
		techlab[26][49] = true;
		techlab[27][49] = true;
		techlab[32][49] = true;
		techlab[33][49] = true;
		techlab[34][49] = true;
		techlab[35][49] = true;
		techlab[36][49] = true;
		techlab[37][49] = true;
		techlab[38][49] = true;
		techlab[39][49] = true;
		techlab[40][49] = true;
		techlab[44][49] = true;
		techlab[45][49] = true;
		techlab[46][49] = true;
		techlab[47][49] = true;
		techlab[52][49] = true;
		techlab[53][49] = true;
		techlab[54][49] = true;
		techlab[55][49] = true;
		techlab[60][49] = true;
		techlab[61][49] = true;
		techlab[62][49] = true;
		techlab[63][49] = true;
		techlab[67][49] = true;
		techlab[68][49] = true;
		techlab[69][49] = true;
		techlab[70][49] = true;
		techlab[71][49] = true;
		techlab[72][49] = true;
		techlab[73][49] = true;
		techlab[74][49] = true;
		techlab[75][49] = true;
		techlab[76][49] = true;
		techlab[77][49] = true;
		techlab[78][49] = true;
		techlab[79][49] = true;
		techlab[82][49] = true;
		techlab[83][49] = true;
		techlab[84][49] = true;
		techlab[85][49] = true;
		techlab[86][49] = true;
		techlab[87][49] = true;
		techlab[88][49] = true;
		techlab[89][49] = true;
		techlab[90][49] = true;
		techlab[91][49] = true;
		techlab[92][49] = true;
		techlab[93][49] = true;
		
		// twenty-first row
		techlab[8][50] = true;
		techlab[9][50] = true;
		techlab[10][50] = true;
		techlab[11][50] = true;
		techlab[12][50] = true;
		techlab[13][50] = true;
		techlab[19][50] = true;
		techlab[20][50] = true;
		techlab[21][50] = true;
		techlab[22][50] = true;
		techlab[23][50] = true;
		techlab[24][50] = true;
		techlab[25][50] = true;
		techlab[33][50] = true;
		techlab[34][50] = true;
		techlab[35][50] = true;
		techlab[36][50] = true;
		techlab[37][50] = true;
		techlab[38][50] = true;
		techlab[39][50] = true;
		techlab[44][50] = true;
		techlab[45][50] = true;
		techlab[46][50] = true;
		techlab[47][50] = true;
		techlab[52][50] = true;
		techlab[53][50] = true;
		techlab[54][50] = true;
		techlab[55][50] = true;
		techlab[60][50] = true;
		techlab[61][50] = true;
		techlab[62][50] = true;
		techlab[63][50] = true;
		techlab[69][50] = true;
		techlab[70][50] = true;
		techlab[71][50] = true;
		techlab[72][50] = true;
		techlab[73][50] = true;
		techlab[76][50] = true;
		techlab[77][50] = true;
		techlab[78][50] = true;
		techlab[79][50] = true;
		techlab[82][50] = true;
		techlab[83][50] = true;
		techlab[84][50] = true;
		techlab[85][50] = true;
		techlab[86][50] = true;
		techlab[87][50] = true;
		techlab[88][50] = true;
		techlab[89][50] = true;
		techlab[90][50] = true;
		techlab[91][50] = true;
	}

	
}