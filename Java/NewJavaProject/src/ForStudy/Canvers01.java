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

public class Canvers01 {

   public static void main(String[] args) {
      MyDrawFrame df = new MyDrawFrame();
      df.display();
   }

}

class MyDrawFrame extends JFrame implements ActionListener, MouseMotionListener {
   MyMakeCanvas myCanvas;
   JButton clear;
   
   MyDrawFrame() {
      myCanvas = new MyMakeCanvas();
      myCanvas.setSize(300, 300);//캔버스의 크기
      myCanvas.setBackground(Color.white);
      myCanvas.addMouseMotionListener(this);
      add(myCanvas);
      
      clear = new JButton("clear");
      clear.addActionListener(this);
      add(clear);

   }
   
   void display() {
      setTitle("캔버스");
      setLayout(new FlowLayout());
      setSize(500, 500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      myCanvas.x = e.getX();
      myCanvas.y = e.getY();
      myCanvas.repaint();
      
   }

   @Override
   public void mouseMoved(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Graphics g = myCanvas.getGraphics();
      g.clearRect(0, 0, 300, 300);
      
   }
}

class MyMakeCanvas extends Canvas {
   int x = 100, y = 100;
   public void paint(Graphics g) {
      g.setColor(Color.black);//연필색
      g.fillOval(x, y, 10, 10);//동그라미
   }
   public void update(Graphics g) {
      paint(g);
   }
}
