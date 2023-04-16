/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.hoadon.ChiTietHoaDon;
import java.sql.*;
import model.hoadon.HoaDon;
import model.sanpham.SanPham;
import model.share.ConnectDB;

/**
 *
 * @author macbookk
 */
public class ChiTietHoaDon_dao {
    public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon){
        ArrayList<ChiTietHoaDon> result = new ArrayList<ChiTietHoaDon>();
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from ChiTietHoaDon where maHoaDon = ?");
            st.setString(1, maHoaDon);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String maSP = rs.getString("maSanPham");
                SanPham sp = new SanPham(maSP);
                String maHD = rs.getString("maHoaDon");
                HoaDon hd = new HoaDon(maHD);
                float giaban = rs.getFloat("giaBan");
                int soluong = rs.getInt("soLuong");
                ChiTietHoaDon cthd = new ChiTietHoaDon(sp, hd, soluong, giaban);
                result.add(cthd);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean xoaDiaChi(String maHoaDon) {
        int n = 0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from DiaChi where maHoaDon = ?");
            st.setString(1, maHoaDon);
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }
}
