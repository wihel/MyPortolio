package ForStudy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

 

public class MyEventTest01 {
	public static void main(String[] args) {
		MyEvent01 ev01 = new MyEvent01();
		ev01.display();
		}
}  

 

class MyEvent01 extends JFrame implements ActionListener,KeyListener,MouseListener,MouseMotionListener
{
	JButton close,clear;
	JTextArea ta;
	JTextField tf;
	JCheckBox ch[];
	JRadioButton gender[];//단일선택
	ButtonGroup group;
	JComboBox<String> combo;
	String fruit[] = {"바나나","딸기","오렌지","키위","수박"};

	MyEvent01()
	{
		close = new JButton("Close");//닫기버튼
		close.addActionListener(this);
		add(close);

		clear = new JButton("Clear");//클리어버튼
		clear.addActionListener(this);
		add(clear);


		ta = new JTextArea(10,40);//텍스트 입력공간을 정함
		add(ta);
		tf = new JTextField(40);//텍스트에 문장을 입력하기 위한 필드제작
		
		add(tf);

		tf.addActionListener(this);
		tf.addKeyListener(this);
		tf.addMouseListener(this);
		ch = new JCheckBox[5];//체크박스 5개 생성

		for(int i=0;i<ch.length;i++)
		{
			ch[i] = new JCheckBox(""+(i+1)+"번");
			ch[i].addActionListener(this);
			add(ch[i]);
		}

		gender = new JRadioButton[3];//성별 라디오 버튼3개생성

		group = new ButtonGroup();

		for(int i=0;i<gender.length;i++)//
		{
			gender[i] = new JRadioButton();
			group.add(gender[i]);
			add(gender[i]);
			gender[i].addActionListener(this);
		}
		
		gender[0].setText("male");//성별 초기화
		gender[1].setText("female");
		gender[2].setText("other");
		combo = new JComboBox<>(fruit);//리스트 초기화
		add(combo);
		combo.addActionListener(this);
	}

	public void display()
	{
		setLayout(new FlowLayout());
		setTitle("재미있는 이벤트");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	

	public void actionPerformed(ActionEvent a)
	{

		// close 버튼
		if(a.getSource()==close) //이벤트객체를 구분해 준다
			System.exit(0);
		
		// clear 버튼
		if(a.getSource()==clear)
			ta.setText("");

		//tf 텍스트필드
		if(a.getSource()==tf)
		{
			//ta.setText(tf.getText());
			ta.append(tf.getText()+"\n"); //텍스트에어리어에 누적시킨다.
			tf.setText("");
		}

		for(int i=0;i<ch.length;i++)//체크박스버튼 이벤트
		{
			if(a.getSource()==ch[i])
			{
			if(ch[i].isSelected()) //선택이 되었느냐?

				ta.append(ch[i].getText()+"선택 \n");

			else ta.append(ch[i].getText()+ "해제 \n");

			}

		}

		for(int i=0;i<gender.length;i++)

		{

			if(a.getSource()==gender[i])
			{
				ta.append(gender[i].getText()+"\n");
			}
		}
		if(a.getSource()==combo)
		{
			ta.append(combo.getSelectedItem()+"\n");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		ta.append(e.getKeyChar()+"입력했습니다.\n");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	ta.append(e.getKeyChar()+"클릭")	;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		ta.append(e.getKeyChar()+ "떼었을때")	;
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		ta.append(e.getX()+","+e.getY()+"\n");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

 