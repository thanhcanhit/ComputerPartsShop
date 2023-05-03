/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.kho.KhoHang;

/**
 *
 * @author thanh
 */
public interface KhoHangInterface {

    public ArrayList<KhoHang> getAllKhoHang();

    public ArrayList<KhoHang> getKhoHangTheoMa(String maKho);
    
    public int getSoLuongTon(String maKho, String maSanPham);
}
