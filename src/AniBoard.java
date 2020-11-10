import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
public class AniBoard extends JPanel implements ActionListener{
	private Dimension d;
	private Timer timer;
	private character nunu;
	private final int DELAY=10;
	public AniBoard() {
		d=new Dimension(400,400);
		addKeyListener(new MoveAdapter());
		setBackground(Color.WHITE);
		setFocusable(true);
	
		nunu=new character();
		
		timer=new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	private void doDrawing(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
	
		g2d.drawImage(nunu.getImage(),nunu.getX(), nunu.getY(), this);
		g2d.setColor(Color.black);
		g2d.drawRect(nunu.getX()+28, nunu.getY()-20, 200,10);
		g2d.setColor(Color.red);
		g2d.fillRect(nunu.getX()+28, nunu.getY()-20, nunu.getHP()*2,10);
		
		g2d.setColor(Color.black);
		g2d.drawRect(nunu.getX()+28, nunu.getY()-10, 200,10);
		g2d.setColor(Color.blue);
		g2d.fillRect(nunu.getX()+28, nunu.getY()-10, (int)nunu.getMP()*2,10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		nunu.move();
		nunu.setMP();
		repaint();
	}
	private class MoveAdapter extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			nunu.keyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			nunu.keyPressed(e);
		}
	}
	
}
