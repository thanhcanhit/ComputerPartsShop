/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import model.kho.ChiTietKhoHang;

/**
 *
 * @author thanh
 */
public interface ChiTietKhoHangInterface {

    public ArrayList<ChiTietKhoHang> getAll();

    public ArrayList<ChiTietKhoHang> getAllChiTietCuaKhoHang(String maKhoHang);

    public boolean themChiTietKhoHang(ChiTietKhoHang chiTietKH);

    public boolean capNhatChiTietKhoHang(String maKho, String maSanPham, ChiTietKhoHang ct);
}
