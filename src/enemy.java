import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class enemy {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private boolean visible;
	private Image image;
	private Rectangle rect;
	private int speed=(int)(Math.random()*5+1);
	private String enemy;
	public enemy(String enemy) {
	
		ImageIcon ic = new ImageIcon("images/"+enemy+".png");
		this.enemy=enemy;
		image=ic.getImage();
		x=(int)(Math.random()*1180);
		w=image.getWidth(null);
		h=image.getHeight(null);
		y=-h;
		rect=new Rectangle(x,y,w,h);
	
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
	public String getEnemy() {
		return enemy;
	}
	public void move() {
		y+=(Board.getLevel()*speed);
		rect.y=y;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible=visible;
	}
	public Rectangle getBounds() {
		return rect;
	}
	
	
	
}
