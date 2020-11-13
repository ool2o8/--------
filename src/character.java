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
	private Rectangle nunu;
	public character() {
		ImageIcon ic = new ImageIcon("images/NUNU.png");
		image=ic.getImage();
		
		w=image.getWidth(null);
		h=image.getHeight(null);
	
		
	}
	public void setMP() {
		if(MP<100&&Main.getInGame()) {
		MP+=0.1;
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
			snowball+=0.01;
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
	public void getDamage(int damage) {
		HP-=damage;
	}
	public Rectangle getBounds() {
		return new Rectangle(x-((int)(snowball/2-100)), y-(int)(snowball/2)-((int)(snowball/2-100))+20, (int)snowball, (int)(snowball));
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_LEFT) {
			if(this.x>0) {
				mx=-6;
			}
			else {
				mx=0;
			}
		}
		if(key==KeyEvent.VK_RIGHT) {
			if(this.x<1080) {
				mx=6;
			}
			else {
				mx=0;
			}
		}
		if(key==KeyEvent.VK_UP) {
			if(this.y>0) {
				my=-6;
			}
			else {
				my=0;
			}
		}
		if(key==KeyEvent.VK_DOWN) {
			if(this.y<800) {
				my=6;
			}
			else {
				my=0;
			}
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
