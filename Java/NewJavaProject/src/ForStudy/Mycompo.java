package ForStudy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mycompo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame01 mf = new MyFrame01();
		mf.display();
	}

}

class MyFrame extends JFrame
{
	JButton btn,btn2,btn3,btn4;
	JPanel p1,p2;
	
	MyFrame()
	{
		btn = new JButton("하하하하");	
		btn2 = new JButton("안녕 얘들아?");
		btn3 = new JButton("만나서 반가워");
		btn4 = new JButton("히히히");
		
		p1=new JPanel(new BorderLayout());
		p1.setBackground(Color.blue);
		p1.add(btn);p1.add(btn2);
		
		p2=new JPanel();
		p2.setBackground(Color.gray);
		p2.add(btn2);
		p2.add(btn3);p2.add(btn4);
		add(p1);
		add(p2);
		
		
	}
	void display()
	{
		setLayout(new FlowLayout());
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
