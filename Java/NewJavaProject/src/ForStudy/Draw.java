package ForStudy;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Draw {

   public static void main(String[] args) 
   {
      MyDrawFrame df = new MyDrawFrame();
      df.display();
   }
}

class MyDrawFrame extends JFrame implements ActionListener, MouseMotionListener {
   MymakeCanvas myCanvas;
   JButton clear;

   MyDrawFrame() 
   {
      myCanvas = new MymakeCanvas(); // 객체화
      myCanvas.setSize(300, 300); // 스케치북의 크기
      myCanvas.setBackground(Color.gray);//배경색 회색
      myCanvas.addMouseMotionListener(this);//myCanvas에 마우스이벤트 할당
      add(myCanvas);
      clear = new JButton("Clear");
      add(clear);
      clear.addActionListener(this);//clear에 버튼클릭시 이벤트를 할당
   }

   void display() 
   {
      setTitle("내가만든캔버스");
      setLayout(new FlowLayout());
      setSize(500, 500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   @Override
   public void mouseDragged(MouseEvent e) 
   {
      myCanvas.x = e.getX();
      myCanvas.y = e.getY();
      myCanvas.repaint();
   }
   @Override
   public void mouseMoved(MouseEvent e) {

   }
   class MymakeCanvas extends Canvas // 스케치북
   {
      int x = 100, y = 100;

      public void paint(Graphics g) {
         g.setColor(Color.black); // 연필색
         g.fillOval(x, y, 10, 10); // 동그라미
      }
      public void update(Graphics g) {
         paint(g);
      }      
   }
   @Override
   public void actionPerformed(ActionEvent arg0) 
   {
      Graphics g=myCanvas.getGraphics();
      g.clearRect(0,0,300,300);
   }
}