package weekend3;
import javax.swing.*;
import javax.swing.border.Border;
import java.math.*;

import java.awt.*;
import java.awt.event.*;


public class moveImage extends JFrame{
	ImageIcon i=new ImageIcon("src/weekend3/다운로드.png");
	JLabel label = new JLabel();
	int x = 0;
	int y = 0;
	public moveImage() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label.setBounds(x, y, 50, 50);
		label.setIcon(i);
		add(label);
		
		move m = new move();
		
		addKeyListener(m);
		
		setVisible(true);
	}
	class move implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int ch = e.getKeyCode();
			
			switch(ch) {
				case KeyEvent.VK_UP :{
					y--;
					if(y <= 0) y = 0;
					break;
				}
				case KeyEvent.VK_DOWN :{
					y++;
					if(y >= 450) y=450;
					break;
				}
				case KeyEvent.VK_LEFT :{
					x--;
					if(x <=  0) x=0;
					break;
				}
				case KeyEvent.VK_RIGHT :{
					x++;
					if(x >= 450 ) x = 450;
				}
				
			}
			label.setLocation(x, y);
			Component component= (Component)e.getSource();
			component.repaint();
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}
	public static void main(String args[]) {
		new moveImage();
	}
}
