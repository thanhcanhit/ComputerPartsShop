/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.kho.ChiTietKhoHang;

/**
 *
 * @author thanh
 */
public interface ChiTietKhoHangInterface {

    public ArrayList<ChiTietKhoHang> getAll();

    public ArrayList<ChiTietKhoHang> getAllChiTietCuaKhoHang(String maKhoHang);

    public int getSoLuongTon(String maKhoHang, String maSanPham);

    public boolean themChiTietKhoHang(ChiTietKhoHang chiTietKH);

    public boolean capNhatChiTietKhoHang(String maKho, String maSanPham, ChiTietKhoHang ct);

    public boolean truSoLuongChiTietKhoHang(String maKho, String maSanPham, int soLuong);

    public boolean congSoLuongChiTietKhoHang(String maKho, String maSanPham, int soLuong);
}
