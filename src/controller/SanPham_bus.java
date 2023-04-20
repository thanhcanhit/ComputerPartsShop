package controller;

import dao.SanPham_dao;
import interface_dao.SanPhamInterface;
import java.util.ArrayList;
import model.sanpham.SanPham;
import model.share.ConnectDB;
import model.share.Utility;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author thanh
 */
public class SanPham_bus implements SanPhamInterface {

    private SanPham_dao dao;

    public SanPham_bus() {
        dao = new SanPham_dao();
    }

    @Override
    public ArrayList<SanPham> getAllSanPham() {
        return dao.getAllSanPham();
    }

    @Override
    public ArrayList<SanPham> getSanPhamTheoMa(String maSanPham) {
        return dao.getSanPhamTheoMa(maSanPham);
    }

    @Override
    public boolean xoaSanPham(String maSanPham) {
        return dao.xoaSanPham(maSanPham);
    }

    @Override
    public boolean capNhatSanPham(String maSanPham, SanPham sanPham) {
        return dao.capNhatSanPham(maSanPham, sanPham);
    }

    @Override
    public boolean themSanPham(SanPham sanPham) {
        return dao.themSanPham(sanPham);
    }

    @Override
    public ArrayList<SanPham> getSanPhamTrang(int soTrang) {
        return dao.getSanPhamTrang(soTrang);
    }

    @Override
    public int getSoTrangMax() {
        return dao.getSoTrangMax();
    }

    public ArrayList<SanPham> timSanPhamTheoTen(String tenSanPham) {
        return dao.timSanPhamTheoTen(tenSanPham);
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "SP", 4);
    }

    public static void main(String[] args) {
        try {

            ConnectDB.connect();
            System.out.println(new SanPham_bus().sinhMa());
        } catch (Exception e) {
        }
    }
}
