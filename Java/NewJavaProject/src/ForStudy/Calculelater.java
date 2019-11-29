package ForStudy;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculelater  {

	public static void main(String[] args) {
		MyCacl cl=new MyCacl();
		cl.display();
	}

}

class MyCacl  extends JFrame implements ActionListener{
	JPanel panel;
	JButton btn[];
	JButton opBtn[];
	JTextField tf;
	String op1="",op2="";//텍스트 필드를 위한 변수
	String operator;
	float i,j,k;//연산을 위한 변수
	
MyCacl(){
	
	panel=new JPanel(new GridLayout(4,4));
	btn=new JButton[12];//0~9,=,clear
	opBtn=new JButton[4];//연산자 +,_,*,/
	
	for(int i=0;i<10;i++) {
		btn[i]=new JButton(""+i);
		btn[i].addActionListener(this);
		panel.add(btn[i]);
	}
	btn[10]=new JButton("=");
	btn[10].addActionListener(this);//버튼 이벤트
	panel.add(btn[10]);
	btn[11]=new JButton("Clear");
	btn[11].addActionListener(this);//clear 버튼 이벤트
	panel.add(btn[11]);
	opBtn[0]=new JButton("+");opBtn[1]=new JButton("-");
	opBtn[2]=new JButton("*");opBtn[3]=new JButton("/");
	
	for(int i=0;i<4;i++) 
	{
		opBtn[i].addActionListener(this);
		panel.add(opBtn[i]);
	}
		tf=new JTextField(23);
		tf.setHorizontalAlignment(4);
		add(tf);
}

void display() {
    setTitle("계산기");
    setLayout(new FlowLayout());
    setSize(500, 500);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(panel);

}


@Override

public void actionPerformed(ActionEvent e) 
{
	if(e.getSource()==btn[11]) 
	{
		op1="";
		tf.setText("");
	}
	for(int i=0;i<10;i++) 
	{
		if(e.getSource()==btn[i])
		{
			op1+=e.getActionCommand();
			tf.setText(op1);
			}
		}
	if((e.getActionCommand()=="+")|(e.getActionCommand()=="-")|
	(e.getActionCommand()=="*")|(e.getActionCommand()=="/"))
	{
		operator=e.getActionCommand();//연산자를 변수에 저장한다
		op2=op1;// 다시 받을 수 있도록 op2에 저장한다.
		op1="";
		i=Float.parseFloat(op2);//숫자가 들어오면 op1에 저장
		}
	if(e.getActionCommand()=="=") {
		j=Float.parseFloat(op1);//나중에 입력할  숫자형 변환
		tf.setText(""+opper(operator, i, j));
	}
}
public float opper(String operato,float i, float j) {
	if(operator.equals("+")) {
		k=i+j;
	}
	else if(operator.equals("-")) {
		k=i-j;
	}
	else if(operator.equals("*")) {
		k=i*j;
	}else if(operator.equals("/")) {
		k=i/j;
	}
		return k;
	}
}