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
		
		JLabel jMain = new JLabel("아래의 버튼 중에서 하나를 클릭하시오!");
		JLabel jResult = new JLabel("게임 시작 전");
		JButton bSissor = new JButton("0: 가위");
		JButton bRock = new JButton("1: 바위");
		JButton bPaper = new JButton("2: 보");
		
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
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "컴퓨터 승리");
				}
				else if(hu == com ) {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "무승부");
				}
				else {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "인간 승리");
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
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "컴퓨터 승리");
				}
				else if(hu == com ) {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "무승부");
				}
				else {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "인간 승리");
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
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "컴퓨터 승리");
				}
				else if(hu == com ) {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "무승부");
				}
				else {
					jResult.setText("인간:" + hu + " 컴퓨터:"+ com + "인간 승리");
				}
				
			}
		});
		
		
		setVisible(true);
	}
	
	
	public static void main(String args[]) {
		new rspGame();
	}
}
