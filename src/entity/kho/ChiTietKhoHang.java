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
public class ChiTietKhoHang {

    private KhoHang khoHang;
    private SanPham sanPham;
    private int soLuong;

    public ChiTietKhoHang(SanPham sanPham, KhoHang khoHang, int soLuong) {
        this.khoHang = khoHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public KhoHang getKhoHang() {
        return khoHang;
    }

    public void setKhoHang(KhoHang khoHang) {
        this.khoHang = khoHang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.khoHang);
        hash = 61 * hash + Objects.hashCode(this.sanPham);
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
        final ChiTietKhoHang other = (ChiTietKhoHang) obj;
        if (!Objects.equals(this.khoHang, other.khoHang)) {
            return false;
        }
        return Objects.equals(this.sanPham, other.sanPham);
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
