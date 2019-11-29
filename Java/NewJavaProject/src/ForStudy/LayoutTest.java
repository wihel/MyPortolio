package ForStudy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LayoutTest {

	public static void main(String[] args) {
		
		MYButtons mb = new MYButtons();
		mb.display();
		}
	}
class MYButtons extends JFrame{
	
	
	JButton btn1,btn2,btn3,btn4,btn5;
	JPanel p1;
	MYButtons(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/2,screenSize.height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyFrame");
		Image img=kit.getImage("icon.gif");
		setIconImage(img);
		setLayout(new FlowLayout());
		JButton button=new JButton("��ư");
		this.add(button);
		setFocusable(true);
		
		p1=new JPanel(new BorderLayout(10,30));
		p1.setBackground(Color.GREEN);
		btn1=new JButton("1");
		btn2=new JButton("2");
		btn3=new JButton("3");
		btn4=new JButton("4");
		btn5=new JButton("5");
		
		p1.add(btn1,BorderLayout.EAST);
		p1.add(btn2,BorderLayout.WEST);
		p1.add(btn3, BorderLayout.SOUTH);
		p1.add(btn4, BorderLayout.NORTH);
		p1.add(btn5, BorderLayout.CENTER);
		add(p1);
	}
	public void display() 
	{

		setLayout(new FlowLayout(20,30,40));
		setTitle("���̸� ����մϴ�.");
		setSize(300,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
	