package practiceJava;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class practice12 extends JFrame {
	
	JCheckBox jCheckBox = new JCheckBox("��Ȱ��ȭ");
	JButton button = new JButton("Ȱ��ȭ");
	
	public practice12() {
		setTitle("practice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		
		jCheckBox.setSelected(false);
		jCheckBox.addItemListener(new myItemListener());
		
		c.add(jCheckBox);
		c.add(button);
		
		setSize(300,300);
		setVisible(true);
		
	}
	
	class myItemListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				button.setText("��Ȱ��ȭ");
				button.setEnabled(false);
			}
			else {
				button.setText("Ȱ��ȭ");
				button.setEnabled(true);
			}
			
		}

	}
	
	
	public static void main(String args[]) {
		new practice12();
	}
}
