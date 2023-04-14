/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import model.kho.KhoHang;

/**
 *
 * @author thanh
 */
public interface KhoHangInterface {

    public ArrayList<KhoHang> getAllQuanLy();

    public ArrayList<KhoHang> getQuanLyTheoMa(String maKho);

    public boolean xoaThuongHieu(String maKho);

    public boolean themThuongHieu(KhoHang kho);

    public boolean capNhatThuongHieu(String maKho, KhoHang kho);
}
