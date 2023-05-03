/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.ThuongHieu_dao;
import interface_dao.ThuongHieuInterface;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.sanpham.ThuongHieu;
import entity.share.Utility;

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
    
    public String sinhMa() {
        String last = dao.getMaLonNhat();

        return Utility.sinhMaTang(last, "THHI", 2);
    }
    
    public String timMaThuongHieuTheoToString(String string) {
        for(ThuongHieu th: getAllThuongHieu()){
            if(th.toString().equals(string)){
                return th.getMaTH();
            }
        }
        return null;
    }
    
    public ThuongHieu taoThuongHieu(String string) throws Exception {
        if(timMaThuongHieuTheoToString(string) == null) {
            String parts[] = string.split(" - ");
            String tenTH = parts[0];
            String quocGia = parts[1]; 
            ThuongHieu th = new ThuongHieu(sinhMa(), tenTH, quocGia);
            themThuongHieu(th);
            return th;
        }
        return getThuongHieuTheoMa(string).get(0);
    }

}
