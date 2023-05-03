/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.hoadon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import entity.connguoi.KhachHang;
import entity.connguoi.NhanVien;

/**
 *
 * @author nxnam
 */
public class HoaDon {

    private String maHoaDon;
    private LocalDate ngayLap;
    private String phuongThucThanhToan;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private ArrayList<ChiTietHoaDon> dsChiTiethoaDon;
    private double tongTien;

    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon(String maHoaDon, LocalDate ngayLap, String phuongThucThanhToan, NhanVien nhanVien, KhachHang khachHang, ArrayList<ChiTietHoaDon> dsChiTiethoaDon, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.dsChiTiethoaDon = dsChiTiethoaDon;
        this.tongTien = tongTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ArrayList<ChiTietHoaDon> getDsChiTiethoaDon() {
        return dsChiTiethoaDon;
    }

    public void setDsChiTiethoaDon(ArrayList<ChiTietHoaDon> dsChiTiethoaDon) {
        this.dsChiTiethoaDon = dsChiTiethoaDon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maHoaDon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHoaDon, other.maHoaDon);
    }

    public double tinhTongTienThanhToan() {
        double tong = 0;
        for (ChiTietHoaDon cthd : dsChiTiethoaDon) {
            tong += cthd.tinhTongTien();
        }
        return tong;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
