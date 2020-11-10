import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class character extends JPanel{
	private int mx;
	private int my;
	private int x=500;
	private int y=400;
	private int w;
	private int h;
	private int HP=200;
	private double MP=0;
	private double snowball=50;
	private int score=0;
	private Image image;
	public character() {
		ImageIcon ic = new ImageIcon("images/´©´©ÀÇ µÞÅÂ.png");
		image=ic.getImage();
		
		w=image.getWidth(null);
		h=image.getHeight(null);
	
		
	}
	public void setMP() {
		if(MP<200) {
		MP+=0.1;
		}
	}
	public int getHP() {
		return HP;
	}
	public double getMP() {
		return MP;
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
		x+=mx;
		y+=my;
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_LEFT) {
			mx=-6;
		}
		if(key==KeyEvent.VK_RIGHT) {
			mx=6;
		}
		if(key==KeyEvent.VK_UP) {
			my=-6;
		}
		if(key==KeyEvent.VK_DOWN) {
			my=6;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_LEFT) {
			mx=0;
		}
		if(key==KeyEvent.VK_RIGHT) {
			mx=0;
		}
		if(key==KeyEvent.VK_UP) {
			my=0;
		}
		if(key==KeyEvent.VK_DOWN) {
			my=0;
		}
	}
	
}
