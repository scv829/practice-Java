package practiceJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class practice14 extends JFrame {
	
	public practice14() {
		setTitle("practice14");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setVisible(true);
		setLayout(new BorderLayout());
		
		
		JToolBar bar = new JToolBar();
		JButton exit = new JButton("종료"); 
		exit.addActionListener(new exitListener());
		bar.add(exit);
		
		add(bar, BorderLayout.NORTH);
		
	}
	
	class exitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "종료 하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.exit(1);
			}
		}
		
	}
	
	
	
	public static void main(String args[]) {
		new practice14();
	}
}
