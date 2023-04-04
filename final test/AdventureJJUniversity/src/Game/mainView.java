package Game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.StyleContext.SmallAttributeSet;

import Game.GamePanel.escape;

import java.awt.event.*;


// 게임 화면 클래스
public class mainView extends JPanel {
	
	// 패널을 입힐 프레임
	static JFrame jFrame = Game.game.frame;
	// 설정한 화면과 실제 화면의 크기 비교 변수
	protected static int heightSizeGap = 0;
	protected static int widthSizeGap = 0;
	
	MyActionListener m = new MyActionListener();
	myKey k = new myKey();
	selectPanel s = new selectPanel();
	
	// static 으로 하여 다른 클래스로 넘어갈 때 상태를 자유롭게 변경 가능
	static escape es;
	static JPanel p = new JPanel();
	static characteristicPanel cp;
	// 시작 화면
	public mainView(){
		
		resizePanel(); // 크기 조율
		
		
		setLayout(null);
		ImageIcon image = new ImageIcon("imageMap/mainView.png");
		Image backimg = image.getImage().getScaledInstance(620, 200, Image.SCALE_SMOOTH);
		image.setImage(backimg);
		JLabel backImage = new JLabel(image);
		backImage.setBounds(0, 300, 600, 200);
		
		image = new ImageIcon("imageMap/sky.png");
		backimg = image.getImage().getScaledInstance(620, 500, Image.SCALE_SMOOTH);
		image.setImage(backimg);
		JLabel sky = new JLabel(image);
		sky.setBounds(0, 0, 600, 500);
		
		
		JLabel titleJLabel = new JLabel("Adventure-JJ-Univercity",SwingConstants.CENTER);
		titleJLabel.setFont(new Font("고딕체", Font.BOLD, 20));
		titleJLabel.setBounds(100, 100, 400, 100);
		
		JButton startButton = new JButton("Start");
		startButton.setHorizontalAlignment(SwingConstants.CENTER);
		startButton.setBounds(90, 300, 150, 50);
		startButton.setBorderPainted(false);
		startButton.setBackground(new Color(0,0,0,0));
		startButton.setFont(new Font("고딕체", Font.BOLD, 15));

		JButton explanButton = new JButton("Explanation");
		explanButton.setHorizontalAlignment(SwingConstants.CENTER);
		explanButton.setBounds(350, 300, 150, 50);
		explanButton.setBorderPainted(false);
		explanButton.setBackground(new Color(0,0,0,0));
		explanButton.setFont(new Font("고딕체", Font.BOLD, 15));
		
		startButton.addActionListener(m);
		explanButton.addActionListener(m);
		
		startButton.addMouseListener(new myMouse());
		explanButton.addMouseListener(new myMouse());
		
		add(titleJLabel);
		add(startButton);
		add(explanButton);
		add(backImage);
		add(sky);
		
		addKeyListener(k);
		
		setBackground(Color.WHITE);
		setOpaque(true);
		
		setVisible(true);
		
	}
	
	// 설명 화면
	class explan extends JPanel{
		public explan() {
			setLayout(null);
			
			ImageIcon image = new ImageIcon("imageMap/explain.png");
			Image backimg = image.getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH);
			image.setImage(backimg);
			JLabel backImage = new JLabel(image);
			backImage.setBounds(0, 350, 600, 150);
			add(backImage);
			
			JLabel[] exJLabel = new JLabel[12];
			
			exJLabel[0] = new JLabel("방문 리스트를 보고 각 건물에 방문하세요!");
			exJLabel[1] = new JLabel("방문 리스트에 해당하는 건물에서");
			exJLabel[2] = new JLabel("상호작용을 하면 방문 리스트에서 제거됩니다.");
			exJLabel[3] = new JLabel("파란색 : 이동통로",SwingConstants.CENTER);
			exJLabel[4] = new JLabel("보라색 : 방문하는 건물의 입구",SwingConstants.CENTER);
			exJLabel[5] = new JLabel("[ESC : 방문 리스트 창 켜기/끄기 | 일시정지]",SwingConstants.CENTER);
			exJLabel[6] = new JLabel("[E : 상호작용 키]",SwingConstants.CENTER);
			exJLabel[7] = new JLabel("[Infinity : 무한 모드] ",SwingConstants.CENTER);
			exJLabel[8] = new JLabel("[Record : 일반 모드]",SwingConstants.CENTER);
			exJLabel[9] = new JLabel("[Practice : 연습 모드]",SwingConstants.CENTER);
			exJLabel[10] = new JLabel("-방문 리스트 창-",SwingConstants.CENTER);
			exJLabel[11] = new JLabel("[C:계속하기] [R:재시작] [M:메인메뉴]",SwingConstants.CENTER);
			
			for(int i = 0 ; i < 12 ; i++) {
				if(i == 3) {
					exJLabel[i].setForeground(Color.BLUE);
				}
				else if(i == 4) {
					exJLabel[i].setForeground(Color.PINK);
				}
				else {
					exJLabel[i].setForeground(Color.BLACK);
				}
				
				if(i < 3) exJLabel[i].setBounds(100, 50 + (25 * i), 500, 50);
				else exJLabel[i].setBounds(50, 50 + (25 * i), 500, 50);
				exJLabel[i].setFont(new Font("고딕체", Font.BOLD, 20));
				add(exJLabel[i]);
			}
	
			
			JButton reButton = new JButton("메뉴로");
			reButton.addActionListener(m);
			reButton.setBounds(0, 0, 100, 50);
			reButton.setBorderPainted(false);
			reButton.setBackground(Color.WHITE);
			reButton.setForeground(Color.BLACK);
			reButton.setFont(new Font("고딕체", Font.BOLD, 20));
			reButton.setOpaque(true);
			
			reButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					JButton b = (JButton) e.getSource();
					b.setBorderPainted(true);
				}
				public void mouseExited(MouseEvent e) {
					JButton b = (JButton) e.getSource();
					b.setBorderPainted(false);
				}
			});
			
			add(reButton);
			
			setBackground(Color.WHITE);
			setOpaque(true);
			
		}
	}
	
	
	// 게임 모드 화면
	class selectMode extends JPanel{
		
		static JButton infiniteButton;
		static JButton RecordButton;
		static JButton PracticeButton;
		public selectMode() {
			
			requestFocus();
			setLayout(null);
			
			s.setLocation(150,150);
			
			add(s);
			
			ImageIcon image = new ImageIcon("imageMap/selectmod.png");
			Image backimg = image.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
			image.setImage(backimg);
			JLabel backImage = new JLabel(image);
			backImage.setBounds(0, 200, 600, 300);
			
			JLabel levelTitle = new JLabel("GAME LEVEL",SwingConstants.CENTER);
			levelTitle.setFont(new Font("고딕체", Font.BOLD, 20));
			levelTitle.setBounds(200, 100, 200, 100);
			
			levelTitle.setBackground(Color.WHITE);
			levelTitle.setOpaque(true);
			
			infiniteButton = new JButton("Infinity");
			infiniteButton.setFont(new Font("고딕체", Font.BOLD, 15));
			infiniteButton.setBounds(50, 250, 100, 50);
			infiniteButton.setBackground(new Color(0,0,0,0));
			infiniteButton.setForeground(Color.RED);
			infiniteButton.setBorderPainted(false);
			infiniteButton.setOpaque(true);
			infiniteButton.setToolTipText("무한 모드");
			
			RecordButton = new JButton("Record");
			RecordButton.setFont(new Font("고딕체", Font.BOLD, 15));
			RecordButton.setBounds(250, 250, 100, 50);
			RecordButton.setBackground(new Color(0,0,0,0));
			RecordButton.setForeground(Color.BLUE);
			RecordButton.setBorderPainted(false);
			RecordButton.setOpaque(true);
			RecordButton.setToolTipText("일반 모드");
			
			PracticeButton = new JButton("Practice");
			PracticeButton.setFont(new Font("고딕체", Font.BOLD, 15));
			PracticeButton.setBounds(450, 250, 100, 50);
			PracticeButton.setBackground(new Color(0,0,0,0));
			PracticeButton.setForeground(Color.GREEN);
			PracticeButton.setBorderPainted(false);
			PracticeButton.setOpaque(true);
			PracticeButton.setToolTipText("연습 모드");
			
			
			infiniteButton.addActionListener(m);
			RecordButton.addActionListener(m);
			PracticeButton.addActionListener(m);
			
			JButton reButton = new JButton("메뉴로");
			reButton.addActionListener(m);
			reButton.setBounds(0, 0, 100, 50);
			reButton.setBorderPainted(false);
			reButton.setBackground(new Color(0,0,0,0));
			reButton.setForeground(Color.BLACK);
			reButton.setFont(new Font("고딕체", Font.BOLD, 20));
			reButton.setOpaque(true);
			
			reButton.addMouseListener(new myMouse());
			infiniteButton.addMouseListener(new myMouse());
			PracticeButton.addMouseListener(new myMouse());
			RecordButton.addMouseListener(new myMouse());
			
			add(reButton);
			
			add(levelTitle);
			add(infiniteButton);
			add(PracticeButton);
			add(RecordButton);
			add(backImage);
			setBackground(Color.WHITE);
			setOpaque(true);
			addKeyListener(k);
			
			setVisible(true);
			
		}	
	}
	
	// Record(일반 모드)의 단계(난이도) 설정 창
	 class selectPanel extends JPanel{
		
		JLabel titleJLabel = new JLabel();
		private JButton b1 = new JButton();
		private JButton b2 = new JButton();
		private JButton b3 = new JButton();
		private JButton reButton = new JButton("R");
		
		public selectPanel() {
			setSize(300, 200);
			setFocusable(false);
			
			titleJLabel.setText("Choose Level");
			titleJLabel.setBounds(50, 20, 100, 50);
			titleJLabel.setBackground(Color.WHITE);
			
			b1.setText("E");
			b1.setToolTipText("쉬운 단계");
			b1.setBounds(50, 100, 50, 50);
			
			b2.setText("N");
			b2.setToolTipText("보통 단계");
			b2.setBounds(125, 100, 50, 50);
			
			
			b3.setText("H");
			b3.setToolTipText("어려운 단계");
			b3.setBounds(200, 100, 50, 50);
			
			reButton.setToolTipText("뒤로가기");
			reButton.setBounds(250, 0, 50, 50);
			
			setBackground(Color.GRAY);
			setOpaque(true);
				
			setLayout(null);
				
			add(titleJLabel);
			add(b1); add(b2); add(b3);
			add(reButton);
				
			b1.addActionListener(m);
			b2.addActionListener(m);
			b3.addActionListener(m);
			
			reButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					Component c = b.getParent();
					c.setVisible(false);
					selectMode.infiniteButton.setEnabled(true);
					selectMode.RecordButton.setEnabled(true);
					selectMode.PracticeButton.setEnabled(true);
				}
			});

			
			setVisible(false);
		}
	}
	
	// 게임 플에이 화면 호출 클래스
	class gameStart {
		public gameStart(String s, escape e) {
			GamePanel g = new GamePanel(e);
			cp = new characteristicPanel(s);
			
			cp.setLocation(500, 0);
			cp.setSize(100, 500);
			
			g.setSize(500, 500);
			g.setLocation(0, 0);
			
			JPanel p = new JPanel();
			p.setLayout(null);
			p.setSize(600, 600);
		
			p.add(g);
			p.add(cp);
			
			jFrame.setContentPane(p);	
			jFrame.getContentPane().revalidate();
			jFrame.getContentPane().repaint();
		
			g.setFocusable(true);
			g.requestFocus();

		}
	}

	// 버튼에 대한 액션리스너
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton ch = (JButton)e.getSource();
			if( ch.getText() == "Start") {
				selectMode sm = new selectMode();
				
				sm.setFocusable(true);
				sm.requestFocus();
				
				jFrame.setContentPane(sm);
				jFrame.getContentPane().revalidate();
				jFrame.getContentPane().repaint();
			}
			else if(ch.getText() == "메뉴로") {
				mainView m = new mainView();
				
				m.setFocusable(true);
				m.requestFocus();
				
				jFrame.setContentPane(m);
				jFrame.getContentPane().revalidate();
				jFrame.getContentPane().repaint();

			}
			else if(ch.getText() == "Record") {
				ch.getParent().repaint();
				s.setVisible(true);

				selectMode.infiniteButton.setEnabled(false);
				selectMode.RecordButton.setEnabled(false);
				selectMode.PracticeButton.setEnabled(false);

			}
			else if(ch.getText() == "Explanation"){
				explan ex = new explan();
				
				ex.setFocusable(true);
				ex.requestFocus();
				
				jFrame.setContentPane(ex);
				jFrame.getContentPane().revalidate();
				jFrame.getContentPane().repaint();
				
			}
			else {
				int level = 0;
				s.setVisible(false);
				switch ( ch.getText() ) {
				case "Infinity" : { level = 4; GamePanel.mod = 1; break;}
				case "E": {  level = 2; break;}
				case "N": { level = 3; break;}
				case "H": { level = 4; break;}
				case "Practice" : { level = 0; break; }
				}
				escape escape = new escape(level);
				gameStart gs = new gameStart(ch.getText(), escape);
			}
		}
	}
	
	class myMouse extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBorderPainted(true);
			b.getParent().repaint();
		}
		public void mouseExited(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBorderPainted(false);
			b.getParent().repaint();
		}
	}
	
	class myKey extends KeyAdapter{
		public void KeyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(s.isVisible()) s.setVisible(false);
				else s.setVisible(true);
			}
		}
		
	}
	// 창의 크기를 조절하는 함수
	static void resizePanel() {
		// 현재의 프레임의 크기를 저장
		int nowW = jFrame.getWidth();
		int nowH = jFrame.getHeight();
		// 설정한 화면과 실제 화면의 크기 비교 변수에 그 차이를 저장
		heightSizeGap = 500 - jFrame.getContentPane().getHeight();
		widthSizeGap = 600 - jFrame.getContentPane().getWidth();
		// 차이만큼을 현제 프레임의 크기와 합치고 프레임의 크기 변경
		jFrame.setSize(nowW + widthSizeGap, nowH + heightSizeGap);
	}

}
