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

// 게임 플레이 화면
public class GamePanel extends JPanel {

	final private int move = 10;	// 움직이는 거리
	final static int startX = 450;	// 시작 x좌표
	final static int startY = 450;	// 시작 y좌표
	final static int startMap = 0;	// 시작 맵 번호
	
	static JFrame jFrame = Game.game.frame; // 패널을 입힐 프레임
	JLabel user = new JLabel("■");	// 유저
	
	protected static int mod = 0;		// 게임 모드 확인
	protected static int keyMap = -1;	// 어떤 상호작용 건물인지 확인하는 변수 (한 맵에 2개 이상의 건물이 있을 때 어떤 건물이지 확인하기 위해)
	protected static int mapNum = 0;	// 맵 번호
	protected static int x = 450;		// x 좌표
	protected static int y = 450;		// y 좌표
	// xMax : 움직일수 있는 최대 X의 크기| yMaX : 움직일수 있는 최대 Y의 크기
	protected static int xMax = jFrame.getWidth() - mainView.widthSizeGap - 20;
	protected static int yMax = jFrame.getHeight() - mainView.heightSizeGap - 20;
	
	// 대각선 움직임을 하기위한 불린 변수 true : 키가 눌림 ,false : 키가 안눌림 
	boolean upP = false; 
	boolean downP = false; 
	boolean leftP = false; 
	boolean rightP = false;
	// timebar 색상 설정
	private static Color c = Color.GREEN;

	static dataClass dc = new dataClass();
	static escape es;
	
	// 게임 플레이 화면 | escape e : mainView에서 게임의 모드를 주면 그에 따른 창을 변수로 가져와 이창을 붙이면 선택했던 창의 맞는 escape 창을 불러올 수 있음
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
	// map 변경 및 유저의 움직이는 걸 표현한 함수
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon("imageMap/map"+ mapNum +".png");
		Image backImage = icon.getImage();
		
		g.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setColor(c);
		g.fillRect(x, y, 20, 20);
		es.repaint();
	}
	
	// esc 및 미션을 보여주는 창
	static class escape extends JPanel{
		
		// posArray : 인 게임내 좌표가 있는 클래스
		posArray ps = new posArray();
		// 상호작용 할 수 있는 건물이 총 26개가 있어 정수령 배열로 선언 
		static int[] missionMap = new int[27];
		// 미션 창을 알려주는 라벨배열
		static JLabel[] missionJLabel = new JLabel[4];
		// 어떤 모드를 선택했는지 알려주는 변수
		protected static int level = 0;
		
		int i = 0;
		
		public escape(int level) {
			
			JLabel missionTitle = new JLabel("방문 해야하는 목록",SwingConstants.CENTER);
			JLabel explainESC = new JLabel("ESC : 메뉴 켜기/끄기",SwingConstants.CENTER);
			setSize(300, 200);
			setLayout(null);
			//mainView에서 선택한 모드를 입력받음
			this.level = level;
			
			missionTitle.setBounds(50, 10, 200, 25);
			missionTitle.setBackground(Color.BLACK);
			missionTitle.setForeground(Color.WHITE);
			missionTitle.setOpaque(true);
			
			// 게임 플레이 상호작용 클래스에 어떤 모드로 실행했는 지 알려줌
			characteristicPanel.mc.mission = level;
			
			
			// 위 라벨배열을 초기화
			for(int j = 0; j < 4; j++) {
				missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
			}
			// 라벨 배열을 모드에 따라 재설정
			reproduceLabel(this.level);

			
			explainESC.setBounds(50, 35 + (20 * this.level), 200, 25);
			explainESC.setBackground(Color.BLACK);
			explainESC.setForeground(Color.WHITE);
			explainESC.setOpaque(true);

			// 계속 하기 버튼
			JButton continueButton = new JButton("C");
			continueButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					es.setVisible(false);
					getParent().getParent().repaint();
					
				}
			});
			// 재시작 버튼
			JButton retryButton = new JButton("R");
			retryButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//시간을 줄이는 쓰레드 강제종료
					characteristicPanel.tb.th.interrupt();
					// 모든 값 초기값으로 초기화
					characteristicPanel.barsize = 60;
					timeBar.rCount = 0;
					timeBar.gCount = 0;
					c = new Color(0,250,0);
					timeBar.c =  c;
					timeBar.timeCount.setText("60");
					// 초기화한 값으로 다시 그리기
					getParent().getParent().repaint();
					
					// 어떤 모드에서 이 버튼이 눌렷는지 확인
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
					// 선택한 모드에 대해 라벨 배열 재설정
					reproduceLabel(es.level);
					// 게임이 재시작이니 같은 모드의 새로운 게임을 해야하므로 같은 모드로 새로운 게임 플레이 패널 소환
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
			
			// 메인 화면으로 가는 버튼
			JButton mainButton = new JButton("M");
			mainButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//시간을 줄이는 쓰레드 강제종료
					characteristicPanel.tb.th.interrupt();
					
					// 미션라벨에 들어있던 내용을 다 지움
					for(int j = 0; j < 4; j++) {
						missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
					}
					// 게임이 시작되기 전 초기값으로 모두 초기화
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
		// 상호작용을 해야하는 건물을 알려주는 라벨배열의 재설정 함수
		void reproduceLabel(int level) {
			// 상호작용하는 건물이 0~26까지 있으니 여기에 해당하지 않는 -1값으로 모두 초기화
			Arrays.fill(missionMap, -1);

			// 모드에 따라 설정
			for(i = 0; i < level ; i++) {
				// 0~26까지의 숫자중 랜덤으로 하나 선택
				int randomMap = (int)(Math.random() * ps.numToPlace.size());
				while( missionMap[randomMap] != -1 ) { // missionMap = -1 : 해당하는 상호작용 건물이 선택되지 않았음 | missionMap != -1 : 이미 해당 건물은 방문목록에 들어감
 					 randomMap = (int)(Math.random() * ps.numToPlace.size());	// 이미 해당 건물이 방문 목록에 있으므로 재탐색 
				}
				// 해당 건물이 뽑힌 순서를 missionMap에 저장
				missionMap[randomMap] = i;
				// randomMap에 해당하는 값을 라벨에 적음
				missionJLabel[i].setText(ps.numToPlace.get(randomMap)); 
				// 이 다음에 연속적으로 보여줘야 하므로 y촤표가 i(반복한 홧수)에 따라 달라짐 
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
			
			// esc를 눌렀을 때
			if(keyCode == KeyEvent.VK_ESCAPE) {
					// es창이 열려있는지 닫혀있는지에 따른 값
					if(es.isVisible()) {
						es.setVisible(false);
					}
					else {
						es.setVisible(true);
					}
					
					// mod = 1 : 무한(Infinity)모드 
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
					// 무한 모드가 아니고 && 시간이 모두 경과하거나 , 모든 미션을 다 맞췃을 때 esc키 눌르면 메인메뉴로 가지는 기능
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
			// 상호작요 키 E를 눌렀을 때
			else if(keyCode == KeyEvent.VK_E) {
				// 현재 있는 맵에서 내 위치에 있는 좌표에 상호작용이 있는 건물을 확인
				if (dc.missionSpace(mapNum, x, y)) {
					// 상호작용이 되는 건물이 내 미션목록에 있는지 확인
					for(int i = 0;  i < es.level; i++) {
						// 미션 목록에 있다면 배경이 WHTIE 색상이여서 글자색 또한 WHITE로 바꿔서 글씨가 지워진것 처럼 보여주고 해당 건물을 방문했다는걸 알려주고 미션수를 줄임
						if( es.missionJLabel[i].getText().equals(posArray.numToPlace.get(keyMap) ) ) {
							es.missionJLabel[i].setForeground(Color.WHITE);
							es.missionMap[keyMap] = -1 ;
							mainView.cp.mc.mission--;
							if(mod != 1 ) mainView.cp.mc.labelChange(); // 이게 무한모드가 아니면 미션수를 줄인것을 보여주기 위해 라벨을 바꿈
							// 무한 모드면
							if(mod == 1) {
								// 맞힌 갯수 추가
								mainView.cp.mc.successMission++;
								// 시간을 5초 늘려줌 60초가 넘어가면 60초로 설정
								characteristicPanel.barsize += 5;
								if(characteristicPanel.barsize >= 60) characteristicPanel.barsize = 60;
								// 시간이 늘어남에 따라 timeBar 색상 재설정
								if(timeBar.gCount > 0) {
									timeBar.gCount -= 5;
									if(timeBar.gCount <= 0) {timeBar.rCount += timeBar.gCount; timeBar.gCount = 0;}
								}
								else if (timeBar.gCount == 0 &&timeBar.rCount < 25) {
									timeBar.rCount -= 5;
									if (timeBar.rCount <= 0) timeBar.rCount = 0;
								}
								//  무한모드에서 이 상호작용으로 미션수가 0이 되면 다시 미션라벨을 재설정
								if( characteristicPanel.mc.mission == 0 ) {
									es.reproduceLabel(es.level);
								}
								
							}
							//무한 모드가 아닌 상태에서 미션을 다 맞추면 esc창을 다시 보여줌
							else if(characteristicPanel.mc.mission == 0) es.setVisible(true);
						}
					}
				}
			}
			
			// 방향키 입력
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
				// 상 우 키가 같이 눌리면 if도 두개가 되서 대각선 움직임을 나타냄
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
				
				// 내가 울직이고자 하는 방향이 막혀있는 장소인지 확인 막혀있는 장소면 움직이기 전 위치로 변경
				if( !dc.blockSpace(mapNum, x, y) ) { 
					if(upP) y = y + move;
					
					if(downP) y = y - move;
					
					if(leftP) x = x + move;
					
					if(rightP) x = x - move;
				}
				else dc.teleport_map(mapNum, x, y);
			}
			// 다시 그림
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
