package practiceJava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class changeText extends JFrame{
	public changeText() {
		setSize(350, 200);								// ������ ũ��
		setTitle("Action �̺�Ʈ ������ �ۼ�");				// ������ Ÿ��Ʋ
		setLayout(new FlowLayout());					// ������ ���̾ƿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ������ ����� ���α׷� ����
		JButton button = new JButton("�׼�");				// �׼��̶�� �ؽ�Ʈ�� ���� ��ư
		add(button);									// �����ӿ� ��ư ����
		
		changeTextButton ctb = new changeTextButton();	// changeTextButton �׼� ������ ��ü ����
		
		button.addActionListener(ctb);					// ��ư�� �׼� ������ ����
		
		setVisible(true);								// ������ ����
	}
	
	class changeTextButton implements ActionListener{	// �������̽� ActionListener �����ϴ� changeTextButton Ŭ���� 
		@Override
		public void actionPerformed(ActionEvent e) {	// �׼��� �߻��ϸ� �۵��� �Լ�
			JButton button = (JButton)e.getSource();	// �׼��� �Ͼ ��ü�� JButton ���� button ���� ����
			if(button.getText().equals("�׼�")) {			// button�� ���ڿ��� ������ "�׼�"���ڿ��� ������ Ȯ��
				button.setText("Action");				// ������ "Action"���� �ٲ�
			}
			else {
				button.setText("�׼�");					// �ݴ�� ���ڿ��� "Action" �̸� "�׼�" �ٲ�
			}
		}
	}
	
	public static void main(String args[]) {			// ���� �Լ�
		new changeText();								// changeText �ν��Ͻ� ����
	}
}
