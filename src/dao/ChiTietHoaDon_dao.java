/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ChiTietHoaDonInterface;
import java.util.ArrayList;
import entity.hoadon.ChiTietHoaDon;
import java.sql.*;
import entity.hoadon.HoaDon;
import entity.sanpham.SanPham;
import entity.share.ConnectDB;

/**
 *
 * @author macbookk
 */
public class ChiTietHoaDon_dao implements ChiTietHoaDonInterface {

    public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<ChiTietHoaDon>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from ChiTietHoaDon where maHoaDon = ?");
            st.setString(1, maHoaDon);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                SanPham sp = new SanPham(maSP);
                String maHD = rs.getString("maHoaDon");
                HoaDon hd = new HoaDon(maHD);
                float giaban = rs.getFloat("giaBan");
                int soluong = rs.getInt("soLuong");
                ChiTietHoaDon cthd = new ChiTietHoaDon(sp, hd, soluong, giaban);
                result.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean xoaChiTietHoaDon(String maHoaDon) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from ChiTietHoaDon where maHoaDon = ?");
            st.setString(1, maHoaDon);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean themChiTietHoaDon(ChiTietHoaDon ct) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into ChiTietHoaDon (maSanPham, maHoaDon, giaBan, soLuong, tongTien) values (?, ?, ?, ?, ?)");
            st.setString(1, ct.getSanPham().getMaSP());
            st.setString(2, ct.getHoaDon().getMaHoaDon());
            st.setDouble(3, ct.getGiaBan());
            st.setInt(4, ct.getSoLuong());
            st.setDouble(5, ct.getSoLuong() * ct.getGiaBan());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

}
