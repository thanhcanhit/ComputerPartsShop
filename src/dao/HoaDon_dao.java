/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.KhachHang_bus;
import interface_dao.HoaDonInterface;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import model.connguoi.KhachHang;
import model.connguoi.NhanVien;
import model.hoadon.HoaDon;
import model.share.ConnectDB;
import java.sql.*;
import model.hoadon.ChiTietHoaDon;

/**
 *
 * @author macbookk
 */
public class HoaDon_dao implements HoaDonInterface {

    @Override
    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from HoaDon");
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String maNV = rs.getString("maNhanVien");
                String maKH = rs.getString("maKhachHang");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                String pttt = rs.getString("phuongThucThanhToan");
                double tongTien = rs.getDouble("tongTien");
                HoaDon hd = new HoaDon(maHD, ngayLap, pttt, new NhanVien(maNV), new KhachHang(maKH), new ChiTietHoaDon_dao().getChiTietHoaDonTheoMaHoaDon(maHD), tongTien);
                result.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonTheoMa(String maHoaDon) {
        ArrayList<HoaDon> result = new ArrayList<HoaDon>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("Select * from HoaDon where maHoaDon = ?");
            st.setString(1, maHoaDon);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String maNV = rs.getString("maNhanVien");
                String maKH = rs.getString("maKhachHang");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                String pttt = rs.getString("phuongThucThanhToan");
                double tongTien = rs.getDouble("tongTien");
                HoaDon hd = new HoaDon(maHD, ngayLap, pttt, new NhanVien(maNV), new KhachHang(maKH), new ChiTietHoaDon_dao().getChiTietHoaDonTheoMaHoaDon(maHD), tongTien);
                result.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean themHoaDon(HoaDon hoaDon) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into HoaDon "
                    + "values(?,?,?,?,?,?)");
            st.setString(1, hoaDon.getMaHoaDon());
            st.setDate(2, Date.valueOf(hoaDon.getNgayLap()));
            st.setString(3, hoaDon.getPhuongThucThanhToan());
            st.setString(4, hoaDon.getNhanVien().getMaNV());
            st.setString(5, hoaDon.getKhachHang().getMaKH());
            st.setDouble(6, hoaDon.getTongTien());

            n = st.executeUpdate();
            for (ChiTietHoaDon ct : hoaDon.getDsChiTiethoaDon()) {
                ct.setHoaDon(hoaDon);
                new ChiTietHoaDon_dao().themChiTietHoaDon(ct);
                new ChiTietKhoHang_dao().truSoLuongChiTietKhoHang("KHO01", ct.getSanPham().getMaSP(), ct.getSoLuong());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean xoaHoaDon(String maHoaDon) {
        int n = 0;
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from HoaDon where maHoaDon = ?");
            st.setString(1, maHoaDon);
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    @Override
    public boolean capNhatHoaDon(String maHoaDon, HoaDon hoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaLonNhat() {
        String s = "HD00000";

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maHoaDon from HoaDon order by maHoaDon desc");
            rs.next();
            s = rs.getString("maHoaDon");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    @Override
    public ArrayList<HoaDon> getHoaDonTheoDieuKien(String manv, String sdt, String giaTu, String giaDen, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        ArrayList<HoaDon> list = new ArrayList<>();
        ArrayList<HoaDon> xoa = new ArrayList<>();
        list = getAllHoaDon();
        KhachHang_bus khachHang_bus = new KhachHang_bus();
        if (sdt.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
                KhachHang kh = khachHang_bus.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKH()).get(0);
                if (!kh.getSoDT().equals(sdt)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (manv.trim().length() > 0) {

            for (HoaDon hoaDon : list) {
                if (!hoaDon.getNhanVien().getMaNV().equals(manv)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (giaTu.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                if (hoaDon.getTongTien() < Double.parseDouble(giaTu)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (giaDen.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                if (hoaDon.getTongTien() > Double.parseDouble(giaDen)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
       
        for (HoaDon hoaDon : list) {
            HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
            if (hd.getNgayLap().isBefore(ngayBatDau)) {
                xoa.add(hoaDon);
            }
        }
        list.removeAll(xoa);
        xoa.clear();
        for (HoaDon hoaDon : list) {
            HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
            if (hd.getNgayLap().isAfter(ngayKetThuc)) {
                xoa.add(hoaDon);
            }
        }
        list.removeAll(xoa);
        return list;
    }

}
