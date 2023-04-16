/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DiaChi_dao;
import interface_dao.DiaChiInterface;
import java.util.ArrayList;
import model.share.DiaChi;

/**
 *
 * @author macbookk
 */
public class DiaChi_bus implements DiaChiInterface{
    private DiaChi_dao dao;
    public DiaChi_bus(){
        dao = new DiaChi_dao();
    }
    @Override
    public ArrayList<DiaChi> getAllDiaChi() {
        return dao.getAllDiaChi();
    }

    @Override
    public ArrayList<DiaChi> getDiaChiTheoMa(String maDiaChi) {
        return dao.getDiaChiTheoMa(maDiaChi);
    }

    @Override
    public boolean themDiaChi(DiaChi diaChi) {
        return dao.themDiaChi(diaChi);
    }

    @Override
    public boolean xoaDiaChi(String maDiaChi) {
        return dao.xoaDiaChi(maDiaChi);
    }

    @Override
    public boolean capNhatDiaChi(String maDiaChi, DiaChi diaChi) {
        return dao.capNhatDiaChi(maDiaChi, diaChi);
    }
    
}
