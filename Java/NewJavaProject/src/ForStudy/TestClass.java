package ForStudy;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestClass {

	public static void main(String[] args) {
		MyAllCompo ma=new MyAllCompo();
		ma.display();
	
	}

}

class MyAllCompo extends JFrame
{
	JButton btn;
	JLabel lb;
	JTextField tf;
	JTextArea ta;
	JCheckBox ch1,ch2;//üũ�ڽ�
	JRadioButton r1,r2;//���� ��ư
	ButtonGroup group;//���� ��ư�� ���� �׷�
	String items[]= {"�ٳ���","����","���","����","�丶��"};
	Integer count[]= {2,3,5,7,8,10};
	JList<String> lst;
	JComboBox<Integer>Combo;
	
	MyAllCompo()
	{
		btn=new JButton("���� ���� ��ư");add(btn);
		lb=new JLabel("���� ��ĥ�� ���� ���̺��̾�");add(lb);
		tf = new JTextField("���� ����",30); add(tf);
		ta=new JTextArea("asfas",10,30);add(ta);
		ch1=new JCheckBox("�ڹ�");add(ch1);
		ch2=new JCheckBox("JSP");add(ch2);
		r1=new JRadioButton("��");add(r1);
		r2=new JRadioButton("��");add(r2);
		group=new ButtonGroup();
		group.add(r1);group.add(r2);
		lst=new JList<>(items);add(lst);
		Combo = new JComboBox<>(count);add(Combo);
		
	}
	void display()
	{
		setLayout(new FlowLayout());
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				
	}
}
