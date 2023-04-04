package practiceJava;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class practice12 extends JFrame {
	
	JCheckBox jCheckBox = new JCheckBox("비활성화");
	JButton button = new JButton("활성화");
	
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
				button.setText("비활성화");
				button.setEnabled(false);
			}
			else {
				button.setText("활성화");
				button.setEnabled(true);
			}
			
		}

	}
	
	
	public static void main(String args[]) {
		new practice12();
	}
}
