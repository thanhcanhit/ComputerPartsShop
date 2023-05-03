/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.hoadon;


import entity.sanpham.SanPham;

/**
 *
 * @author nxnam
 */
public class PhieuBaoHanh {

    private String maPhieu;
    private SanPham sanPham;
    private HoaDon hoaDon;

    public PhieuBaoHanh(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public PhieuBaoHanh(String maPhieu, SanPham sanPham, HoaDon hoaDon) {
        this.maPhieu = maPhieu;
        this.sanPham = sanPham;
        this.hoaDon = hoaDon;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

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

}
