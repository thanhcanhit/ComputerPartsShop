/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interface_dao.ThuongHieuInterface;
import java.sql.*;
import java.util.ArrayList;
import entity.sanpham.SanPham;
import entity.sanpham.ThuongHieu;
import entity.share.ConnectDB;

/**
 *
 * @author thanh
 */
public class ThuongHieu_dao implements ThuongHieuInterface {

    @Override
    public ArrayList<ThuongHieu> getAllThuongHieu() {
        ArrayList<ThuongHieu> result = new ArrayList<>();
        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select * from ThuongHieu");

            while (rs.next()) {
                String maTH = rs.getString("maThuongHieu");
                String tenTH = rs.getString("tenThuongHieu");
                String quocGia = rs.getString("quocGia");

                ThuongHieu th = new ThuongHieu(maTH, tenTH, quocGia);
                result.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<ThuongHieu> getThuongHieuTheoMa(String maTH) {

        ArrayList<ThuongHieu> result = new ArrayList<>();
        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement(
                    "select * from ThuongHieu where maThuongHieu = ?");
            st.setString(1, maTH);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String ma = rs.getString("maThuongHieu");
                String ten = rs.getString("tenThuongHieu");
                String quocGia = rs.getString("quocGia");

                ThuongHieu th = new ThuongHieu(ma, ten, quocGia);
                result.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean xoaThuongHieu(String maTH) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("delete from ThuongHieu where maThuongHieu = ?");
            st.setString(1, maTH);

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean themThuongHieu(ThuongHieu thuongHieu) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("INSERT INTO ThuongHieu(maThuongHieu, tenThuongHieu, quocGia) VALUES (?, ?, ?);");
            st.setString(1, thuongHieu.getMaTH());
            st.setString(2, thuongHieu.getTenTH());
            st.setString(3, thuongHieu.getQuocGia());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    @Override
    public boolean capNhatThuongHieu(String maTH, ThuongHieu thuongHieu) {
        int n = 0;

        try {
            PreparedStatement st = ConnectDB.conn.prepareStatement("UPDATE ThuongHieu SET tenThuongHieu = ?, quocGia = ? WHERE maThuongHieu = ?;");
            st.setString(1, thuongHieu.getTenTH());
            st.setString(2, thuongHieu.getQuocGia());
            st.setString(3, thuongHieu.getMaTH());

            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return n > 0;
    }
    
    public String getMaLonNhat() {
        String s = "THHI01";

        try {
            Statement st = ConnectDB.conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 maThuongHieu from ThuongHieu order by maThuongHieu desc");
            rs.next();
            s = rs.getString("maThuongHieu");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }
}
