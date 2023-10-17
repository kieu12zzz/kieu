package app_quanlynhansu_3layer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
public abstract class NHANVIEN implements INHANVIEN{
	private String Manv;	
	private String Hoten;
	private String Gioitinh;
	private Date Ngaysinh;
	private long SoCM;
	private Date Ngayvaocoquan;	
	
	public String getManv() {
		return Manv;
	}

	public void setManv(String manv) {
		Manv = manv;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String hoten) {
		Hoten = hoten;
	}

	public String getGioitinh() {
		return Gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		Gioitinh = gioitinh;
	}

	public Date getNgaysinh() {
		return Ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		Ngaysinh = ngaysinh;
	}

	public long getSoCM() {
		return SoCM;
	}

	public void setSoCM(long soCM) {
		SoCM = soCM;
	}

	public Date getNgayvaocoquan() {
		return Ngayvaocoquan;
	}

	public void setNgayvaocoquan(Date ngayvaocoquan) {
		Ngayvaocoquan = ngayvaocoquan;
	}
	protected Scanner sc;
	
	
	
	public NHANVIEN() {
		super();
		this.sc=new Scanner(System.in);
	}	

	public NHANVIEN(String manv, String hoten, String gioitinh, Date ngaysinh, long soCM, Date ngayvaocoquan) {
		super();
		Manv = manv;
		Hoten = hoten;
		Gioitinh = gioitinh;
		Ngaysinh = ngaysinh;
		SoCM = soCM;
		Ngayvaocoquan = ngayvaocoquan;
		this.sc=new Scanner(System.in);
	}
	protected int tinhThamnien() {
		Calendar cl_NgayVao=new GregorianCalendar();
		cl_NgayVao.setTime(this.Ngayvaocoquan);
		Calendar cl_Ngayhientai=new GregorianCalendar();
		cl_Ngayhientai.setTime(Calendar.getInstance().getTime());		
		return (cl_Ngayhientai.get(Calendar.YEAR)-cl_NgayVao.get(Calendar.YEAR));		
	}	
	
}



