/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import java.util.ArrayList;
import model.sanpham.SanPham;

/**
 *
 * @author nxnam
 */
public class ChiTietHoaDon {
    private SanPham sanPham;
    private HoaDon hoaDon;
    private String maChiTietHoaDon;
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

    public String getMaChiTietHoaDon() {
        return maChiTietHoaDon;
    }

    public void setMaChiTietHoaDon(String maChiTietHoaDon) {
        this.maChiTietHoaDon = maChiTietHoaDon;
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

    public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, String maChiTietHoaDon, int soLuong, double giaBan) {
        this.sanPham = sanPham;
        this.hoaDon = hoaDon;
        this.maChiTietHoaDon = maChiTietHoaDon;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    
    

    public double tinhTongTien() {
        double tong = 0;
        
        return tong;
    }
    
    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
