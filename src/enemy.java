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
	public enemy(String enemy) {
	
		ImageIcon ic = new ImageIcon(enemy);
		image=ic.getImage();
		x=(int)(Math.random()*1180);
		System.out.println(x);
		w=image.getWidth(null);
		h=image.getHeight(null);
		y=0;
		
		
	
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
		y+=3;
	}
	public void setVisible(boolean visible) {
		this.visible=visible;
	}
	
	
	
	
}
