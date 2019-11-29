package ForStudy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MyMission {
	public static void main(String[] args) {
	MyMission01	mm=new MyMission01();
	mm.display();
	mm.MakeMenu();
	}

}
class MyMission01 extends JFrame{
	
	JMenuBar Bar;
	JMenu File,Edit,form,example,Help;							//메뉴바
	JMenuItem NewFile,Open,Save,SaveAs,Select,toPrinter,Quit;	//파일
	JMenuItem SRun,Cut,Copy,Pastes,Delet,Bing,Find,NFind,Change,Move,AllC,Time;//편집
	JMenuItem LineFeed,Font;									//서식
	JMenuItem zORin,zoomin,zoomout,restore,status;				//보기
	JMenuItem creater,info,create,mipHelp;						//도움
MyMission01(){
	Bar=new JMenuBar();
//////////////메뉴바//////////////////////////////

	
	File=new JMenu("파일(F) ");
	Edit=new JMenu("편집(E)");
	form=new JMenu("서식(O)");
	example=new JMenu("보기(V)");
	Help=new JMenu("도움말");
	
/////////////파일///////////////////////////////////
	NewFile=new JMenuItem("새 새로만들기(N)");
	NewFile.setAccelerator(KeyStroke.getKeyStroke('N',ActionEvent.CTRL_MASK));
	Open=new JMenuItem("열기(O)");
	Open.setAccelerator(KeyStroke.getKeyStroke('O',ActionEvent.CTRL_MASK));
	Save=new JMenuItem("저장(S)");
	Save.setAccelerator(KeyStroke.getKeyStroke('S',ActionEvent.CTRL_MASK));
	SaveAs=new JMenuItem("다른이름으로 저장(A)...");
	Select=new JMenuItem("페이지 설정(U)...");
	toPrinter=new JMenuItem("인쇄(P)...Ctlr+P");
	toPrinter.setAccelerator(KeyStroke.getKeyStroke('P',ActionEvent.CTRL_MASK));
	Quit=new JMenuItem("끝내기(X)");
//////////////편집//////////////
	SRun=new JMenuItem("실행취소(U)	Ctlr+Z");
	SRun.setEnabled(false);
	Cut=new JMenuItem("잘라내기(T)	Ctlr+X");
	Cut.setEnabled(false);
	Copy=new JMenuItem("복사(C)Ctlr+ C");
	Copy.setAccelerator(KeyStroke.getKeyStroke('C',ActionEvent.CTRL_MASK));
	Copy.setEnabled(false);
	Pastes = new JMenuItem("붙여넣기(P)Ctlr+V");
	Pastes.setEnabled(false);
	Delet=new JMenuItem ("삭제(L)Ctlr+L");
	Delet.setEnabled(false);
	Bing=new JMenuItem(" Bing으로 검색(S)Ctlr+E"); 
	Find=new JMenuItem(" 찾기(F)Ctlr+F"); 
	Find.setEnabled(false);
	NFind=new JMenuItem("다음찾기(N)F3");
	NFind.setEnabled(false);
	Change=new JMenuItem("바꾸기(R)Ctlr+H");
	Move=new JMenuItem("이동(G)Ctlr+G");
	AllC=new JMenuItem("모두선택(A)Ctlr+A");
	Time=new JMenuItem("시간날짜(D)F5");
/////////////서식//////////////////
	LineFeed=new JMenuItem("자동줄바꿈(W)");
	Font=new JMenuItem("글꼴(F)...");
/////////////보기///////////////////
	zORin=new JMenu("확대하기/축소하기");
	zoomin=new JMenuItem("확대하기 Ctrls+더하기");
	zoomout=new JMenuItem("축호하기 Ctrls+뺘기");
	restore=new JMenuItem("확대하기/축소하기기본값 복원 Ctrls+0");
	status=new JMenuItem("상태표시줄(S) ");
////////////도움말////////////////////////
	info=new JMenuItem("메모장 정보(A)");
	mipHelp=new JMenuItem("도움말보기(H)");
	create=new JMenu("제작자");
	creater=new JMenuItem("우지윤");
}
void display(){
	setTitle("메모장");
	setLayout(new FlowLayout());
	setSize(500,500);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
void MakeMenu() {
	setJMenuBar(Bar);
//////////////메뉴바//////////////////////////////
	Bar.add(File);

	Bar.add(Edit);
	Bar.add(form);
	Bar.add(example);
	Bar.add(Help);	
//////////////파일//////////////////////////////
	File.add(NewFile);
	File.add(Open);
	File.add(Save);
	File.add(SaveAs);
    File.addSeparator();
	File.add(Select);
	File.add(toPrinter);
    File.addSeparator();
	File.add(Quit);
//////////////편집//////////////////////////////
	Edit.add(SRun);
	Edit.addSeparator();
	Edit.add(Cut);
	Edit.add(Copy);
	Edit.add(Pastes);
	Edit.add(Delet);
	Edit.addSeparator();
	Edit.add(Bing);
	Edit.add(Find);
	Edit.add(Change);
	Edit.add(Move);
	Edit.addSeparator();
	Edit.add(AllC);
	Edit.add(Time);
//////////////서식//////////////////////////////
	form.add(LineFeed);
	form.add(Font);
//////////////보기//////////////////////////////
	example.add(zORin);
	zORin.add(zoomin);
	zORin.add(zoomout);
	zORin.add(restore);
	example.add(status);
//////////////도움말//////////////////////////////
	Help.add(info);
	Help.addSeparator();
	Help.add(mipHelp);
	Help.add(create);
	create.add(creater);
	
	}
}

