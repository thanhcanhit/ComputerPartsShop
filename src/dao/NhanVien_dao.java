/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.NhanVienInterface;
import java.util.ArrayList;
import model.connguoi.NhanVien;
import java.sql.*;
import java.time.LocalDate;
import model.share.ConnectDB;
import model.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class NhanVien_dao implements NhanVienInterface{
    public NhanVien_dao(){
        
    }

    @Override
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try{
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from NhanVien");
            while(rs.next()){
                String maNV = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");
                
                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                NhanVien nv = new NhanVien(maNV, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh);
                result.add(nv);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNV) {
       ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from NhanVien where maNhanVien = ?");
            st.setString(1, maNV);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String ma = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");
                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                NhanVien nv = new NhanVien(ma, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh);
                result.add(nv);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean xoaNhanVien(String maNV) {
        int n = 0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from NhanVien where maNhanVien = ?");
            st.setString(1, maNV);
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }

    @Override
    public boolean themNhanVien(NhanVien nhanVien) {
        int n=0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into NhanVien"
                    +"values(?,?,?,?,?,?,?,?)");
            st.setString(1, nhanVien.getMaNV());
            st.setString(2, nhanVien.getHoTen());
            st.setString(3, nhanVien.getSoDT());
            LocalDate namSinh = nhanVien.getNamSinh();
            st.setDate(4, Date.valueOf(namSinh));
            st.setString(5, nhanVien.getEmail());
            st.setString(6, nhanVien.getChucDanh());
            st.setString(7, nhanVien.getDiaChi().getMaDiaChi());
            st.setBoolean(8, nhanVien.isGioiTinh());
           
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }

    @Override
    public boolean capNhatNhanVien(String maNV, NhanVien nhanVien) {
        int n=0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("update NhanVien"
                    +"set hoTen= ?, email=?, chucDanh=?, soDienThoai=?, ngaySinh=?, maDiaChi = ?, gioiTinh=?" 
                    + "where maNhanVien = ?");
            st.setString(1, nhanVien.getHoTen());
            st.setString(2, nhanVien.getEmail());
            st.setString(3, nhanVien.getChucDanh());
            st.setString(4, nhanVien.getSoDT());
            LocalDate namSinh = nhanVien.getNamSinh();
            st.setDate(5, Date.valueOf(namSinh));
            st.setString(6, nhanVien.getDiaChi().getMaDiaChi());
            st.setBoolean(7, nhanVien.isGioiTinh());
            st.setString(8, maNV);
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }

    @Override
    public ArrayList<NhanVien> getdsQuanLy() {
        ArrayList<NhanVien> result = new ArrayList<NhanVien>();
        try{
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from NhanVien where chucDanh = N'Quản Lý'");
            while(rs.next()){
                String maNV = rs.getString("maNhanVien");
                String tenNV = rs.getString("hoTen");
                String soDT = rs.getString("soDienThoai");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String email = rs.getString("email");
                String chucDanh = rs.getString("chucDanh");
                
                DiaChi diaChi = new DiaChi(rs.getString("maDiaChi"));
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                NhanVien nv = new NhanVien(maNV, chucDanh, tenNV, soDT, email, ngaySinh, diaChi, gioiTinh);
                result.add(nv);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    
}
