/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.hoadon;


import java.util.Objects;
import entity.sanpham.SanPham;

/**
 *
 * @author nxnam
 */
public class ChiTietHoaDon {

    private SanPham sanPham;
    private HoaDon hoaDon;
    private int soLuong;
    private double giaBan;

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong, double giaBan) {
        this.sanPham = sanPham;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public double tinhTongTien() {
        double tong = soLuong*getGiaBan();
        return tong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.sanPham);
        hash = 89 * hash + Objects.hashCode(this.hoaDon);
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
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.sanPham, other.sanPham)) {
            return false;
        }
        return Objects.equals(this.hoaDon, other.hoaDon);
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
