package practiceJava;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.math.*;

public class practice11 extends JFrame{
	
	JComboBox<String> comboBox = new JComboBox<String>();
	JTextField field = new JTextField(20);
	JLabel result = new JLabel();
	
	JLabel[] la = new JLabel[10];
	int checkCount = 0;
	
	
	public practice11(int choice) {
		setTitle("practice_11");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,200);
		setVisible(true);
		
		Container container = getContentPane();
		
		switch (choice){
		case 1: {
			container.setLayout(new FlowLayout());
			
			container.add(field);
			container.add(comboBox);
			
			field.addActionListener(new fristAction());
			
			break;
		}
		case 2: {
			
			container.setLayout(new FlowLayout());
			container.add(field);
			
			field.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					field.setText(field.getText().toUpperCase());
				}
			});
			
			break;
		}
		case 3: {
			container.setLayout(new FlowLayout());
			JSlider slider = new JSlider(JSlider.HORIZONTAL,0,200, 100);
			
			result.setText(Integer.toString(slider.getValue()));
			
			container.add(slider); container.add(result);
			
			slider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider s = (JSlider)e.getSource();
					
					result.setText(Integer.toString(s.getValue()));
					
				}
			});
			
		break;
		}
		case 4: {
			
			container.setLayout(null);
			
			int i = 0;
			
			ForthMouseAdapter f = new ForthMouseAdapter();
			
			
			for(i = 0; i < 10; i++) {
				la[i] = new JLabel(Integer.toString(i));
				la[i].setSize(15,15);
				la[i].addMouseListener(f);
				
				int Xmax = container.getWidth() - la[i].getWidth();
				int Ymax = container.getHeight() - la[i].getHeight();
				int x = (int)(Math.random() * Xmax);
				int y = (int)(Math.random() * Ymax);
				
				la[i].setLocation(x, y);
				la[i].setVisible(true);
				

				container.add(la[i]);
				
			}
				
			
			
			
		
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		
		
		
	}
	
	class fristAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTextField f = (JTextField)e.getSource();
			comboBox.addItem(f.getText());
			
		}
	}
	
	class ForthMouseAdapter extends MouseAdapter{
		
		
		public void mouseClicked(MouseEvent e) {
			JLabel checkLabel = (JLabel)e.getSource();
			Container container = getContentPane();
			
			if(Integer.toString(checkCount).equals(checkLabel.getText()) ) {
				la[checkCount++].setVisible(false);
			}
			if(checkCount == 10) {
				int i = 0;
				
				for(i = 0; i < 10; i++) {
					la[i].setSize(15,15);
					la[i].setText(Integer.toString(i));
					
					int Xmax = container.getWidth() - la[i].getWidth();
					int Ymax = container.getHeight() - la[i].getHeight();
					int x = (int)(Math.random() * Xmax);
					int y = (int)(Math.random() * Ymax);
					
					la[i].setLocation(x, y);
					la[i].setVisible(true);
					container.add(la[i]);
					
				}
				checkCount = 0;
			}
			
		}
		
		
	}
	
	
	
	
	public static void main(String args[]) {
		new practice11(4);
		
	}
}
