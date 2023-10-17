package app_quanlynhansu_3layer;

import java.util.Date;

import app_quanlynhansu_3layer.NHANVIEN;

public class NHANVIENHOPDONG extends NHANVIEN {
	
	private double mucluong;	

    public double getMucluong() {
        return mucluong;
    }

	public NHANVIENHOPDONG() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NHANVIENHOPDONG(String manv, String hoten, String gioitinh, Date ngaysinh, long soCM, Date ngayvaocoquan,
			double mucluong) {
		super(manv, hoten, gioitinh, ngaysinh, soCM, ngayvaocoquan);
		this.mucluong = mucluong;
	}
	
	
	@Override
	public double tinhPhuCap() {
		 if(super.tinhThamnien()>2)
			 return (this.mucluong * 0.1 + 100000);
		 else 
			 return (this.mucluong * 0.1 + 100000);		
	}
	@Override
	public double tinhThucLinh() {
		return (this.mucluong+Luongcoban+this.tinhPhuCap());
	}
	
}
