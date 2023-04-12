/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.kho;

import java.util.Objects;
import model.connguoi.NhaCungCap;
import model.sanpham.SanPham;

/**
 *
 * @author thanh
 */
public class ChiTietDonNhap {

    private SanPham sanPham;
    private NhaCungCap nhaCungCap;
    private int soLuong;

    public ChiTietDonNhap(SanPham sanPham, NhaCungCap nhaCungCap, int soLuong) {
        this.sanPham = sanPham;
        this.nhaCungCap = nhaCungCap;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sanPham);
        hash = 97 * hash + Objects.hashCode(this.nhaCungCap);
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
        return Objects.equals(this.nhaCungCap, other.nhaCungCap);
    }

}
