import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.Timer;
public class Main extends JFrame implements ActionListener{
	private boolean inGame=false;
	private Timer timer;
	public Main() {
		initUI();
	}
	public boolean getInGame() {
		return inGame;
	}
	public void setInGame() {
		inGame=true;
	}
	private void initUI() {
		add(new Board());
		
		timer=new Timer(40,this);
		timer.start();
		setTitle("µ¥±¼µ¥±¼ ´«µ¢ÀÌ!!!");
		setSize(1280,1000);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void initGame() {
			AniBoard B=new AniBoard();
		 	add(B);
	}
	public static void main(String[] args) throws Exception {
		
		Main A=new Main();
		
		A.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
