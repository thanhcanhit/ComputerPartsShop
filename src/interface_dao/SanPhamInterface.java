package interface_dao;


import java.util.ArrayList;
import entity.sanpham.SanPham;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author thanh
 */
public interface SanPhamInterface {
    public ArrayList<SanPham> getAllSanPham();
    public ArrayList<SanPham> getSanPhamTheoMa(String maSanPham);
    public boolean xoaSanPham(String maSanPham);
    public boolean capNhatSanPham(String maSanPham, SanPham sanPham);
    public boolean themSanPham(SanPham sanPham);
//    moi trang max 
    public ArrayList<SanPham> getSanPhamTrang(int soTrang);
    public int getSoTrangMax();
}
