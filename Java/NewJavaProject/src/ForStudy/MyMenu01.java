package ForStudy;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class MyMenu01 {

   public static void main(String[] args) {
      MyMenu mm = new MyMenu();
      mm.display();
      mm.makeMenu();
   }
}

class MyMenu extends JFrame   implements ActionListener {


   JMenuBar mb;
   JMenu mFile, mEdit, mHelp;
   JMenu mOut;
   JMenuItem miNew, miOpen, miSave, miQuit;
   JMenuItem miCopy, miPaste, miCut;
   JMenuItem miHelp,create;
   JMenuItem toPdf, toPrinter,info,Creatermip,mipPaste,Creater;
   JTextArea area;
   FileDialog open,save;
   JMenuItem miCal,miCanvas;
   
   MyMenu() {
      mb = new JMenuBar();
  
      
      mFile = new JMenu("파일");//메뉴
      mEdit = new JMenu("편집");

      mHelp = new JMenu("도움말");
      mOut = new JMenu("출력");      // 서브메뉴 만들기 mFile - 
      miNew = new JMenuItem("새 글");
      miNew.addActionListener(this);
      miOpen = new JMenuItem("열기");
      miOpen.addActionListener(this);
     open=new FileDialog(this,"열기");
      miSave = new JMenuItem("저장");
     save=new FileDialog(this,"저장");
      miSave.addActionListener(this);
      miQuit = new JMenuItem("나가기");
      miQuit.addActionListener(this);
      miCopy = new JMenuItem("복사하기");
      miPaste = new JMenuItem("붙여넣기");
      miCut = new JMenuItem("잘라내기");
      miHelp = new JMenuItem("도움말");
      miHelp = new JMenuItem("도움말");
      
      
      toPdf = new JMenuItem("PDF으로 변환");      // mOut - 
      toPrinter = new JMenuItem("인쇄하기");      // mOut - 
      create = new JMenu("정보");  
      info=new JMenuItem("제작");
      Creater=new JMenuItem("만든이");
      
      Creater.setEnabled(false);
      
      area=new JTextArea(10,30);
      add(area);
   miCal=new JMenuItem("계산기");
   miCanvas=new JMenuItem("그림판");
   miCanvas.addActionListener(this);
   }
   
   
   void display() {
      setTitle("내가 만든 메뉴");
      setLayout(new FlowLayout());
      setSize(500, 500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }

   
   
   void makeMenu() {
      setJMenuBar(mb);

      mb.add(mFile);
      mb.add(mEdit);
      mb.add(mHelp);
      
      mFile.add(miNew);
      mFile.add(miOpen);
      mFile.add(miSave);
      mFile.add(mOut);
      mFile.addSeparator();
      mFile.add(miQuit);
      
      
      mOut.add(toPdf);
      mOut.add(toPrinter);
      
      mEdit.add(miCopy);
      mEdit.add(miPaste);
      mEdit.add(miCut);
      
      mHelp.add(miHelp);
      mHelp.add(create);//create에 info랑 Creater랑 삭속
      create.add(info);
      create.add(Creater);   
      mHelp.add(miCal);
      mHelp.add(miCanvas);
      miHelp.addActionListener(this);
      miCopy.setAccelerator(KeyStroke.getKeyStroke('c',ActionEvent.CTRL_MASK));
    
      
      area=new JTextArea(10,50);
      add(area);
   }


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  if(e.getSource()==miQuit) {
		  System.exit(0);
	  }
	  if(e.getSource()==miNew)
	  {
		  area.setText("");
	  }
	  if(e.getSource()==miOpen) {
		  open.setVisible(true);
	  }
	  if(e.getSource()==miSave) {
		  save.setVisible(true);
	  }
	  if(e.getSource()==miCal) {
		  MyCacl cl=new MyCacl();
		  cl.display();
	  }
	  if(e.getSource()==miHelp) {
		   MyHelp hp=new MyHelp();
		 hp.isShowing();
	  }
	  if(e.getSource()==miCanvas) {
		  MyDrawFrame dw=new MyDrawFrame();
		  dw.display();
	  }
	  
	}
}
class MyHelp extends JDialog{
	  JButton btn;
	  MyHelp(){
		  btn=new JButton("Close");
		  btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  System.exit(0);	
			}
});
		  add(btn);  
			setLayout(new FlowLayout());
			setTitle("내가 만든 대화상자");
			setSize(200,100);
			setVisible(true);
	  }
}