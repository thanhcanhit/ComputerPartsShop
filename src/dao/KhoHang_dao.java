/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.KhoHangInterface;
import java.util.ArrayList;
import entity.kho.KhoHang;
import java.sql.*;
import entity.kho.ChiTietKhoHang;
import entity.share.ConnectDB;
import entity.share.DiaChi;

/**
 *
 * @author thanh
 */
public class KhoHang_dao implements KhoHangInterface {
    
    @Override
    public ArrayList<KhoHang> getAllKhoHang() {
        ArrayList<KhoHang> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from KhoHang");
            
            while (rs.next()) {
                String ma = rs.getString("maKho");
                String ten = rs.getString("tenKho");
                double dienTich = rs.getDouble("dienTich");
                String loai = rs.getString("maDiaChi");

                // Chi tiet kho hang
                ArrayList<ChiTietKhoHang> list = new ChiTietKhoHang_dao().getAllChiTietCuaKhoHang(ma);
                
                KhoHang khoHang = new KhoHang(ma, new DiaChi(loai), ten, dienTich, list);
                
                result.add(khoHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public ArrayList<KhoHang> getKhoHangTheoMa(String maKho) {
        ArrayList<KhoHang> result = new ArrayList<>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from KhoHang where maKho = ?");
            st.setString(1, maKho);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String ma = rs.getString("maKho");
                String ten = rs.getString("tenKho");
                double dienTich = rs.getDouble("dienTich");
                String loai = rs.getString("maDiaChi");

                // Chi tiet kho hang
                ArrayList<ChiTietKhoHang> list = new ChiTietKhoHang_dao().getAllChiTietCuaKhoHang(ma);
                
                KhoHang khoHang = new KhoHang(ma, new DiaChi(loai), ten, dienTich, list);
                
                result.add(khoHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public int getSoLuongTon(String maKho, String maSanPham) {
        return new ChiTietKhoHang_dao().getSoLuongTon(maKho, maSanPham);
    }
    
    public String getMaLonNhat() {
        String s = "KHO00";
        
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maKho from KhoHang order by maKho desc");
            rs.next();
            s = rs.getString("maKho");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return s;
    }
}
