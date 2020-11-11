import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class character extends JPanel{
	private int mx;
	private int my;
	private int x=500;
	private int y=700;
	private int w;
	private int h;
	private int HP=100;
	private double MP=0;
	private double snowball=200;
	private int score=0;
	private Image image;
	public character() {
		ImageIcon ic = new ImageIcon("images/´©´©ÀÇ µÞÅÂ.png");
		image=ic.getImage();
		
		w=image.getWidth(null);
		h=image.getHeight(null);
	
		
	}
	public void setMP() {
		if(MP<100&&Main.getInGame()) {
		MP+=0.1;
		snowball+=0.01;
		score++;
		}
	}
	public void setscore(){
		if(Main.getInGame()) {
			score++;
			}
	}
	public void setsnowball(){
		if(Main.getInGame()) {
			snowball+=0.1;
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
	public int getScore() {
		return score;
	}
	public double getSnowball() {
		return snowball;
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
