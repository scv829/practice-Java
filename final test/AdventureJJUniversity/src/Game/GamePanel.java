package Game;

import java.util.*;
import java.util.spi.LocaleServiceProvider;
import java.awt.*;
import java.awt.dnd.DnDConstants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.text.AbstractDocument.Content;

import org.w3c.dom.events.EventTarget;

import Game.GamePanel.escape;
import Game.characteristicPanel.missionCount;
import Game.characteristicPanel.timeBar;
import Game.mainView.selectPanel;

import java.awt.event.*;
import java.lang.System.Logger.Level;
import java.lang.annotation.AnnotationTypeMismatchException;

// ���� �÷��� ȭ��
public class GamePanel extends JPanel {

	final private int move = 10;	// �����̴� �Ÿ�
	final static int startX = 450;	// ���� x��ǥ
	final static int startY = 450;	// ���� y��ǥ
	final static int startMap = 0;	// ���� �� ��ȣ
	
	static JFrame jFrame = Game.game.frame; // �г��� ���� ������
	JLabel user = new JLabel("��");	// ����
	
	protected static int mod = 0;		// ���� ��� Ȯ��
	protected static int keyMap = -1;	// � ��ȣ�ۿ� �ǹ����� Ȯ���ϴ� ���� (�� �ʿ� 2�� �̻��� �ǹ��� ���� �� � �ǹ����� Ȯ���ϱ� ����)
	protected static int mapNum = 0;	// �� ��ȣ
	protected static int x = 450;		// x ��ǥ
	protected static int y = 450;		// y ��ǥ
	// xMax : �����ϼ� �ִ� �ִ� X�� ũ��| yMaX : �����ϼ� �ִ� �ִ� Y�� ũ��
	protected static int xMax = jFrame.getWidth() - mainView.widthSizeGap - 20;
	protected static int yMax = jFrame.getHeight() - mainView.heightSizeGap - 20;
	
	// �밢�� �������� �ϱ����� �Ҹ� ���� true : Ű�� ���� ,false : Ű�� �ȴ��� 
	boolean upP = false; 
	boolean downP = false; 
	boolean leftP = false; 
	boolean rightP = false;
	// timebar ���� ����
	private static Color c = Color.GREEN;

	static dataClass dc = new dataClass();
	static escape es;
	
	// ���� �÷��� ȭ�� | escape e : mainView���� ������ ��带 �ָ� �׿� ���� â�� ������ ������ ��â�� ���̸� �����ߴ� â�� �´� escape â�� �ҷ��� �� ����
	public GamePanel(escape e) {
		
		this.x = startX;
		this.y = startY;
		this.mapNum = startMap;
		
		setLayout(null);
		
		this.es = e;
		es.setBounds(200, 150, 300, 200);
		
		
		add(es);
		
		requestFocus();
		
		addKeyListener(new MyKeyListener());
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Component c = (Component)e.getSource();
				c.setFocusable(true);
				c.requestFocus();
			}
		});
		
		setVisible(true);
	}
	// map ���� �� ������ �����̴� �� ǥ���� �Լ�
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon("imageMap/map"+ mapNum +".png");
		Image backImage = icon.getImage();
		
		g.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setColor(c);
		g.fillRect(x, y, 20, 20);
		es.repaint();
	}
	
	// esc �� �̼��� �����ִ� â
	static class escape extends JPanel{
		
		// posArray : �� ���ӳ� ��ǥ�� �ִ� Ŭ����
		posArray ps = new posArray();
		// ��ȣ�ۿ� �� �� �ִ� �ǹ��� �� 26���� �־� ������ �迭�� ���� 
		static int[] missionMap = new int[27];
		// �̼� â�� �˷��ִ� �󺧹迭
		static JLabel[] missionJLabel = new JLabel[4];
		// � ��带 �����ߴ��� �˷��ִ� ����
		protected static int level = 0;
		
		int i = 0;
		
		public escape(int level) {
			
			JLabel missionTitle = new JLabel("�湮 �ؾ��ϴ� ���",SwingConstants.CENTER);
			JLabel explainESC = new JLabel("ESC : �޴� �ѱ�/����",SwingConstants.CENTER);
			setSize(300, 200);
			setLayout(null);
			//mainView���� ������ ��带 �Է¹���
			this.level = level;
			
			missionTitle.setBounds(50, 10, 200, 25);
			missionTitle.setBackground(Color.BLACK);
			missionTitle.setForeground(Color.WHITE);
			missionTitle.setOpaque(true);
			
			// ���� �÷��� ��ȣ�ۿ� Ŭ������ � ���� �����ߴ� �� �˷���
			characteristicPanel.mc.mission = level;
			
			
			// �� �󺧹迭�� �ʱ�ȭ
			for(int j = 0; j < 4; j++) {
				missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
			}
			// �� �迭�� ��忡 ���� �缳��
			reproduceLabel(this.level);

			
			explainESC.setBounds(50, 35 + (20 * this.level), 200, 25);
			explainESC.setBackground(Color.BLACK);
			explainESC.setForeground(Color.WHITE);
			explainESC.setOpaque(true);

			// ��� �ϱ� ��ư
			JButton continueButton = new JButton("C");
			continueButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					es.setVisible(false);
					getParent().getParent().repaint();
					
				}
			});
			// ����� ��ư
			JButton retryButton = new JButton("R");
			retryButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//�ð��� ���̴� ������ ��������
					characteristicPanel.tb.th.interrupt();
					// ��� �� �ʱⰪ���� �ʱ�ȭ
					characteristicPanel.barsize = 60;
					timeBar.rCount = 0;
					timeBar.gCount = 0;
					c = new Color(0,250,0);
					timeBar.c =  c;
					timeBar.timeCount.setText("60");
					// �ʱ�ȭ�� ������ �ٽ� �׸���
					getParent().getParent().repaint();
					
					// � ��忡�� �� ��ư�� ���Ǵ��� Ȯ��
					String s = new String();
					
					if(es.level == 2) {
						s = "E";
					}
					else if(es.level == 3) {
						s= "N";
					}
					else if(es.level == 4) {
						if(mod == 1) s = "Infinity";
						else s = "H";
					}
					else {
						s = "Practice";
					}
					// ������ ��忡 ���� �� �迭 �缳��
					reproduceLabel(es.level);
					// ������ ������̴� ���� ����� ���ο� ������ �ؾ��ϹǷ� ���� ���� ���ο� ���� �÷��� �г� ��ȯ
					escape es = new escape(level);
					GamePanel g = new GamePanel(es);
					mainView.cp = new characteristicPanel(s);
					
					mainView.cp.setLocation(500, 0);
					mainView.cp.setSize(100, 500);
					
					g.setSize(500, 500);
					g.setLocation(0, 0);
					
					JPanel p = new JPanel();
					p.setLayout(null);
					p.setSize(600, 600);
					
					p.add(g);
					p.add(mainView.cp);
					
					jFrame.setContentPane(p);	
					jFrame.getContentPane().revalidate();
					jFrame.getContentPane().repaint();
					
					g.setFocusable(true);
					g.requestFocus();
					
				}
			});
			
			// ���� ȭ������ ���� ��ư
			JButton mainButton = new JButton("M");
			mainButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//�ð��� ���̴� ������ ��������
					characteristicPanel.tb.th.interrupt();
					
					// �̼Ƕ󺧿� ����ִ� ������ �� ����
					for(int j = 0; j < 4; j++) {
						missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
					}
					// ������ ���۵Ǳ� �� �ʱⰪ���� ��� �ʱ�ȭ
					mainView.cp.mc.successMission = 0;
					mod = 0;
					mainView m = new mainView();
					
					characteristicPanel.barsize = 60;
					timeBar.rCount = 0;
					timeBar.gCount = 0;
					c = new Color(0,250,0);
					timeBar.c =  c;
					timeBar.timeCount.setText("60");
					
					m.setFocusable(true);
					m.requestFocus();
					
					jFrame.setContentPane(m);
					jFrame.getContentPane().revalidate();
					jFrame.getContentPane().repaint();
					
				}
			});
			
			continueButton.setBounds(25, 140, 50, 50);
			retryButton.setBounds(125, 140, 50, 50);
			mainButton.setBounds(225, 140, 50, 50);
			
			add(missionTitle);
			add(continueButton);
			add(retryButton);
			add(mainButton);
			add(explainESC);
			
			setBackground(Color.GRAY);
			setOpaque(true);
			
			setVisible(true);
			
		}
		// ��ȣ�ۿ��� �ؾ��ϴ� �ǹ��� �˷��ִ� �󺧹迭�� �缳�� �Լ�
		void reproduceLabel(int level) {
			// ��ȣ�ۿ��ϴ� �ǹ��� 0~26���� ������ ���⿡ �ش����� �ʴ� -1������ ��� �ʱ�ȭ
			Arrays.fill(missionMap, -1);

			// ��忡 ���� ����
			for(i = 0; i < level ; i++) {
				// 0~26������ ������ �������� �ϳ� ����
				int randomMap = (int)(Math.random() * ps.numToPlace.size());
				while( missionMap[randomMap] != -1 ) { // missionMap = -1 : �ش��ϴ� ��ȣ�ۿ� �ǹ��� ���õ��� �ʾ��� | missionMap != -1 : �̹� �ش� �ǹ��� �湮��Ͽ� ��
 					 randomMap = (int)(Math.random() * ps.numToPlace.size());	// �̹� �ش� �ǹ��� �湮 ��Ͽ� �����Ƿ� ��Ž�� 
				}
				// �ش� �ǹ��� ���� ������ missionMap�� ����
				missionMap[randomMap] = i;
				// randomMap�� �ش��ϴ� ���� �󺧿� ����
				missionJLabel[i].setText(ps.numToPlace.get(randomMap)); 
				// �� ������ ���������� ������� �ϹǷ� y��ǥ�� i(�ݺ��� ȱ��)�� ���� �޶��� 
				missionJLabel[i].setBounds(50, 35 + (20 * i), 200, 25);
				missionJLabel[i].setBackground(Color.WHITE);
				
				missionJLabel[i].setForeground(Color.BLACK);
				
				missionJLabel[i].setOpaque(true);
				
				missionJLabel[i].setVisible(true);
				add(missionJLabel[i]);
			}
			
		}

	}
	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			// esc�� ������ ��
			if(keyCode == KeyEvent.VK_ESCAPE) {
					// esâ�� �����ִ��� �����ִ����� ���� ��
					if(es.isVisible()) {
						es.setVisible(false);
					}
					else {
						es.setVisible(true);
					}
					
					// mod = 1 : ����(Infinity)��� 
					if(mod == 1) {
						if(characteristicPanel.barsize == 0) {
							characteristicPanel.tb.th.interrupt();
							
							mainView m = new mainView();
							mod = 0;
							characteristicPanel.barsize = 60;
							timeBar.rCount = 0;
							timeBar.gCount = 0;
							c = new Color(0,250,0);
							timeBar.c =  c;
							timeBar.timeCount.setText("60");
							
							m.setFocusable(true);
							m.requestFocus();
							
							jFrame.setContentPane(m);
							jFrame.getContentPane().revalidate();
							jFrame.getContentPane().repaint();
						}
					}
					// ���� ��尡 �ƴϰ� && �ð��� ��� ����ϰų� , ��� �̼��� �� �­��� �� escŰ ������ ���θ޴��� ������ ���
					else if( ( characteristicPanel.barsize == 0 || characteristicPanel.missionCount.mission == 0) && es.level != 0) {
						characteristicPanel.tb.th.interrupt();
						
						mainView m = new mainView();
						mod = 0;
						characteristicPanel.barsize = 60;
						timeBar.rCount = 0;
						timeBar.gCount = 0;
						c = new Color(0,250,0);
						timeBar.c =  c;
						timeBar.timeCount.setText("60");
						
						m.setFocusable(true);
						m.requestFocus();
						
						jFrame.setContentPane(m);
						jFrame.getContentPane().revalidate();
						jFrame.getContentPane().repaint();
						
					}
			}
			// ��ȣ�ۿ� Ű E�� ������ ��
			else if(keyCode == KeyEvent.VK_E) {
				// ���� �ִ� �ʿ��� �� ��ġ�� �ִ� ��ǥ�� ��ȣ�ۿ��� �ִ� �ǹ��� Ȯ��
				if (dc.missionSpace(mapNum, x, y)) {
					// ��ȣ�ۿ��� �Ǵ� �ǹ��� �� �̼Ǹ�Ͽ� �ִ��� Ȯ��
					for(int i = 0;  i < es.level; i++) {
						// �̼� ��Ͽ� �ִٸ� ����� WHTIE �����̿��� ���ڻ� ���� WHITE�� �ٲ㼭 �۾��� �������� ó�� �����ְ� �ش� �ǹ��� �湮�ߴٴ°� �˷��ְ� �̼Ǽ��� ����
						if( es.missionJLabel[i].getText().equals(posArray.numToPlace.get(keyMap) ) ) {
							es.missionJLabel[i].setForeground(Color.WHITE);
							es.missionMap[keyMap] = -1 ;
							mainView.cp.mc.mission--;
							if(mod != 1 ) mainView.cp.mc.labelChange(); // �̰� ���Ѹ�尡 �ƴϸ� �̼Ǽ��� ���ΰ��� �����ֱ� ���� ���� �ٲ�
							// ���� ����
							if(mod == 1) {
								// ���� ���� �߰�
								mainView.cp.mc.successMission++;
								// �ð��� 5�� �÷��� 60�ʰ� �Ѿ�� 60�ʷ� ����
								characteristicPanel.barsize += 5;
								if(characteristicPanel.barsize >= 60) characteristicPanel.barsize = 60;
								// �ð��� �þ�� ���� timeBar ���� �缳��
								if(timeBar.gCount > 0) {
									timeBar.gCount -= 5;
									if(timeBar.gCount <= 0) {timeBar.rCount += timeBar.gCount; timeBar.gCount = 0;}
								}
								else if (timeBar.gCount == 0 &&timeBar.rCount < 25) {
									timeBar.rCount -= 5;
									if (timeBar.rCount <= 0) timeBar.rCount = 0;
								}
								//  ���Ѹ�忡�� �� ��ȣ�ۿ����� �̼Ǽ��� 0�� �Ǹ� �ٽ� �̼Ƕ��� �缳��
								if( characteristicPanel.mc.mission == 0 ) {
									es.reproduceLabel(es.level);
								}
								
							}
							//���� ��尡 �ƴ� ���¿��� �̼��� �� ���߸� escâ�� �ٽ� ������
							else if(characteristicPanel.mc.mission == 0) es.setVisible(true);
						}
					}
				}
			}
			
			// ����Ű �Է�
			if(!es.isVisible()){
				switch(keyCode) {
					case KeyEvent.VK_UP:
						upP = true;
						break;
					case KeyEvent.VK_DOWN:
						downP = true;
						break;
					case KeyEvent.VK_LEFT:
						leftP = true;
						break;
					case KeyEvent.VK_RIGHT:
						rightP = true;
						break;
					default:
						break;
				}
				// �� �� Ű�� ���� ������ if�� �ΰ��� �Ǽ� �밢�� �������� ��Ÿ��
				if(upP) {
					y = y - move;
					if(y - move < 0 ) y = 0;
				}
				if(downP) {
					y = y + move;
					if(y + move > yMax ) y = yMax;	
				}
				if(leftP) {
					x = x - move;
					if(x - move < 0 ) x = 0;
				}
				if(rightP) {
					x = x + move;
					if(x + move > xMax) x = xMax;
				}
				
				// ���� �����̰��� �ϴ� ������ �����ִ� ������� Ȯ�� �����ִ� ��Ҹ� �����̱� �� ��ġ�� ����
				if( !dc.blockSpace(mapNum, x, y) ) { 
					if(upP) y = y + move;
					
					if(downP) y = y - move;
					
					if(leftP) x = x + move;
					
					if(rightP) x = x - move;
				}
				else dc.teleport_map(mapNum, x, y);
			}
			// �ٽ� �׸�
			getParent().repaint();
			
		}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				upP = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
			    downP = false; 
			} 
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
			    leftP = false; 
			} 
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
			    rightP = false; 
			} 
			getParent().repaint();
		}
		public void keyProcess() {
			
		}
	}  
	
	
}
