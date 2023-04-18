/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.DiaChiInterface;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.share.ConnectDB;
import model.share.DiaChi;
import java.sql.*;


/**
 *
 * @author macbookk
 */
public class DiaChi_dao implements DiaChiInterface {

    @Override
    public ArrayList<DiaChi> getAllDiaChi() {
        ArrayList<DiaChi> result = new ArrayList<DiaChi>();
        try{
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from DiaChi");
            while(rs.next()){
                String maDiaChi = rs.getString("maDiaChi");
                String soNha = rs.getString("soNha");
                String duong = rs.getString("duong");
                
                String huyen = rs.getString("huyen");
                String thanhpho = rs.getString("thanhPho");
                String quocgia = rs.getString("quocGia");
                DiaChi dc = new DiaChi(maDiaChi,soNha,duong,huyen,thanhpho,quocgia);
                result.add(dc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public DiaChi getDiaChiTheoMa(String maDiaChi) {
        DiaChi result = null;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("select * from DiaChi where maDiaChi = ?");
            st.setString(1, maDiaChi);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                
                String soNha = rs.getString("soNha");
                String duong = rs.getString("duong");
                String huyen = rs.getString("huyen");
                String thanhpho = rs.getString("thanhPho");
                String quocgia = rs.getString("quocGia");
                result = new DiaChi(maDiaChi,soNha,duong,huyen,thanhpho,quocgia);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean themDiaChi(DiaChi diaChi) {
        int n=0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("insert into DiaChi"
                    +"values(?,?,?,?,?,?)");
            st.setString(1, diaChi.getMaDiaChi());
            st.setString(2, diaChi.getSo());
            st.setString(3, diaChi.getDuong());
            st.setString(4, diaChi.getQuan());
            st.setString(5, diaChi.getThanhPho());
            st.setString(6, diaChi.getQuocGia());
           
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }

    @Override
    public boolean xoaDiaChi(String maDiaChi) {
        int n = 0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from DiaChi where maDiaChi = ?");
            st.setString(1, maDiaChi);
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }

    @Override
    public boolean capNhatDiaChi(String maDiaChi, DiaChi diaChi) {
        int n=0;
        try{
            PreparedStatement st = ConnectDB.conn.prepareStatement("update DiaChi"
                    +" so=?, duong=?, huyen=?, thanhPho=?, quocGia = ?"
                    + "where maDiaChi = ?");
            st.setString(1, diaChi.getSo());
            st.setString(2, diaChi.getDuong());
            st.setString(3, diaChi.getQuan());
            st.setString(4, diaChi.getThanhPho());
            st.setString(5, diaChi.getQuocGia());
            st.setString(6, maDiaChi);
            n = st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return n>0;
    }
    
}