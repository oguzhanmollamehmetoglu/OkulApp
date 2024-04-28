import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Panel1 extends JPanel {
	JTable tablo;
	JScrollPane sp;
	DefaultTableModel model;
	JComboBox kutu;
	JButton b1,b2,b3;
	JTextField t1,t2,t3;
	JLabel e1,e2,e3,e4;
	Connection baglanti;
	String id;
	ListSelectionModel hucre;
	ListSelectionListener degis;
	public Panel1() {
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
	t2=new JTextField();
	t3=new JTextField();
	e1=new JLabel("Öğrenci Adı= ");
	e2=new JLabel("Öğrenci Soyadı= ");
	e3=new JLabel("Öğrenci Bölümü= ");
	e4=new JLabel("Arama= ");
	b1=new JButton("Ekle");
	b2=new JButton("Güncelle");
	b3=new JButton("SİL");
	kutu=new JComboBox();
	kutuVeriAl();
	veriAl();
	setLayout(null);
	sp.setBounds(10, 50, 400, 260);
	e1.setBounds(10, 320, 100, 30);
	e2.setBounds(230, 320, 100, 30);
	e3.setBounds(10, 360, 140, 30);
	e4.setBounds(10, 10, 100, 30);
	t1.setBounds(120, 320, 100, 30);
	t2.setBounds(330, 320, 100, 30);
	t3.setBounds(120, 10, 100, 30);
	kutu.setBounds(160, 360, 100, 30);
	b1.setBounds(10, 400, 100, 30);
	b2.setBounds(120, 400, 100, 30);
	b3.setBounds(230, 400, 100, 30);
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
		   t2.setText(tablo.getValueAt(tablo.getSelectedRow(),2).toString());
		   int bid=(int) Integer.valueOf(tablo.getValueAt(tablo.getSelectedRow(), 3).toString());
		   
		   for(int i=0;i<kutu.getItemCount();i++) {
			   Bolum b=(Bolum) kutu.getItemAt(i);
			   if(b.getId()==bid) {
				   kutu.setSelectedIndex(i);
			   }
		   }
		}};
	hucre.addListSelectionListener(degis);
	t3.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			hucre.removeListSelectionListener(degis);
			TableRowSorter<DefaultTableModel> filter=new TableRowSorter<DefaultTableModel>(model);
			tablo.setRowSorter(filter);
			filter.setRowFilter(RowFilter.regexFilter(t3.getText()));
			hucre.addListSelectionListener(degis);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	});
	add(sp);
	add(e1);
	add(e2);
	add(e3);
	add(e4);
	add(t1);
	add(t2);
	add(t3);
	add(b1);
	add(b2);
	add(b3);
	add(kutu);
    }
	public void ekle() {
		try {
			hucre.removeListSelectionListener(degis);
			baglan();
			String sql="insert into ogrenci(ad,soyad,bolum) values(?,?,?)";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,t1.getText());
			ps.setString(2,t2.getText());
			Bolum b=(Bolum) kutu.getSelectedItem(); 
			ps.setInt(3, b.getId());
			ps.executeUpdate();
			baglanti.close();
			t1.setText("");
			t2.setText("");
			kutu.setSelectedIndex(0);
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
			String sql="update ogrenci set ad=?,soyad=?,bolum=? where id=?";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,t1.getText());
			ps.setString(2,t2.getText());
			Bolum b=(Bolum) kutu.getSelectedItem(); 
			ps.setInt(3, b.getId());
			ps.setString(4,id);
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
			String sql="delete from ogrenci where id=?";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ps.setString(1,id);
			ps.executeUpdate();
			baglanti.close();
			t1.setText("");
			t2.setText("");
			kutu.setSelectedIndex(0);
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
			String sql="Select o.id as id,o.ad as ad,o.soyad as soyad,b.id as 'bölüm id',b.ad as bölüm from ogrenci as o left join bolum as b on o.bolum=b.id";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ResultSet sonuc=ps.executeQuery();
			model.setRowCount(0);
			model.setColumnIdentifiers(new Object[] {"id","ad","soyad","bölüm id","bölüm"});
			while(sonuc.next()){
				model.addRow(new Object[] {sonuc.getString("id"),sonuc.getString("ad"),sonuc.getString("soyad"),sonuc.getString("bölüm id")==null?"0":sonuc.getString("bölüm id"),sonuc.getString("bölüm")==null?"Boş":sonuc.getString("bölüm")});
			}
			baglanti.close();
			tablo.getColumnModel().getColumn(3).setMinWidth(0);
			tablo.getColumnModel().getColumn(3).setMinWidth(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void kutuVeriAl() {
    	try {
			baglan();
			String sql="Select * from bolum";
			PreparedStatement ps=baglanti.prepareStatement(sql);
			ResultSet sonuc=ps.executeQuery();
			kutu.removeAllItems();
			kutu.addItem(new Bolum(0,"Boş"));
			while(sonuc.next()){
				kutu.addItem(new Bolum(sonuc.getInt("id"),sonuc.getString("ad")));
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
