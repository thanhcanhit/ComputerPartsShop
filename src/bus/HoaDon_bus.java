/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.HoaDon_dao;
import interface_dao.HoaDonInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import entity.connguoi.KhachHang;
import entity.hoadon.HoaDon;
import entity.share.Utility;

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
    public ArrayList<HoaDon> getHoaDonTheoDieuKien(String manv, String sdt, String giaTu, String giaDen, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        ArrayList<HoaDon> list = new ArrayList<>();
        ArrayList<HoaDon> xoa = new ArrayList<>();
        list = dao.getAllHoaDon();
        KhachHang_bus khachHang_bus = new KhachHang_bus();
        if (sdt.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
                KhachHang kh = khachHang_bus.getKhachHangTheoMa(hoaDon.getKhachHang().getMaKH()).get(0);
                if (!kh.getSoDT().equals(sdt)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (manv.trim().length() > 0) {

            for (HoaDon hoaDon : list) {
                if (!hoaDon.getNhanVien().getMaNV().equals(manv)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (giaTu.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                if (hoaDon.getTongTien() < Double.parseDouble(giaTu)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
        if (giaDen.trim().length() > 0) {
            for (HoaDon hoaDon : list) {
                if (hoaDon.getTongTien() > Double.parseDouble(giaDen)) {
                    xoa.add(hoaDon);
                }
            }
            list.removeAll(xoa);
        }
        xoa.clear();
       
        for (HoaDon hoaDon : list) {
            HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
            if (hd.getNgayLap().isBefore(ngayBatDau)) {
                xoa.add(hoaDon);
            }
        }
        list.removeAll(xoa);
        xoa.clear();
        for (HoaDon hoaDon : list) {
            HoaDon hd = getHoaDonTheoMa(hoaDon.getMaHoaDon()).get(0);
            if (hd.getNgayLap().isAfter(ngayKetThuc)) {
                xoa.add(hoaDon);
            }
        }
        list.removeAll(xoa);
        return list;
    }
}
