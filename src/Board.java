import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
public class Board extends JPanel implements ActionListener{
	private static boolean inGame=false;
	private Timer timer;
	private character nunu;
	private Background inGameBack=new Background();
	private Dimension d;
	Bgm B=new Bgm();
	public void InGame() {//�ΰ��� ��ȯ
		Main.getInGame();
		inGame=true;
	}
	public static boolean getInGame() {
		return inGame;
	}
	public Board() {//�г� ������
		B.playBgm(new File("sounds/26_Nunu and Willump, the Boy and his Yeti- Trailer.wav"), 1.0f, false);
		setBackground(Color.black);
		initBoard();
		initVariables();
	}
	private void initVariables() {
		d=new Dimension(400,400);
		timer=new Timer(10,this);
		timer.start();
	}
	private void initBoard() {
		
		
		nunu=new character();
		addKeyListener(new TAdapter());
		setFocusable(true);
	}
	
	class TAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int key =e.getExtendedKeyCode();
			if(key=='s'||key=='S') {
				InGame();
				B.stopBgm();
			}
			else {
				nunu.keyPressed(e);
			}
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			nunu.keyReleased(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		nunu.move();//���� ���� �Լ�
		nunu.setMP();//���� ���� ���
		inGameBack.setY();
		repaint();
	}
	private void showIntroScreen(Graphics2D g2d) {//��Ʈ�� ȭ��
		Image back=new ImageIcon("images/introImage.jpg").getImage();
		g2d.drawImage(back, 0, 120, 1280, 720, null);
		
		//g2d.setColor(new Color(0,32,48));
		//g2d.fillRect(480,400,400,100);
		g2d.setColor(Color.white); 
		g2d.drawRect(460,450,400,100);
		
		String s="Press 'S' to start";
		Font small=new Font("Helvetica",Font.BOLD,30);
		FontMetrics metr=this.getFontMetrics(small);
		
		g2d.setColor(Color.white);
		g2d.setFont(small);
		g2d.drawString(s,560,400);
		g2d.setFont(new Font("Serif", Font.PLAIN,50));
		g2d.drawString("START", 590, 510);
		
			
	}
	private void doDrawNunu(Graphics g) {//���� ĳ���� �׸��� �Լ�
		Graphics2D g2d=(Graphics2D) g;
	
		g2d.drawImage(nunu.getImage(),nunu.getX(), nunu.getY(), 200,200, this);
		g2d.setColor(Color.green);
		g2d.fillRect(nunu.getX()+45, nunu.getY()-20, nunu.getHP(),10);
		g2d.setColor(Color.white);
		g2d.drawRect(nunu.getX()+45, nunu.getY()-20, 100,10);
	
		g2d.setColor(Color.blue);
		g2d.fillRect(nunu.getX()+45, nunu.getY()-10, (int)nunu.getMP(),10);
		g2d.setColor(Color.white);
		g2d.drawRect(nunu.getX()+45, nunu.getY()-10, 100,10);
	}
	public void doDrawBackground(Graphics g2d) {
		g2d.drawImage(inGameBack.getImage(), 0, inGameBack.getY1(), 1280,inGameBack.getHeigh(), this);
		g2d.drawImage(inGameBack.getImage(), 0, inGameBack.getY2(), 1280, inGameBack.getHeigh(), this);
	}
	
	@Override
	public void paintComponent(Graphics g) {//�ڽ� �׸�
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	private void doDrawing(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		if(!inGame) {//!�ΰ���
			showIntroScreen(g2d);//��Ʈ��ȭ�� 
		}
		else {//�ΰ���
			doDrawBackground(g2d);
			doDrawNunu(g2d);//���� ���
		}
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
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
