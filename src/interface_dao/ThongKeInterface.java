/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.sanpham.SanPham;

/**
 *
 * @author thanh
 */
public interface ThongKeInterface {
    public ArrayList<SanPham> get3sanPhamBanChay();
    public ArrayList<SanPham> get3sanPhamBanChay(int thang);
    public ArrayList<SanPham> get3sanPhamBanChay(int thang, int nam);
    public double getDoanhThuSanPham(String maSP);
    public double getDoanhThuSanPham(String maSP, int thang);
    public double getDoanhThuSanPham(String maSP, int thang, int nam);
    public double[] getDoanhThu12Thang();
}
