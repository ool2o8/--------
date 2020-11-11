import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class enemy extends JPanel{
	
	private int x=500;
	private int y;
	private int w;
	private int h;
	private int damage=30;
	private int speed=3;
	private Image image;
	public enemy() {
		ImageIcon ic = new ImageIcon("images/mushroom.png");
		image=ic.getImage();
		
		w=image.getWidth(null);
		h=image.getHeight(null);
		y=-h;
		
	}
	
	public int getDamage() {
		return damage;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return w;
	}
	public int getHeigh() {
		return h;
	}
	public Image getImage() {
		return image;
	}
	
	
	public void move() {
		if(Main.getInGame()) {
			y+=speed;
		}
	}
}
