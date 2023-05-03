/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.DonNhapHang_dao;
import interface_dao.DonNhapHangInterface;
import java.util.ArrayList;
import entity.kho.DonNhapHang;
import entity.share.Utility;

/**
 *
 * @author thanh
 */
public class DonNhapHang_bus implements DonNhapHangInterface {

    private DonNhapHang_dao dao;

    public DonNhapHang_bus() {
        dao = new DonNhapHang_dao();
    }

    @Override
    public ArrayList<DonNhapHang> getAllDonNhapHang() {
        return dao.getAllDonNhapHang();
    }

    @Override
    public ArrayList<DonNhapHang> getDonNhapHangTheoMa(String maDon) {
        return dao.getDonNhapHangTheoMa(maDon);
    }

    @Override
    public boolean xoaDonNhapHang(String maDon) {
        return dao.xoaDonNhapHang(maDon);
    }

    @Override
    public boolean themDonNhapHang(DonNhapHang donNhap) {
        return dao.themDonNhapHang(donNhap);
    }

    @Override
    public boolean capNhatDonNhapHang(String maDon, DonNhapHang donNhap) {
        return dao.capNhatDonNhapHang(maDon, donNhap);
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "DNH", 4);
    }

    @Override
    public boolean capNhatGiaoDonThanhCong(String maDon) {
        return dao.capNhatGiaoDonThanhCong(maDon);
    }


}
