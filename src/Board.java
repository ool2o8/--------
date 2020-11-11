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
	private Background inGameBack;
	private enemy E=new enemy();
	private Dimension d;
	private JLabel score=new JLabel();
	Bgm B=new Bgm();
	public void InGame() {//인게임 전환
		Main.getInGame();
		inGame=true;
	}
	public static boolean getInGame() {
		return inGame;
	}
	public Board() {//패널 생성자
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
		
		inGameBack=new Background();
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
				Main.setInGame();
				B.stopBgm();
				B.playSound(new File("sounds/소환사의 협곡에 오신것을 환영합니다.wav"), 1.0f, false);
				
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
		nunu.move();//누누 조작 함수
		nunu.setMP();//누누 마나 재생
		nunu.setscore();
		nunu.setsnowball();
		inGameBack.setY();
		E.move();
		repaint();
	}
	private void showIntroScreen(Graphics2D g2d) {//인트로 화면
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
	private void doDrawNunu(Graphics g) {//누누 캐릭터 그리는 함수
		Graphics2D g2d=(Graphics2D) g;
		
		String s="SCORE : "+nunu.getScore();
		Font small=new Font("Verdana", Font.BOLD, 30);
		
		g2d.setColor(Color.white);
		g2d.setFont(small);
		g2d.drawString(s, 560, 40);
		g2d.setColor(Color.white);
		g2d.fillOval(nunu.getX()-((int)nunu.getSnowball()/2-100), nunu.getY()-(int)nunu.getSnowball()/2-((int)nunu.getSnowball()/2-100)+20, (int)nunu.getSnowball(), (int)nunu.getSnowball());
		
		g2d.drawImage(nunu.getImage(),nunu.getX(), nunu.getY(), 200,200, this);
		g2d.setColor(Color.green);
		g2d.fillRect(nunu.getX()+45, nunu.getY()-20, nunu.getHP(),10);
		g2d.setColor(Color.black);
		g2d.drawRect(nunu.getX()+45, nunu.getY()-20, 100,10);
	
		g2d.setColor(Color.blue);
		g2d.fillRect(nunu.getX()+45, nunu.getY()-10, (int)nunu.getMP(),10);
		g2d.setColor(Color.black);
		g2d.drawRect(nunu.getX()+45, nunu.getY()-10, 100,10);
	}
	public void doDrawBackground(Graphics g2d) {
		g2d.drawImage(inGameBack.getImage(), 0, inGameBack.getY1(), 1280,inGameBack.getHeigh(), this);
		g2d.drawImage(inGameBack.getImage(), 0, inGameBack.getY2(), 1280, inGameBack.getHeigh(), this);
	}
	public void doDrawEnemy(Graphics g2d) {
		g2d.drawImage(E.getImage(), E.getX(), E.getY(), 100,E.getHeigh(), this);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {//자신 그림
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	private void doDrawing(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		if(!inGame) {//!인게임
			showIntroScreen(g2d);//인트로화면 
		}
		else {//인게임
			
			doDrawBackground(g2d);
			doDrawNunu(g2d);//누누 출력
			doDrawEnemy(g2d);
		
			
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
