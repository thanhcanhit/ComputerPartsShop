/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.connguoi.KhachHang;

/**
 *
 * @author macbookk
 */
public interface KhachHangInterface {
    public ArrayList<KhachHang> getAllKhachHang();
    public ArrayList<KhachHang> getKhachHangTheoSoDT(String soDT);
    public KhachHang getKhachHangTheoSDT(String sdt);
    public boolean xoaKhachHang(String maKH);
    public boolean themKhachHang(KhachHang khachHang);
    public boolean capNhatKhachHang(String maKH, KhachHang khachHang);
    public boolean congDiemKhachHang(String maKH, int diemThem);
    public ArrayList<KhachHang> getKhachHangTheoMa(String maKH);
}
