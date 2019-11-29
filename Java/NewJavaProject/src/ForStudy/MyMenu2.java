package ForStudy;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenu2 {

   public static void main(String[] args) {
      MyMenu mm = new MyMenu();
      mm.display();
      mm.makeMenu();
   }

}

class MyMenu extends JFrame {
   
   JMenuBar mb;
   JMenu mFile, mEdit, mHelp;
   JMenu mOut;
   JMenuItem miNew, miOpen, miSave, miQuit;
   JMenuItem miCopy, miPaste, miCut;
   JMenuItem miHelp,create;
   JMenuItem toPdf, toPrinter,info,Creatermip,mipPaste,Creater;
   
   MyMenu() {
      mb = new JMenuBar();
      
      mFile = new JMenu("파일");//메뉴
      mEdit = new JMenu("편집");
      mHelp = new JMenu("도움말");
      mOut = new JMenu("출력");      // 서브메뉴 만들기 mFile - 
      miNew = new JMenuItem("새 글");
      miOpen = new JMenuItem("열기");
      miSave = new JMenuItem("저장");
      miQuit = new JMenuItem("나가기");
      miCopy = new JMenuItem("복사하기");
      miPaste = new JMenuItem("붙여넣기");
      miCut = new JMenuItem("잘라내기");
      miHelp = new JMenuItem("도움말");
      toPdf = new JMenuItem("PDF으로 변환");      // mOut - 
      toPrinter = new JMenuItem("인쇄하기");      // mOut - 
      create = new JMenu("정보");  
      info=new JMenuItem("제작");
      Creater=new JMenuItem("만든이");
      Creater.setEnabled(false);
      
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
      
      
   }
}