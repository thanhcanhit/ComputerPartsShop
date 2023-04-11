/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import java.util.Date;
import model.sanpham.SanPham;

/**
 *
 * @author nxnam
 */
public class PhieuBaoHanh {
    private String maPhieu;
    private SanPham sanPham;
    private HoaDon hoaDon;

    public PhieuBaoHanh() {
    }

    public PhieuBaoHanh(String maPhieu, SanPham sanPham, HoaDon hoaDon) {
        this.maPhieu = maPhieu;
        this.sanPham = sanPham;
        this.hoaDon = hoaDon;
    }

    
    
}
