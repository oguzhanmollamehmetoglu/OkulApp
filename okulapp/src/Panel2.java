import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Panel2 extends JPanel {
	JTable tablo;
	JScrollPane sp;
	DefaultTableModel model;
	JButton b1,b2,b3;
	JTextField t1;
	JLabel e1;
	Connection baglanti;
	String id;
	ListSelectionModel hucre;
	ListSelectionListener degis;
	public Panel2() {
	tablo=new JTable();
	model=new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}};
	model.setColumnIdentifiers(new Object[] {"id","Bölüm Ad"});
	tablo.setModel(model);
	hucre=tablo.getSelectionModel();
	sp=new JScrollPane(tablo);
	t1=new JTextField();
	e1=new JLabel("Bölüm Adı= ");
	b1=new JButton("Ekle");
	b2=new JButton("Güncelle");
	b3=new JButton("SİL");
	veriAl();
	setLayout(null);
	sp.setBounds(10, 10, 400, 300);
	e1.setBounds(10, 320, 100, 30);
	t1.setBounds(120, 320, 100, 30);
	b1.setBounds(10, 360, 100, 30);
	b2.setBounds(120, 360, 100, 30);
	b3.setBounds(230, 360, 100, 30);
	b1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		ekle();
		}
	});
    b2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		guncelle();	
		}
	});
    b3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    sil();	
	    }
    });
    degis=new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
		   id = tablo.getValueAt(tablo.getSelectedRow(),0).toString();
		   t1.setText(tablo.getValueAt(tablo.getSelectedRow(),1).toString());
		}};
	hucre.addListSelectionListener(degis);
	add(sp);
	add(e1);
	add(t1);
	add(b1);
	add(b2);
	add(b3);
    }
	public void ekle() {
		try {
			hucre.removeListSelectionListener(degis);
			baglan();
			String sql="insert into bolum(ad) values(?)";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,t1.getText());
			ps.executeUpdate();
			baglanti.close();
			t1.setText("");
			t1.requestFocus();
			veriAl();
			hucre.addListSelectionListener(degis);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public void guncelle() {
    	try {
			hucre.removeListSelectionListener(degis);
			baglan();
			String sql="update bolum set ad=? where id=?";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,t1.getText());
			ps.setString(2,id);
			ps.executeUpdate();
			baglanti.close();
			t1.setText("");
			t1.requestFocus();
			id="";
			veriAl();
			hucre.addListSelectionListener(degis);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public void sil() {
    	try {
			hucre.removeListSelectionListener(degis);
			baglan();
			String sql="delete from bolum where id=?";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,id);
			ps.executeUpdate();
			baglanti.close();
			t1.setText("");
			t1.requestFocus();
			veriAl();
			hucre.addListSelectionListener(degis);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void veriAl() {
    	try {
			baglan();
			String sql="Select * from bolum";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ResultSet sonuc=ps.executeQuery();
			model.setRowCount(0);
			model.setColumnIdentifiers(new Object[] {"bölüm id","bölüm"});
			while(sonuc.next()){
				model.addRow(new Object[] {sonuc.getString("id"),sonuc.getString("ad")});
			}
			baglanti.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void baglan() throws SQLException {
	   String yol="jdbc:mysql://localhost:3306/okul?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	   String kullanici="mudur";
	   String sifre="1234";
	   baglanti=DriverManager.getConnection(yol, kullanici, sifre);
	}
}
