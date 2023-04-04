package practiceJava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class changeText extends JFrame{
	public changeText() {
		setSize(350, 200);								// 프레임 크기
		setTitle("Action 이벤트 리스너 작성");				// 프레임 타이틀
		setLayout(new FlowLayout());					// 프레임 레이아웃
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// 프레임 종료시 프로그램 종료
		JButton button = new JButton("액션");				// 액션이라는 텍스트를 가진 버튼
		add(button);									// 프레임에 버튼 삽입
		
		changeTextButton ctb = new changeTextButton();	// changeTextButton 액션 리스너 객체 생성
		
		button.addActionListener(ctb);					// 버튼에 액션 리스너 삽입
		
		setVisible(true);								// 프레임 보임
	}
	
	class changeTextButton implements ActionListener{	// 인터페이스 ActionListener 구연하는 changeTextButton 클래스 
		@Override
		public void actionPerformed(ActionEvent e) {	// 액션이 발생하면 작동할 함수
			JButton button = (JButton)e.getSource();	// 액션이 일어난 객체를 JButton 형인 button 값에 넣음
			if(button.getText().equals("액션")) {			// button의 문자열을 가져와 "액션"문자열과 같은지 확인
				button.setText("Action");				// 같으면 "Action"으로 바꿈
			}
			else {
				button.setText("액션");					// 반대로 문자열이 "Action" 이면 "액션" 바꿈
			}
		}
	}
	
	public static void main(String args[]) {			// 메인 함수
		new changeText();								// changeText 인스턴스 생성
	}
}
