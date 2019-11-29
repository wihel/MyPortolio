package ForStudy;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exchange 
{
	public static void main(String[] args) 
	{
		mylab ml=new mylab();//클래스 메소드가 대뮨자가 아닌 소문자로 해야 실행이 된다
		ml.display();
	}
}
	class mylab extends JFrame{
	JPanel panel=new JPanel();
	JLabel label1,label2;
	JTextField field1, field2;
	JButton button;
	mylab(){
		label1=new JLabel("센티미터");
		label2=new JLabel("인치");
		field1=new  JTextField(15); 
		field2=new  JTextField(15);
		button=new JButton("변환");
	
		panel.add(label1);
		panel.add(label2);
		panel.add(field1);
		panel.add(field2);
		panel.add(button);
	
	}
	
	void display() {
		
		setTitle("길이변환");
	     setLayout(new FlowLayout());
	     setSize(300, 150);
	     setVisible(true);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      }
	
	
}

