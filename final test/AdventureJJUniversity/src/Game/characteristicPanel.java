package Game;

import java.awt.*;
import java.awt.Taskbar.State;

import javax.swing.*;

import Game.GamePanel.escape;

import java.awt.event.*;

// 게임 플레이 상호작용 클래스
public class characteristicPanel extends JPanel{
	
	// 현재 선택한 모드를 알려주는 클래스
	private modeNamePanel mp;
	// 남은 시간을 알려주는 클래스
	protected static timeBar tb;
	// 남은 미션의 갯수를 알려주는 클래스 
	protected static missionCount mc;
	// 시간의 길이 : 60초
	protected static int barsize = 60;
	
	public characteristicPanel(String s) {
		setSize(100, 500);
		setLayout(null);
		
		
		//어떤 모드를 선택하냐에 따라 각 클래스에 해당하는 모드의 값으로 생성
		if(s == "Infinity") {	// Infinity 무한모드
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(1);
			
		}
		else if( s == "E") { 	// record -> Easy 쉬운단계 
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(2);
			
		}
		else if( s == "N") {	// record -> Normal 보통단계
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(3);
			
		}
		else if( s == "H") {	// record -> Hard 어려운단계
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(4);
			
		}
		else {					// practice 연습모드
			mp = new modeNamePanel(s);
			tb = new timeBar(0);
			mc = new missionCount(0);
		}
		
		
		mp.setLocation(0,0);
		tb.setLocation(0, 100);
		mc.setLocation(0, 400);
		
		add(mp);
		add(tb);
		add(mc);
		
		
		setVisible(true);
	}

	// 현재 선택한 모드를 알려주는 클래스
	class modeNamePanel extends JPanel {
		public modeNamePanel(String s) {
			setSize(100,100);
			setLayout(new GridLayout(2,1) );
			JLabel modeName = new JLabel("게임 모드",SwingConstants.CENTER);
			modeName.setFont(new Font("고딕체", Font.BOLD, 20));
			modeName.setForeground(Color.WHITE);
			
			JLabel mode = new JLabel(s,SwingConstants.CENTER);
			mode.setFont(new Font("고딕체", Font.BOLD, 20));
			mode.setForeground(Color.WHITE);
			
			add(modeName);
			add(mode);
			
			setBackground(Color.BLUE);
			
			setOpaque(true);
			
			setVisible(true);
		}
	}
	
	// 남은 시간을 알려주는 클래스
	static class timeBar extends JPanel {
		
		JLabel timeTitle = new JLabel("남은 시간",SwingConstants.CENTER);
		static JLabel timeCount = new JLabel("60",SwingConstants.CENTER);
		
		// timeBar에서 시각적으로 보여주는 라벨
		TimeBarLabel bar = new TimeBarLabel();
		// 라벨이 줄어드는 쓰레드
		ConsumerThread th = new ConsumerThread(bar);
		
		// timBar 색상 변환 rgb에서 r,g 부분
		static protected int rCount = 0;
		static protected int gCount = 0;
		// 초기 색상 : 초록으로 맞춰져 있음
		static protected Color c = new Color(0,250,0);
		
		protected static int state;
		
		//i == 0 : 연습모드 | i != 0 연습모드를 제외한 다름 게임 모드
		public timeBar(int i) {
			this.state = i;
			
			setSize(100, 300);
			setLayout(new BorderLayout());
			
			timeTitle.setSize(100,100);
			bar.setSize(20, 300);
			timeCount.setSize(100, 100);
			
			ConsumerThread th = new ConsumerThread(bar);
			this.th = th;
			
			if(i == 0) {
				timeTitle.setFont(new Font("고딕체", Font.BOLD, 20));
				timeCount.setFont(new Font("고딕체", Font.BOLD, 20));
				timeCount.setText("연습 모드");
			}
			else {
				
				th.start();
				
				timeTitle.setFont(new Font("고딕체", Font.BOLD, 20));
				timeCount.setFont(new Font("고딕체", Font.BOLD, 20));
			
				bar.setBackground(Color.BLACK);
			}
			
			bar.setOpaque(true);
			add(timeTitle, BorderLayout.NORTH);
			add(timeCount, BorderLayout.SOUTH);
			add(bar, BorderLayout.CENTER);
		
			setBackground(Color.GREEN);
			setOpaque(true);
			
			setVisible(true);
		}
		// 시각적으로 보여주는 라벨 부분
		class TimeBarLabel extends JLabel{
			private int size;
			private int maxBarSize = 60;

			public TimeBarLabel () {
				this.size = barsize;
			}
			
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(state == 0) g.setColor(Color.GRAY);
				else {
					g.setColor(c);
				}
				this.size = barsize;
				int height = (int)(((double)(this.getHeight()))/maxBarSize*size);
				if(height == 0) return;
				g.fillRect(0, 0, this.getWidth(), height);
			}
			
			synchronized public void consume() {
					if(barsize == 0) {
						try {
							wait();
						} catch (InterruptedException e) { return;  }
					}
					
					if( GamePanel.es.isVisible() ) {
						timeCount.setText(Integer.toString(barsize));
						repaint();
					}
					else {
						// 시간이 흐를 수록 크기가 줄어들면서 라벨 색상이 초록색 -> 노란색 -> 빨강색 순으로 색상 변화
						this.size--;
						barsize--;
						timeCount.setText(Integer.toString(barsize));
						
						if(rCount < 25) rCount++;
						else if (rCount == 25 && gCount < 25) gCount++;
						
						c = new Color(10 * rCount , 250 - (10 * gCount) , 0);
						
						repaint();
						notify();
					}
					
			}
			
		}
		// 라벨이 줄어드는 쓰레드
		class ConsumerThread extends Thread{
			private TimeBarLabel bar;
			protected int bs;
			public ConsumerThread(TimeBarLabel bar) {
				this.bs = barsize;
				this.bar = bar;
			}
			@Override
			public void run() {
				while( barsize != 0 ) {
					try {
						sleep(1000);
						bar.consume();
						if(barsize == 0) GamePanel.es.setVisible(true);
					} catch (InterruptedException e) { return; }
				}
			}
		}
	}
	
	// 남은 미션의 갯수를 알려주는 클래스 
	class missionCount extends JPanel {
		
		private static String s;
		static protected int mission = 0;
		static JLabel mode = new JLabel();
		static int successMission = 0;
		
		public missionCount(int i) {
			setSize(100, 100);
			setLayout(new GridLayout(2,1) );
			
			JLabel modeName = new JLabel("남은 미션",SwingConstants.CENTER);
			
			switch (i) {
			case 0: { this.s = "연습"; 	break;  }
			case 1: { modeName.setText("맞춘 미션"); 	break;  }
			case 2: { this.s = "/2"; 	break;  }
			case 3: { this.s = "/3"; 	break;  }
			case 4: { this.s = "/4"; 	break;  }
			default:
				throw new IllegalArgumentException("Unexpected value: " + i);
			}
			
			
			if( i == 0) {
				mode.setText(s);
				mode.setHorizontalAlignment(SwingConstants.CENTER);
			}
			else if(i == 1) {
				mode.setText(Integer.toString(successMission));
				mode.setHorizontalAlignment(SwingConstants.CENTER);
			}
			else {
				labelChange();
			}
			modeName.setFont(new Font("고딕체", Font.BOLD, 20));
			mode.setFont(new Font("고딕체", Font.BOLD, 20));
			
			add(modeName);
			add(mode);
			
			setBackground(Color.RED);
			setOpaque(true);
			
			setVisible(true);
		}
		static void labelChange() {
			mode.setText(Integer.toString(mission) + s);
			mode.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	
}
