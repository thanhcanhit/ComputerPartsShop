/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.NhanVienInterface;
import java.util.ArrayList;
import entity.connguoi.NhanVien;
import java.sql.*;
import java.time.LocalDate;
import entity.connguoi.TaiKhoan;
import entity.share.ConnectDB;
import entity.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class NhanVien_dao implements NhanVienInterface {

    public NhanVien_dao() {

    }

    @Override
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from NhanVien");
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");

                DiaChi_dao dao = new DiaChi_dao();
                DiaChi diaChi = dao.getDiaChiTheoMa(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                boolean trangThai = rs.getBoolean("trangThai");
                NhanVien nv = new NhanVien(maNV, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh,trangThai);
                result.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public String getMaDiaChi(String maNV){
        String maDC = null;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from NhanVien where maNhanVien = ?");
            st.setString(1, maNV);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                maDC = rs.getString("maDiaChi");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maDC;
    }

    @Override
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNV) {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from NhanVien where maNhanVien = ?");
            st.setString(1, maNV);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");
                  DiaChi_dao dao = new DiaChi_dao();
                DiaChi diaChi = dao.getDiaChiTheoMa(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                boolean trangThai = rs.getBoolean("trangThai");
                NhanVien nv = new NhanVien(ma, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh,trangThai);
                result.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean xoaNhanVien(String maNV) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from NhanVien where maNhanVien = ?");
            st.setString(1, maNV);
            TaiKhoan_dao dao = new TaiKhoan_dao();
            dao.xoaTaiKhoan(maNV);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean themNhanVien(NhanVien nhanVien) {
        int n = 0;
        try {
            DiaChi_dao dao = new DiaChi_dao();
            TaiKhoan_dao TK_dao = new TaiKhoan_dao();
            dao.themDiaChi(nhanVien.getDiaChi());
           
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into NhanVien "
                    + " values(?,?,?,?,?,?,?,?,?)");
            st.setString(1, nhanVien.getMaNV());
            st.setString(2, nhanVien.getHoTen());
            st.setString(3, nhanVien.getSoDT());
            LocalDate namSinh = nhanVien.getNamSinh();
            st.setDate(4, Date.valueOf(namSinh));
            st.setString(5, nhanVien.getEmail());
            st.setString(6, nhanVien.getChucDanh());
            st.setString(7, nhanVien.getDiaChi().getMaDiaChi());
            st.setBoolean(8, nhanVien.isGioiTinh()); 
            st.setBoolean(9, nhanVien.isTrangThai());
            n = st.executeUpdate();
            TK_dao.themTaiKhoan(new TaiKhoan(nhanVien.getMaNV(),"1111"));
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean capNhatNhanVien(String maNV, NhanVien nhanVien) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update NhanVien "
                    +"set hoTen= ?, email=?, chucDanh=?, soDienThoai=?, ngaySinh=?, gioiTinh=?, trangThai=? " 
                    + " where maNhanVien = ?");
            st.setString(1, nhanVien.getHoTen());
            st.setString(2, nhanVien.getEmail());
            st.setString(3, nhanVien.getChucDanh());
            st.setString(4, nhanVien.getSoDT());
            LocalDate namSinh = nhanVien.getNamSinh();
            st.setDate(5, Date.valueOf(namSinh));
            st.setBoolean(6, nhanVien.isGioiTinh());
            st.setBoolean(7, nhanVien.isTrangThai());
            st.setString(8, maNV);
            
            DiaChi_dao dao = new DiaChi_dao();
            dao.capNhatDiaChi(nhanVien.getDiaChi().getMaDiaChi(), nhanVien.getDiaChi());
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }
    public boolean capNhatTrangThaiNhanVien(String maNV, boolean trangThai) {
        int n=0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("update NhanVien "
                    +"set trangThai=? " 
                    + " where maNhanVien = ?");
            st.setBoolean(1, trangThai);
            st.setString(2, maNV);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public ArrayList<NhanVien> getdsQuanLy() {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from NhanVien where chucDanh = N'Quản Lý'");
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");

                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                boolean trangThai = rs.getBoolean("trangThai");
                NhanVien nv = new NhanVien(maNV, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh,trangThai);
                result.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getMaLonNhat() {
        String s = "NV0001";

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maNhanVien from NhanVien order by maNhanVien desc");
            rs.next();
            s = rs.getString("maNhanVien");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

}
