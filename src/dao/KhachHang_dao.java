/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.KhachHangInterface;
import java.util.ArrayList;
import entity.connguoi.KhachHang;
import entity.share.ConnectDB;
import java.sql.*;
import java.time.LocalDate;
import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class KhachHang_dao implements KhachHangInterface {

    @Override
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from KhachHang");
            while (rs.next()) {
                String maKH = rs.getString("maKhachHang");
                String tenKH = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String maSoThue = rs.getString("maSoThue");
                int diem = rs.getInt("diemThanhVien");
                DiaChi_dao dao = new DiaChi_dao();
                DiaChi diaChi = dao.getDiaChiTheoMa(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");

                KhachHang kh = new KhachHang(maKH, maSoThue, tenKH, soDT, email, ngaySinh, diaChi, gioiTinh, diem);
                result.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getMaDiaChi(String maKH) {
        String maDC = null;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from KhachHang where maKhachHang = ?");
            st.setString(1, maKH);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                maDC = rs.getString("maDiaChi");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maDC;
    }

    @Override
    public ArrayList<KhachHang> getKhachHangTheoSoDT(String soDT) {
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from KhachHang where soDienThoai = ?");
            st.setString(1, soDT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maKhachHang");
                String tenKH = rs.getString("hoTen");

                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String maSoThue = rs.getString("maSoThue");
                int diem = rs.getInt("diemThanhVien");
                DiaChi_dao dao = new DiaChi_dao();
                DiaChi diaChi = dao.getDiaChiTheoMa(rs.getString("maDiaChi"));
                
                boolean gioiTinh = rs.getBoolean("gioiTinh");

                KhachHang kh = new KhachHang(ma, maSoThue, tenKH, soDT, email, ngaySinh, diaChi, gioiTinh, diem);
                result.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean xoaKhachHang(String maKH) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from KhachHang where maKhachHang = ?");
            st.setString(1, maKH);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean themKhachHang(KhachHang khachHang) {
        int n = 0;
        try {
            DiaChi_dao DC_dao = new DiaChi_dao();
            
            DC_dao.themDiaChi(khachHang.getDiaChi());
            

            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into KhachHang values(?,?,?,?,?,?,?,?,?)");
            st.setString(1, khachHang.getMaKH());
            st.setString(2, khachHang.getHoTen());
            st.setString(3, khachHang.getSoDT());
            LocalDate namSinh = khachHang.getNamSinh();
            st.setDate(4, Date.valueOf(namSinh));
            st.setString(5, khachHang.getEmail());
            st.setString(6, khachHang.getMaSoThue());
            st.setString(7, khachHang.getDiaChi().getMaDiaChi());
            
            System.out.println(khachHang.getDiaChi().getMaDiaChi());
            
            st.setInt(8, khachHang.getDiemThanhVien());
            st.setBoolean(9, khachHang.isGioiTinh());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean capNhatKhachHang(String maKH, KhachHang khachHang) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update KhachHang "
                    + "set hoTen = ?, email=?, soDienThoai=?, ngaySinh=?,maSoThue=?, diemThanhVien=?, gioiTinh=? "
                    + "where maKhachHang= ?");
            st.setString(1, khachHang.getHoTen());
            st.setString(2, khachHang.getEmail());
            st.setString(3, khachHang.getSoDT());
            LocalDate namSinh = khachHang.getNamSinh();
            st.setDate(4, Date.valueOf(namSinh));
            st.setString(5, khachHang.getMaSoThue());

            st.setInt(6, khachHang.getDiemThanhVien());
            st.setBoolean(7, khachHang.isGioiTinh());
            st.setString(8, maKH);
            DiaChi_dao dao = new DiaChi_dao();
            dao.capNhatDiaChi(khachHang.getDiaChi().getMaDiaChi(), khachHang.getDiaChi());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public KhachHang getKhachHangTheoSDT(String sdt) {
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from KhachHang where soDienThoai = ?");
            st.setString(1, sdt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maKhachHang");
                String tenKH = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String maSoThue = rs.getString("maSoThue");
                int diem = rs.getInt("diemThanhVien");
                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");

                KhachHang kh = new KhachHang(ma, maSoThue, tenKH, soDT, email, ngaySinh, diaChi, gioiTinh, diem);
                result.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        KhachHang i = null;
        try {
            i = result.get(0);
        } catch (Exception e) {

        }
        return i;
    }

    public String getMaLonNhat() {
        String s = "KH00000";

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maKhachHang from KhachHang order by maKhachHang desc");
            rs.next();
            s = rs.getString("maKhachHang");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    @Override
    public boolean congDiemKhachHang(String maKH, int diemThem) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update KhachHang set diemThanhVien = diemThanhVien + ? where maKhachHang= ?");
            st.setInt(1, diemThem);
            st.setString(2, maKH);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }


    @Override
    public ArrayList<KhachHang> getKhachHangTheoMa(String maKH) {
        ArrayList<KhachHang> result = new ArrayList<KhachHang>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from KhachHang where maKhachHang = ?");
            st.setString(1, maKH);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maKhachHang");
                String tenKH = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String maSoThue = rs.getString("maSoThue");
                int diem = rs.getInt("diemThanhVien");
                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");

                KhachHang kh = new KhachHang(maKH, maSoThue, tenKH, soDT, email, ngaySinh, diaChi, gioiTinh, diem);
                result.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

