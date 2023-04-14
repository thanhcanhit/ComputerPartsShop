/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import model.kho.DonNhapHang;

/**
 *
 * @author thanh
 */
public interface DonNhapHangInterface {
    public ArrayList<DonNhapHang> getAllThuongHieu();
    public ArrayList<DonNhapHang> getThuongHieuTheoMa(String maDon);
    public boolean xoaThuongHieu(String maDon);
    public boolean themThuongHieu(DonNhapHang donNhap);
    public boolean capNhatThuongHieu(String maDon, DonNhapHang donNhap);
}
