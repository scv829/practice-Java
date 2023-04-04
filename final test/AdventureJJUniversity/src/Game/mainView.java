package Game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.StyleContext.SmallAttributeSet;

import Game.GamePanel.escape;

import java.awt.event.*;


// ���� ȭ�� Ŭ����
public class mainView extends JPanel {
	
	// �г��� ���� ������
	static JFrame jFrame = Game.game.frame;
	// ������ ȭ��� ���� ȭ���� ũ�� �� ����
	protected static int heightSizeGap = 0;
	protected static int widthSizeGap = 0;
	
	MyActionListener m = new MyActionListener();
	myKey k = new myKey();
	selectPanel s = new selectPanel();
	
	// static ���� �Ͽ� �ٸ� Ŭ������ �Ѿ �� ���¸� �����Ӱ� ���� ����
	static escape es;
	static JPanel p = new JPanel();
	static characteristicPanel cp;
	// ���� ȭ��
	public mainView(){
		
		resizePanel(); // ũ�� ����
		
		
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
		titleJLabel.setFont(new Font("���ü", Font.BOLD, 20));
		titleJLabel.setBounds(100, 100, 400, 100);
		
		JButton startButton = new JButton("Start");
		startButton.setHorizontalAlignment(SwingConstants.CENTER);
		startButton.setBounds(90, 300, 150, 50);
		startButton.setBorderPainted(false);
		startButton.setBackground(new Color(0,0,0,0));
		startButton.setFont(new Font("���ü", Font.BOLD, 15));

		JButton explanButton = new JButton("Explanation");
		explanButton.setHorizontalAlignment(SwingConstants.CENTER);
		explanButton.setBounds(350, 300, 150, 50);
		explanButton.setBorderPainted(false);
		explanButton.setBackground(new Color(0,0,0,0));
		explanButton.setFont(new Font("���ü", Font.BOLD, 15));
		
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
	
	// ���� ȭ��
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
			
			exJLabel[0] = new JLabel("�湮 ����Ʈ�� ���� �� �ǹ��� �湮�ϼ���!");
			exJLabel[1] = new JLabel("�湮 ����Ʈ�� �ش��ϴ� �ǹ�����");
			exJLabel[2] = new JLabel("��ȣ�ۿ��� �ϸ� �湮 ����Ʈ���� ���ŵ˴ϴ�.");
			exJLabel[3] = new JLabel("�Ķ��� : �̵����",SwingConstants.CENTER);
			exJLabel[4] = new JLabel("����� : �湮�ϴ� �ǹ��� �Ա�",SwingConstants.CENTER);
			exJLabel[5] = new JLabel("[ESC : �湮 ����Ʈ â �ѱ�/���� | �Ͻ�����]",SwingConstants.CENTER);
			exJLabel[6] = new JLabel("[E : ��ȣ�ۿ� Ű]",SwingConstants.CENTER);
			exJLabel[7] = new JLabel("[Infinity : ���� ���] ",SwingConstants.CENTER);
			exJLabel[8] = new JLabel("[Record : �Ϲ� ���]",SwingConstants.CENTER);
			exJLabel[9] = new JLabel("[Practice : ���� ���]",SwingConstants.CENTER);
			exJLabel[10] = new JLabel("-�湮 ����Ʈ â-",SwingConstants.CENTER);
			exJLabel[11] = new JLabel("[C:����ϱ�] [R:�����] [M:���θ޴�]",SwingConstants.CENTER);
			
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
				exJLabel[i].setFont(new Font("���ü", Font.BOLD, 20));
				add(exJLabel[i]);
			}
	
			
			JButton reButton = new JButton("�޴���");
			reButton.addActionListener(m);
			reButton.setBounds(0, 0, 100, 50);
			reButton.setBorderPainted(false);
			reButton.setBackground(Color.WHITE);
			reButton.setForeground(Color.BLACK);
			reButton.setFont(new Font("���ü", Font.BOLD, 20));
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
	
	
	// ���� ��� ȭ��
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
			levelTitle.setFont(new Font("���ü", Font.BOLD, 20));
			levelTitle.setBounds(200, 100, 200, 100);
			
			levelTitle.setBackground(Color.WHITE);
			levelTitle.setOpaque(true);
			
			infiniteButton = new JButton("Infinity");
			infiniteButton.setFont(new Font("���ü", Font.BOLD, 15));
			infiniteButton.setBounds(50, 250, 100, 50);
			infiniteButton.setBackground(new Color(0,0,0,0));
			infiniteButton.setForeground(Color.RED);
			infiniteButton.setBorderPainted(false);
			infiniteButton.setOpaque(true);
			infiniteButton.setToolTipText("���� ���");
			
			RecordButton = new JButton("Record");
			RecordButton.setFont(new Font("���ü", Font.BOLD, 15));
			RecordButton.setBounds(250, 250, 100, 50);
			RecordButton.setBackground(new Color(0,0,0,0));
			RecordButton.setForeground(Color.BLUE);
			RecordButton.setBorderPainted(false);
			RecordButton.setOpaque(true);
			RecordButton.setToolTipText("�Ϲ� ���");
			
			PracticeButton = new JButton("Practice");
			PracticeButton.setFont(new Font("���ü", Font.BOLD, 15));
			PracticeButton.setBounds(450, 250, 100, 50);
			PracticeButton.setBackground(new Color(0,0,0,0));
			PracticeButton.setForeground(Color.GREEN);
			PracticeButton.setBorderPainted(false);
			PracticeButton.setOpaque(true);
			PracticeButton.setToolTipText("���� ���");
			
			
			infiniteButton.addActionListener(m);
			RecordButton.addActionListener(m);
			PracticeButton.addActionListener(m);
			
			JButton reButton = new JButton("�޴���");
			reButton.addActionListener(m);
			reButton.setBounds(0, 0, 100, 50);
			reButton.setBorderPainted(false);
			reButton.setBackground(new Color(0,0,0,0));
			reButton.setForeground(Color.BLACK);
			reButton.setFont(new Font("���ü", Font.BOLD, 20));
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
	
	// Record(�Ϲ� ���)�� �ܰ�(���̵�) ���� â
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
			b1.setToolTipText("���� �ܰ�");
			b1.setBounds(50, 100, 50, 50);
			
			b2.setText("N");
			b2.setToolTipText("���� �ܰ�");
			b2.setBounds(125, 100, 50, 50);
			
			
			b3.setText("H");
			b3.setToolTipText("����� �ܰ�");
			b3.setBounds(200, 100, 50, 50);
			
			reButton.setToolTipText("�ڷΰ���");
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
	
	// ���� �ÿ��� ȭ�� ȣ�� Ŭ����
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

	// ��ư�� ���� �׼Ǹ�����
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
			else if(ch.getText() == "�޴���") {
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
	// â�� ũ�⸦ �����ϴ� �Լ�
	static void resizePanel() {
		// ������ �������� ũ�⸦ ����
		int nowW = jFrame.getWidth();
		int nowH = jFrame.getHeight();
		// ������ ȭ��� ���� ȭ���� ũ�� �� ������ �� ���̸� ����
		heightSizeGap = 500 - jFrame.getContentPane().getHeight();
		widthSizeGap = 600 - jFrame.getContentPane().getWidth();
		// ���̸�ŭ�� ���� �������� ũ��� ��ġ�� �������� ũ�� ����
		jFrame.setSize(nowW + widthSizeGap, nowH + heightSizeGap);
	}

}
