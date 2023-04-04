package practiceJava;
import java.math.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import javax.net.ssl.ExtendedSSLSession;
import javax.swing.*;



public class practice10 extends JFrame{
	
	private JLabel la = new JLabel("Love Java");
	
	private JLabel num1 = new JLabel();
	private JLabel num2 = new JLabel();
	private JLabel num3 = new JLabel();

	private JLabel result = new JLabel();
	
	public practice10(int choice) {
		
		setTitle("practice_10");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		
		Container container = getContentPane();
		
		switch (choice) {
		case 1: {
			container.setBackground(Color.green);
			fristMouseMotion motion = new fristMouseMotion();
			container.addMouseMotionListener(motion);
			container.addMouseListener(motion);
			
			break;
		}
		case 2: {
			container.setFocusable(true);
			container.requestFocus();
			
			container.setLayout(new BorderLayout());
			container.add(la, BorderLayout.CENTER);
			container.addKeyListener(new secondKey());
			
			break;
		}
		case 3: {
			container.setFocusable(true);
			container.requestFocus();
			
			container.setLayout(new GridLayout(1,2));
			
			JPanel jPanel = new JPanel();
			jPanel.setLayout(new  FlowLayout());
			
			jPanel.add(num1); 
			jPanel.add(num2);
			jPanel.add(num3);
			
			add(jPanel);
			add(result);
			
			container.addKeyListener(new thridKey() );
			
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		
	}
	
	class fristMouseMotion extends MouseAdapter implements MouseMotionListener{ 
		public void mouseDragged(MouseEvent e) {
			Container component = (Container)e.getSource();
			component.setBackground(Color.yellow);
		}
		public void mouseMoved(MouseEvent e) {
		
		}
		public void mouseReleased(MouseEvent e) {
			Container component = (Container)e.getSource();
			component.setBackground(Color.green);
		}
	}
	
	class secondKey extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				StringBuffer s = new StringBuffer();
				s.append(la.getText());
				s.reverse();
				la.setText(s.toString());
			}
		}
	}
	
	
	class thridKey extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				int x = (int)(Math.random() * 4);
				num1.setText(Integer.toString(x));
				int x2 = (int)(Math.random() * 4);
				num2.setText(Integer.toString(x2));
				int x3 = (int)(Math.random() * 4);
				num3.setText(Integer.toString(x3));
				
				if(x == x2 && x == x3) {
					result.setText("축하합니다!!");
				}
				else result.setText("아쉽군요");
				
			}
		}
	}
	
	public static void main(String args[]) {
		new practice10(3);
	}
}
