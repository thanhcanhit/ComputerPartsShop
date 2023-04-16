/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ChiTietDonNhapInterface;
import java.util.ArrayList;
import model.kho.ChiTietDonNhap;
import java.sql.*;
import model.kho.DonNhapHang;
import model.sanpham.SanPham;
import model.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class ChiTietDonNhap_dao implements ChiTietDonNhapInterface{

    @Override
    public ArrayList<ChiTietDonNhap> getAll() {
        ArrayList<ChiTietDonNhap> result = new ArrayList<ChiTietDonNhap>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT maSanPham, maDonNhap, soLuongCungCap, tongTien FROM ChiTietDonNhap;");
            while (rs.next()) {
                String maDonNhap = rs.getString("maDonNhap");
                String maSanPham = rs.getString("maSanPham");
                int sl = rs.getInt("soLuongTon");
                double tongTien = rs.getDouble("tongTien");
                result.add(new ChiTietDonNhap(new SanPham(maSanPham), new DonNhapHang(maDonNhap), sl, tongTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietDonNhap> getAllChiTietCuaDonNhap(String maDonNhap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean themChiTietDonNhap(ChiTietDonNhap chiTietDN) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean capNhatChiTietDonNhap(String maSanPham, String madonNhap, ChiTietDonNhap chiTietDN) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
