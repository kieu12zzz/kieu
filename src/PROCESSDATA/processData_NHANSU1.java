package PROCESSDATA;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import app_quanlynhansu_3layer.*;


/**
 *
 * @author AD
 */
public class processData_NHANSU1 {

    private String sqlFind, sqlInsert, sqlUpdate,
            sqlDelete, sqlSelect;
    private SQLSEVERDataAccess conn;

    public processData_NHANSU1() {
        conn = new SQLSEVERDataAccess();
        sqlFind = " select * from tbNHANVIEN  where IDMANHANVIEN=?";
        sqlInsert = "INSERT INTO tbNHANVIEN(HOTEN,NGAYSINH,"
                + "GIOITINH,SOCHUNGMINH,NGAYVAOCOQUAN,HESOLUONG,MUCLUONG,IDLOAINHANVIEN) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        sqlUpdate = "Update tbNHANVIEN set HOTEN=?, NGAYSINH=?,"
                + "GIOITINH=?,SOCHUNGMINH=?,NGAYVAOCOQUAN=?,IDLOAINHANVIEN=?,";
        sqlDelete = " delete from tbNHANVIEN where IDMANHANVIEN=?";
        sqlSelect = " select * from tbNHANVIEN";
    }

    public int Insert(NHANVIEN nv) {

        int k = 0;
        try {
            int loainhanvien;
            if (nv instanceof NHANVIENBC) {
                loainhanvien = 1;
                k = conn.ExecuteSQL(sqlInsert, new Object[]{nv.getHoten(), nv.getNgaysinh(), nv.getGioitinh(), nv.getSoCM(), nv.getNgayvaocoquan(), ((NHANVIENBC) nv).getHesoluong(), null, loainhanvien});
            } else {
                loainhanvien = 2;
                k = conn.ExecuteSQL(sqlInsert, new Object[]{nv.getHoten(), nv.getNgaysinh(), nv.getGioitinh(), nv.getSoCM(), nv.getNgayvaocoquan(), null, ((NHANVIENHOPDONG) nv).getMucluong(), loainhanvien});
            }
        } catch (Exception ex) {
        }
        return k;
    }

    public int Delete(String manv) {
        
        int k = 0;
        try{
        k = conn.Execute_StoredProcedures(" DeleteStaff(?)", new Object[]{manv});
        }catch(Exception ex){}
        return k;
    }

    public int Update(NHANVIEN nv) {
        int k = 0;
        try {
            int loainhanvien;
            if (nv instanceof NHANVIENBC) {
                loainhanvien = 1;
                sqlUpdate += " HESOLUONG=? where IDMANHANVIEN=?";
                k = conn.ExecuteSQL(sqlUpdate, new Object[]{nv.getHoten(), nv.getNgaysinh(), nv.getGioitinh(), nv.getSoCM(), nv.getNgayvaocoquan(), loainhanvien, ((NHANVIENBC) nv).getHesoluong(), nv.getManv()});
            } else {
                loainhanvien = 2;
                sqlUpdate += " MUCLUONG=? where IDLOAINHANVIEN=?";
                k = conn.ExecuteSQL(sqlUpdate, new Object[]{nv.getHoten(), nv.getNgaysinh(), nv.getGioitinh(), nv.getSoCM(), nv.getNgayvaocoquan(), loainhanvien, ((NHANVIENHOPDONG) nv).getMucluong(), nv.getManv()});
            }
        } catch (Exception ex) {
        }
        return k;
    }

    public Hashtable<String, NHANVIEN> List() {
        Hashtable<String, NHANVIEN> ListStaff = new Hashtable<String, NHANVIEN>();
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            ResultSet rs = conn.getResultSet(sqlSelect);
            while (rs.next()) {
                String Manv = rs.getString(1);
                String Hoten = rs.getString(2);
                Date ngaysinh = rs.getDate(3);
                String Gioitinh = rs.getString(4);
                long SCM = Long.parseLong(rs.getString(5));
                Date ngayvaocoquang = rs.getDate(6);
                int loainhanvien = Integer.parseInt(rs.getString(7));
                NHANVIEN nv;
                if (loainhanvien == 1) {
                    double HESOLUONG = Double.parseDouble(rs.getString(8));
                    nv = new NHANVIENBC(Manv, Hoten, Gioitinh, ngaysinh, SCM, ngayvaocoquang, HESOLUONG);
                } else {
                    double MUCLUONG = Double.parseDouble(rs.getString(9));
                    nv = new NHANVIENHOPDONG(Manv, Hoten, Gioitinh, ngaysinh, SCM, ngayvaocoquang, MUCLUONG);
                }
                ListStaff.put(Manv, nv);
            }
            return ListStaff;
        } catch (Exception EX) {
        }
        return null;
    }

    public NHANVIEN Find(String manv) {
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            ResultSet rs = conn.getResultSet(sqlFind, new Object[]{manv});
            rs.next();
            String Manv = rs.getString(1);
            String Hoten = rs.getString(2);
            Date ngaysinh = rs.getDate(3);
            String Gioitinh = rs.getString(4);
            long SCM = Long.parseLong(rs.getString(5));
            Date ngayvaocoquang = rs.getDate(6);
            int loainhanvien = Integer.parseInt(rs.getString(7));
            NHANVIEN nv;
            if (loainhanvien == 1) {
                double HESOLUONG = rs.getDouble(8);
                nv = new NHANVIENBC(Manv, Hoten, Gioitinh, ngaysinh, SCM, ngayvaocoquang, HESOLUONG);
            } else {
                double MUCLUONG =rs.getDouble(9);
                nv = new NHANVIENHOPDONG(Manv, Hoten, Gioitinh, ngaysinh, SCM, ngayvaocoquang, MUCLUONG);
            }
            return nv;

        } catch (Exception ex) {
            String s = ex.getMessage();
        }
        return null;
    }
}
