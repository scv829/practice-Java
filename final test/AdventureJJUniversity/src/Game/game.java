package Game;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

import java.awt.event.*;
//프레임 클래스
public class game {
	// 프레임
	protected static JFrame frame = new JFrame();
	
	public game(){
		frame.setTitle("heelo");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false);
		frame.setVisible(true);
	
		mainView m = new mainView();
				
		m.setFocusable(true);
		m.requestFocus();
				
		frame.setContentPane(m);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
				
		frame.setTitle("Adventure-JJ-University");

	}
	
	public static void main(String args[]) {
		game g = new game();
	}
}


