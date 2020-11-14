import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Board extends JPanel implements ActionListener{
	private static boolean inGame=false;
	private Timer timer;
	private character nunu;
	private Background inGameBack;
	private Dimension d;
	private JLabel score=new JLabel();
	private enemy e;
	private Mushroom m;
	private static int level=1;
	private JButton help;
	int init=100;
	int t=0, t2=0;
	int player=1;
	private JPanel leftPanel;
//	private Help H;
	ArrayList enemy_list=new ArrayList();
	ArrayList mushroom_list=new ArrayList();
	Bgm B=new Bgm();
	public void InGame() {//인게임 전환
		Main.getInGame();
		inGame=true;
	}
	public static boolean getInGame() {
		return inGame;
	}
	public static int getLevel() {
		return level;
	}
	public Board() {//패널 생성자
		setLayout(null);
		
		
		
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
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		inGameBack=new Background();
		nunu=new character();
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
			else if(key=='h'||key=='H') {
				JFrame help=new JFrame();
				help.setTitle("데굴데굴 눈덩이!!!");
				help.setSize(500,500);
				help.setResizable(false);
				help.setVisible(true);
				help.setLocationRelativeTo(null);
				help.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public void initmushroom() {
		if(t%(init*3)==0) {
			m = new Mushroom("minion", 20);
			mushroom_list.add(m);
		}
		else if(t%(init*2)==0) {
			m = new Mushroom("icewall", 10);
			mushroom_list.add(m);
	
		}
		else if(t%(init)==0) {
			m = new Mushroom("mushroom", 20);
			mushroom_list.add(m);
	
		}
		
	}
	public void actionPerformed(ActionEvent e) {
	
		if(Main.getInGame()) {
		t+=level;
		if(t%(init*30)==0) {
			level+=1;
			init-=5;
		}
		collisions();
		nunu.move();//누누 조작 함수
		nunu.setMP();//누누 마나 재생
		nunu.setscore();
		nunu.setsnowball();
		inGameBack.setY();
		initmushroom();
		move();
		if(nunu.getHP()<=0) {
			timer.stop();
			//String nickname=JOptionPane.showInputDialog();
			String[] buttons= {"다시하기","끝내기","랭크확인"};
			int result=JOptionPane.showOptionDialog(this, "NUNU가 당했습니다", "GAME OVER!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
 
			if(result==JOptionPane.YES_OPTION) {//계속하면 
				reStart();				
				}
			else if(result==JOptionPane.NO_OPTION) {//끝내기
				System.exit(0);//종료
			}
			else {//취소하면 그대로
				
			}
		}
		}
	
		repaint();
	}
	public void move() {
		for(int i=0;i<mushroom_list.size();i++) {
			m = (Mushroom)(mushroom_list.get(i));
			m.move();
			if(m.getY()>1280) {
				mushroom_list.remove(i);
			}
		}
	}
	public void collisions() {
		for(int i=0;i<mushroom_list.size();i++) {
			m = (Mushroom)(mushroom_list.get(i));
			Rectangle r1=m.getBounds();
			
			if(r1.intersects(nunu.getBounds())) {
		
				nunu.getDamage(m.getDamage());
				sound(m.getEnemy());
				mushroom_list.remove(i);
				nunu.setScore();
			}
			}
	}
	public void sound(String enemy) {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(new File("sounds/"+enemy+".wav"));
			
			Clip clip=AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		}
		catch(Exception ex) {
			
		}
	}
	public void reStart() {
		timer.start();
		t=0;
		level=1;
		init=100;
		for(int i=0;i<mushroom_list.size();i++) {
			m = (Mushroom)(mushroom_list.get(i));
				mushroom_list.remove(i);
			}
		nunu.setRe();
		
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
		g2d.drawString(s,550,400);
		g2d.setFont(new Font("Serif", Font.PLAIN,50));
		g2d.drawString("START", 580, 510);
		String S="Press \'h\' to HELP";
		g2d.setFont(small);
		g2d.setColor(Color.darkGray);
		g2d.drawString(S, 530, 900);
			
	}
	private void doDrawNunu(Graphics g) {//누누 캐릭터 그리는 함수
		Graphics2D g2d=(Graphics2D) g;
		Font small=new Font("Verdana", Font.BOLD, 30);
		
		String s="SCORE : "+nunu.getScore();
		String S="LEVEL : "+level;
		
		
		g2d.setColor(Color.white);
		g2d.setFont(small);
		g2d.drawString(s, 560, 40);
		g2d.drawString(S, 30, 30);
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
		for(int i=0;i<mushroom_list.size();i++) {
			m = (Mushroom)(mushroom_list.get(i));
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), m.getWidth(), m.getHeigh(), this);
			
		}
		
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

