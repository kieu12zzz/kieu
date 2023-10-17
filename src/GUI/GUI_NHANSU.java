package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.*;
import BUSINESSLOGIC.*;

import PROCESSDATA.*;

public class GUI_NHANSU extends JFrame implements ActionListener, ItemListener{
	private JLabel lbeMaNV, lbeHoten, lbeNgaysinh, lbeGioitinh, lbeSCM, lbeNgayvaocoquang, lbeHesoluong, lbeMucluong;
	public JTextField txtMaNV, txtHoten, txtNgaysinh, txtSCM, txtNgayvaocoquang, txtHesoluong, txtMucluong;
	public JRadioButton rdoNam, rdoNu, rdoNVBC, rdoNVHD;
	private ButtonGroup btgGioiTinh, btgNhanVien;
	private JButton btnThem, btnXoa, btnSua, btnTim, btnReadFile, btnWriteFile;
	private businesslogic_NHANSU blNHANSU;
	private String colNames[] = { "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh" };
	public JTable tbleNHANSU;
	JScrollPane jScrollPane1;
	ProcessData_NHANSU processData_NHANSU;
	 
	public GUI_NHANSU() {
		setSize(750, 600);
		setLayout(null);
		this.addWindowListener(new MyWindowClose());
		lbeMaNV = new JLabel("Mã nhân viên:");
		lbeMaNV.setBounds(30, 30, 100, 30);
		this.add(lbeMaNV);
		lbeHoten = new JLabel("Họ tên:");
		lbeHoten.setBounds(30, 70, 100, 30);
		this.add(lbeHoten);
		lbeGioitinh = new JLabel("Giới tính:");
		lbeGioitinh.setBounds(30, 110, 100, 30);
		this.add(lbeGioitinh);
		lbeNgaysinh = new JLabel("Ngày sinh:");
		lbeNgaysinh.setBounds(30, 150, 100, 30);
		this.add(lbeNgaysinh);
		lbeSCM = new JLabel("Số chứng minh:");
		lbeSCM.setBounds(30, 190, 100, 30);
		this.add(lbeSCM);
		lbeNgayvaocoquang = new JLabel("Ngày vào cơ quang:");
		lbeNgayvaocoquang.setBounds(30, 230, 120, 30);
		this.add(lbeNgayvaocoquang);
		txtMaNV = new JTextField();
		txtMaNV.setBounds(150, 30, 150, 30);
		this.add(txtMaNV);
		txtHoten = new JTextField();
		txtHoten.setBounds(150, 70, 150, 30);
		this.add(txtHoten);
		btgGioiTinh = new ButtonGroup();
		rdoNam = new JRadioButton("Nam", true);
		rdoNam.setBounds(150, 110, 100, 30);
		this.add(rdoNam);
		btgGioiTinh.add(rdoNam);
		rdoNu = new JRadioButton("Nữ");
		rdoNu.setBounds(250, 110, 100, 30);
		this.add(rdoNu);
		btgGioiTinh.add(rdoNu);
		txtNgaysinh = new JTextField();
		txtNgaysinh.setBounds(150, 150, 150, 30);
		this.add(txtNgaysinh);
		txtSCM = new JTextField();
		txtSCM.setBounds(150, 190, 150, 30);
		this.add(txtSCM);
		txtNgayvaocoquang = new JTextField();
		txtNgayvaocoquang.setBounds(150, 230, 150, 30);
		this.add(txtNgayvaocoquang);
		btgNhanVien = new ButtonGroup();
		rdoNVBC = new JRadioButton("Nhân viên biên chế:", true);
		rdoNVBC.setBounds(30, 270, 150, 30);
		this.add(rdoNVBC);
		rdoNVBC.addItemListener(this);
		
		btgNhanVien.add(rdoNVBC);
		rdoNVHD = new JRadioButton("Nhân viên hợp đồng");
		rdoNVHD.setBounds(250, 270, 150, 30);
		this.add(rdoNVHD);
		rdoNVHD.addItemListener(this);
		
		btgNhanVien.add(rdoNVHD);
		lbeHesoluong = new JLabel("Hệ số lương:");
		lbeHesoluong.setBounds(30, 310, 100, 30);
		this.add(lbeHesoluong);
		txtHesoluong = new JTextField();
		txtHesoluong.setBounds(130, 310, 100, 30);
		this.add(txtHesoluong);
		lbeMucluong = new JLabel("Hệ số lương:");
		lbeMucluong.setBounds(250, 310, 100, 30);
		this.add(lbeMucluong);
		txtMucluong = new JTextField();
		txtMucluong.setBounds(350, 310, 100, 30);
		this.add(txtMucluong);
		btnThem = new JButton("Thêm mới");
		btnThem.setBounds(30, 350, 100, 30);
		this.add(btnThem);
		btnThem.addActionListener(this);
		btnSua = new JButton("Sửa");
		btnSua.setBounds(140, 350, 100, 30);
		this.add(btnSua);
		btnSua.addActionListener(this);
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(260, 350, 100, 30);
		this.add(btnXoa);
		btnXoa.addActionListener(this);
		btnTim = new JButton("Tìm");
		btnTim.setBounds(370, 350, 80, 30);
		this.add(btnTim);
		btnTim.addActionListener(this);
		
		btnReadFile = new JButton("Đọc file");
		btnReadFile.setBounds(470, 350, 100, 30);
		btnReadFile.addActionListener(this);
		this.add(btnReadFile);
		
		btnWriteFile = new JButton("Ghi file");
		btnWriteFile.setBounds(580, 350, 100, 30);
		this.add(btnWriteFile);
		btnWriteFile.addActionListener(this);
		
		blNHANSU = new businesslogic_NHANSU(this);
		tbleNHANSU = new JTable();
		blNHANSU.ListStaff();
		tbleNHANSU.setBounds(60, 400, 600, 300);
		this.add(tbleNHANSU);
		tbleNHANSU.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tbleNHANSUMouseClicked(evt);
			}
		});
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(tbleNHANSU);
		jScrollPane1.setBounds(60, 400, 600, 300);
		this.add(jScrollPane1);
	}

	private void tbleNHANSUMouseClicked(MouseEvent evt) {
		if (this.tbleNHANSU.getSelectedRow() >= 0) {
			String Manv = this.tbleNHANSU.getValueAt(this.tbleNHANSU.getSelectedRow(), 0).toString();
			blNHANSU.BindingStaff(Manv);
		}
	}
	
	public void resetJtextField() {
		txtMaNV.setText("");
		txtHoten.setText("");
		txtNgaysinh.setText("");
		txtSCM.setText("");
		txtNgayvaocoquang.setText("");
		txtHesoluong.setText("");
		txtMucluong.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnThem) {
			if (blNHANSU.InsertStaff() > 0) {
				JOptionPane.showMessageDialog(this, "Thêm mới thành công");
				 
				blNHANSU.ListStaff();
				
				
			} else {
				JOptionPane.showMessageDialog(this, "Thêm mới không thành công");
				
			}
		} else if (ae.getSource() == btnXoa) {
			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa thông tin này không", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (result < 1) {
				if (blNHANSU.DeleteStaff() > 0) {
					JOptionPane.showMessageDialog(this, "Nhân viên đã được xóa");
				} else {
					JOptionPane.showMessageDialog(this, "Nhân viên chưa được xóa");
				}
				blNHANSU.ListStaff();
			}
		} else if (ae.getSource() == btnTim) {
			blNHANSU.findStaff();
		} else if (ae.getSource() == btnSua) {
			int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa thông tin này không", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (result < 1) {
				if (blNHANSU.updateStaff() > 0) {
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công");
					blNHANSU.ListStaff();
				} else {
					JOptionPane.showMessageDialog(this, "thông tin cập nhật nhân viên chưa được chỉnh sửa");
				}
			}
		}
			 
		resetJtextField();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(rdoNVBC.isSelected()) {
			txtMucluong.setEditable(false);
			txtHesoluong.setEditable(true);
		} else if(rdoNVHD.isSelected()) {
			txtMucluong.setEditable(true);
			txtHesoluong.setEditable(false);
		}

		
	}
}

