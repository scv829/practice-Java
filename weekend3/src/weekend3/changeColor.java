package weekend3;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;


public class changeColor extends JFrame{
	public changeColor(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		setBackground(Color.WHITE);
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.setOpaque(true);
		
		Container c = getContentPane();
		
		add(p, BorderLayout.NORTH);
		
		
		JButton bYellow = new JButton("노란색");
		JButton bPink = new JButton("핑크색");
		
		p.add(bYellow);
		p.add(bPink);
		
		
		bYellow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setBackground(Color.YELLOW);
				p.setBackground(Color.YELLOW);
			}
		});
		
		bPink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setBackground(Color.PINK);
				p.setBackground(Color.PINK);
			}
		});
		
		setVisible(true);
	}
	public static void main(String args[]) {
		new changeColor();
	}
}
