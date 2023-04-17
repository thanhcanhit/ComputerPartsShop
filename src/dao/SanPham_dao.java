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

    @Override
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
                int loai = rs.getInt("maLoai");
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
                int loai = rs.getInt("maLoai");
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
            int i = 1;
            st.setString(i++, sanPham.getTenSP());
            st.setDouble(i++, sanPham.getGiaNhap());
            st.setDouble(i++, sanPham.getGiamGia());
            st.setInt(i++, sanPham.getLoai());
            st.setDouble(i++, sanPham.getVAT());
            st.setString(i++, sanPham.getThuongHieu().getMaTH());
            st.setInt(i++, sanPham.getSoThangBaoHanh());
            st.setString(i++, sanPham.getCauHinh());
            st.setString(i++, maSanPham);
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
            int i = 1;
            st.setString(i++, sanPham.getMaSP());
            st.setString(i++, sanPham.getTenSP());
            st.setDouble(i++, sanPham.getGiaNhap());
            st.setDouble(i++, sanPham.getGiamGia());
            st.setInt(i++, sanPham.getLoai());
            st.setDouble(i++, sanPham.getVAT());
            st.setString(i++, sanPham.getThuongHieu().getMaTH());
            st.setInt(i++, sanPham.getSoThangBaoHanh());
            st.setString(i++, sanPham.getCauHinh());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public static void main(String[] args) {
        try {
            ConnectDB.connect();
            System.out.println("compleet");

//            ChiTietDonNhap cc = new ChiTietDonNhap(new SanPham("SP0009"), new DonNhapHang("DNH0001"), 0);
//            System.out.println(new ChiTietDonNhap_dao()));
            ArrayList<SanPham> list = new SanPham_dao().getSanPhamTheoMa("SP0177");

            for (SanPham i : list) {
                System.out.println(i.toString());
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}
