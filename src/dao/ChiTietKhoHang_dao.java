/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ChiTietKhoHangInterface;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import entity.kho.ChiTietKhoHang;
import entity.kho.KhoHang;
import entity.sanpham.SanPham;
import entity.share.ConnectDB;
import java.sql.*;

/**
 *
 * @author thanh
 */
public class ChiTietKhoHang_dao implements ChiTietKhoHangInterface {

    @Override
    public ArrayList<ChiTietKhoHang> getAll() {
        ArrayList<ChiTietKhoHang> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT maKho, maSanPham, soLuongTon FROM ChiTietKhoHang");
            while (rs.next()) {
                String maKho = rs.getString("maKho");
                String maSP = rs.getString("maSanPham");
                int sl = rs.getInt("soLuongTon");
                result.add(new ChiTietKhoHang(new SanPham(maSP), new KhoHang(maKho), sl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietKhoHang> getAllChiTietCuaKhoHang(String maKhoHang) {
        ArrayList<ChiTietKhoHang> result = new ArrayList<ChiTietKhoHang>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT maKho, maSanPham, soLuongTon FROM ChiTietKhoHang where maKho = ?");
            st.setString(1, maKhoHang);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maKho = rs.getString("maKho");
                String maSP = rs.getString("maSanPham");
                int sl = rs.getInt("soLuongTon");
                result.add(new ChiTietKhoHang(new SanPham(maSP), new KhoHang(maKho), sl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean themChiTietKhoHang(ChiTietKhoHang chiTietKH) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO ChiTietKhoHang(maKho, maSanPham, soLuongTon) VALUES (?, ?, ?);");
            st.setString(1, chiTietKH.getKhoHang().getMaKho());
            st.setString(2, chiTietKH.getSanPham().getMaSP());
            st.setInt(3, chiTietKH.getSoLuong());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean capNhatChiTietKhoHang(String maKho, String maSanPham, ChiTietKhoHang ct) {

        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE ChiTietKhoHang SET soLuongTon = ? WHERE maKho = ? AND maSanPham = ?;");
            st.setInt(1, ct.getSoLuong());
            st.setString(2, maKho);
            st.setString(3, maSanPham);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;

    }

    @Override
    public int getSoLuongTon(String maKhoHang, String maSanPham) {
        int result = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT soLuongTon FROM ChiTietKhoHang where maKho = ? and maSanPham = ?");
            st.setString(1, maKhoHang);
            st.setString(2, maSanPham);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int sl = rs.getInt("soLuongTon");
                result = sl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    @Override
    public boolean truSoLuongChiTietKhoHang(String maKho, String maSanPham, int soLuong) {
        int n = 0;

        try {
            int soLuongMoi = getSoLuongTon(maKho, maSanPham) - soLuong;

            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE ChiTietKhoHang SET soLuongTon = ? WHERE maKho = ? AND maSanPham = ?;");
            st.setInt(1, soLuongMoi);
            st.setString(2, maKho);
            st.setString(3, maSanPham);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean congSoLuongChiTietKhoHang(String maKho, String maSanPham, int soLuong) {
        int n = 0;

        try {
            int soLuongMoi = getSoLuongTon(maKho, maSanPham) + soLuong;

            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE ChiTietKhoHang SET soLuongTon = ? WHERE maKho = ? AND maSanPham = ?;");
            st.setInt(1, soLuongMoi);
            st.setString(2, maKho);
            st.setString(3, maSanPham);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }
}
