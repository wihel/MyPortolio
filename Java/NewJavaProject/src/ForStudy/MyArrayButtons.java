package ForStudy;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyArrayButtons {

	public static void main(String[] args) {
		MyAryButton mb=new MyAryButton();
		mb.display();
	}

}
class MyAryButton extends JFrame{
	JButton btn[];
	MyAryButton()
	{
		btn=new JButton[10];
		for(int i=0;i<10;i++)
		{
			btn[i]=new JButton(""+i);
			add(btn[i]);
		}
	}
	void display() 
	{
		setLayout(new GridLayout(4,3));
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

