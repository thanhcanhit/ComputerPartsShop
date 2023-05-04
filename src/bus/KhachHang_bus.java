/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.KhachHang_dao;
import interface_dao.KhachHangInterface;
import java.util.ArrayList;
import entity.connguoi.KhachHang;
import entity.share.Utility;

/**
 *
 * @author macbookk
 */
public class KhachHang_bus implements KhachHangInterface {

    private KhachHang_dao dao;

    public KhachHang_bus() {
        dao = new KhachHang_dao();
    }
    
    public String getMaDiaChi(String maKH){
        return dao.getMaDiaChi(maKH);
    }

    @Override
    public ArrayList<KhachHang> getAllKhachHang() {
        return dao.getAllKhachHang();
    }

    @Override
    public ArrayList<KhachHang> getKhachHangTheoSoDT(String soDT) {
        return dao.getKhachHangTheoSoDT(soDT);
    }

    public boolean xoaKhachHang(String maKH) {
        return dao.xoaKhachHang(maKH);
    }

    @Override
    public boolean themKhachHang(KhachHang khachHang) {
        return dao.themKhachHang(khachHang);
    }

    @Override
    public boolean capNhatKhachHang(String maKH, KhachHang khachHang) {
        return dao.capNhatKhachHang(maKH, khachHang);
    }

    @Override
    public KhachHang getKhachHangTheoSDT(String sdt) {
        return dao.getKhachHangTheoSDT(sdt);
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "KH", 5);
    }

    @Override
    public boolean congDiemKhachHang(String maKH, int diemThem) {
        return dao.congDiemKhachHang(maKH, diemThem);
    }

    @Override
    public ArrayList<KhachHang> getKhachHangTheoMa(String maKH) {
        return dao.getKhachHangTheoMa(maKH);
    }

}
