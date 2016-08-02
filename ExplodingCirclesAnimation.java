import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

/**
 * Introduction five for Techlab's video Arduino tutorials. This animation consists of colorful circles
 * spawning on the screen. After 5000 circles have popped up, the word techlab is displayed on the screen
 * and it increases in size as time goes on. There is also an effective trash collection system implemented
 * so that circles in the background don't keep expanding when they cannot be seen. 
 * @author Tejas Bhat
 * @version 1.0
 * @since 6/27/2016
 */

@SuppressWarnings("serial")
public class ExplodingCirclesAnimation extends JFrame
{
	ArrayList<GrowingItem> items;    //a list of all the items that are on the screen
	boolean done;                    //a boolean that checks whether or not the program should be terminated
	boolean addText;                 //a boolean that checks to see if it is time to display the word "techlab"
	Image img;                       //an image used to manage lag in the program
	Graphics g2;                     //a second Graphics instance used to manage lag in the program
	int count;                       //counts the number of times the animation has been updated

	/**
	 * Runs the program by calling the constructor of this clas.
	 * @param args  Array of arguments that run the program.
	 */
	public static void main(String[]args)
	{	
		new ExplodingCirclesAnimation();
	}

	/**
	 * Constructs an Intro5 object by setting default values and calling the run() method
	 * to start the animation.
	 */
	public ExplodingCirclesAnimation()
	{
		// Create the window.
		super("Techlab Intro 5");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		//Sets all variables to default values and runs the program.
		items = new ArrayList<GrowingItem>();
		count = 0;
		done = addText = false;
		run();
	}

	/**
	 * Loops the drawing of the animation so that circles keep spawning up on the screen.
	 * This is done by calling the methods updateAnimation(), repaint(), and delay() in order.
	 */
	public void run()
	{   
		//runs the graphic animation in a loop until done is set to true
		while(!done)
		{
			updateAnimation();
			repaint();
			delay();
		}
	}

	/**
	 * Updates the current animation by adding a circle if there are less than 30 circles on the screen or deleting a
	 * circle if there are already 30 circles on the screen. Also, this method expands all the current circles
	 * by calling the expand() method for every circle on the screen.
	 */
	public void updateAnimation()
	{
		count ++; //update count so that it stores the number of times updateAnimation() has been called

		//if count is less than 150, add or remove a circle depending on the size of items
		if(count < 150)
		{
			//if there are less than 30 items, add one more circle to the screen
			if(items.size() < 30)
			{
				items.add(new Circle());
			}
			//if there are more than 30 items, remove the item that was placed first on the screen
			else
			{
				items.remove(0);
			}
		}
		//if the animation has been updated 150 times, add a text item to the items list
		else if(count == 150)
		{
			items.add(new Text());
		}
		//if the animation has been updated more than 100 times, don't allow for any more items to be added

		//make all the circles bigger by calling the expand() method for each one
		for(GrowingItem gi: items)
		{
			gi.expand();
		}
	}

	/**
	 * Updates the graphic by drawing every circle on the screen again and adding the techlab text if need be.
	 * Also, a complex image system is used to deal with lag and make it so that the program does not lag.
	 * @param g  a Graphics object used for outputting circles to the GUI
	 */
	public void update(Graphics g)
	{
		//deal with lag
		if(img == null)
		{
			img = createImage(getWidth(), getHeight());
			g2 = img.getGraphics();
		}

		if(items.size() == 0) return; //check if items has any items in it before starting

		//draw each of the circles in the list of circles with their respective color, radius, and x-y coordinates
		for(GrowingItem gi: items)
		{
			if(gi instanceof Circle)
			{
				Circle current = ((Circle)gi);
				g2.setColor(current.color);
				g2.fillOval(current.topx - current.radius, current.topy - current.radius, 2*current.radius, 2*current.radius);
			}
			else
			{
				g2.setColor(new Color(172, 184, 189));
				g2.setFont(((Text)gi).updateAndGetFont());
				g2.drawString("tech", 160 - ((Text)gi).getTextSize(), 300);

				g2.setColor(new Color(12, 189, 247));
				g2.drawString("lab", 260 + ((Text)gi).getTextSize(), 300);
			}
		}
		g.drawImage(img, 0, 0, this); //dealing with lag
	}

	/**
	 * Calls update() to update all the circles.
	 * @param g  a Graphics object 
	 */
	public void paint(Graphics g)
	{
		update(g);
	}

	/**
	 * Delays the program by 30 milliseconds.
	 */
	public void delay()
	{
		//try-catch block to delay the program between the drawing of each circle
		try{
			Thread.sleep(30);
		} catch(InterruptedException e){}
	}
}

/**
 * Class that represents a circle. Every circle has a color, radius, xcenter, and y center.
 * @author Tejas Bhat 
 * @version 1.0
 * @since 6/27/2016
 */
class Circle implements GrowingItem
{
	Color color;                    //represents the color of this Circle
	int radius, topx, topy;   //represents the radius and x-y coordinates of the top-left corner of the box containing this Circle

	/**
	 * Creates a Circle instance by setting values to all the global variables.
	 */
	public Circle()
	{
		color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)); //random color
		radius = 10 + (int)(Math.random()*25); //random radius between 10 and 34
		topx = (int)(Math.random()*800); //random topx between 0 and 799
		topy = (int)(Math.random()*600); //random topy between 0 and 599
	}

	/**
	 * Creates a set Circle instance by setting passed-in values to all the global variables.
	 * @param  c       the color of this circle
	 * @param  r       the radius of this circle
	 * @param  x       the  x-coordinate of the top-left corner of the box containing this Circle
	 * @param  y       the  y-coordinate of the top-left corner of the box containing this Circle
	 */
	public Circle(Color c, int r, int x, int y)
	{
		color = c;
		radius = r;
		topx = x;
		topy = y;
	}

	/**
	 * Expands a circle by incrementing its raduis by 2. The x-y coordinates of the center are left unchanged.
	 */
	public void expand()
	{
		//expands the circles by setting their radius values higher by 2
		radius += 2;
	}
}

/**
 * Class that represents a text string. Every text string has a font size and font style.
 * @author Tejas Bhat 
 * @version 1.0
 * @since 6/29/2016
 */
class Text implements GrowingItem
{
	Font f;                  //the font style of this text string
	int textSize;            //the font size of this text string
	String message;          //the message inside the text string

	/**
	 * Creates a Text instance by setting values to all the global variables.
	 */
	public Text()
	{
		message = "techlab";
		textSize = 20;
		f = new Font("Monospaced", Font.BOLD, textSize);
	}

	/**
	 * Updates the font of this text String and returns the new font.
	 * @return  f   the new Font of this text String
	 */
	public Font updateAndGetFont()
	{
		f = new Font("Monospaced", Font.BOLD, textSize);
		return f;
	}

	/**
	 * Expands this text string by incrementing its text size.
	 */
	public void expand()
	{
		//expands the circles by setting their radius values higher by 2
		textSize ++;
	}

	/**
	 * Returns this text string's text size.
	 * @return  the text size of this text string
	 */
	public int getTextSize()
	{
		return textSize;
	}
}