/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.SanPhamInterface;
import java.util.ArrayList;
import model.sanpham.SanPham;
import java.sql.*;
import model.sanpham.ThuongHieu;
import model.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class SanPham_dao implements SanPhamInterface {

    public SanPham_dao() {
    }

    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from SanPham");

            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                double giaNhap = rs.getDouble("giaNhap");
                double giamGia = rs.getDouble("giamGia");
                int loai = rs.getInt("loai");
                double vat = rs.getDouble("VAT");
                ThuongHieu thuongHieu = new ThuongHieu(rs.getString("maThuongHieu"));
                int soThangBaohanh = rs.getInt("soThangBaoHanh");
                String cauHinh = rs.getString("cauHinh");
                SanPham sp = new SanPham(maSP, tenSP, giaNhap, giamGia, loai, vat, thuongHieu, soThangBaohanh, cauHinh);

                result.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<SanPham> getSanPhamTheoMa(String maSanPham) {
        ArrayList<SanPham> result = new ArrayList<>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from SanPham where maSanPham = ?");
            st.setString(1, maSanPham);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                double giaNhap = rs.getDouble("giaNhap");
                double giamGia = rs.getDouble("giamGia");
                int loai = rs.getInt("loai");
                double vat = rs.getDouble("VAT");
                ThuongHieu thuongHieu = new ThuongHieu(rs.getString("maThuongHieu"));
                int soThangBaohanh = rs.getInt("soThangBaoHanh");
                String cauHinh = rs.getString("cauHinh");
                SanPham sp = new SanPham(maSP, tenSP, giaNhap, giamGia, loai, vat, thuongHieu, soThangBaohanh, cauHinh);

                result.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean xoaSanPham(String maSanPham) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from SanPham where maSanPham = ?");
            st.setString(1, maSanPham);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean capNhatSanPham(String maSanPham, SanPham sanPham) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update SanPham"
                    + " set tenSanPham = ?, giaNhap = ?, giamGia = ?, loai = ?, vat = ?, thuongHieu = ?, soThangBaoHanh = ?, cauHinh = ?"
                    + " where maSanPham = ?");
            st.setString(1, sanPham.getTenSP());
            st.setDouble(2, sanPham.getGiaNhap());
            st.setDouble(3, sanPham.getGiamGia());
            st.setInt(4, sanPham.getLoai());
            st.setDouble(5, sanPham.getVAT());
            st.setString(6, sanPham.getThuongHieu().getMaTH());
            st.setInt(7, sanPham.getSoThangBaoHanh());
            st.setString(8, sanPham.getCauHinh());
            st.setString(9, maSanPham);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean themSanPham(SanPham sanPham) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into SanPham"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, sanPham.getMaSP());
            st.setString(2, sanPham.getTenSP());
            st.setDouble(3, sanPham.getGiaNhap());
            st.setDouble(4, sanPham.getGiamGia());
            st.setInt(5, sanPham.getLoai());
            st.setDouble(6, sanPham.getVAT());
            st.setString(7, sanPham.getThuongHieu().getMaTH());
            st.setInt(8, sanPham.getSoThangBaoHanh());
            st.setString(9, sanPham.getCauHinh());
            
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }
}
