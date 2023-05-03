/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.hoadon.ChiTietHoaDon;

/**
 *
 * @author thanh
 */
public interface ChiTietHoaDonInterface {

    public boolean xoaChiTietHoaDon(String maHoaDon);

    public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMaHoaDon(String maHoaDon);
    
    public boolean themChiTietHoaDon(ChiTietHoaDon ct);
    
}
