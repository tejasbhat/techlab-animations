import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

/**
 * PolygonAnimation.java
 * This is a rotating pentagon animation that shows the techlab logo in the center.
 * @author Dean Stratakos, Tejas Bhat
 * @version 1.0
 * @since 06/10/2016
 */

@SuppressWarnings("serial")
public class PolygonAnimation extends JFrame {
	
	static int count = 0;
	static boolean printed = false;
	
	static int width = 1000;
	static int height = 1000;
	
	static int halfWidth = width / 2;
	static int halfHeight = height / 2;
	
	static int numSides = 5;
	
	private Image dbImage;
	private Graphics dbg;
	
	double[] x = new double[numSides];							// x-coordinates
	double[] y = new double[numSides];							// y-coordinates
	int diameter;												// radius of outer circle
	int [] radii;
	double degreesAngle = 90;
	double angle = Math.toRadians(degreesAngle);				// counter-clockwise angle measurement of first coordinate from 0 degrees
	double centerAngle = Math.toRadians(360/numSides);			// measure of one central angle
	
	static int numFractals = 8;									// number of polygons to draw
																// VARIABLE
	
	Font robotoBold = new Font("Roboto Bold", Font.TRUETYPE_FONT, 60);
	Font robotoThin = new Font("Roboto Thin", Font.BOLD, 60);
	
	Color[] colors;
	
	public static void main(String[] args) {
		new PolygonAnimation();
	}
	
	/**
	 * Constructor for a PolygonAnimation object.
	 */
	public PolygonAnimation() {
		// call the constructor for the parent class (JFrame)
		super("Polygon Animation");
		// set the size of the window
		setSize(width, height);
		
		colors = new Color[numFractals];
		for (int i = 0; i < numFractals; i++) {
			// set the color to some shade of blue
			int c = (int)(Math.random() * 255);
			colors[i] = new Color(10, 10, c);
		}
		
		// set diameter for largest fractal
		if (width < height) {
			diameter = width - 75;
		}
		else {
			diameter = height - 75;
		}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    /**
     * Allows double buffering in the graphical portion of the program.
     */
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
    /**
     * Processes all the visual components of the project. Draws the pentagon fractal by
     * calling drawFractal() and also keeps the techlab logo on the screen.
     */
	public void paintComponent(Graphics g) {
		// clear the background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		// convert the degree measure to radians
		angle = Math.toRadians(degreesAngle);
		// set the x and y coordinates for the outermost fractal
		for(int i = 0; i < numSides; i ++) 
		{
			x[i] = (int) ((100 * (diameter/2 * Math.cos(angle))) + 50);				// cos(angle) = x/r; r * cos(angle) = r * x/r = x
			y[i] = (int) ((100 * (diameter/2 * Math.sin(angle))) + 50);				// sin(angle) = y/r; r * sin(angle) = r * y/r = y
			angle += centerAngle;
		}
		
		drawFractal(g, numFractals, diameter, x, y, colors);
		
		g.setColor(new Color(172, 184, 189));
		g.setFont(new Font("Roboto Bold", Font.TRUETYPE_FONT, 150));
		g.drawString("tech", halfWidth - 245, halfHeight + 50);
		
		g.setColor(new Color(12, 189, 247));
		g.setFont(new Font("Roboto Thin", Font.BOLD, 150));
		g.drawString("lab", halfWidth + 35, halfHeight + 50);
		
		// increment the degree measure of the angle each time
		degreesAngle = (degreesAngle + 1) % 360;
		count++;
		repaint();
	}
	
	/**
	 * Recursively draws pentagons in a spinning fashion.
	 * @param n
	 * @param radius
	 * @param x
	 * @param y
	 * @param d
	 */
	public static void drawFractal(Graphics g, int n, int diameter, double [] x, double [] y, Color [] colors)
	{
		// base case of recursion
		if(n == 0)
		{
			return;
		}
		
		g.fillOval(halfWidth - diameter / 2, halfHeight - diameter / 2, diameter, diameter);
		g.setColor(colors[n-1]);
		if (n % 2 == 0) {
			int [] intX = new int [numSides];
			int [] intY = new int [numSides];
			for (int i = 0; i < numSides; i++) {
				intX[i] = (int) (x[i] / 100.0) + halfWidth;
				intY[i] = (int) (y[i] / 100.0) + halfHeight;
			}
			g.fillPolygon(intX, intY, numSides);
		}
		else {
			double [] tempX = new double [numSides];
			for (int i = 0; i < numSides; i++) {
				tempX[i] = 1 - x[i];
			}
			int [] intTempX = new int [numSides];
			int [] intY = new int [numSides];
			for (int i = 0; i < numSides; i++) {
				intTempX[i] = (int) (tempX[i] / 100.0) + halfWidth;
				intY[i] = (int) (y[i] / 100.0) + halfHeight;
			}
			g.fillPolygon(intTempX, intY, numSides);
		}
		
		
		 // Midpoint related things calculate midpoints of this polygon's sides.
         // This translates to the vertices of the next polygon.
		double nextX [] = new double[numSides];
		double nextY [] = new double[numSides];

		for(int i = 0; i < numSides; i ++)
		{
			if(i < numSides - 1)
			{
				nextX[i] = (x[i] + x[i + 1])/2;
				nextY[i] = (y[i] + y[i + 1])/2;
			}
			else {
				nextX[i] = (x[i] + x[0])/2;
				nextY[i] = (y[i] + y[0])/2;
			}
		}	
		
		// use distance formula to calculate radius for circle of next fractal: d^2 = sqrt(x^2 + y^2)
		int nextDiameter = (int) (Math.sqrt(Math.pow(halfWidth - nextX[0], 2) + Math.pow(halfHeight - nextY[0], 2)) / 100.0);
		
		// draw the next smallest fractal
		drawFractal(g, n - 1, nextDiameter, nextX, nextY, colors);
	}
	
}
