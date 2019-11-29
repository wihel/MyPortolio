package ForStudy;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class temperature {

	public static void main(String[] args) {
		CreEvn ev=new CreEvn();
		ev.dispaly();
		

	}
	}

	class CreEvn extends JFrame implements ActionListener{

		JPanel panel=new JPanel();
		JLabel label1=new JLabel("화씨온도\n");
		JLabel label2=new JLabel("섭씨온도\n");
		JLabel label3=new JLabel("합");
		JTextField text1=new JTextField(15);
		JTextField text2=new JTextField(15);
		JTextField text3=new JTextField(15);
		JButton button1=new JButton("섭씨온도");
		JButton close=new JButton("닫기");
		setVericalAlignment();
		void CreEvn(){
		panel.add(label1);
		panel.add(text1);
		panel.add(label2);
		panel.add(text2);
		panel.add(label3);
		panel.add(text3);
		panel.add(button1);
		close.addActionListener(null);
		panel.add(close);
		add(panel);
		}
		
		void dispaly() {
		setLayout(new GridLayout(2,0,5,5));
		setTitle("온도계");
		setSize(800,150);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
			
		}
		
	}
		

