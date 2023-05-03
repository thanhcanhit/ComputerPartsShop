/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.kho.DonNhapHang;

/**
 *
 * @author thanh
 */
public interface DonNhapHangInterface {
    public ArrayList<DonNhapHang> getAllDonNhapHang();
    public ArrayList<DonNhapHang> getDonNhapHangTheoMa(String maDon);
    public boolean xoaDonNhapHang(String maDon);
    public boolean themDonNhapHang(DonNhapHang donNhap);
    public boolean capNhatDonNhapHang(String maDon, DonNhapHang donNhap);
    public boolean capNhatGiaoDonThanhCong(String maDon);
}
