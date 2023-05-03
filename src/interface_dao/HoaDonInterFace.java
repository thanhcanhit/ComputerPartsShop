/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.time.LocalDate;
import java.util.ArrayList;
import entity.hoadon.HoaDon;

/**
 *
 * @author macbookk
 */
public interface HoaDonInterface {
    public ArrayList<HoaDon> getAllHoaDon();
    public ArrayList<HoaDon> getHoaDonTheoMa(String maHoaDon);
    public boolean themHoaDon(HoaDon hoaDon);
    public boolean xoaHoaDon(String maHoaDon);
    public boolean capNhatHoaDon(String maHoaDon, HoaDon hoaDon);
    public ArrayList<HoaDon> getHoaDonTheoDieuKien(String ma, String sdt, String giaBatDau, String giaKetThuc, LocalDate ngayBatDau, LocalDate ngayKetThuc);
    
}
