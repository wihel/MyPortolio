package ForStudy;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameTest extends JFrame{
public FrameTest() {
	Toolkit kit=Toolkit.getDefaultToolkit();//현재 화면에 크기를 얻는다.
	Dimension screenSize=kit.getScreenSize();
	setSize(300,300);//프레임크기 설정
	setLocation(screenSize.width/2,screenSize.height/2);//버튼프레임위치를 현제화면의 중앙으로 설정한다.
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//사용자가 프레임의 오른쪽 상단에 있는 종료버튼을 누르면 종료하도록 제작
	setVisible(true);//프레임을 화면에 나타내도록함
	setTitle("MyFrame");//화면타이틀에 글자를 출력시킨다.
	Image img=kit.getImage("icon.gif");
	setIconImage(img);
	
	setLayout(new FlowLayout());//컴포넌트를 순차적으로 배치시킨다. 배치관리자는 setLayout()메소드를 호출하면된다. 배치관리자 객체는 FlowLayout클래스로 생성한다.
	JButton button=new JButton("버튼");
	this.add(button);
	setVisible(true);
	
}
	public static void main(String[] args) {
		FrameTest f=new FrameTest();
		
		
		}
}
