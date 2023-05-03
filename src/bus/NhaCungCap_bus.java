/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.NhaCungCap_dao;
import interface_dao.NhaCungCapInterface;
import java.util.ArrayList;
import entity.connguoi.NhaCungCap;

/**
 *
 * @author thanh
 */
public class NhaCungCap_bus implements NhaCungCapInterface {

    private NhaCungCap_dao dao;

    public NhaCungCap_bus() {
        dao = new NhaCungCap_dao();
    }

    @Override
    public ArrayList<NhaCungCap> getAllNhaCungCap() {
        return dao.getAllNhaCungCap();
    }

    @Override
    public ArrayList<NhaCungCap> getNhaCungCapTheoMa(String maNCC) {
        return dao.getNhaCungCapTheoMa(maNCC);
    }

    @Override
    public boolean xoaNhaCungCap(String maNCC) {
        return dao.xoaNhaCungCap(maNCC);
    }

    @Override
    public boolean themNhaCungCap(NhaCungCap ncc) {
        return dao.themNhaCungCap(ncc);
    }

    @Override
    public boolean capNhatNhaCungCap(String maNCC, NhaCungCap ncc) {
        return dao.capNhatNhaCungCap(maNCC, ncc);
    }

}
