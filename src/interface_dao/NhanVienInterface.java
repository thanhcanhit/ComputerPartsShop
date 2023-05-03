/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.connguoi.NhanVien;

/**
 *
 * @author macbookk
 */
public interface NhanVienInterface {
    public ArrayList<NhanVien> getAllNhanVien();
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNV);
    public boolean themNhanVien(NhanVien nhanVien);
    public boolean capNhatNhanVien(String maNV,NhanVien nhanVien);
    public ArrayList<NhanVien> getdsQuanLy();
    
}
