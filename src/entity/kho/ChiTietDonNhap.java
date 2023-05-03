/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.kho;

import java.util.Objects;
import entity.sanpham.SanPham;

/**
 *
 * @author thanh
 */
public class ChiTietDonNhap {

    private SanPham sanPham;
    private DonNhapHang donNhap;
    private int soLuong;
    private double tongTien;

    public void tinhTongTien() {
        this.tongTien = sanPham.getGiaBan() * soLuong;
    }

    public ChiTietDonNhap(SanPham sanPham, DonNhapHang donNhap, int soLuong) {
        this.sanPham = sanPham;
        this.donNhap = donNhap;
        this.soLuong = soLuong;
        tinhTongTien();

    }

    public ChiTietDonNhap(SanPham sanPham, DonNhapHang donNhap, int soLuong, double tongTien) {
        this.sanPham = sanPham;
        this.donNhap = donNhap;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }
    
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public DonNhapHang getDonNhap() {
        return donNhap;
    }

    public void setDonNhap(DonNhapHang donNhap) {
        this.donNhap = donNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.sanPham);
        hash = 47 * hash + Objects.hashCode(this.donNhap);
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
        final ChiTietDonNhap other = (ChiTietDonNhap) obj;
        if (!Objects.equals(this.sanPham, other.sanPham)) {
            return false;
        }
        return Objects.equals(this.donNhap, other.donNhap);
    }

}
