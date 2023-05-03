/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.sanpham.ThuongHieu;

/**
 *
 * @author thanh
 */
public interface ThuongHieuInterface {
    public ArrayList<ThuongHieu> getAllThuongHieu();
    public ArrayList<ThuongHieu> getThuongHieuTheoMa(String maTH);
    public boolean xoaThuongHieu(String maTH);
    public boolean themThuongHieu(ThuongHieu thuongHieu);
    public boolean capNhatThuongHieu(String maTH, ThuongHieu thuongHieu);
}
