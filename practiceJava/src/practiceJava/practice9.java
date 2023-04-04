package practiceJava;
import java.awt.*;
import javax.swing.*;
import java.math.*;

public class practice9 extends JFrame {
	
	public practice9(int choice){
		
		setTitle("practice_9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300, 300);
		
		Container container = getContentPane();
		
		
		switch (choice) {
		case 1: {
			container.setLayout(new GridLayout(1,10));
			int i = 0;
			for(i = 0;  i < 10; i++) {
				JButton button = new JButton(Integer.toString(i+1));
				container.add(button);
			}
			break;
		}
		case 2:{
			container.setLayout(null);
			int i = 0;
			for(i = 0; i  < 20; i++) {
				JLabel label = new JLabel(Integer.toString(i+1));
				int x = (int)(Math.random() * 200) + 50;
				int y = (int)(Math.random() * 200) + 50;
				label.setBounds(x, y, 10, 10);
				label.setBackground(Color.blue);
				label.setOpaque(true);
				container.add(label);
			}
			break;
		}
		case 3:{
			String string[] = { "Hello", "Java","Love"} ;
		
			container.setLayout(new BorderLayout());
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			JButton b1 = new JButton("Open");
			JButton b2 = new JButton("Read");
			JButton b3 = new JButton("Close");
			panel.add(b1); panel.add(b2); panel.add(b3);
			
			container.add(panel, BorderLayout.NORTH);
			
			JPanel ranJPanel = new JPanel();
			ranJPanel.setLayout(null);
			
			int i = 0;
			for(i = 0; i  < 3; i++) {
				JLabel label = new JLabel(string[i]);
				int x = (int)(Math.random() * 100) + 50;
				int y = (int)(Math.random() * 100) + 50;
				label.setBounds(x, y, 50, 50);
				ranJPanel.add(label);
			}
			container.add(ranJPanel, BorderLayout.CENTER);
			
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
	}
	
	
	
	public static void main(String args[]) {
		new practice9(3);
	}
}
