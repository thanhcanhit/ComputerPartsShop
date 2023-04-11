/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.kho;

import java.util.ArrayList;
import model.share.DiaChi;

/**
 *
 * @author HP
 */
public class Kho {
    private String maKho;
    private DiaChi diaChi;
    private String tenKho;
//    private  ArrayList<dsThongTinChiTietSanPham>;
    private double dienTich;

    public Kho() {
    }

    public Kho(String maKho) {
        this.maKho = maKho;
    }

//    public Kho(String maKho, DiaChi diaChi, String tenKho, DanhSachHangHoa dsHang, double dienTich) {
//        this.maKho = maKho;
//        this.diaChi = diaChi;
//        this.tenKho = tenKho;
////        this.dsHang = dsHang;
//        this.dienTich = dienTich;
//    }

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

//    public DanhSachHangHoa getDsHang() {
//        return dsHang;
//    }
//
//    public void setDsHang(DanhSachHangHoa dsHang) {
//        this.dsHang = dsHang;
//    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

//    @Override
//    public String toString() {
//        return "Kho{" + "maKho=" + maKho + ", diaChi=" + diaChi + ", tenKho=" + tenKho + ", dsHang=" + dsHang + ", dienTich=" + dienTich + '}';
//    }
//     
    
    
}
