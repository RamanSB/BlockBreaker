import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle{

	Image pic;
	int dx = 3;
	int dy = -3;
	boolean isDestroyed;
	boolean isPowerup;
	Rectangle right;
	Rectangle left;
	
	public Block(int a, int b, int w, int h, String s) {
		x = a; //Overrides superclass instance variables.
		y = b;
		width = w;
		height = h;
		isDestroyed = false;
		left = new Rectangle(a-1, b, 1, h);
		right = new Rectangle(a+w+1, b, 1, h);
		
		pic = Toolkit.getDefaultToolkit().getImage(s);
	}
	
	public void draw(Graphics g, Component c) {
		if(!isDestroyed) {
			g.drawImage(pic, x, y, width, height, c);
		}
	}
	
	
}
