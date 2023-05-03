package bus;

import dao.SanPham_dao;
import interface_dao.SanPhamInterface;
import java.util.ArrayList;
import entity.sanpham.SanPham;
import entity.sanpham.ThuongHieu;
import entity.share.Utility;

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
    private ThuongHieu_bus thuongHieu_bus;

    public SanPham_bus() {
        dao = new SanPham_dao();
        thuongHieu_bus = new ThuongHieu_bus();
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

    public ArrayList<SanPham> searchTheoDieuKien(String ten, String loaiSanPham, String thuongHieu, String cauHinh) {
        ArrayList<SanPham> ds = new ArrayList<>();
        ds = getAllSanPham();
        ArrayList<SanPham> xoa = new ArrayList<>();
        if (ten.length() > 0) {
            for (SanPham sp : ds) {
                if (!sp.getTenSP().toLowerCase().contains(ten.toLowerCase().trim())) {
                    xoa.add(sp);
                }
            }
            ds.removeAll(xoa);
        }
        xoa.clear();
        if (!loaiSanPham.equals("Tất cả")) {
            for (SanPham sp : ds) {
                if (!loaiSanPham.equals(sp.getTenLoai())) {
                    xoa.add(sp);
                }
            }
            ds.removeAll(xoa);
        }
        xoa.clear();
        if (!thuongHieu.equals("Tất cả")) {
            for (SanPham sp : ds) {
                ThuongHieu th = new ThuongHieu_bus().getThuongHieuTheoMa(sp.getThuongHieu().getMaTH()).get(0);
                if (!thuongHieu.equals(th.toString())) {
                    xoa.add(sp);
                }
            }
            ds.removeAll(xoa);
        }
        xoa.clear();

        if (cauHinh.trim().length() > 0) {
            String[] thanhPhanCauHinh = cauHinh.split(";");
            for (SanPham sp : ds) {
                for (String ctCauHinh : thanhPhanCauHinh) {
                    if (!sp.getCauHinh().toLowerCase().contains(ctCauHinh.toLowerCase().trim())) {
                        xoa.add(sp);
                        break;
                    }
                }
            }
            ds.removeAll(xoa);
        }
        return ds;
    }

    public String sinhMa() {
        String last = dao.getMaLonNhat();
        return Utility.sinhMaTang(last, "SP", 4);
    }

    public String thuongHieuParseMa(String string) {
        return thuongHieu_bus.timMaThuongHieuTheoToString(string);
    }

    public int loaiParseInt(String string) {
        int result = 0;
        switch (string) {
            case "CPU" ->
                result = 0;
            case "MainBoard" ->
                result = 1;
            case "VGA" ->
                result = 2;
            case "RAM" ->
                result = 3;
            case "Ổ cứng" ->
                result = 4;
            case "Nguồn" ->
                result = 5;
            case "Case" ->
                result = 6;
            case "Tản nhiệt" ->
                result = 7;
            case "Chuột" ->
                result = 8;
            case "Bàn phím" ->
                result = 9;
        }
        return result;
    }
}
