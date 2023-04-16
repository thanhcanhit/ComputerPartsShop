/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ThuongHieu_dao;
import interface_dao.ThuongHieuInterface;
import java.util.ArrayList;
import model.sanpham.ThuongHieu;

/**
 *
 * @author thanh
 */
public class ThuongHieu_bus implements ThuongHieuInterface {

    private ThuongHieu_dao dao;

    public ThuongHieu_bus() {
        dao = new ThuongHieu_dao();
    }

    @Override
    public ArrayList<ThuongHieu> getAllThuongHieu() {
        return dao.getAllThuongHieu();
    }

    @Override
    public ArrayList<ThuongHieu> getThuongHieuTheoMa(String maTH) {
        return dao.getThuongHieuTheoMa(maTH);
    }

    @Override
    public boolean xoaThuongHieu(String maTH) {
        return dao.xoaThuongHieu(maTH);
    }

    @Override
    public boolean themThuongHieu(ThuongHieu thuongHieu) {
        return dao.themThuongHieu(thuongHieu);
    }

    @Override
    public boolean capNhatThuongHieu(String maTH, ThuongHieu thuongHieu) {
        return dao.capNhatThuongHieu(maTH, thuongHieu);
    }

}
