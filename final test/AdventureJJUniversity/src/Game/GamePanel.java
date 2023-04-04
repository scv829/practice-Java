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

// °ÔÀÓ ÇÃ·¹ÀÌ È­¸é
public class GamePanel extends JPanel {

	final private int move = 10;	// ¿òÁ÷ÀÌ´Â °Å¸®
	final static int startX = 450;	// ½ÃÀÛ xÁÂÇ¥
	final static int startY = 450;	// ½ÃÀÛ yÁÂÇ¥
	final static int startMap = 0;	// ½ÃÀÛ ¸Ê ¹øÈ£
	
	static JFrame jFrame = Game.game.frame; // ÆĞ³ÎÀ» ÀÔÈú ÇÁ·¹ÀÓ
	JLabel user = new JLabel("¡á");	// À¯Àú
	
	protected static int mod = 0;		// °ÔÀÓ ¸ğµå È®ÀÎ
	protected static int keyMap = -1;	// ¾î¶² »óÈ£ÀÛ¿ë °Ç¹°ÀÎÁö È®ÀÎÇÏ´Â º¯¼ö (ÇÑ ¸Ê¿¡ 2°³ ÀÌ»óÀÇ °Ç¹°ÀÌ ÀÖÀ» ¶§ ¾î¶² °Ç¹°ÀÌÁö È®ÀÎÇÏ±â À§ÇØ)
	protected static int mapNum = 0;	// ¸Ê ¹øÈ£
	protected static int x = 450;		// x ÁÂÇ¥
	protected static int y = 450;		// y ÁÂÇ¥
	// xMax : ¿òÁ÷ÀÏ¼ö ÀÖ´Â ÃÖ´ë XÀÇ Å©±â| yMaX : ¿òÁ÷ÀÏ¼ö ÀÖ´Â ÃÖ´ë YÀÇ Å©±â
	protected static int xMax = jFrame.getWidth() - mainView.widthSizeGap - 20;
	protected static int yMax = jFrame.getHeight() - mainView.heightSizeGap - 20;
	
	// ´ë°¢¼± ¿òÁ÷ÀÓÀ» ÇÏ±âÀ§ÇÑ ºÒ¸° º¯¼ö true : Å°°¡ ´­¸² ,false : Å°°¡ ¾È´­¸² 
	boolean upP = false; 
	boolean downP = false; 
	boolean leftP = false; 
	boolean rightP = false;
	// timebar »ö»ó ¼³Á¤
	private static Color c = Color.GREEN;

	static dataClass dc = new dataClass();
	static escape es;
	
	// °ÔÀÓ ÇÃ·¹ÀÌ È­¸é | escape e : mainView¿¡¼­ °ÔÀÓÀÇ ¸ğµå¸¦ ÁÖ¸é ±×¿¡ µû¸¥ Ã¢À» º¯¼ö·Î °¡Á®¿Í ÀÌÃ¢À» ºÙÀÌ¸é ¼±ÅÃÇß´ø Ã¢ÀÇ ¸Â´Â escape Ã¢À» ºÒ·¯¿Ã ¼ö ÀÖÀ½
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
	// map º¯°æ ¹× À¯ÀúÀÇ ¿òÁ÷ÀÌ´Â °É Ç¥ÇöÇÑ ÇÔ¼ö
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon("imageMap/map"+ mapNum +".png");
		Image backImage = icon.getImage();
		
		g.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setColor(c);
		g.fillRect(x, y, 20, 20);
		es.repaint();
	}
	
	// esc ¹× ¹Ì¼ÇÀ» º¸¿©ÁÖ´Â Ã¢
	static class escape extends JPanel{
		
		// posArray : ÀÎ °ÔÀÓ³» ÁÂÇ¥°¡ ÀÖ´Â Å¬·¡½º
		posArray ps = new posArray();
		// »óÈ£ÀÛ¿ë ÇÒ ¼ö ÀÖ´Â °Ç¹°ÀÌ ÃÑ 26°³°¡ ÀÖ¾î Á¤¼ö·É ¹è¿­·Î ¼±¾ğ 
		static int[] missionMap = new int[27];
		// ¹Ì¼Ç Ã¢À» ¾Ë·ÁÁÖ´Â ¶óº§¹è¿­
		static JLabel[] missionJLabel = new JLabel[4];
		// ¾î¶² ¸ğµå¸¦ ¼±ÅÃÇß´ÂÁö ¾Ë·ÁÁÖ´Â º¯¼ö
		protected static int level = 0;
		
		int i = 0;
		
		public escape(int level) {
			
			JLabel missionTitle = new JLabel("¹æ¹® ÇØ¾ßÇÏ´Â ¸ñ·Ï",SwingConstants.CENTER);
			JLabel explainESC = new JLabel("ESC : ¸Ş´º ÄÑ±â/²ô±â",SwingConstants.CENTER);
			setSize(300, 200);
			setLayout(null);
			//mainView¿¡¼­ ¼±ÅÃÇÑ ¸ğµå¸¦ ÀÔ·Â¹ŞÀ½
			this.level = level;
			
			missionTitle.setBounds(50, 10, 200, 25);
			missionTitle.setBackground(Color.BLACK);
			missionTitle.setForeground(Color.WHITE);
			missionTitle.setOpaque(true);
			
			// °ÔÀÓ ÇÃ·¹ÀÌ »óÈ£ÀÛ¿ë Å¬·¡½º¿¡ ¾î¶² ¸ğµå·Î ½ÇÇàÇß´Â Áö ¾Ë·ÁÁÜ
			characteristicPanel.mc.mission = level;
			
			
			// À§ ¶óº§¹è¿­À» ÃÊ±âÈ­
			for(int j = 0; j < 4; j++) {
				missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
			}
			// ¶óº§ ¹è¿­À» ¸ğµå¿¡ µû¶ó Àç¼³Á¤
			reproduceLabel(this.level);

			
			explainESC.setBounds(50, 35 + (20 * this.level), 200, 25);
			explainESC.setBackground(Color.BLACK);
			explainESC.setForeground(Color.WHITE);
			explainESC.setOpaque(true);

			// °è¼Ó ÇÏ±â ¹öÆ°
			JButton continueButton = new JButton("C");
			continueButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					es.setVisible(false);
					getParent().getParent().repaint();
					
				}
			});
			// Àç½ÃÀÛ ¹öÆ°
			JButton retryButton = new JButton("R");
			retryButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//½Ã°£À» ÁÙÀÌ´Â ¾²·¹µå °­Á¦Á¾·á
					characteristicPanel.tb.th.interrupt();
					// ¸ğµç °ª ÃÊ±â°ªÀ¸·Î ÃÊ±âÈ­
					characteristicPanel.barsize = 60;
					timeBar.rCount = 0;
					timeBar.gCount = 0;
					c = new Color(0,250,0);
					timeBar.c =  c;
					timeBar.timeCount.setText("60");
					// ÃÊ±âÈ­ÇÑ °ªÀ¸·Î ´Ù½Ã ±×¸®±â
					getParent().getParent().repaint();
					
					// ¾î¶² ¸ğµå¿¡¼­ ÀÌ ¹öÆ°ÀÌ ´­·Ç´ÂÁö È®ÀÎ
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
					// ¼±ÅÃÇÑ ¸ğµå¿¡ ´ëÇØ ¶óº§ ¹è¿­ Àç¼³Á¤
					reproduceLabel(es.level);
					// °ÔÀÓÀÌ Àç½ÃÀÛÀÌ´Ï °°Àº ¸ğµåÀÇ »õ·Î¿î °ÔÀÓÀ» ÇØ¾ßÇÏ¹Ç·Î °°Àº ¸ğµå·Î »õ·Î¿î °ÔÀÓ ÇÃ·¹ÀÌ ÆĞ³Î ¼ÒÈ¯
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
			
			// ¸ŞÀÎ È­¸éÀ¸·Î °¡´Â ¹öÆ°
			JButton mainButton = new JButton("M");
			mainButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//½Ã°£À» ÁÙÀÌ´Â ¾²·¹µå °­Á¦Á¾·á
					characteristicPanel.tb.th.interrupt();
					
					// ¹Ì¼Ç¶óº§¿¡ µé¾îÀÖ´ø ³»¿ëÀ» ´Ù Áö¿ò
					for(int j = 0; j < 4; j++) {
						missionJLabel[j] = new JLabel("",SwingConstants.CENTER);
					}
					// °ÔÀÓÀÌ ½ÃÀÛµÇ±â Àü ÃÊ±â°ªÀ¸·Î ¸ğµÎ ÃÊ±âÈ­
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
		// »óÈ£ÀÛ¿ëÀ» ÇØ¾ßÇÏ´Â °Ç¹°À» ¾Ë·ÁÁÖ´Â ¶óº§¹è¿­ÀÇ Àç¼³Á¤ ÇÔ¼ö
		void reproduceLabel(int level) {
			// »óÈ£ÀÛ¿ëÇÏ´Â °Ç¹°ÀÌ 0~26±îÁö ÀÖÀ¸´Ï ¿©±â¿¡ ÇØ´çÇÏÁö ¾Ê´Â -1°ªÀ¸·Î ¸ğµÎ ÃÊ±âÈ­
			Arrays.fill(missionMap, -1);

			// ¸ğµå¿¡ µû¶ó ¼³Á¤
			for(i = 0; i < level ; i++) {
				// 0~26±îÁöÀÇ ¼ıÀÚÁß ·£´ıÀ¸·Î ÇÏ³ª ¼±ÅÃ
				int randomMap = (int)(Math.random() * ps.numToPlace.size());
				while( missionMap[randomMap] != -1 ) { // missionMap = -1 : ÇØ´çÇÏ´Â »óÈ£ÀÛ¿ë °Ç¹°ÀÌ ¼±ÅÃµÇÁö ¾Ê¾ÒÀ½ | missionMap != -1 : ÀÌ¹Ì ÇØ´ç °Ç¹°Àº ¹æ¹®¸ñ·Ï¿¡ µé¾î°¨
 					 randomMap = (int)(Math.random() * ps.numToPlace.size());	// ÀÌ¹Ì ÇØ´ç °Ç¹°ÀÌ ¹æ¹® ¸ñ·Ï¿¡ ÀÖÀ¸¹Ç·Î ÀçÅ½»ö 
				}
				// ÇØ´ç °Ç¹°ÀÌ »ÌÈù ¼ø¼­¸¦ missionMap¿¡ ÀúÀå
				missionMap[randomMap] = i;
				// randomMap¿¡ ÇØ´çÇÏ´Â °ªÀ» ¶óº§¿¡ ÀûÀ½
				missionJLabel[i].setText(ps.numToPlace.get(randomMap)); 
				// ÀÌ ´ÙÀ½¿¡ ¿¬¼ÓÀûÀ¸·Î º¸¿©Áà¾ß ÇÏ¹Ç·Î yÃÒÇ¥°¡ i(¹İº¹ÇÑ È±¼ö)¿¡ µû¶ó ´Ş¶óÁü 
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
			
			// esc¸¦ ´­·¶À» ¶§
			if(keyCode == KeyEvent.VK_ESCAPE) {
					// esÃ¢ÀÌ ¿­·ÁÀÖ´ÂÁö ´İÇôÀÖ´ÂÁö¿¡ µû¸¥ °ª
					if(es.isVisible()) {
						es.setVisible(false);
					}
					else {
						es.setVisible(true);
					}
					
					// mod = 1 : ¹«ÇÑ(Infinity)¸ğµå 
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
					// ¹«ÇÑ ¸ğµå°¡ ¾Æ´Ï°í && ½Ã°£ÀÌ ¸ğµÎ °æ°úÇÏ°Å³ª , ¸ğµç ¹Ì¼ÇÀ» ´Ù ¸Â­ŸÀ» ¶§ escÅ° ´­¸£¸é ¸ŞÀÎ¸Ş´º·Î °¡Áö´Â ±â´É
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
			// »óÈ£ÀÛ¿ä Å° E¸¦ ´­·¶À» ¶§
			else if(keyCode == KeyEvent.VK_E) {
				// ÇöÀç ÀÖ´Â ¸Ê¿¡¼­ ³» À§Ä¡¿¡ ÀÖ´Â ÁÂÇ¥¿¡ »óÈ£ÀÛ¿ëÀÌ ÀÖ´Â °Ç¹°À» È®ÀÎ
				if (dc.missionSpace(mapNum, x, y)) {
					// »óÈ£ÀÛ¿ëÀÌ µÇ´Â °Ç¹°ÀÌ ³» ¹Ì¼Ç¸ñ·Ï¿¡ ÀÖ´ÂÁö È®ÀÎ
					for(int i = 0;  i < es.level; i++) {
						// ¹Ì¼Ç ¸ñ·Ï¿¡ ÀÖ´Ù¸é ¹è°æÀÌ WHTIE »ö»óÀÌ¿©¼­ ±ÛÀÚ»ö ¶ÇÇÑ WHITE·Î ¹Ù²ã¼­ ±Û¾¾°¡ Áö¿öÁø°Í Ã³·³ º¸¿©ÁÖ°í ÇØ´ç °Ç¹°À» ¹æ¹®Çß´Ù´Â°É ¾Ë·ÁÁÖ°í ¹Ì¼Ç¼ö¸¦ ÁÙÀÓ
						if( es.missionJLabel[i].getText().equals(posArray.numToPlace.get(keyMap) ) ) {
							es.missionJLabel[i].setForeground(Color.WHITE);
							es.missionMap[keyMap] = -1 ;
							mainView.cp.mc.mission--;
							if(mod != 1 ) mainView.cp.mc.labelChange(); // ÀÌ°Ô ¹«ÇÑ¸ğµå°¡ ¾Æ´Ï¸é ¹Ì¼Ç¼ö¸¦ ÁÙÀÎ°ÍÀ» º¸¿©ÁÖ±â À§ÇØ ¶óº§À» ¹Ù²Ş
							// ¹«ÇÑ ¸ğµå¸é
							if(mod == 1) {
								// ¸ÂÈù °¹¼ö Ãß°¡
								mainView.cp.mc.successMission++;
								// ½Ã°£À» 5ÃÊ ´Ã·ÁÁÜ 60ÃÊ°¡ ³Ñ¾î°¡¸é 60ÃÊ·Î ¼³Á¤
								characteristicPanel.barsize += 5;
								if(characteristicPanel.barsize >= 60) characteristicPanel.barsize = 60;
								// ½Ã°£ÀÌ ´Ã¾î³²¿¡ µû¶ó timeBar »ö»ó Àç¼³Á¤
								if(timeBar.gCount > 0) {
									timeBar.gCount -= 5;
									if(timeBar.gCount <= 0) {timeBar.rCount += timeBar.gCount; timeBar.gCount = 0;}
								}
								else if (timeBar.gCount == 0 &&timeBar.rCount < 25) {
									timeBar.rCount -= 5;
									if (timeBar.rCount <= 0) timeBar.rCount = 0;
								}
								//  ¹«ÇÑ¸ğµå¿¡¼­ ÀÌ »óÈ£ÀÛ¿ëÀ¸·Î ¹Ì¼Ç¼ö°¡ 0ÀÌ µÇ¸é ´Ù½Ã ¹Ì¼Ç¶óº§À» Àç¼³Á¤
								if( characteristicPanel.mc.mission == 0 ) {
									es.reproduceLabel(es.level);
								}
								
							}
							//¹«ÇÑ ¸ğµå°¡ ¾Æ´Ñ »óÅÂ¿¡¼­ ¹Ì¼ÇÀ» ´Ù ¸ÂÃß¸é escÃ¢À» ´Ù½Ã º¸¿©ÁÜ
							else if(characteristicPanel.mc.mission == 0) es.setVisible(true);
						}
					}
				}
			}
			
			// ¹æÇâÅ° ÀÔ·Â
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
				// »ó ¿ì Å°°¡ °°ÀÌ ´­¸®¸é ifµµ µÎ°³°¡ µÇ¼­ ´ë°¢¼± ¿òÁ÷ÀÓÀ» ³ªÅ¸³¿
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
				
				// ³»°¡ ¿ïÁ÷ÀÌ°íÀÚ ÇÏ´Â ¹æÇâÀÌ ¸·ÇôÀÖ´Â Àå¼ÒÀÎÁö È®ÀÎ ¸·ÇôÀÖ´Â Àå¼Ò¸é ¿òÁ÷ÀÌ±â Àü À§Ä¡·Î º¯°æ
				if( !dc.blockSpace(mapNum, x, y) ) { 
					if(upP) y = y + move;
					
					if(downP) y = y - move;
					
					if(leftP) x = x + move;
					
					if(rightP) x = x - move;
				}
				else dc.teleport_map(mapNum, x, y);
			}
			// ´Ù½Ã ±×¸²
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
