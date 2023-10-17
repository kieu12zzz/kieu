package BUSINESSLOGIC;



import java.awt.Component;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JTable;
import BUSINESSLOGIC.*;
import PROCESSDATA.*;
import app_quanlynhansu_3layer.*;
import GUI.*;

public class businesslogic_NHANSU {
	Hashtable<String, NHANVIEN> ListStaff;
	ProcessData_NHANSU pdNHANSU;
	GUI_NHANSU frmNHANSU;

	public businesslogic_NHANSU(GUI_NHANSU frm) {
		this.frmNHANSU = frm;
		pdNHANSU = new ProcessData_NHANSU();
	}

	public void ListStaff() {
		ListStaff = pdNHANSU.List();
		frmNHANSU.tbleNHANSU.setModel(new ModelData(ListStaff));
		
	}

	private void SetValueControl(NHANVIEN nv) {
		try {
			this.frmNHANSU.txtMaNV.setText(nv.getManv());
			this.frmNHANSU.txtHoten.setText(nv.getHoten());
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			this.frmNHANSU.txtNgaysinh.setText(d.format(nv.getNgaysinh()).toString());
			this.frmNHANSU.txtNgayvaocoquang.setText(d.format(nv.getNgayvaocoquan()).toString());
			this.frmNHANSU.txtSCM.setText(String.valueOf(nv.getSoCM()));
			if (nv.getGioitinh().equals("Nam")) {
				this.frmNHANSU.rdoNam.setSelected(true);
			} else {
				this.frmNHANSU.rdoNu.setSelected(true);
			}
			if (nv instanceof NHANVIENBC) {
				double hesoluong = ((NHANVIENBC) nv).getHesoluong();
				this.frmNHANSU.txtHesoluong.setText(String.valueOf(hesoluong));
			} else {
				double mucluong = ((NHANVIENHOPDONG) nv).getMucluong();
				this.frmNHANSU.txtMucluong.setText(String.valueOf(mucluong));
			}
		} catch (Exception ex) {
		}
	}

	public void BindingStaff(String Manv) {
		try {
			NHANVIEN nv = this.ListStaff.get(Manv);
			SetValueControl(nv);
		} catch (Exception ex) {
		}
	}

	public void findStaff() {
		try {
			String Manv = this.frmNHANSU.txtMaNV.getText();
			NHANVIEN nv = pdNHANSU.Find(Manv);
			SetValueControl(nv);
		} catch (Exception e) {
		}
	}

	public int InsertStaff() {
		int k = 0;
		try {
			String Manv = this.frmNHANSU.txtMaNV.getText();
			String Hoten = this.frmNHANSU.txtHoten.getText();
			String Gioitinh = "";
			if (this.frmNHANSU.rdoNam.isSelected()) {
				Gioitinh = "Nam";
			} else {
				Gioitinh = "Nữ";
			}
			long SoCM = Long.parseLong(this.frmNHANSU.txtSCM.getText());
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			Date Ngaysinh = d.parse(this.frmNHANSU.txtNgaysinh.getText());
			Date Ngayvaocoquan = d.parse(this.frmNHANSU.txtNgayvaocoquang.getText());
			NHANVIEN nv;
			if (this.frmNHANSU.rdoNVBC.isSelected()) {
				double Hesoluong = Double.parseDouble(this.frmNHANSU.txtHesoluong.getText());
				nv = new NHANVIENBC(Manv, Hoten, Gioitinh, Ngaysinh, SoCM, Ngayvaocoquan, Hesoluong);
			} else {
				double muluong = Double.parseDouble(this.frmNHANSU.txtMucluong.getText());
				nv = new NHANVIENHOPDONG(Manv, Hoten, Gioitinh, Ngaysinh, SoCM, Ngayvaocoquan, muluong);
			}
			
			k = this.pdNHANSU.Insert(nv);
			 
		} catch (ParseException e) {
		}
		return k;
	}

	public int updateStaff() {
		int k = 0;
		try {
			String Manv = this.frmNHANSU.txtMaNV.getText();
			String Hoten = this.frmNHANSU.txtHoten.getText();
			String Gioitinh = "";
			if (this.frmNHANSU.rdoNam.isSelected()) {
				Gioitinh = "Nam";
			} else {
				Gioitinh = "Nữ";
			}
			long SoCM = Long.parseLong(this.frmNHANSU.txtSCM.getText());
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			Date Ngaysinh = d.parse(this.frmNHANSU.txtNgaysinh.getText());
			Date Ngayvaocoquan = d.parse(this.frmNHANSU.txtNgayvaocoquang.getText());
			NHANVIEN nv;
			if (this.frmNHANSU.rdoNVBC.isSelected()) {
				double Hesoluong = Double.parseDouble(this.frmNHANSU.txtHesoluong.getText());
				nv = new NHANVIENBC(Manv, Hoten, Gioitinh, Ngaysinh, SoCM, Ngayvaocoquan, Hesoluong);
			} else {
				double muluong = Double.parseDouble(this.frmNHANSU.txtMucluong.getText());
				nv = new NHANVIENHOPDONG(Manv, Hoten, Gioitinh, Ngaysinh, SoCM, Ngayvaocoquan, muluong);
			}
			k = this.pdNHANSU.Update(nv);
		} catch (ParseException e) {
		}
		return k;
	}

	public int DeleteStaff() {
		int k = 0;
		try {
			String Manv = this.frmNHANSU.txtMaNV.getText();
			k = this.pdNHANSU.Delete(Manv);
			
		} catch (Exception ex) {
		}
		return k;
	}

	public Hashtable<String, NHANVIEN> getListStaff() {
		return ListStaff;
	}
}//////////////////// calss QUANLYNHANSU_3LAYER/////////////

 
 
