/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.kho.ChiTietDonNhap;

/**
 *
 * @author thanh
 */
public interface ChiTietDonNhapInterface {

    public ArrayList<ChiTietDonNhap> getAll();

    public ArrayList<ChiTietDonNhap> getAllChiTietCuaDonNhap(String maDonNhap);

    public boolean themChiTietDonNhap(ChiTietDonNhap chiTietDN);    
    
}
