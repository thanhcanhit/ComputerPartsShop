/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import model.connguoi.NguoiQuanLy;

/**
 *
 * @author macbookk
 */
public interface QuanLyInterface {
    public ArrayList<NguoiQuanLy> getAllQuanLy();
    public ArrayList<NguoiQuanLy> getQuanLyTheoMa(String maNV);
    public boolean xoaQuanLy(String maNV);
    public boolean themQuanLy(NguoiQuanLy nguoiQuanLy);
    public boolean capNhatQuanLy(String maNV, NguoiQuanLy nguoiQuanLy);
}
