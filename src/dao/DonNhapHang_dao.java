/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.DonNhapHangInterface;
import java.util.ArrayList;
import entity.kho.DonNhapHang;
import java.sql.*;
import java.time.LocalDate;
import entity.connguoi.NhaCungCap;
import entity.connguoi.NhanVien;
import entity.kho.ChiTietDonNhap;
import entity.kho.KhoHang;
import entity.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class DonNhapHang_dao implements DonNhapHangInterface {

    @Override
    public ArrayList<DonNhapHang> getAllDonNhapHang() {
        ArrayList<DonNhapHang> result = new ArrayList<>();

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT maDonNhap, ngayNhap, maKho, maNhaCungCap, ghiChu, tongTien, daNhan, maNhanVien FROM DonNhapHang;");

            while (rs.next()) {
                String maDonNhap = rs.getString("maDonNhap");
                String maNhanVien = rs.getString("maNhanVien");
                LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();
                String maKho = rs.getString("maKho");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String ghiChu = rs.getString("ghiChu");
                double tongTien = rs.getDouble("tongTien");
                boolean daNhan = rs.getBoolean("daNhan");

                ArrayList<ChiTietDonNhap> list = new ChiTietDonNhap_dao().getAllChiTietCuaDonNhap(maDonNhap);

                result.add(new DonNhapHang(maDonNhap, ngayNhap, ghiChu, daNhan, new KhoHang(maKho), list, new NhaCungCap(maNhaCungCap), new NhanVien(maNhanVien), tongTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<DonNhapHang> getDonNhapHangTheoMa(String maDon) {
        ArrayList<DonNhapHang> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("SELECT maDonNhap, ngayNhap, maKho, maNhaCungCap, ghiChu, tongTien, daNhan, maNhanVien FROM DonNhapHang where maDonNhap = ?");
            st.setString(1, maDon);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                String maDonNhap = rs.getString("maDonNhap");
                String maNhanVien = rs.getString("maNhanVien");
                LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();
                String maKho = rs.getString("maKho");
                String maNhaCungCap = rs.getString("maNhaCungCap");
                String ghiChu = rs.getString("ghiChu");
                double tongTien = rs.getDouble("tongTien");
                boolean daNhan = rs.getBoolean("daNhan");

                ArrayList<ChiTietDonNhap> list = new ChiTietDonNhap_dao().getAllChiTietCuaDonNhap(maDonNhap);

                result.add(new DonNhapHang(maDonNhap, ngayNhap, ghiChu, daNhan, new KhoHang(maKho), list, new NhaCungCap(maNhaCungCap), new NhanVien(maNhanVien), tongTien));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean xoaDonNhapHang(String maDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean themDonNhapHang(DonNhapHang donNhap) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into DonNhapHang (maDonNhap, ngayNhap, maKho, maNhaCungCap, ghiChu, tongTien, daNhan, maNhanVien) values (?, ?, ?, ?, ?, ?, ? ,?)");
            st.setString(1, donNhap.getMaDonNhap());
            st.setDate(2, Date.valueOf(donNhap.getNgayNhap()));
            st.setString(3, "KHO01");
            st.setString(4, donNhap.getNhaCungCap().getMaNCC());
            st.setString(5, donNhap.getGhiChu());
            st.setDouble(6, donNhap.getTongTien());
            st.setBoolean(7, donNhap.isDanhan());
            st.setString(8, donNhap.getNhanVien().getMaNV());
            n = st.executeUpdate();

            for (ChiTietDonNhap ct : donNhap.getChiTietDonNhap()) {
                ct.setDonNhap(donNhap);
                new ChiTietDonNhap_dao().themChiTietDonNhap(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean capNhatDonNhapHang(String maDon, DonNhapHang donNhap) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaLonNhat() {
        String s = "DNH0001";

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maDonNhap from DonNhapHang order by maDonNhap desc");
            rs.next();
            s = rs.getString("maDonNhap");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    @Override
    public boolean capNhatGiaoDonThanhCong(String maDon) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("update DonNhapHang set daNhan = 1 where maDonNhap = ?");
            st.setString(1, maDon);

//        Cập nhật số lượng vào kho
            DonNhapHang donNhap = getDonNhapHangTheoMa(maDon).get(0);
            for (ChiTietDonNhap ct : donNhap.getChiTietDonNhap()) {
                new ChiTietKhoHang_dao().congSoLuongChiTietKhoHang("KHO01", ct.getSanPham().getMaSP(), ct.getSoLuong());
            }

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

}
