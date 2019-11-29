package ForStudy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rand {

	   public static void main(String[] args) {
	      DotPrint dp = new DotPrint();
	      dp.display();      
	   }

	}

	class DotPrint extends JFrame{

	   JPanel p1;
	   JLabel num[];
	   
	   DotPrint()
	   {
	      // 화면 크기 알아보기
	      Toolkit kit = Toolkit.getDefaultToolkit();
	      Dimension screenSize = kit.getScreenSize();
	      //p1=new JPanel();
	      //p1.setLayout(null);
	      //p1.setBackground(Color.YELLOW);
	      
	      
	      //getContentPane().setBackground(Color.YELLOW);
	      int x = screenSize.width;
	      int y = screenSize.height;
	      Dimension dim=new Dimension(x/2,y/2);
	      setPreferredSize(dim);
	      //setSize();
	      pack();
	      num=new JLabel[100];
	      for(int i=0;i<100;i++) 
	      {
	    	  num[i]=new JLabel(""+(int)(Math.random()*100)+1);//난수가1부터 100까지 나옴
	    	  num[i].setLocation((int)(Math.random()*x/2),(int)(Math.random()*y/2));
	    	  num[i].setSize(100,20);
		      num[i].setForeground(Color.blue);
			    //p1.add(num[i]);
		    add(num[i]); 
	      }
	   }
	   
	   void display()
	   {
	      setLayout(null); // 사용자 위치를 설정
	      //setSize(300,300);
	      setTitle("난수 생성");
	      setVisible(true);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }
	}
