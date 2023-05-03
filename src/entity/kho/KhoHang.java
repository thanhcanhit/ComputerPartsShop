/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.kho;

import java.util.ArrayList;
import java.util.Objects;
import entity.share.DiaChi;

/**
 *
 * @author HP
 */
public class KhoHang {

    private String maKho;
    private DiaChi diaChi;
    private String tenKho;
    private double dienTich;
    private ArrayList<ChiTietKhoHang> dsChiTietKhoHang;

    public KhoHang() {
    }

    public KhoHang(String maKho) {
        this.maKho = maKho;
    }

    public KhoHang(String maKho, DiaChi diaChi, String tenKho, double dienTich, ArrayList<ChiTietKhoHang> dsChiTietKhoHang) {
        this.maKho = maKho;
        this.diaChi = diaChi;
        this.tenKho = tenKho;
        this.dienTich = dienTich;
        this.dsChiTietKhoHang = dsChiTietKhoHang;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public DiaChi getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public ArrayList<ChiTietKhoHang> getDsChiTietKhoHang() {
        return dsChiTietKhoHang;
    }

    public void setDsChiTietKhoHang(ArrayList<ChiTietKhoHang> dsChiTietKhoHang) {
        this.dsChiTietKhoHang = dsChiTietKhoHang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maKho);
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
        final KhoHang other = (KhoHang) obj;
        return Objects.equals(this.maKho, other.maKho);
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
