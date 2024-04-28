import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Merkez {
	
	public static void main(String[] args) {
		Panel1 panel1=new Panel1();
		Panel2 panel2=new Panel2();
		JFrame pencere=new JFrame();
		JTabbedPane tp=new JTabbedPane();
		tp.add("Öğrenciler",panel1);
		tp.add("Bölümler",panel2);
		tp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				panel1.kutuVeriAl();
				panel1.veriAl();
			}});
		Image logo= Toolkit.getDefaultToolkit().getImage("./img/icons8-user-64.png");
		pencere.add(tp);
		pencere.setTitle("Okul-Aplication");
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setSize(600,600);
		pencere.setIconImage(logo);
		pencere.setResizable(false);
		pencere.setLocation(100, 100);
		pencere.setVisible(true);
	}

}