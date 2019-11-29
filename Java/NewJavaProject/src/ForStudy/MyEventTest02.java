package ForStudy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyEventTest02 {
	public static void main(String[] args) {
		Myevent02 mm=new Myevent02();
		mm.display();
		
	}
}

	class Myevent02 extends JFrame{
		JButton close,clear;//참조변수
		JTextArea ta;
		JCheckBox ch[];
		JRadioButton gender[];
		ButtonGroup group;
		
		Myevent02(){//생성자
			close=new JButton("close");
			close.addActionListener(new shutdown());
			
			clear=new JButton("Clear");
			clear.addAncestorListener(this);
			add(clear);
			ta=new JTextArea(10,40);
			add(ta);
			tf=new JTextField(40);
			add(tf);
		for(int i=0;i<ch.lenght;i++) {
			ch[i]=new JCheckBox(""+(i+1)+"번");
			ch[i].addAction
		}
		}
		
		void display() {
			setLayout(new FlowLayout());
			setTitle("재미있는 이벤트");
			setSize(500,500);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	class shutdown implements ActionListener{
		
		public void actionperformed(ActionEvent a)
		{
			System.exit(0);
		}
	}
