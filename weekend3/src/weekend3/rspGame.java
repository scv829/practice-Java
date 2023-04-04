package weekend3;
import javax.swing.*;
import javax.swing.border.Border;
import java.math.*;

import java.awt.*;
import java.awt.event.*;


public class rspGame extends JFrame{
	
	public rspGame() {
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		
		JLabel jMain = new JLabel("�Ʒ��� ��ư �߿��� �ϳ��� Ŭ���Ͻÿ�!");
		JLabel jResult = new JLabel("���� ���� ��");
		JButton bSissor = new JButton("0: ����");
		JButton bRock = new JButton("1: ����");
		JButton bPaper = new JButton("2: ��");
		
		add(jMain,BorderLayout.NORTH);
		add(bSissor,BorderLayout.WEST);
		add(bRock,BorderLayout.CENTER); 
		add(bPaper,BorderLayout.EAST);
		add(jResult,BorderLayout.SOUTH);
		
		bSissor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				int hu = Character.getNumericValue(b.getText().charAt(0));	
				
				int com = (int)Math.random()*3 ;
				
				if(hu == 0 && com == 1 || hu == 1 && com == 2 || hu == 2 && com == 0 ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "��ǻ�� �¸�");
				}
				else if(hu == com ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "���º�");
				}
				else {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "�ΰ� �¸�");
				}
			}
		});
		bRock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				int hu = Character.getNumericValue(b.getText().charAt(0));	
				
				int com = (int)(Math.random()*3) ;
				
				if(hu == 0 && com == 1 || hu == 1 && com == 2 || hu == 2 && com ==0 ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "��ǻ�� �¸�");
				}
				else if(hu == com ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "���º�");
				}
				else {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "�ΰ� �¸�");
				}
				
			}
		});
		bPaper.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				int hu = Character.getNumericValue(b.getText().charAt(0));	
				
				int com = (int)(Math.random()*3) ;
				
				if(hu == 0 && com == 1 || hu == 1 && com == 2 || hu == 2 && com ==0 ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "��ǻ�� �¸�");
				}
				else if(hu == com ) {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "���º�");
				}
				else {
					jResult.setText("�ΰ�:" + hu + " ��ǻ��:"+ com + "�ΰ� �¸�");
				}
				
			}
		});
		
		
		setVisible(true);
	}
	
	
	public static void main(String args[]) {
		new rspGame();
	}
}
