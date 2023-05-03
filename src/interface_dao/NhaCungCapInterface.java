/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_dao;

import java.util.ArrayList;
import entity.connguoi.NhaCungCap;

/**
 *
 * @author thanh
 */
public interface NhaCungCapInterface {

    public ArrayList<NhaCungCap> getAllNhaCungCap();

    public ArrayList<NhaCungCap> getNhaCungCapTheoMa(String maNCC);

    public boolean xoaNhaCungCap(String maNCC);

    public boolean themNhaCungCap(NhaCungCap ncc);

    public boolean capNhatNhaCungCap(String maNCC, NhaCungCap ncc);
}
