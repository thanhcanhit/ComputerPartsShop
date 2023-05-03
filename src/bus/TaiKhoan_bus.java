/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.TaiKhoan_dao;
import interface_dao.TaiKhoanInterface;
import java.util.ArrayList;
import entity.connguoi.TaiKhoan;

/**
 *
 * @author macbookk
 */
public class TaiKhoan_bus implements TaiKhoanInterface{
    private TaiKhoan_dao dao ;
    public TaiKhoan_bus(){
        dao = new TaiKhoan_dao();
    }
    @Override
    public ArrayList<TaiKhoan> getTaiKhoanTheoMaNV(String soTK) {
        return dao.getTaiKhoanTheoMaNV(soTK);
    }

    @Override
    public boolean themTaiKhoan(TaiKhoan taiKhoan) {
        return dao.themTaiKhoan(taiKhoan);
    }

    @Override
    public boolean xoaTaiKhoan(String soTK) {
       return dao.xoaTaiKhoan(soTK);
    }

    @Override
    public boolean capNhatTaiKhoan(String soTK, TaiKhoan taiKhoan) {
        return dao.capNhatTaiKhoan(soTK, taiKhoan);
    }

    @Override
    public boolean kiemTraTaiKhoan(String tk, String mk) {
        return dao.kiemTraTaiKhoan(tk, mk);
    }
    
 
}
