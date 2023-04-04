package Game;

import java.awt.*;
import java.awt.Taskbar.State;

import javax.swing.*;

import Game.GamePanel.escape;

import java.awt.event.*;

// ���� �÷��� ��ȣ�ۿ� Ŭ����
public class characteristicPanel extends JPanel{
	
	// ���� ������ ��带 �˷��ִ� Ŭ����
	private modeNamePanel mp;
	// ���� �ð��� �˷��ִ� Ŭ����
	protected static timeBar tb;
	// ���� �̼��� ������ �˷��ִ� Ŭ���� 
	protected static missionCount mc;
	// �ð��� ���� : 60��
	protected static int barsize = 60;
	
	public characteristicPanel(String s) {
		setSize(100, 500);
		setLayout(null);
		
		
		//� ��带 �����ϳĿ� ���� �� Ŭ������ �ش��ϴ� ����� ������ ����
		if(s == "Infinity") {	// Infinity ���Ѹ��
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(1);
			
		}
		else if( s == "E") { 	// record -> Easy ����ܰ� 
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(2);
			
		}
		else if( s == "N") {	// record -> Normal ����ܰ�
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(3);
			
		}
		else if( s == "H") {	// record -> Hard �����ܰ�
			mp = new modeNamePanel(s);
			tb = new timeBar(1);
			mc = new missionCount(4);
			
		}
		else {					// practice �������
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

	// ���� ������ ��带 �˷��ִ� Ŭ����
	class modeNamePanel extends JPanel {
		public modeNamePanel(String s) {
			setSize(100,100);
			setLayout(new GridLayout(2,1) );
			JLabel modeName = new JLabel("���� ���",SwingConstants.CENTER);
			modeName.setFont(new Font("���ü", Font.BOLD, 20));
			modeName.setForeground(Color.WHITE);
			
			JLabel mode = new JLabel(s,SwingConstants.CENTER);
			mode.setFont(new Font("���ü", Font.BOLD, 20));
			mode.setForeground(Color.WHITE);
			
			add(modeName);
			add(mode);
			
			setBackground(Color.BLUE);
			
			setOpaque(true);
			
			setVisible(true);
		}
	}
	
	// ���� �ð��� �˷��ִ� Ŭ����
	static class timeBar extends JPanel {
		
		JLabel timeTitle = new JLabel("���� �ð�",SwingConstants.CENTER);
		static JLabel timeCount = new JLabel("60",SwingConstants.CENTER);
		
		// timeBar���� �ð������� �����ִ� ��
		TimeBarLabel bar = new TimeBarLabel();
		// ���� �پ��� ������
		ConsumerThread th = new ConsumerThread(bar);
		
		// timBar ���� ��ȯ rgb���� r,g �κ�
		static protected int rCount = 0;
		static protected int gCount = 0;
		// �ʱ� ���� : �ʷ����� ������ ����
		static protected Color c = new Color(0,250,0);
		
		protected static int state;
		
		//i == 0 : ������� | i != 0 ������带 ������ �ٸ� ���� ���
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
				timeTitle.setFont(new Font("���ü", Font.BOLD, 20));
				timeCount.setFont(new Font("���ü", Font.BOLD, 20));
				timeCount.setText("���� ���");
			}
			else {
				
				th.start();
				
				timeTitle.setFont(new Font("���ü", Font.BOLD, 20));
				timeCount.setFont(new Font("���ü", Font.BOLD, 20));
			
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
		// �ð������� �����ִ� �� �κ�
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
						// �ð��� �带 ���� ũ�Ⱑ �پ��鼭 �� ������ �ʷϻ� -> ����� -> ������ ������ ���� ��ȭ
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
		// ���� �پ��� ������
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
	
	// ���� �̼��� ������ �˷��ִ� Ŭ���� 
	class missionCount extends JPanel {
		
		private static String s;
		static protected int mission = 0;
		static JLabel mode = new JLabel();
		static int successMission = 0;
		
		public missionCount(int i) {
			setSize(100, 100);
			setLayout(new GridLayout(2,1) );
			
			JLabel modeName = new JLabel("���� �̼�",SwingConstants.CENTER);
			
			switch (i) {
			case 0: { this.s = "����"; 	break;  }
			case 1: { modeName.setText("���� �̼�"); 	break;  }
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
			modeName.setFont(new Font("���ü", Font.BOLD, 20));
			mode.setFont(new Font("���ü", Font.BOLD, 20));
			
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
