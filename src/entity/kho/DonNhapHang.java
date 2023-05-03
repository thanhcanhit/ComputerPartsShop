/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.kho;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import entity.connguoi.NhaCungCap;
import entity.connguoi.NhanVien;

/**
 *
 * @author thanh
 */
public class DonNhapHang {

    private String maDonNhap;
    private LocalDate ngayNhap;
    private String ghiChu;
    private boolean danhan;
    private KhoHang khoHang;
    private ArrayList<ChiTietDonNhap> chiTietDonNhap;
    private NhaCungCap nhaCungCap;
    private NhanVien nhanVien;
    private double tongTien;

    public DonNhapHang(String maDonNhap) {
        this.maDonNhap = maDonNhap;
    }

    public DonNhapHang(String maDonNhap, LocalDate ngayNhap, String ghiChu, boolean danhan, KhoHang khoHang, ArrayList<ChiTietDonNhap> chiTietDonNhap, NhaCungCap nhaCungCap, NhanVien nhanVien, double tongTien) {
        this.maDonNhap = maDonNhap;
        this.ngayNhap = ngayNhap;
        this.ghiChu = ghiChu;
        this.danhan = danhan;
        this.khoHang = khoHang;
        this.chiTietDonNhap = chiTietDonNhap;
        this.nhaCungCap = nhaCungCap;
        this.nhanVien = nhanVien;
        this.tongTien = tongTien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaDonNhap() {
        return maDonNhap;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setMaDonNhap(String maDonNhap) {
        this.maDonNhap = maDonNhap;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean isDanhan() {
        return danhan;
    }

    public void setDanhan(boolean danhan) {
        this.danhan = danhan;
    }

    public KhoHang getKhoHang() {
        return khoHang;
    }

    public void setKhoHang(KhoHang khoHang) {
        this.khoHang = khoHang;
    }

    public ArrayList<ChiTietDonNhap> getChiTietDonNhap() {
        return chiTietDonNhap;
    }

    public void setChiTietDonNhap(ArrayList<ChiTietDonNhap> chiTietDonNhap) {
        this.chiTietDonNhap = chiTietDonNhap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.maDonNhap);
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
        final DonNhapHang other = (DonNhapHang) obj;
        return Objects.equals(this.maDonNhap, other.maDonNhap);
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
