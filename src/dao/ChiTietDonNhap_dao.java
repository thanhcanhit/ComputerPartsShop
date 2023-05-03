/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ChiTietDonNhapInterface;
import java.util.ArrayList;
import entity.kho.ChiTietDonNhap;
import java.sql.*;
import entity.kho.DonNhapHang;
import entity.sanpham.SanPham;
import entity.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class ChiTietDonNhap_dao implements ChiTietDonNhapInterface {

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
    public ArrayList<ChiTietDonNhap> getAllChiTietCuaDonNhap(String maDon) {
        ArrayList<ChiTietDonNhap> result = new ArrayList<ChiTietDonNhap>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select maSanPham, maDonNhap, soLuongCungCap, tongTien FROM ChiTietDonNhap where maDonNhap = ?;");
            st.setString(1, maDon);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDonNhap = rs.getString("maDonNhap");
                String maSanPham = rs.getString("maSanPham");
                int sl = rs.getInt("soLuongCungCap");
                double tongTien = rs.getDouble("tongTien");
                result.add(new ChiTietDonNhap(new SanPham(maSanPham), new DonNhapHang(maDonNhap), sl, tongTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean themChiTietDonNhap(ChiTietDonNhap chiTietDN) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO ChiTietDonNhap(maSanPham, maDonNhap, soLuongCungCap, tongTien) VALUES (?, ?, ?, ?);");
            int i = 1;
            st.setString(i++, chiTietDN.getSanPham().getMaSP());
            st.setString(i++, chiTietDN.getDonNhap().getMaDonNhap());
            st.setInt(i++, chiTietDN.getSoLuong());
            st.setDouble(i++, chiTietDN.getTongTien());

            n = st.executeUpdate();
        } catch (Exception e) {

        }

        return n > 0;
    }
}
