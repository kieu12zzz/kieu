package BUSINESSLOGIC;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import app_quanlynhansu_3layer.NHANVIEN;



class ModelData extends AbstractTableModel {

    ArrayList<NHANVIEN> data = new ArrayList<NHANVIEN>();
    String colNames[] = {"Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh"};
    Class<?> colClasses[] = {String.class, String.class, String.class, Date.class};

    ModelData(Hashtable<String, NHANVIEN> list) {
    	try {
    		data.addAll(list.values());
    	}catch (Exception ex){
    		
    	}
    }

    public int getRowCount() {

        return data.size();
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return data.get(rowIndex).getManv();
        }
        if (columnIndex == 1) {
            return data.get(rowIndex).getHoten();
        }
        if (columnIndex == 2) {
            return data.get(rowIndex).getGioitinh();
        }
        if (columnIndex == 3) {
            return data.get(rowIndex).getNgaysinh();
        }
        return null;
    }

    public String getColumnName(int columnIndex) {
        return colNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
