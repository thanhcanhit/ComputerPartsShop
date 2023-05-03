/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.NhanVien_dao;
import interface_dao.NhanVienInterface;
import java.util.ArrayList;
import entity.connguoi.NhanVien;
import entity.share.ConnectDB;
import entity.share.Utility;

/**
 *
 * @author macbookk
 */
public class NhanVien_bus implements NhanVienInterface {

    private NhanVien_dao dao;

    public NhanVien_bus() {
        dao = new NhanVien_dao();
    }

    @Override
    public ArrayList<NhanVien> getAllNhanVien() {
        return dao.getAllNhanVien();
    }

    @Override
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNV) {
        return dao.getNhanVienTheoMa(maNV);
    }

    @Override
    public boolean themNhanVien(NhanVien nhanVien) {
        return dao.themNhanVien(nhanVien);
    }

    @Override
    public boolean capNhatNhanVien(String maNV, NhanVien nhanVien) {
        return dao.capNhatNhanVien(maNV, nhanVien);
    }

    @Override
    public ArrayList<NhanVien> getdsQuanLy() {
        return dao.getdsQuanLy();
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "NV", 4);
    }
    
    public String getMaDiaChi(String maNV){
        return dao.getMaDiaChi(maNV);
    }
    public boolean capNhatTrangThaiNhanVien(String maNV, boolean trangThai){
        return dao.capNhatTrangThaiNhanVien(maNV, trangThai);
    }
}
