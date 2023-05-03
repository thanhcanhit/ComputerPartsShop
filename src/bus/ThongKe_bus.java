/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.ThongKe_dao;
import interface_dao.ThongKeInterface;
import java.util.ArrayList;
import entity.sanpham.SanPham;

/**
 *
 * @author thanh
 */
public class ThongKe_bus implements ThongKeInterface {

    private ThongKe_dao dao;

    public ThongKe_bus() {
        dao = new ThongKe_dao();
    }

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay() {
        return dao.get3sanPhamBanChay();
    }

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay(int thang) {
        return dao.get3sanPhamBanChay(thang);
    }

    @Override
    public ArrayList<SanPham> get3sanPhamBanChay(int thang, int nam) {
        return dao.get3sanPhamBanChay(thang, nam);
    }

    @Override
    public double getDoanhThuSanPham(String maSP) {
        return dao.getDoanhThuSanPham(maSP);
    }

    @Override
    public double getDoanhThuSanPham(String maSP, int thang) {
        return dao.getDoanhThuSanPham(maSP, thang);
    }

    @Override
    public double getDoanhThuSanPham(String maSP, int thang, int nam) {
        return dao.getDoanhThuSanPham(maSP, thang, nam);
    }

    @Override
    public double[] getDoanhThu12Thang() {
        return dao.getDoanhThu12Thang();
    }
    public double[] getDoanhThu12Thang(int year) {
        return dao.getDoanhThu12Thang(year);
    }
    public int getSoLuongBanSanPham(String maSanPham,int thang){
        return dao.getSoLuongBanSanPham(maSanPham, thang);
    }

    public int getSoLuongBanSanPham(String maSanPham,int thang,int nam){
        return dao.getSoLuongBanSanPham(maSanPham, thang, nam);
    }
    public int getSoLuongBanSanPham(String maSanPham){
        return dao.getSoLuongBanSanPham(maSanPham);
    }
 
    public ArrayList<SanPham> getsanPham(int thang, int nam) {
        return dao.getsanPham(thang, nam);
    }
}
