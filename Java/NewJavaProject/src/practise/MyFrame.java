package practise;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	JPanel p1;
	public MyFrame() {

		setSize(300,200);
		setTitle("MyFrame");
		p1=new JPanel();
		p1.setLayout(new FlowLayout());
		for(int i=0;i<10;i++) {
			p1.add(new JButton("Button"+i));//출력될 버튼 메세지
			add(p1);//출력
			setVisible(true);//보이게함
		}//버튼이 3x3이런식으로 나열된는 이유는 그냥 자바가 알아서 화면을 맟춰주니까 
		//화면 조정을 하면 나열이 바뀌는걸 볼 수 있다.
	}
	public static void main(String[] args) {
		MyFrame f=new MyFrame();
	}
}
