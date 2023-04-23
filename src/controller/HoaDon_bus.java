/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.HoaDon_dao;
import interface_dao.HoaDonInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import model.hoadon.HoaDon;
import model.share.Utility;

/**
 *
 * @author macbookk
 */
public class HoaDon_bus implements HoaDonInterface {

    private HoaDon_dao dao;

    public HoaDon_bus() {
        dao = new HoaDon_dao();
    }

    @Override
    public ArrayList<HoaDon> getAllHoaDon() {
        return dao.getAllHoaDon();
    }

    @Override
    public ArrayList<HoaDon> getHoaDonTheoMa(String maHoaDon) {
        return dao.getHoaDonTheoMa(maHoaDon);
    }

    @Override
    public boolean themHoaDon(HoaDon hoaDon) {
        return dao.themHoaDon(hoaDon);
    }

    @Override
    public boolean xoaHoaDon(String maHoaDon) {
        return dao.xoaHoaDon(maHoaDon);
    }

    @Override
    public boolean capNhatHoaDon(String maHoaDon, HoaDon hoaDon) {
        return dao.capNhatHoaDon(maHoaDon, hoaDon);
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "HD", 5);
    }

    @Override
    public ArrayList<HoaDon> getHoaDonTheoDieuKien(String ma, String sdt, String giaBatDau, String giaKetThuc, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        return dao.getHoaDonTheoDieuKien(ma, sdt, giaBatDau, giaKetThuc, ngayBatDau, ngayKetThuc);
    }
}
